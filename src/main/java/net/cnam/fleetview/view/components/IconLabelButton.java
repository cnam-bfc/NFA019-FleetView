package net.cnam.fleetview.view.components;

import net.cnam.fleetview.App;

import javax.swing.*;
import java.awt.*;

public class IconLabelButton extends JButton {
    // CONSTANTES
    private final String logo;
    private final String texte;

    // ÉLÉMENTS DE L'INTERFACE
    private final JLabel logoLabel;
    private final JLabel texteLabel;

    public IconLabelButton(String logo, String texte) {
        super();

        this.logo = logo;
        this.texte = texte;

        // Layout
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(layout);


        // Création des éléments de l'interface
        this.logoLabel = new JLabel();
        this.texteLabel = new JLabel();


        // Configuration des éléments de l'interface
        // Logo label
        this.logoLabel.setText(this.logo);
        this.logoLabel.setFont(App.FONTAWESOME_SOLID_FONT.deriveFont(32f));
        // Définir la taille souhaitée pour l'icône
        Dimension logoSize = new Dimension(40, 40);
        this.logoLabel.setPreferredSize(logoSize);
        this.logoLabel.setMaximumSize(logoSize);
        this.logoLabel.setMinimumSize(logoSize);
        this.logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Texte label
        this.texteLabel.setText(this.texte);


        // Ajout des éléments de l'interface
        this.add(this.logoLabel);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(this.texteLabel);
    }
}
