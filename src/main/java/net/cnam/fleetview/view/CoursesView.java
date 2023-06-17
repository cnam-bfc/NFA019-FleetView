package net.cnam.fleetview.view;

import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;

public class CoursesView extends JPanel implements View {
    // ÉLÉMENTS DE L'INTERFACE
    private final IconLabel iconLabel;

    public CoursesView() {
        super();

        // Création des éléments de l'interface
        this.iconLabel = new IconLabel("\uF0D1", "COURSES");

        // Ajout des éléments de l'interface
        this.add(this.iconLabel);
    }

    @Override
    public boolean onClose() {
        return true;
    }
}
