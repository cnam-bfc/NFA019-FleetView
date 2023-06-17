package net.cnam.fleetview.view.base.main.head;

import net.cnam.fleetview.App;

import javax.swing.*;
import java.awt.*;

public class TopMenuPanelView extends JPanel {
    // Composants graphiques
    // Logo minimisation de l'application
    private final JButton minimizeButton;
    // Logo redimensionnement de l'application
    private final JButton resizeButton;
    // Logo fermeture de l'application
    private final JButton closeButton;

    public TopMenuPanelView() {
        super();

        // Layout
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.RIGHT);
        this.setLayout(layout);

        // Hauteur maximale du menu
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));

        this.setBackground(new Color(103, 175, 172));

        // Création des éléments de l'interface
        this.minimizeButton = new JButton();
        this.resizeButton = new JButton();
        this.closeButton = new JButton();


        // Configuration des éléments de l'interface
        // Logo minimisation de l'application
        this.minimizeButton.setFont(App.FONTAWESOME_SOLID_FONT.deriveFont(Font.PLAIN, 25));
        this.minimizeButton.setText("\uF2D1");
        this.minimizeButton.setOpaque(false);
        this.minimizeButton.setVerticalAlignment(SwingConstants.CENTER);

        this.minimizeButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame != null) {
                frame.setExtendedState(JFrame.ICONIFIED);
            }
        });

        // Logo redimensionnement de l'application
        this.resizeButton.setFont(App.FONTAWESOME_SOLID_FONT.deriveFont(Font.PLAIN, 25));
        this.resizeButton.setText("\uF2D2");
        this.resizeButton.setOpaque(false);
        this.resizeButton.setVerticalAlignment(SwingConstants.CENTER);

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
        this.closeButton.setFont(App.FONTAWESOME_SOLID_FONT.deriveFont(Font.PLAIN, 25));
        this.closeButton.setText("\uF410");
        this.closeButton.setOpaque(false);
        this.closeButton.setVerticalAlignment(SwingConstants.CENTER);

        this.closeButton.addActionListener(e -> System.exit(0));


        // Ajout des éléments de l'interface
        this.add(this.minimizeButton);
        this.add(this.resizeButton);
        this.add(this.closeButton);
    }
}
