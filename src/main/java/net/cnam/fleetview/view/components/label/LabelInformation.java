package net.cnam.fleetview.view.components.label;

import net.cnam.fleetview.App;

import javax.swing.*;
import java.awt.*;

public class LabelInformation extends JPanel {
    // CONSTANTES
    private final String textFirstLabel;
    private final String textSecondLabel;

    // ÉLÉMENTS DE L'INTERFACE
    private final JLabel firstLabel;
    private final JLabel secondLabel;

    /**
     * Constructeur
     *
     * @param textFirstLabel  Texte du premier label
     * @param textSecondLabel Texte du second label
     */
    public LabelInformation(String textFirstLabel, String textSecondLabel) {
        super();

        this.textFirstLabel = textFirstLabel;
        this.textSecondLabel = textSecondLabel;

        // Layout
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        // Création des éléments de l'interface
        this.firstLabel = new JLabel();
        this.secondLabel = new JLabel();


        // Configuration des éléments de l'interface
        // First label
        this.firstLabel.setText(this.textFirstLabel);

        // Second label
        this.secondLabel.setText(this.textSecondLabel);


        // Ajout des éléments de l'interface
        this.add(this.firstLabel);
        this.add(Box.createRigidArea(new Dimension(0, 3)));
        this.add(this.secondLabel);
    }

    public JLabel getFirstLabel() {
        return firstLabel;
    }

    public JLabel getSecondLabel() {
        return secondLabel;
    }
}
