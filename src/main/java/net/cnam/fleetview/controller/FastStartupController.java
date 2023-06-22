package net.cnam.fleetview.controller;

import net.cnam.fleetview.controller.courses.CourseChooser;
import net.cnam.fleetview.controller.courses.CoursesController;
import net.cnam.fleetview.controller.cycle.CycleChooser;
import net.cnam.fleetview.controller.cycle.CyclesController;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.Connector;
import net.cnam.fleetview.database.CustomConnectorGenerator;
import net.cnam.fleetview.model.coliscourse.ColisCourseDAO;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.course.CourseDAO;
import net.cnam.fleetview.model.coursier.Coursier;
import net.cnam.fleetview.model.coursier.CoursierDAO;
import net.cnam.fleetview.model.coursiertravail.CoursierTravail;
import net.cnam.fleetview.model.coursiertravail.CoursierTravailDAO;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.cycle.CycleDAO;
import net.cnam.fleetview.view.CoursierRecapitulatifCourseView;
import net.cnam.fleetview.view.course.list.CoursesView;
import net.cnam.fleetview.view.cycle.CyclesView;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class FastStartupController extends Controller<CoursierRecapitulatifCourseView> implements CourseChooser, CycleChooser {
    // Attributs
    private Coursier coursier;
    private Course course;
    private Cycle cycle;


    // DAO
    private final CoursierDAO coursierDAO;
    private final CourseDAO courseDAO;
    private final CycleDAO cycleDAO;
    private final ColisCourseDAO colisCourseDAO;
    private final CoursierTravailDAO coursierTravailDAO;

    /**
     * Constructeur
     *
     * @param view
     */
    public FastStartupController(CoursierRecapitulatifCourseView view) {
        super(view);

        // Init du connector
        CustomConnectorGenerator generator = new CustomConnectorGenerator();
        Connector connector = generator.getConnector();
        Connection connection = BDDConnection.getInstance(connector);

        // Initialisation des DAO
        this.coursierDAO = new CoursierDAO(connection);
        this.courseDAO = new CourseDAO(connection);
        this.cycleDAO = new CycleDAO(connection);
        this.colisCourseDAO = new ColisCourseDAO(connection);
        this.coursierTravailDAO = new CoursierTravailDAO(connection);

        // On récupère le coursier en fonction de l'id utilisateur
        this.coursier = this.coursierDAO.getByIdUtilisateur(RootController.getConnectedUser().getIdUtilisateur());
        if (coursier == null) {
            // Lever une exception interdit
            throw new RuntimeException("Le coursier n'existe pas");
        }

        // On récupère la course en fonction du coursier connecté
        this.course = courseDAO.getCourseEnCours(coursier.getIdCoursier());

        if (this.course != null) {
            RootController.closeAll();
            // Retirer le bouton et mettre le nouveau
            RootController.getRootPanelView().getRightMenuPanel().getContentPanel().setVisibleCoursierStartCourse(false);
            RootController.getRootPanelView().getRightMenuPanel().getContentPanel().setVisibleCoursierEndCourse(true);
        }

        // On lance les vérifications
        checkLancerCourse();
    }

    /**
     * Méthode permettant de vérifier si le coursier a une course en cours ou non et si un cycle est sélectionné
     */
    private boolean checkLancerCourse() {
        // On vérifie si le coursier a bien une course d'assigné
        if (this.course == null || !courseDAO.estDisponible(this.course)) {
            this.onAjouterCourse();
        } else if (this.cycle == null || !cycleDAO.estDisponible(this.cycle)) {
            this.onAjouterCycle();
        } else {
            return true;
        }
        return false;
    }

    /**
     * Méthode permettant de lancer la course du coursier et de l'enregistrer en base de données.
     */
    public void lancerCourse() {
        if (!checkLancerCourse())
            return;

        // On crée un nouveau coursier travail
        CoursierTravail coursierTravail = new CoursierTravail();
        coursierTravail.setIdCoursier(this.coursier.getIdCoursier());
        coursierTravail.setDateSaisie(LocalDateTime.now());
        boolean coursierTravailCreated = coursierTravailDAO.create(coursierTravail, RootController.getConnectedUser());

        if (!coursierTravailCreated) {
            throw new RuntimeException("Impossible de créer le coursier travail");
        }

        // Enregistrer en bdd
        this.course.setDateDebutCourse(LocalDateTime.now());
        this.course.setIdCycle(this.cycle.getIdCycle());
        this.course.setIdCoursierTravail(coursierTravail.getIdCoursierTravail());
        boolean courseUpdated = courseDAO.update(this.course, RootController.getConnectedUser());

        if (!courseUpdated) {
            throw new RuntimeException("Impossible de mettre à jour la course");
        }

        // Fermer les pages, retirer le bouton pour prendre une course et mettre le nouveau
        RootController.closeAll();
        RootController.getRootPanelView().getRightMenuPanel().getContentPanel().setVisibleCoursierStartCourse(false);
        RootController.getRootPanelView().getRightMenuPanel().getContentPanel().setVisibleCoursierEndCourse(true);
    }


    public void onAjouterCourse() {
        // Affichage du tableau des course
        // Création de la vue
        CoursesView coursesView = new CoursesView();
        // Création du controller
        CoursesController coursesController = new CoursesController(coursesView);
        coursesView.setController(coursesController);

        // Liaison callback
        coursesController.bindCourseChooser(this);

        // Affichage de la vue
        RootController.open(coursesView);
    }

    @Override
    public void chooseCourse(Course course) {
        this.course = course;
        String poidsTotal = "" + colisCourseDAO.getPoidsColisCourse(course.getIdCourse());
        String distance = course.getDistance() == null ? "N/A" : "" + course.getDistance();
        view.fillCourse(distance, poidsTotal);
        this.checkLancerCourse();
    }

    public void onAjouterCycle() {
        // Affichage du tableau des cyles
        // Création de la vue
        CyclesView cyclesView = new CyclesView();
        // Création du controller
        CyclesController cyclesController = new CyclesController(cyclesView);
        cyclesView.setController(cyclesController);

        // Liaison callback
        cyclesController.bindCycleChooser(this);

        // Affichage de la vue
        RootController.open(cyclesView);
    }

    @Override
    public void chooseCycle(Cycle cycle) {
        this.cycle = cycle;
        String identifiant = cycle.getIdentifiant() == null ? "N/A" : "" + cycle.getIdentifiant();
        String chargeMax = cycle.getChargeMaximale() == null ? "N/A" : "" + cycle.getChargeMaximale();
        view.fillCycle(identifiant, chargeMax);
        this.checkLancerCourse();
    }

    @Override
    public List<Integer> getBlacklist() {
        return null;
    }
}
