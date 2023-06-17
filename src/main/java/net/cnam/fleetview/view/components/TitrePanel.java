package net.cnam.fleetview.view.components;

import net.cnam.fleetview.App;

import javax.swing.*;
import java.awt.*;

public class TitrePanel extends JPanel {
    // CONSTANTES
    private final String logo;
    private final String titre;

    // ÉLÉMENTS DE L'INTERFACE
    private final JLabel logoLabel;
    private final JLabel titreLabel;

    public TitrePanel(String logo, String titre) {
        super(new FlowLayout(FlowLayout.LEFT));

        this.logo = logo;
        this.titre = titre;

        // Création des éléments de l'interface
        this.logoLabel = new JLabel();
        this.titreLabel = new JLabel();

        // Configuration des éléments de l'interface
        this.logoLabel.setFont(App.FONTAWESOME_SOLID_FONT.deriveFont(32f));
        this.logoLabel.setText(this.logo);
        this.titreLabel.setText(this.titre);

        // Ajout des éléments de l'interface
        this.add(this.logoLabel);
        this.add(this.titreLabel);
    }
}
