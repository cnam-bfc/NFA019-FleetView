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
    private final InformationJLabel secondLabel;

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
        // on centre le texte
        this.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Création des éléments de l'interface
        this.firstLabel = new JLabel();
        this.firstLabel.setFont(App.TEXT_FONT.deriveFont(Font.BOLD, 12f));
        this.secondLabel = new InformationJLabel();


        // Configuration des éléments de l'interface
        // First label
        JPanel panelUn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.firstLabel.setText(this.textFirstLabel);

        // Second label
        JPanel panelDeux = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.secondLabel.setText(this.textSecondLabel);

        // Ajout des éléments de l'interface
        panelUn.add(this.firstLabel);
        panelDeux.add(this.secondLabel);
        this.add(panelUn);
        this.add(Box.createRigidArea(new Dimension(0, 3)));
        this.add(panelDeux);
    }

    public JLabel getFirstLabel() {
        return firstLabel;
    }

    public JLabel getSecondLabel() {
        return secondLabel;
    }
}
