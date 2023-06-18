package net.cnam.fleetview.controller;

import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.course.CourseDAO;
import net.cnam.fleetview.view.course.edit.CourseView;

public class CourseController extends Controller<CourseView> {
    // DAO
    private final CourseDAO courseDAO;

    public CourseController(CourseView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        this.courseDAO = new CourseDAO(BDDConnection.getInstance(connector));
    }

    /**
     * Charger une course vide dans la vue
     */
    public void loadEmptyCourse() {
        // Rendre les champs modifiables
        view.setFieldsEditable(true);
    }

    /**
     * Charger une course affichable dans la vue
     *
     * @param id
     */
    public void loadViewableCourse(int id) {
        // Récupération de la course
        Course course = courseDAO.getById(id);

        String idForm = String.valueOf(course.getIdCourse());
        String dateForm = course.getDateCourse().toString();

        // Chargement des données dans la vue
        view.fill(idForm, dateForm);

        // Rendre les champs non modifiables
        view.setFieldsEditable(false);
    }

    /**
     * Charger une course modifiable dans la vue
     *
     * @param id
     */
    public void loadEditableCourse(int id) {
        // Récupération de la course
        Course course = courseDAO.getById(id);

        String idForm = String.valueOf(course.getIdCourse());
        String dateForm = course.getDateCourse().toString();

        // Chargement des données dans la vue
        view.fill(idForm, dateForm);

        // Rendre les champs modifiables
        view.setFieldsEditable(true);
    }
}
