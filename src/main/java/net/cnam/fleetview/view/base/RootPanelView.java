package net.cnam.fleetview.view.base;

import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.base.main.MainPanelView;
import net.cnam.fleetview.view.base.menu.LeftMenuPanelView;

import java.awt.*;

public class RootPanelView extends View<RootController> {
    // Composants graphiques
    // Left menu
    private final LeftMenuPanelView leftMenuPanelView;
    // Main panel
    private final MainPanelView mainPanelView;

    public RootPanelView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Création des éléments de l'interface
        this.leftMenuPanelView = new LeftMenuPanelView();
        this.mainPanelView = new MainPanelView();


        // Configuration des éléments de l'interface


        // Ajout des éléments de l'interface
        this.add(leftMenuPanelView, BorderLayout.WEST);
        this.add(mainPanelView, BorderLayout.CENTER);
    }

    public LeftMenuPanelView getRightMenuPanel() {
        return leftMenuPanelView;
    }

    public MainPanelView getMainPanel() {
        return mainPanelView;
    }
}
