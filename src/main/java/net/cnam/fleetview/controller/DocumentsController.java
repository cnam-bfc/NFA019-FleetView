package net.cnam.fleetview.controller;


import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.course.CourseDAO;
import net.cnam.fleetview.model.courseaccident.CourseAccident;
import net.cnam.fleetview.model.courseaccident.CourseAccidentDAO;
import net.cnam.fleetview.view.course.list.CoursesView;
import net.cnam.fleetview.view.documents.DocumentsView;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class DocumentsController extends Controller<DocumentsView>{


    private final CourseAccidentDAO accidentDAO;

    public DocumentsController(DocumentsView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        Connection connection = BDDConnection.getInstance(connector);
        view.setCoursierFilter(initCycleFilter());
        view.setCoursierFilter(initCoursierFilter());
        this.accidentDAO = new CourseAccidentDAO(connection);

    }
/*
    public void onRefreshAccident() {
        // Chargement des courses dans la vue
        List<CourseAccident> accidents = accidentDAO.getAll();
        for (CourseAccident accident : accidents) {
            String id = String.valueOf(accident.getIdCourseAccident());
            String adresse = accident.getIdAdresse();
            String date = accident.getDateCourse().toString();
            String distance = String.valueOf(accident.getDistance());

            // Ajout des courses dans la vue
            view.addAccident(id, nom, date, distance + " km", "Cycle", "Livreur", "status");
        }
    }
*/
    public void onAjouterFicheAccident(){

        //appel AccidetView
    }

    public void onAjouterRapport(){
        //appel page rapport
    }

    public String[] listCoursiers(){

        String[] retour = new String[]{"cycliste&", "hjjhfhjhjhj"};
        return retour;
    }

    public String[] listCycles(){

        String[] retour = new String[]{"velo&", "hjjhfhjhjhj"};
        return retour;
    }

    private JComboBox initCoursierFilter() {
        //get liste coursier sous un tableau de nom
        String[] listCoursiers;
        listCoursiers = listCoursiers();

        //retourner le filter avec la liste
        JComboBox coursier = new JComboBox(listCoursiers);
        return coursier;

    }

    private JComboBox initCycleFilter() {
        //get liste coursier sous un tableau de nom
        String[] listeCycles = listCycles();

        //retourner le filtre avec la liste
        return new JComboBox(listeCycles);

    }

}