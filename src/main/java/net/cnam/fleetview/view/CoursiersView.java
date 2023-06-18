package net.cnam.fleetview.view;

import net.cnam.fleetview.view.components.label.IconLabel;

import java.awt.*;

public class CoursiersView extends View {
    // ÉLÉMENTS DE L'INTERFACE
    private final IconLabel iconLabel;

    public CoursiersView() {
        super();
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        // Création des éléments de l'interface
        this.iconLabel = new IconLabel("\uF84A", "COURSIERS");

        // Ajout des éléments de l'interface
        this.add(this.iconLabel);
    }
}
