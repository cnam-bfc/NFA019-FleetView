package net.cnam.fleetview.view;

import net.cnam.fleetview.view.components.IconLabel;

import javax.swing.*;

public class ListeCoursesPanelView extends JPanel {
    // ÉLÉMENTS DE L'INTERFACE
    private final IconLabel iconLabel;

    public ListeCoursesPanelView() {
        super();

        // Création des éléments de l'interface
        this.iconLabel = new IconLabel("\uF0D1", "COURSES");

        // Ajout des éléments de l'interface
        this.add(this.iconLabel);
    }
}