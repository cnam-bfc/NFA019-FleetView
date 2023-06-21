package net.cnam.fleetview.controller;

import net.cnam.fleetview.controller.courses.CourseChooser;
import net.cnam.fleetview.controller.courses.CoursesController;
import net.cnam.fleetview.controller.cycle.CycleChooser;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
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
        DefaultConnector connector = new DefaultConnector();
        Connection bddConnection = BDDConnection.getInstance(connector);

        // Initialisation des DAO
        this.coursierDAO = new CoursierDAO(bddConnection);
        this.courseDAO = new CourseDAO(bddConnection);
        this.cycleDAO = new CycleDAO(bddConnection);
        this.colisCourseDAO = new ColisCourseDAO(bddConnection);
        this.coursierTravailDAO = new CoursierTravailDAO(bddConnection);

        // On récupère le coursier en fonction de l'id utilisateur
        this.coursier = this.coursierDAO.getByIdUtilisateur(RootController.getCurrentUser().getIdUtilisateur());
        if (coursier == null) {
            // Lever une exception interdit
            throw new RuntimeException("Le coursier n'existe pas");
        }

        // On récupère la course en fonction du coursier connecté
        this.course = courseDAO.getCourseEnCours(coursier.getIdCoursier());

        if (this.course != null && this.course.getIdCycle() != 0) {
            // On récupère le cycle en fonction de la course
            this.cycle = cycleDAO.getById(course.getIdCycle());
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
            // Diriger page des cycles
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
        boolean coursierTravailCreated = coursierTravailDAO.create(coursierTravail, RootController.getCurrentUser());

        if (!coursierTravailCreated) {
            throw new RuntimeException("Impossible de créer le coursier travail");
        }

        // Enregistrer en bdd
        this.course.setDateDebutCourse(LocalDateTime.now());
        this.course.setIdCycle(this.cycle.getIdCycle());
        this.course.setIdCoursierTravail(coursierTravail.getIdCoursierTravail());

        // Retirer le bouton et mettre le nouveau

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
        this.checkLancerCourse();
        String poidsTotal = "" + colisCourseDAO.getPoidsColisCourse(course.getIdCourse());
        String distance = course.getDistance() == null ? "N/A" : "" + course.getDistance();
        view.fillCourse(distance, poidsTotal);
    }

    public void onAjouterCycle() {
        // Affichage du tableau des cyles
        // Création de la vue
        CyclesView cyclesView = new CyclesView();
        // Création du controller
        CyclesController cyclesController = new CyclesController(cyclesView);
        cyclesView.setController(cyclesController);

        // Liaison callback
        cyclesController.bindCourseChooser(this);

        // Affichage de la vue
        RootController.open(cyclesView);
    }

    @Override
    public void cycleCourse(Cycle cycle) {
        this.cycle = cycle;
        this.checkLancerCourse();
        String identifiant = cycle.getIdentifiant() == null ? "N/A" : "" + cycle.getIdentifiant();
        String chargeMax = cycle.getChargeMaximale() == null ? "N/A" : "" + cycle.getChargeMaximale();
        view.fillCycle(identifiant, chargeMax);
    }

    @Override
    public List<Integer> getBlacklist() {
        return null;
    }
}
