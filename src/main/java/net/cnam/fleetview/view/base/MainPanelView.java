package net.cnam.fleetview.view.base;

import net.cnam.fleetview.view.accueil.AdminMainView;
import net.cnam.fleetview.view.administrateur.ViewUsers;

import javax.swing.*;
import java.awt.*;

public class MainPanelView extends JPanel {
    private final TopMenuPanelView topMenuPanelView = new TopMenuPanelView();
    //private final ContentPanelView contentPanelView = new ContentPanelView();
    //private final AdminMainView adminMainView = new AdminMainView();
    private final ViewUsers viewUsers = new ViewUsers();
    public MainPanelView() {
        super();

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.add(topMenuPanelView, BorderLayout.NORTH);
        //this.add(contentPanelView, BorderLayout.CENTER);
        //this.add(adminMainView,BorderLayout.CENTER);
        this.add(viewUsers,BorderLayout.CENTER);
    }

    public TopMenuPanelView getTopMenuPanel() {
        return topMenuPanelView;
    }

    //public ContentPanelView getContentPanel() {return contentPanelView;}
}
