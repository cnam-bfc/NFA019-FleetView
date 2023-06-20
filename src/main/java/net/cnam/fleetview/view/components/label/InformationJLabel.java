package net.cnam.fleetview.view.components.label;

import javax.swing.*;
import java.awt.*;

public class InformationJLabel extends JLabel {

    public InformationJLabel() {
        this.setMaximumSize(new Dimension(50, 25));
        this.setMinimumSize(new Dimension(50, 25));
        this.setPreferredSize(new Dimension(50, 25));
        this.setBackground(new Color(20, 150, 220));
        this.setOpaque(true);
    }
}