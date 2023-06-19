package net.cnam.fleetview.controller;

import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.course.CourseDAO;
import net.cnam.fleetview.view.course.edit.CourseView;
import net.cnam.fleetview.view.course.list.CoursesView;

import java.util.List;

public class CoursesController extends Controller<CoursesView> {
    // Modèle
    // DAO
    private final CourseDAO courseDAO;

    public CoursesController(CoursesView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        this.courseDAO = new CourseDAO(BDDConnection.getInstance(connector));

        // Chargement des courses dans la vue
        List<Course> courses = courseDAO.getAllNotArchived();
        for (Course course : courses) {
            String id = String.valueOf(course.getIdCourse());
            String nom = course.getNom();
            String date = course.getDateCourse().toString();
            String distance = String.valueOf(course.getDistance());

            // Ajout des courses dans la vue
            view.addCourse(id, nom, date, distance + " km", "Cycle", "Livreur", "status");
        }
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
            courseDAO.archive(course, RootController.getCurrentUser());

            // Suppression de la course dans la vue
            view.removeCourse(idCourse);
        }
    }
}
