package net.cnam.fleetview.view.components;

import net.cnam.fleetview.App;

import javax.swing.*;
import java.awt.*;

public class IconLabel extends JPanel {
    // CONSTANTES
    private final String logo;
    private final String titre;

    // ÉLÉMENTS DE L'INTERFACE
    private final JLabel logoLabel;
    private final JLabel titreLabel;

    public IconLabel(String logo, String titre) {
        super();

        this.logo = logo;
        this.titre = titre;

        // Layout
        BorderLayout layout = new BorderLayout();
        layout.setHgap(10);
        this.setLayout(layout);

        // Création des éléments de l'interface
        this.logoLabel = new JLabel();
        this.titreLabel = new JLabel();


        // Configuration des éléments de l'interface
        // Logo label
        this.logoLabel.setText(this.logo);
        this.logoLabel.setFont(App.FONTAWESOME_SOLID_FONT.deriveFont(32f));

        // Titre label
        this.titreLabel.setText(this.titre);


        // Ajout des éléments de l'interface
        this.add(this.logoLabel, BorderLayout.WEST);
        this.add(this.titreLabel, BorderLayout.CENTER);
    }
}
