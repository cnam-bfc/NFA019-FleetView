package net.cnam.fleetview.view.components.button;

import net.cnam.fleetview.App;

import javax.swing.*;

public class IconButton extends JButton {
    public IconButton() {
        super();

        this.setOpaque(false);
        this.setFocusPainted(false);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setFont(App.FONTAWESOME_SOLID_FONT.deriveFont(32f));
    }
}
