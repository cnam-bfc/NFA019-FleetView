package net.cnam.fleetview.view.components;

import net.cnam.fleetview.App;

import javax.swing.*;
import java.awt.*;

public class IconLabelButton extends JButton {
    // CONSTANTES
    private final String logo;
    private final String texte;

    // ÉLÉMENTS DE L'INTERFACE
    private final JPanel contentPanel;
    private final JLabel logoLabel;
    private final JLabel texteLabel;

    public IconLabelButton(String logo, String texte) {
        super();

        this.logo = logo;
        this.texte = texte;

        // Création des éléments de l'interface
        this.contentPanel = new JPanel();
        this.logoLabel = new JLabel();
        this.texteLabel = new JLabel();


        // Configuration des éléments de l'interface
        // Content panel
        FlowLayout layout = new FlowLayout(FlowLayout.LEADING);
        this.contentPanel.setLayout(layout);
        this.contentPanel.setOpaque(false);

        // Logo label
        this.logoLabel.setText(this.logo);
        this.logoLabel.setFont(App.FONTAWESOME_SOLID_FONT.deriveFont(32f));

        // Texte label
        this.texteLabel.setText(this.texte);


        // Ajout des éléments de l'interface
        this.contentPanel.add(this.logoLabel);
        this.contentPanel.add(this.texteLabel);
        this.add(this.contentPanel);
    }
}
