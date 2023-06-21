package net.cnam.fleetview.view.base;

import net.cnam.fleetview.App;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.view.utils.ComponentMover;
import net.cnam.fleetview.view.utils.ComponentResizer;

import javax.swing.*;
import java.awt.*;

public class RootFrameView extends JFrame {
    // Composants graphiques
    // Panel
    private final RootPanelView panel;

    public RootFrameView() {
        super("FleetView");

        // Création de la vue
        this.panel = new RootPanelView();
        // Création du contrôleur de la vue
        RootController rootController = new RootController(panel);
        panel.setController(rootController);

        this.setIconImage(App.LOGO_SIMPLIFIED);
        this.setContentPane(panel);
        this.setUndecorated(true);

        // Taille minimale de la fenêtre
        this.setMinimumSize(new Dimension(800, 500));

        // Taille par défaut de la fenêtre (3/4 de la taille de l'écran)
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(new Dimension((int) (screenSize.getWidth() * 0.75), (int) (screenSize.getHeight() * 0.75)));

        this.setLocationRelativeTo(null);

        this.setBackground(new Color(249, 242, 217));

        // Ajouter une bordure autour de la fenêtre (effet d'ombre)
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 0, 0, 50)));

        // Permet de redimensionner la fenêtre
        ComponentResizer resizer = new ComponentResizer();
        resizer.registerComponent(this);

        // Permet de déplacer la fenêtre (menu de gauche et menu du haut)
        ComponentMover mover = new ComponentMover(this, panel.getRightMenuPanel(), panel.getMainPanel().getTopMenuPanel());
    }

    public RootPanelView getRootPanelView() {
        return panel;
    }
}
