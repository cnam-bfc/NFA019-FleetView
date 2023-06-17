package net.cnam.fleetview.view.base.main.head.left;

import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.view.components.button.IconButton;

import javax.swing.*;
import java.awt.*;

public class TopLeftMenuPanelView extends JPanel {
    // Composants graphiques
    // Logo retour
    private final IconButton backButton;

    public TopLeftMenuPanelView() {
        super();

        // Layout
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        layout.setHgap(10);
        layout.setVgap(0);
        this.setLayout(layout);

        this.setOpaque(false);

        // Création des éléments de l'interface
        this.backButton = new IconButton();


        // Configuration des éléments de l'interface
        // Logo retour
        this.backButton.setText("\uF0A8");
        this.backButton.setVisible(false);

        this.backButton.addActionListener(e -> {
            RootController.closeLast();
        });


        // Ajout des éléments de l'interface
        this.add(this.backButton);
    }

    public void setBackButtonVisible(boolean visible) {
        this.backButton.setVisible(visible);
    }
}
