package net.cnam.fleetview.view.base.main.head.right;

import net.cnam.fleetview.view.components.button.IconButton;

import javax.swing.*;
import java.awt.*;

public class TopRightMenuPanelView extends JPanel {
    // Composants graphiques
    // Logo minimisation de l'application
    private final IconButton minimizeButton;
    // Logo redimensionnement de l'application
    private final IconButton resizeButton;
    // Logo fermeture de l'application
    private final IconButton closeButton;

    public TopRightMenuPanelView() {
        super();

        // Layout
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.RIGHT);
        layout.setHgap(10);
        layout.setVgap(0);
        this.setLayout(layout);

        this.setOpaque(false);

        // Création des éléments de l'interface
        this.minimizeButton = new IconButton();
        this.resizeButton = new IconButton();
        this.closeButton = new IconButton();


        // Configuration des éléments de l'interface
        // Logo minimisation de l'application
        this.minimizeButton.setText("\uF2D1");

        this.minimizeButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame != null) {
                frame.setExtendedState(JFrame.ICONIFIED);
            }
        });

        // Logo redimensionnement de l'application
        this.resizeButton.setText("\uF2D2");

        this.resizeButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame != null) {
                if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                    frame.setExtendedState(JFrame.NORMAL);
                } else {
                    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    frame.setMaximizedBounds(env.getMaximumWindowBounds());
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            }
        });

        // Logo fermeture de l'application
        this.closeButton.setText("\uF410");

        this.closeButton.addActionListener(e -> System.exit(0));


        // Ajout des éléments de l'interface
        this.add(this.minimizeButton);
        this.add(this.resizeButton);
        this.add(this.closeButton);
    }
}
