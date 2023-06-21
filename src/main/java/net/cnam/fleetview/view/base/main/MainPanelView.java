package net.cnam.fleetview.view.base.main;

import net.cnam.fleetview.view.EmptyView;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.base.main.head.TopMenuPanelView;

import javax.swing.*;
import java.awt.*;

public class MainPanelView extends JPanel {
    // Composants graphiques
    // Top menu
    private final TopMenuPanelView topMenuPanelView;
    // Content panel
    private View contentPanelView;

    public MainPanelView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Création des éléments de l'interface
        this.topMenuPanelView = new TopMenuPanelView();
        this.contentPanelView = new EmptyView();


        // Configuration des éléments de l'interface


        // Ajout des éléments de l'interface
        this.add(topMenuPanelView, BorderLayout.NORTH);
        this.add(contentPanelView, BorderLayout.CENTER);
    }

    public TopMenuPanelView getTopMenuPanel() {
        return topMenuPanelView;
    }

    public View getContentPanelView() {
        return contentPanelView;
    }

    public synchronized void setContentPanelView(View contentPanelView) {
        // Suppression du panel actuel
        if (this.contentPanelView != null) {
            this.remove(this.contentPanelView);
        }

        // Ajout du nouveau panel
        this.contentPanelView = contentPanelView;
        this.add(contentPanelView, BorderLayout.CENTER);

        // Mise à jour de l'interface
        this.revalidate();
        this.repaint();
    }
}
