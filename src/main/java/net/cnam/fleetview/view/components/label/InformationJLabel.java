package net.cnam.fleetview.view.components.label;

import javax.swing.*;
import java.awt.*;

public class InformationJLabel extends JLabel {

    public InformationJLabel() {
        this.setMaximumSize(new Dimension(50, 25));
        this.setMinimumSize(new Dimension(50, 25));
        this.setPreferredSize(new Dimension(50, 25));
        this.setBackground(new Color(190, 190, 190));
        this.setOpaque(true);
        // On centre l'écriture au milieu
        this.setHorizontalAlignment(SwingConstants.CENTER);
        // On arrondie les bordures
        this.setBorder(BorderFactory.createLineBorder(new Color(190, 190, 190), 5));
    }
}