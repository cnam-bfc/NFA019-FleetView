package net.cnam.fleetview.view.base.main.head;

import net.cnam.fleetview.view.base.main.head.left.TopLeftMenuPanelView;
import net.cnam.fleetview.view.base.main.head.right.TopRightMenuPanelView;

import javax.swing.*;
import java.awt.*;

public class TopMenuPanelView extends JPanel {
    // Composants graphiques
    // Panel de gauche
    private final TopLeftMenuPanelView topLeftMenuPanelView;
    // Panel de droite
    private final TopRightMenuPanelView topRightMenuPanelView;

    public TopMenuPanelView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        this.setBackground(new Color(103, 175, 172));

        // Création des éléments de l'interface
        this.topLeftMenuPanelView = new TopLeftMenuPanelView();
        this.topRightMenuPanelView = new TopRightMenuPanelView();


        // Configuration des éléments de l'interface
        /*JScrollPane scrollPane = new JScrollPane(this.topLeftMenuPanelView);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setOpaque(false);*/

        // Ajout des éléments de l'interface
        this.add(this.topLeftMenuPanelView, BorderLayout.CENTER);
        this.add(this.topRightMenuPanelView, BorderLayout.EAST);
    }

    public TopLeftMenuPanelView getTopLeftMenuPanelView() {
        return topLeftMenuPanelView;
    }

    public TopRightMenuPanelView getTopRightMenuPanelView() {
        return topRightMenuPanelView;
    }
}
