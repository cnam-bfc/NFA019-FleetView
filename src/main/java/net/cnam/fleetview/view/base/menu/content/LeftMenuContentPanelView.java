package net.cnam.fleetview.view.base.menu.content;

import net.cnam.fleetview.controller.FastStartupController;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.controller.carte.CarteController;
import net.cnam.fleetview.controller.colis.ColissController;
import net.cnam.fleetview.controller.connexion.ConnectionController;
import net.cnam.fleetview.controller.courses.CoursesController;
import net.cnam.fleetview.controller.coursier.CoursiersController;
import net.cnam.fleetview.controller.cycle.CyclesController;
import net.cnam.fleetview.view.CoursierRecapitulatifCourseView;
import net.cnam.fleetview.view.carte.CarteView;
import net.cnam.fleetview.view.colis.list.ColissView;
import net.cnam.fleetview.view.components.button.IconLabelButton;
import net.cnam.fleetview.view.connexion.ConnectionView;
import net.cnam.fleetview.view.course.list.CoursesView;
import net.cnam.fleetview.view.coursier.list.CoursiersView;
import net.cnam.fleetview.view.cycle.CyclesView;

import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant dans le panneau de gauche les boutons d'accès rapide en dessous du logo.
 */
public class LeftMenuContentPanelView extends JPanel {
    // Composants graphiques
    // Bouton d'accès rapide pour le coursier
    private final IconLabelButton coursierStartCourse;
    private final IconLabelButton coursierEndCourse;

    // Colis
    private final IconLabelButton colisButton;
    // Courses
    private final IconLabelButton coursesButton;
    // Cycles
    private final IconLabelButton cyclesButton;
    // Coursiers
    private final IconLabelButton coursiersButton;
    // Carte
    private final IconLabelButton carteButton;

    /**
     * Constructeur de la vue
     */
    public LeftMenuContentPanelView() {
        super();

        // Layout
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        this.setOpaque(false);

        // Bordure
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.coursierStartCourse = new IconLabelButton("\uF11E", "Débuter une course");
        this.coursierEndCourse = new IconLabelButton("\uF04D", "Terminer une course");
        this.colisButton = new IconLabelButton("\uF466", "Colis");
        this.coursesButton = new IconLabelButton("\uF0D1", "Courses");
        this.cyclesButton = new IconLabelButton("\uF206", "Cycles");
        this.coursiersButton = new IconLabelButton("\uF84A", "Coursiers");
        this.carteButton = new IconLabelButton("\uf279", "Carte");


        // Configuration des éléments de l'interface

        // Bouton de début de course
        this.coursierStartCourse.addActionListener(e -> {
            // Fermeture de toutes les vues
            RootController.closeAll();
            // Création de la vue de la liste des courses
            CoursierRecapitulatifCourseView coursierRecapitulatifCourseView = new CoursierRecapitulatifCourseView();
            // Affichage de la vue de la liste des courses
            RootController.open(coursierRecapitulatifCourseView);
            // Création du contrôleur de la vue
            FastStartupController fastStartupController = new FastStartupController(coursierRecapitulatifCourseView);
            coursierRecapitulatifCourseView.setController(fastStartupController);
        });

        // Bouton de fin de course
        this.coursierEndCourse.addActionListener(e -> {
            // Envoie sur le rapport d'activite ?
            // Archive la course
        });

        // Bouton de liste des colis
        this.colisButton.addActionListener(e -> {
            // Fermeture de toutes les vues
            RootController.closeAll();

            // Création de la vue de la liste des colis
            ColissView colissView = new ColissView();
            // Création du contrôleur de la vue
            ColissController colissController = new ColissController(colissView);
            colissView.setController(colissController);

            // Affichage de la vue de la liste des colis
            RootController.open(colissView);
        });

        // Bouton de liste des courses
        this.coursesButton.addActionListener(e -> {
            // Fermeture de toutes les vues
            RootController.closeAll();

            // Création de la vue de la liste des courses
            CoursesView coursesView = new CoursesView();
            // Création du contrôleur de la vue
            CoursesController coursesController = new CoursesController(coursesView);
            coursesView.setController(coursesController);

            // Affichage de la vue de la liste des courses
            RootController.open(coursesView);
        });

        // Bouton de liste des cycles
        this.cyclesButton.addActionListener(e -> {
            // Fermeture de toutes les vues
            RootController.closeAll();

            // Création de la vue de la liste des cycles
            CyclesView cyclesView = new CyclesView();
            // Création du contrôleur de la vue
            CyclesController cyclesController = new CyclesController(cyclesView);
            cyclesView.setController(cyclesController);

            // Affichage de la vue de la liste des cycles
            RootController.open(cyclesView);
        });

        // Bouton de liste des coursiers
        this.coursiersButton.addActionListener(e -> {
            // Fermeture de toutes les vues
            RootController.closeAll();

            // Création de la vue de la liste des courses
            CoursiersView coursiersView = new CoursiersView();
            // Création du contrôleur de la vue
            CoursiersController coursiersController = new CoursiersController(coursiersView);
            coursiersView.setController(coursiersController);

            // Affichage de la vue de la liste des courses
            RootController.open(coursiersView);
        });

        // Bouton de la carte
        this.carteButton.addActionListener(e -> {
            // Fermeture de toutes les vues
            RootController.closeAll();

            // Création de la vue de la carte
            CarteView coursesView = new CarteView();
            // Création du contrôleur de la vue
            CarteController coursesController = new CarteController(coursesView);
            coursesView.setController(coursesController);

            // Affichage de la vue de la carte
            RootController.open(coursesView);
        });


        // Ajout des éléments de l'interface

        // Désactivation des boutons
        this.coursierStartCourse.setVisible(false);
        this.coursierEndCourse.setVisible(false);
        this.colisButton.setVisible(false);
        this.coursesButton.setVisible(false);
        this.cyclesButton.setVisible(false);
        this.coursiersButton.setVisible(false);
        this.carteButton.setVisible(false);

        refreshMenu();
    }

