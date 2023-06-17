package net.cnam.fleetview.view.base.menu;

import net.cnam.fleetview.view.base.menu.content.LeftMenuContentPanelView;
import net.cnam.fleetview.view.base.menu.foot.LeftMenuFootPanelView;
import net.cnam.fleetview.view.base.menu.head.LeftMenuHeadPanelView;

import javax.swing.*;
import java.awt.*;

public class LeftMenuPanelView extends JPanel {
    // Composants graphiques
    // Head menu
    private final LeftMenuHeadPanelView headPanel;
    // Content menu
    private final LeftMenuContentPanelView contentPanel;
    // Foot menu
    private final LeftMenuFootPanelView footPanel;

    public LeftMenuPanelView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Largeur maximale du menu
        this.setPreferredSize(new Dimension(200, Integer.MAX_VALUE));

        // Création des éléments de l'interface
        this.headPanel = new LeftMenuHeadPanelView();
        this.contentPanel = new LeftMenuContentPanelView();
        this.footPanel = new LeftMenuFootPanelView();


        // Configuration des éléments de l'interface
        // Head menu
        this.headPanel.setBackground(new Color(74, 123, 89, 255));

        // Content menu
        this.contentPanel.setBackground(new Color(74, 123, 89, 255));

        // Foot menu
        this.footPanel.setBackground(new Color(74, 123, 89, 255));


        // Ajout des éléments de l'interface
        this.add(this.headPanel, BorderLayout.NORTH);
        this.add(this.contentPanel, BorderLayout.CENTER);
        this.add(this.footPanel, BorderLayout.SOUTH);
    }

    public LeftMenuHeadPanelView getHeadPanel() {
        return headPanel;
    }

    public LeftMenuContentPanelView getContentPanel() {
        return contentPanel;
    }

    public LeftMenuFootPanelView getFootPanel() {
        return footPanel;
    }
}
