package net.cnam.fleetview.view.base;

import javax.swing.*;
import java.awt.*;

public class MainPanelView extends JPanel {
    private final TopMenuPanelView topMenuPanelView = new TopMenuPanelView();
    private JPanel contentPanelView;

    public MainPanelView() {
        super();

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

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
    }
}
