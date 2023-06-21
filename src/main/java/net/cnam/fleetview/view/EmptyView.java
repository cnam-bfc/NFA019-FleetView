package net.cnam.fleetview.view;

import net.cnam.fleetview.App;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EmptyView extends View {
    // ÉLÉMENTS DE L'INTERFACE
    // Logo
    private final JLabel logo;

    public EmptyView() {
        super();

        // Layout centrer horizontalement et verticalement
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Création des éléments de l'interface
        this.logo = new JLabel();


        // Configuration des éléments de l'interface
        // Logo
        // Charger l'image
        BufferedImage image = App.LOGO_SIMPLIFIED;

        // Définir une largeur maximale de 125 pixels
        int maxWidth = 125;
        int width = image.getWidth();
        int height = image.getHeight();
        if (width > maxWidth) {
            height = (int) ((double) height * ((double) maxWidth / (double) width));
            width = maxWidth;
        }

        // Redimensionner l'image en conservant ses proportions d'origine
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Créer un JLabel avec l'image redimensionnée
        ImageIcon imageIcon = new ImageIcon(scaledImage);
        this.logo.setIcon(imageIcon);
        // Configuration des contraintes pour centrer l'élément
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;

        // Ajout des éléments à l'interface
        this.add(this.logo, constraints);
    }
}
