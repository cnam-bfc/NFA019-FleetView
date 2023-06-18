package net.cnam.fleetview.view.carte;

import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import javax.swing.*;
import java.awt.*;

public class CarteView extends View {
    // ÉLÉMENTS DE L'INTERFACE
    // Titre
    private final IconLabel titre;
    // Carte
    private final JMapViewer carte;

    public CarteView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Bordures
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.titre = new IconLabel("\uF0D1", "Courses");
        this.carte = new JMapViewer();


        // Configuration des éléments de l'interface
        // Carte
        this.carte.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        // Ajout des éléments de l'interface
        this.add(this.titre, BorderLayout.NORTH);
        this.add(this.carte, BorderLayout.CENTER);
    }
}
