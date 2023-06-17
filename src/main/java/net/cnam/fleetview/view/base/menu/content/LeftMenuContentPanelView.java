package net.cnam.fleetview.view.base.menu.content;

import net.cnam.fleetview.App;
import net.cnam.fleetview.view.ListeCoursesPanelView;
import net.cnam.fleetview.view.ParametrageBddView;
import net.cnam.fleetview.view.components.button.IconLabelButton;

import javax.swing.*;
import java.awt.*;

public class LeftMenuContentPanelView extends JPanel {
    // Composants graphiques
    // Courses
    private final IconLabelButton coursesButton;
    // Paramètres
    private final IconLabelButton parametresButton;

    public LeftMenuContentPanelView() {
        super();

        // Layout
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        // Bordure
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.coursesButton = new IconLabelButton("\uF0D1", "Courses");
        this.parametresButton = new IconLabelButton("\uF013", "Paramètres");


        // Configuration des éléments de l'interface
        // Bouton de liste des courses
        this.coursesButton.addActionListener(e -> {
            // Création de la vue de la liste des courses
            ListeCoursesPanelView listeCoursesPanelView = new ListeCoursesPanelView();

            // Affichage de la vue de la liste des courses
            App.INSTANCE.getPanel().getMainPanel().setContentPanelView(listeCoursesPanelView);
        });

        // Bouton de paramètres
        this.parametresButton.addActionListener(e -> {
            // Création de la vue des paramètres
            ParametrageBddView parametrageBddView = new ParametrageBddView();

            // Affichage de la vue des paramètres
            App.INSTANCE.getPanel().getMainPanel().setContentPanelView(parametrageBddView);
        });


        // Ajout des éléments de l'interface
        this.add(this.coursesButton);
        this.add(this.parametresButton);
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
