package net.cnam.fleetview;

import javax.swing.*;
import java.awt.*;

public class TopMenuPanel extends JPanel {
    // Composants graphiques
    // Logo minimisation de l'application
    private final JButton minimizeButton = new JButton();

    // Logo redimensionnement de l'application
    private final JButton resizeButton = new JButton();

    // Logo fermeture de l'application
    private final JButton closeButton = new JButton();

    public TopMenuPanel() {
        super();

        // Layout
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.RIGHT);
        this.setLayout(layout);

        // Hauteur maximale du menu
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));

        this.setBackground(new Color(103, 175, 172));

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

        // Ajout du logo minimisation de l'application au menu
        this.add(this.minimizeButton);

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

        // Ajout du logo redimensionnement de l'application au menu
        this.add(this.resizeButton);

        // Logo fermeture de l'application
        this.closeButton.setFont(App.FONTAWESOME_SOLID_FONT.deriveFont(Font.PLAIN, 25));
        this.closeButton.setText("\uF410");
        this.closeButton.setOpaque(false);
        this.closeButton.setVerticalAlignment(SwingConstants.CENTER);

        this.closeButton.addActionListener(e -> System.exit(0));

        // Ajout du logo fermeture de l'application au menu
        this.add(this.closeButton);
    }
}
