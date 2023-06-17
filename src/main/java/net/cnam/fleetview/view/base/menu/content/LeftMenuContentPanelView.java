package net.cnam.fleetview.view.base.menu.content;

import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.view.CarteView;
import net.cnam.fleetview.view.CoursesView;
import net.cnam.fleetview.view.components.button.IconLabelButton;

import javax.swing.*;
import java.awt.*;

public class LeftMenuContentPanelView extends JPanel {
    // Composants graphiques
    // Courses
    private final IconLabelButton coursesButton;
    // Carte
    private final IconLabelButton carteButton;

    public LeftMenuContentPanelView() {
        super();

        // Layout
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        // Bordure
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.coursesButton = new IconLabelButton("\uF0D1", "Courses");
        this.carteButton = new IconLabelButton("\uf279", "Carte");


        // Configuration des éléments de l'interface
        // Bouton de liste des courses
        this.coursesButton.addActionListener(e -> {
            // Création de la vue de la liste des courses
            CoursesView coursesView = new CoursesView();

            // Affichage de la vue de la liste des courses
            RootController.open(coursesView);
        });

        // Bouton de la carte
        this.carteButton.addActionListener(e -> {
            // Création de la vue de la carte
            CarteView coursesView = new CarteView();

            // Affichage de la vue de la carte
            RootController.open(coursesView);
        });


        // Ajout des éléments de l'interface
        this.add(this.coursesButton);
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
