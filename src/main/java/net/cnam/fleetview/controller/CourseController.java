package net.cnam.fleetview.controller;

import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
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
     * Rendre la page éditable ou non
     *
     * @param editable
     */
    public void setEditable(boolean editable) {
        view.setEditable(editable);
    }

    /**
     * Charger
     */
}
