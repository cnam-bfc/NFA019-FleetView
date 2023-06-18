package net.cnam.fleetview.controller;

import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.course.CourseDAO;
import net.cnam.fleetview.view.course.list.CoursesView;

import java.util.List;

public class CoursesController extends Controller {
    // Vue
    private final CoursesView view;

    // Modèle
    // DAO
    private final CourseDAO courseDAO;

    public CoursesController(CoursesView view) {
        super();

        this.view = view;

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        this.courseDAO = new CourseDAO(BDDConnection.getInstance(connector));
    }

    @Override
    public void onViewLoaded() {
        List<Course> courses = courseDAO.getAllNotArchived();
        for (Course course : courses) {
            String id = String.valueOf(course.getIdCourse());
            String date = course.getDateCourse().toString();
            String distance = String.valueOf(course.getDistance());

            // Ajout des courses dans la vue
            view.addCourse(id, date, distance + " km", "Cycle", "Livreur", "status");
        }
    }

    public void onAjouterCourse() {
        view.afficherMessage("Ajouter une course");

        view.addCourse("5", "Date", "2 km", "Cycle", "Livreur", "status");
    }

    public void onVoirCourse(int id) {
        view.afficherMessage("Voir la course " + id);
    }

    public void onEditerCourse(int id) {
        view.afficherMessage("Editer la course " + id);
    }

    public void onSupprimerCourse(int id) {
        view.afficherMessage("Supprimer la course " + id);
    }
}
