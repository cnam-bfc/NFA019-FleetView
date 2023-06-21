package net.cnam.fleetview.view.accueil;

import net.cnam.fleetview.controller.AdminMainController;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.model.utilisateur.Utilisateur;
import net.cnam.fleetview.view.ParametrageBddView;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.administrateur.CreateModifyUsers;
import net.cnam.fleetview.view.administrateur.ViewUsers;
import net.cnam.fleetview.view.carte.CarteView;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.course.list.CoursesView;
import net.cnam.fleetview.view.coursier.list.CoursiersView;
import net.cnam.fleetview.view.cycle.ListeCycleView;
import net.cnam.fleetview.view.documents.FicheAccidentView;

import javax.swing.*;
import java.awt.*;

public class AdminMainView extends View<AdminMainController> {

    //private final JFrame test = new JFrame();
    private final JButton viewUsers = new JButton("<html>Visualisation de tous les utilisateurs</html>");
    private final JButton modifyUsers = new JButton("<html>Modification des utilisateurs</html>");
    private final JButton dbConnection = new JButton("<html>Connexion à la base de données</html>");


    private final JButton menuCourses = new JButton("<html>Menu des courses</html>");
    private final JButton dailyReport = new JButton("<html>Rapport quotidien</html>");
    private final JButton accidentSheet = new JButton("<html>Saisie des fiches d'accident</html>");

    private final JButton viewDocs = new JButton("<html>Visualisation des documents</html>");
    private final JButton viewCourses = new JButton("<html>Visualisation de toutes les courses</html>");
    private final JButton viewCoursiers = new JButton("<html>Visualisation de tous les coursiers</html>");
    private final JButton viewCycles = new JButton("<html>Visualisation de tous les cycles</html>");
    private final JButton stats = new JButton("<html> Gestion des statistiques </html>");
    private final JButton map = new JButton("<html>Carte interactive</html>");


    private final IconLabel iconLabel;

    public AdminMainView() {
        Utilisateur user = RootController.getCurrentUser();


        this.iconLabel = new IconLabel("\uF013", "Accueil");
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel bttnPanel = new JPanel(new GridLayout(2, 5, 50, 50));

        this.dbConnection.setPreferredSize(new Dimension(120, 120));
        this.modifyUsers.setPreferredSize(new Dimension(120, 120));
        this.viewUsers.setPreferredSize(new Dimension(120, 120));
        this.viewCourses.setPreferredSize(new Dimension(120, 120));
        this.viewCoursiers.setPreferredSize(new Dimension(120, 120));
        this.viewCycles.setPreferredSize(new Dimension(100, 100));
        this.viewDocs.setPreferredSize(new Dimension(100, 100));
        this.stats.setPreferredSize(new Dimension(100, 100));
        this.map.setPreferredSize(new Dimension(100, 100));
        this.menuCourses.setPreferredSize(new Dimension(100, 100));
        this.dailyReport.setPreferredSize(new Dimension(100, 100));
        this.accidentSheet.setPreferredSize(new Dimension(100, 100));

        if (user.getIdRole() == 1) {
            this.dbConnection.addActionListener(e -> {
                // Création de la vue des paramètres
                ParametrageBddView bdd = new ParametrageBddView();

                // Affichage de la vue des paramètres
                RootController.open(bdd);
            });

            this.modifyUsers.addActionListener(e -> {
                // Création de la vue des paramètres
                CreateModifyUsers createModifyUsers = new CreateModifyUsers();

                // Affichage de la vue des paramètres
                RootController.open(createModifyUsers);
            });

            this.viewUsers.addActionListener(e -> {
                // Création de la vue des paramètres
                ViewUsers viewUsers = new ViewUsers();

                // Affichage de la vue des paramètres
                RootController.open(viewUsers);
            });

            bttnPanel.add(this.dbConnection);
            bttnPanel.add(this.modifyUsers);
            bttnPanel.add(this.viewUsers);
        }

        //if (user.getIdRole() == 2) {

        this.viewCourses.addActionListener(e -> {
            // Création de la vue des paramètres
            CoursesView coursesView = new CoursesView();

            // Affichage de la vue des paramètres
            RootController.open(coursesView);
        });
        this.viewCoursiers.addActionListener(e -> {
            // Création de la vue des paramètres
            CoursiersView coursiersView = new CoursiersView();

            // Affichage de la vue des paramètres
            RootController.open(coursiersView);
        });
        this.viewCycles.addActionListener(e -> {
            // Création de la vue des paramètres
            ListeCycleView cycleView = new ListeCycleView();

            // Affichage de la vue des paramètres
            RootController.open(cycleView);
        });
            /*
            this.viewDocs.addActionListener(e -> {
                // Création de la vue des paramètres
                DocumentView viewDocs = new DocumentView();

                // Affichage de la vue des paramètres
                RootController.open(viewDocs);
            });
             */
        this.map.addActionListener(e -> {
            // Création de la vue des paramètres
            CarteView map = new CarteView();

            // Affichage de la vue des paramètres
            RootController.open(map);
        });

        bttnPanel.add(this.viewCourses);
        bttnPanel.add(this.viewCoursiers);
        bttnPanel.add(this.viewCycles);
        bttnPanel.add(this.viewDocs);
        bttnPanel.add(this.stats);
        bttnPanel.add(this.map);
        //}

        //if (user.getIdRole() == 3) {
        /*
            this.menuCourses.addActionListener(e -> {
                // Création de la vue des paramètres
                FicheCourseView menuCourses = new FicheCourseView();

                // Affichage de la vue des paramètres
                RootController.open(menuCourses);
            });

         */
            /*
            this.dailyReport.addActionListener(e -> {
                // Création de la vue des paramètres
                FicheCourseView report = new FicheCourseView();

                // Affichage de la vue des paramètres
                RootController.open(report);
            });

             */
        this.accidentSheet.addActionListener(e -> {
            // Création de la vue des paramètres
            FicheAccidentView accidentSheet = new FicheAccidentView();

            // Affichage de la vue des paramètres
            RootController.open(accidentSheet);
        });

        bttnPanel.add(this.menuCourses);
        bttnPanel.add(this.dailyReport);
        bttnPanel.add(this.accidentSheet);
        //}

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(100, 0));
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(100, 0));

        mainPanel.add(this.iconLabel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.LINE_START);
        mainPanel.add(bttnPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.LINE_END);
        this.add(mainPanel);
    }


}