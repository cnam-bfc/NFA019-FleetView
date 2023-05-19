package net.cnam.fleetview.view.base;

import net.cnam.fleetview.App;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LeftMenuPanelView extends JPanel {
    // Composants graphiques
    // Logo
    private final JLabel logo = new JLabel();

    // Titre
    private final JLabel title = new JLabel();

    public LeftMenuPanelView() {
        super();

        // Largeur maximale du menu
        this.setPreferredSize(new Dimension(200, Integer.MAX_VALUE));

        this.setBackground(new Color(74, 123, 89, 255));

        // Logo
        // Charger l'image
        BufferedImage image = App.LOGO_NORMAL;

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
        this.logo.setHorizontalAlignment(SwingConstants.CENTER);

        // Ajout du logo au menu
        this.add(this.logo);

        // Titre
        // Font Quicksand, Bold, 24px
        this.title.setText("FleetView");
        this.title.setFont(App.LOGO_FONT);
        this.title.setForeground(App.PRIMARY_COLOR);
        this.title.setHorizontalAlignment(SwingConstants.CENTER);

        // Ajout du titre au menu
        this.add(this.title);
    }
}
