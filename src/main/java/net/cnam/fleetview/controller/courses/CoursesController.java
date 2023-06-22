package net.cnam.fleetview.controller.courses;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.Connector;
import net.cnam.fleetview.database.CustomConnectorGenerator;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.course.CourseDAO;
import net.cnam.fleetview.model.coursier.Coursier;
import net.cnam.fleetview.model.coursier.CoursierDAO;
import net.cnam.fleetview.model.coursiertravail.CoursierTravail;
import net.cnam.fleetview.model.coursiertravail.CoursierTravailDAO;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.cycle.CycleDAO;
import net.cnam.fleetview.model.utilisateur.Utilisateur;
import net.cnam.fleetview.model.utilisateur.UtilisateurDAO;
import net.cnam.fleetview.view.course.edit.CourseView;
import net.cnam.fleetview.view.course.list.CoursesView;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class CoursesController extends Controller<CoursesView> {
    // Modèle
    // DAO
    private final CourseDAO courseDAO;
    private final CoursierTravailDAO coursierTravailDAO;
    private final CoursierDAO coursierDAO;
    private final UtilisateurDAO utilisateurDAO;
    private final CycleDAO cycleDAO;

    private CourseChooser courseChooser;

    public CoursesController(CoursesView view) {
        super(view);

        // Initialisation des DAO
        CustomConnectorGenerator generator = new CustomConnectorGenerator();
        Connector connector = generator.getConnector();
        Connection connection = BDDConnection.getInstance(connector);

        this.courseDAO = new CourseDAO(connection);
        this.coursierTravailDAO = new CoursierTravailDAO(connection);
        this.coursierDAO = new CoursierDAO(connection);
        this.utilisateurDAO = new UtilisateurDAO(connection);
        this.cycleDAO = new CycleDAO(connection);

        Utilisateur connectedUser = RootController.getConnectedUser();
        if (connectedUser.getIdRole() != 2) {
            view.hideEditAndDeleteColumns();
        }
    }

    public void onRefreshCourses() {
        // Suppression des courses de la vue
        view.removeAllCourses();

        List<Integer> coursesBlacklist = new LinkedList<>();
        if (courseChooser != null) {
            List<Integer> blacklist = courseChooser.getBlacklist();
            if (blacklist != null) {
                coursesBlacklist.addAll(blacklist);
            }
        }

        // Chargement des courses dans la vue
        List<Course> courses = courseDAO.getAllNotArchived();
        for (Course course : courses) {
            if (coursesBlacklist.contains(course.getIdCourse())) {
                continue;
            }

            // Récupération du travail du coursier
            CoursierTravail coursierTravail = null;
            if (course.getIdCoursierTravail() != null) {
                coursierTravail = coursierTravailDAO.getById(course.getIdCoursierTravail());
            }
            // Récupération du coursier
            Coursier coursier = null;
            if (coursierTravail != null && coursierTravail.getIdCoursier() != null) {
                coursier = coursierDAO.getById(coursierTravail.getIdCoursier());
            }
            // Récupération de l'utilisateur
            Utilisateur utilisateur = null;
            if (coursier != null && coursier.getIdUtilisateur() != null) {
                utilisateur = utilisateurDAO.getById(coursier.getIdUtilisateur());
            }
            // Récupération du cycle
            Cycle cycle = null;
            if (course.getIdCycle() != null) {
                cycle = cycleDAO.getById(course.getIdCycle());
            }

            String id = String.valueOf(course.getIdCourse());
            String nom = course.getNom();
            String date = course.getDateCourse().toString();
            String distance;
            if (course.getDistance() == null) {
                distance = "N/A";
            } else {
                distance = String.valueOf(course.getDistance());
            }
            String nomCycle = "N/A";
            if (cycle != null) {
                nomCycle = cycle.getIdentifiant();
            }
            String nomCoursier = "N/A";
            if (utilisateur != null) {
                nomCoursier = utilisateur.getNom() + " " + utilisateur.getPrenom();
            }

            // Ajout des courses dans la vue
            view.addCourse(id, nom, date, distance + " km", nomCycle, nomCoursier);
        }
    }

    public void bindCourseChooser(CourseChooser courseChooser) {
        this.courseChooser = courseChooser;

        // Ajout de la colonne choisir dans la vue
        view.addChooseColumn();

        // Rafraichissement des courses
        onRefreshCourses();
    }

    public void onAjouterCourse() {
        // Création de la vue
        CourseView courseView = new CourseView();
        // Créer le contrôleur
        CourseController courseController = new CourseController(courseView);
        courseView.setController(courseController);

        // On charge une course vide
        courseController.loadEmptyCourse();

        // Ouverture de la vue du cycle
        RootController.open(courseView);
    }

    public void onVoirCourse(int id) {
        // Création de la vue
        CourseView courseView = new CourseView();
        // Créer le contrôleur
        CourseController courseController = new CourseController(courseView);
        courseView.setController(courseController);

        // On charge la course avec l'id voulu
        courseController.loadViewableCourse(id);

        // Ouverture de la vue du cycle
        RootController.open(courseView);
    }

    public void onEditerCourse(int id) {
        // Création de la vue
        CourseView courseView = new CourseView();
        // Créer le contrôleur
        CourseController courseController = new CourseController(courseView);
        courseView.setController(courseController);

        // On charge la course avec l'id voulu
        courseController.loadEditableCourse(id);

        // Ouverture de la vue du cycle
        RootController.open(courseView);
    }

    public void onSupprimerCourse(int id) {
        // Récupération de la course
        Course course = courseDAO.getById(id);

        String idCourse = String.valueOf(course.getIdCourse());
        String nomCourse = course.getNom();

        // Demande de confirmation
        boolean confirm = view.afficherMessageConfirmation("Supprimer la course", "Êtes-vous sûr de vouloir supprimer la course " + idCourse + " - " + nomCourse + " ?");

        if (confirm) {
            // Suppression de la course
            courseDAO.archive(course, RootController.getConnectedUser());

            // Suppression de la course dans la vue
            view.removeCourse(idCourse);
        }
    }

    public void onChoisirCourse(int id) {
        if (courseChooser != null) {
            Course course = courseDAO.getById(id);

            if (course == null) {
                view.afficherMessageErreur("Erreur", "La course n'existe pas.");
                return;
            }

            // Fermeture de la vue
            RootController.close(view);

            // Callback du chooser
            courseChooser.chooseCourse(course);
        }
    }
}