    /**
     * Méthode redéfinie permettant d'ajouter un petit style à un composant
     */
    @Override
    public Component add(Component comp) {
        // Ajout d'une bordure entre chaque composant
        if (this.getComponentCount() > 0) {
            super.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // 100% de la largeur
        comp.setMaximumSize(new Dimension(Integer.MAX_VALUE, comp.getMaximumSize().height));

        return super.add(comp);
    }

    /**
     * Méthode permettant de rendre visible les boutons pour l'administrateur
     */
    public void showAdmin() {
        this.refreshMenu();
    }

    /**
     * Méthode permettant de rendre visible les boutons pour le gestionnaire de flotte
     */
    public void showGestionnaireFlotte() {
        this.colisButton.setVisible(true);
        this.coursesButton.setVisible(true);
        this.cyclesButton.setVisible(true);
        this.coursiersButton.setVisible(true);
        this.carteButton.setVisible(true);

        this.refreshMenu();
    }

    /**
     * Méthode permettant de rendre visible les boutons pour le coursier
     */
    public void showCoursier() {
        this.coursierStartCourse.setVisible(true);
        this.coursierEndCourse.setVisible(true);

        this.refreshMenu();
    }

    /**
     * Méthode permettant de rendre visible ou non le bouton de début de course
     *
     * @param visible boolean
     */
    public void setVisibleCoursierStartCourse(boolean visible) {
        this.coursierStartCourse.setVisible(visible);

        this.refreshMenu();
    }

    /**
     * Méthode permettant de rendre visible ou non le bouton de fin de course
     *
     * @param visible boolean
     */
    public void setVisibleCoursierEndCourse(boolean visible) {
        this.coursierEndCourse.setVisible(visible);

        this.refreshMenu();
    }

    private void refreshMenu() {
        this.removeAll();

        if (coursierStartCourse.isVisible()) {
            this.add(this.coursierStartCourse);
        }
        if (coursierEndCourse.isVisible()) {
            this.add(this.coursierEndCourse);
        }
        if (colisButton.isVisible()) {
            this.add(this.colisButton);
        }
        if (coursesButton.isVisible()) {
            this.add(this.coursesButton);
        }
        if (cyclesButton.isVisible()) {
            this.add(this.cyclesButton);
        }
        if (coursiersButton.isVisible()) {
            this.add(this.coursiersButton);
        }
        if (carteButton.isVisible()) {
            this.add(this.carteButton);
        }
    }
}