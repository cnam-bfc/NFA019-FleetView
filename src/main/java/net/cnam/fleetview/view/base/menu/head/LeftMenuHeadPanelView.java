package net.cnam.fleetview.view.base.menu.head;

import net.cnam.fleetview.App;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LeftMenuHeadPanelView extends JPanel {
    // Composants graphiques
    // Logo
    private final JLabel logo;
    // Titre
    private final JLabel title;

    public LeftMenuHeadPanelView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Création des éléments de l'interface
        this.logo = new JLabel();
        this.title = new JLabel();


        // Configuration des éléments de l'interface
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

        // Titre
        // Font Quicksand, Bold, 24px
        this.title.setText(App.APP_NAME);
        this.title.setFont(App.LOGO_FONT);
        this.title.setForeground(App.PRIMARY_COLOR);
        this.title.setHorizontalAlignment(SwingConstants.CENTER);


        // Ajout des éléments à l'interface
        this.add(this.logo, BorderLayout.CENTER);
        this.add(this.title, BorderLayout.SOUTH);
    }
}
