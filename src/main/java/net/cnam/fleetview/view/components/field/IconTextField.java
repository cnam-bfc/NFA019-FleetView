package net.cnam.fleetview.view.components.field;

import net.cnam.fleetview.App;

import javax.swing.*;
import java.awt.*;

public class IconTextField extends JPanel {
    // CONSTANTES
    private final String icon;

    // ÉLÉMENTS DE L'INTERFACE
    private final JLabel iconLabel;
    private final JTextField textField;

    public IconTextField(String icon) {
        super();

        this.icon = icon;

        // Layout
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(layout);

        // Création des éléments de l'interface
        this.iconLabel = new JLabel();
        this.textField = new JTextField();


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

        // Text field


        // Ajout des éléments de l'interface
        this.add(this.iconLabel);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(this.textField);
    }

    public JLabel getIconLabel() {
        return iconLabel;
    }

    public JTextField getTextField() {
        return textField;
    }
}
