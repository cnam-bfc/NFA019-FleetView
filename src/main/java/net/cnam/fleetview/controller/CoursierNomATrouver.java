package net.cnam.fleetview.controller;

import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.course.CourseDAO;
import net.cnam.fleetview.model.coursier.Coursier;
import net.cnam.fleetview.model.coursier.CoursierDAO;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.cycle.CycleDAO;
import net.cnam.fleetview.view.CoursierRecapitulatifCourseView;

import java.sql.Connection;

public class CoursierNomATrouver extends Controller<CoursierRecapitulatifCourseView> {+
    // Attributs
    private Coursier coursier;
    private Course course;
    private Cycle cycle;


    // DAO
    private final CoursierDAO coursierDAO;
    private final CourseDAO courseDAO;
    private final CycleDAO cycleDAO;

    /**
     * Constructeur
     *
     * @param view
     */
    public CoursierNomATrouver(CoursierRecapitulatifCourseView view) {
        super(view);

        // Init du connector
        DefaultConnector connector = new DefaultConnector();
        Connection bddConnection = BDDConnection.getInstance(connector);

        // Initialisation des DAO
        this.coursierDAO = new CoursierDAO(bddConnection);
        this.courseDAO = new CourseDAO(bddConnection);
        this.cycleDAO = new CycleDAO(bddConnection);

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

        if (checkLancerCourse()) {

        }
    }

    /**
     * Méthode permettant de vérifier si le coursier a une course en cours ou non et si un cycle est sélectionné
     */
    private boolean checkLancerCourse() {
        // On vérifie si le coursier a bien une course d'assigné
        if (this.course == null ||courseDAO.estDisponible(this.course)) {
            // Diriger page des cycles
        } else if (this.cycle == null || cycleDAO.estDisponible(this.cycle)) {
            // Diriger page des cycles
        } else {
            return true;
        }
        return false;
    }

    /**
     * Méthode permettant de lancer la course du coursier de l'enregistrer en base de données.
     */
    public void lancerCourse () {
        if (!checkLancerCourse())
            return;
    }
}
