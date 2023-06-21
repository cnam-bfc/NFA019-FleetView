package net.cnam.fleetview.controller;


import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.course.CourseDAO;
import net.cnam.fleetview.view.course.list.CoursesView;
import net.cnam.fleetview.view.documents.DocumentsView;

import java.sql.Connection;

public class DocumentsController extends Controller<DocumentsView>{

    public DocumentsController(DocumentsView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        Connection connection = BDDConnection.getInstance(connector);

    }

    public void onAjouterFicheAccident(){

    }

    public void onAjouterRapport(){

    }

    public String[] listCoursiers(){

        String[] retour = new String[]{"cycliste&", "hjjhfhjhjhj"};
        return retour;
    }

    public String[] listCycles(){

        String[] retour = new String[]{"velo&", "hjjhfhjhjhj"};
        return retour;
    }

}