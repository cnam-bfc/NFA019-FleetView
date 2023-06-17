package net.cnam.fleetview.view.base.menu.content;

import net.cnam.fleetview.App;
import net.cnam.fleetview.view.components.MenuButton;
import net.cnam.fleetview.view.ListeCoursesPanelView;
import net.cnam.fleetview.view.ParametrageBddView;

import javax.swing.*;

public class LeftMenuContentPanelView extends JPanel {
    // Composants graphiques
    // Courses
    private final MenuButton coursesButton;
    // Paramètres
    private final MenuButton parametresButton;

    public LeftMenuContentPanelView() {
        super();

        // Layout
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        // Création des éléments de l'interface
        this.coursesButton = new MenuButton("\uF0D1", "Courses");
        this.parametresButton = new MenuButton("\uF013", "Paramètres");


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
}
