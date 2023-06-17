package net.cnam.fleetview.view.components;

import net.cnam.fleetview.App;

import javax.swing.*;

public class MenuButton extends JButton {
    // CONSTANTES
    private final String logo;
    private final String texte;

    // ÉLÉMENTS DE L'INTERFACE
    private final JLabel logoLabel;
    private final JLabel texteLabel;

    public MenuButton(String logo, String texte) {
        super();

        this.logo = logo;
        this.texte = texte;

        // Création des éléments de l'interface
        this.logoLabel = new JLabel();
        this.texteLabel = new JLabel();

        // Configuration des éléments de l'interface
        this.logoLabel.setFont(App.FONTAWESOME_SOLID_FONT.deriveFont(32f));
        this.logoLabel.setText(this.logo);
        this.texteLabel.setText(this.texte);

        // Ajout des éléments de l'interface
        this.add(this.logoLabel);
        this.add(this.texteLabel);
    }
}
