package net.cnam.fleetview.controller;

import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.course.CourseDAO;
import net.cnam.fleetview.view.course.edit.CourseView;

import java.sql.Connection;
import java.time.LocalDate;

public class CourseController extends Controller<CourseView> {
    // DAO
    private final CourseDAO courseDAO;

    // Course
    private Course course;

    public CourseController(CourseView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        Connection connection = BDDConnection.getInstance(connector);
        this.courseDAO = new CourseDAO(connection);
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
        this.course = courseDAO.getById(id);

        String idForm = String.valueOf(course.getIdCourse());
        String nomForm = course.getNom();
        String dateForm = course.getDateCourse().toString();

        // Chargement des données dans la vue
        view.fill(idForm, nomForm, dateForm);

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
        this.course = courseDAO.getById(id);

        String idForm = String.valueOf(course.getIdCourse());
        String nomForm = course.getNom();
        String dateForm = course.getDateCourse().toString();

        // Chargement des données dans la vue
        view.fill(idForm, nomForm, dateForm);

        // Rendre les champs modifiables
        view.setFieldsEditable(true);
    }

    /**
     * Sauvegarder une course
     *
     * @param nom  Nom de la course
     * @param date Date de la course
     * @return Si la sauvegarde a réussi
     */
    public boolean saveCourse(String nom, String date) {
        // Si on est en mode ajout d'une course
        if (course == null) {
            this.course = new Course();
        }

        // Sauvegarde des données dans la course
        course.setNom(nom);
        course.setDateCourse(LocalDate.parse(date));

        // Sauvegarde de la course
        boolean success;
        if (course.getIdCourse() == null) {
            success = courseDAO.create(course, RootController.getCurrentUser());
        } else {
            success = courseDAO.update(course, RootController.getCurrentUser());
        }

        return success;
    }
}
