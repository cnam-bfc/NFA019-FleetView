package net.cnam.fleetview.view;

import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import java.awt.*;

public class CoursiersView extends JPanel {
    // ÉLÉMENTS DE L'INTERFACE
    private final IconLabel iconLabel;

    public CoursiersView() {
        super();
        this.setLayout(new BorderLayout());

        // Création des éléments de l'interface
        this.iconLabel = new IconLabel("\uF84A", "COURSIERS");

        // Ajout des éléments de l'interface
        this.add(this.iconLabel);
    }
}
