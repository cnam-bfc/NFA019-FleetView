package net.cnam.fleetview.view;

import net.cnam.fleetview.App;

import javax.swing.*;

public class MenuButton extends JButton {
    public MenuButton(String logo, String text) {
        super();

        this.setFont(App.FONTAWESOME_SOLID_FONT.deriveFont(32f));
        this.setText(logo + " " + text);
    }
}
