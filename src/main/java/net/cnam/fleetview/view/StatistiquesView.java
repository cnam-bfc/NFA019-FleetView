package net.cnam.fleetview.view;

import net.cnam.fleetview.view.components.label.IconLabel;

import java.awt.*;

public class StatistiquesView extends View {

    // ÉLÉMENTS DE L'INTERFACE
    private final IconLabel iconLabel;

    public StatistiquesView() {
        super();
        GridLayout gridLayout = new GridLayout();
        this.setLayout(gridLayout);

        // Création des éléments de l'interface
        this.iconLabel = new IconLabel("\uF200", "STATISTIQUES");

        // Ajout des éléments de l'interface
        this.add(this.iconLabel);
    }
}
