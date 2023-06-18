package net.cnam.fleetview.view.base;

import net.cnam.fleetview.view.accueil.AdminMainView;

import javax.swing.*;

public class ContentPanelView extends JPanel {
    public ContentPanelView() {
        super();
        final AdminMainView adminMainView = new AdminMainView();
        //this.setLayout(new GridLayout(1, 1));
        this.add(adminMainView);
    }
}
