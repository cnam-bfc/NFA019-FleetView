package net.cnam.fleetview.view.base.menu.content;

import net.cnam.fleetview.controller.ColissController;
import net.cnam.fleetview.controller.CoursesController;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.controller.coursier.CoursiersController;
import net.cnam.fleetview.view.carte.CarteView;
import net.cnam.fleetview.view.colis.list.ColissView;
import net.cnam.fleetview.view.components.button.IconLabelButton;
import net.cnam.fleetview.view.course.list.CoursesView;
import net.cnam.fleetview.view.coursier.list.CoursiersView;

import javax.swing.*;
import java.awt.*;

public class LeftMenuContentPanelView extends JPanel {
    // Composants graphiques
    // Colis
    private final IconLabelButton colisButton;
    // Courses
    private final IconLabelButton coursesButton;
    // Coursiers
    private final IconLabelButton coursiersButton;
    // Carte
    private final IconLabelButton carteButton;

    public LeftMenuContentPanelView() {
        super();

        // Layout
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        this.setOpaque(false);

        // Bordure
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.colisButton = new IconLabelButton("\uF466", "Colis");
        this.coursesButton = new IconLabelButton("\uF0D1", "Courses");
        this.coursiersButton = new IconLabelButton("\uF84A", "Coursiers");
        this.carteButton = new IconLabelButton("\uf279", "Carte");

        // Configuration des éléments de l'interface
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

            // Affichage de la vue de la carte
            RootController.open(coursesView);
        });


        // Ajout des éléments de l'interface
        this.add(this.colisButton);
        this.add(this.coursesButton);
        this.add(this.coursiersButton);
        this.add(this.carteButton);
    }

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
}
