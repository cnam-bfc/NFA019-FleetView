package net.cnam.fleetview.view.components.button;

import net.cnam.fleetview.App;

import javax.swing.*;
import java.awt.*;

public class IconLabelButton extends JButton {
    // CONSTANTES
    private final String icon;
    private final String texte;

    // ÉLÉMENTS DE L'INTERFACE
    private final JLabel iconLabel;
    private final JLabel texteLabel;

    /**
     * Constructeur
     *
     * @param icon  Icône du bouton
     * @param texte Texte du bouton
     */
    public IconLabelButton(String icon, String texte) {
        super();

        this.icon = icon;
        this.texte = texte;

        // Layout
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(layout);

        this.setOpaque(false);
        this.setFocusPainted(false);

        // Création des éléments de l'interface
        this.iconLabel = new JLabel();
        this.texteLabel = new JLabel();


        // Configuration des éléments de l'interface
        // Icon label
        this.iconLabel.setText(this.icon);
        this.iconLabel.setFont(App.FONTAWESOME_SOLID_FONT.deriveFont(32f));
        // Définir la taille souhaitée pour l'icône
        Dimension iconSize = new Dimension(40, 40);
        this.iconLabel.setPreferredSize(iconSize);
        this.iconLabel.setMaximumSize(iconSize);
        this.iconLabel.setMinimumSize(iconSize);
        this.iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Texte label
        this.texteLabel.setText(this.texte);


        // Ajout des éléments de l'interface
        this.add(this.iconLabel);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(this.texteLabel);
    }

    public JLabel getIconLabel() {
        return iconLabel;
    }

    public JLabel getTexteLabel() {
        return texteLabel;
    }
}
