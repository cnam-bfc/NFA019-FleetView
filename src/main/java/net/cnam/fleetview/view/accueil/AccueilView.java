package net.cnam.fleetview.view.accueil;

import net.cnam.fleetview.controller.AccueilController;
import net.cnam.fleetview.controller.ParametrageBddController;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.controller.courses.CoursesController;
import net.cnam.fleetview.controller.coursier.CoursiersController;
import net.cnam.fleetview.controller.utilisateur.UsersController;
import net.cnam.fleetview.view.ParametrageBddView;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.administrateur.CreateModifyUserView;
import net.cnam.fleetview.view.administrateur.UsersView;
import net.cnam.fleetview.view.carte.CarteView;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.course.list.CoursesView;
import net.cnam.fleetview.view.coursier.list.CoursiersView;
import net.cnam.fleetview.view.cycle.ListeCycleView;
import net.cnam.fleetview.view.documents.FicheAccidentView;

import javax.swing.*;
import java.awt.*;

public class AccueilView extends View<AccueilController> {
    private final JPanel buttonsPanel = new JPanel();

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

    public AccueilView() {


        this.iconLabel = new IconLabel("\uF013", "Accueil");
        JPanel mainPanel = new JPanel(new BorderLayout());

        GridLayout buttonsPanelLayout = new GridLayout(2, 5, 50, 50);
        this.buttonsPanel.setLayout(buttonsPanelLayout);

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

        this.dbConnection.addActionListener(e -> {
            // Création de la vue des paramètres
            ParametrageBddView bdd = new ParametrageBddView();
            ParametrageBddController parametrageBddController = new ParametrageBddController(bdd);
            bdd.setController(parametrageBddController);
            // Affichage de la vue des paramètres
            RootController.open(bdd);
        });

        this.modifyUsers.addActionListener(e -> {
            // Création de la vue des paramètres
            CreateModifyUserView createModifyUserView = new CreateModifyUserView();

            // Affichage de la vue des paramètres
            RootController.open(createModifyUserView);
        });

        this.viewUsers.addActionListener(e -> {
            // Création de la vue des paramètres
            UsersView usersView = new UsersView();
            UsersController usersController = new UsersController(usersView);
            usersView.setController(usersController);
            // Affichage de la vue des paramètres
            RootController.open(usersView);
        });


        this.viewCourses.addActionListener(e -> {
            // Création de la vue des paramètres
            CoursesView coursesView = new CoursesView();
            CoursesController coursesController = new CoursesController(coursesView);
            coursesView.setController(coursesController);
            // Affichage de la vue des paramètres
            RootController.open(coursesView);
        });
        this.viewCoursiers.addActionListener(e -> {
            // Création de la vue des paramètres
            CoursiersView coursiersView = new CoursiersView();
            CoursiersController coursiersController = new CoursiersController(coursiersView);
            coursiersView.setController(coursiersController);
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
            // Création du controller de la vue
            CarteController carteController = new CarteController(map);
            map.setController(carteController);

            // Affichage de la vue des paramètres
            RootController.open(map);
        });



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

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(100, 0));
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(100, 0));

        mainPanel.add(this.iconLabel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.LINE_START);
        mainPanel.add(this.buttonsPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.LINE_END);
        this.add(mainPanel);
    }

    @Override
    public void onDisplayed() {
        super.onDisplayed();

        this.controller.choixAffichage();
    }

    public void afficherAdmin() {
        this.buttonsPanel.add(this.dbConnection);
        this.buttonsPanel.add(this.modifyUsers);
        this.buttonsPanel.add(this.viewUsers);
    }

    public void afficherGestionnaireFlotte() {
        this.buttonsPanel.add(this.viewCourses);
        this.buttonsPanel.add(this.viewCoursiers);
        this.buttonsPanel.add(this.viewCycles);
        this.buttonsPanel.add(this.viewDocs);
        this.buttonsPanel.add(this.stats);
        this.buttonsPanel.add(this.map);
    }

    public void afficherCoursier() {
        this.buttonsPanel.add(this.menuCourses);
        this.buttonsPanel.add(this.dailyReport);
        this.buttonsPanel.add(this.accidentSheet);
    }

    public void clearButtons() {
        this.buttonsPanel.removeAll();
    }
}