package net.cnam.fleetview.view.base.main;

import net.cnam.fleetview.view.base.main.head.TopMenuPanelView;

import javax.swing.*;
import java.awt.*;

public class MainPanelView extends JPanel {
    // Composants graphiques
    // Top menu
    private final TopMenuPanelView topMenuPanelView;
    // Content panel
    private JPanel contentPanelView;

    public MainPanelView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Création des éléments de l'interface
        this.topMenuPanelView = new TopMenuPanelView();


        // Configuration des éléments de l'interface


        // Ajout des éléments de l'interface
        this.add(topMenuPanelView, BorderLayout.NORTH);
    }

    public TopMenuPanelView getTopMenuPanel() {
        return topMenuPanelView;
    }

    public JPanel getContentPanelView() {
        return contentPanelView;
    }

    public synchronized void setContentPanelView(JPanel contentPanelView) {
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
