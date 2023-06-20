package net.cnam.fleetview.view.base.menu.foot;

import net.cnam.fleetview.App;
import net.cnam.fleetview.controller.ConnectionController;
import net.cnam.fleetview.controller.CreationCycleController;
import net.cnam.fleetview.controller.ListeCycleController;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.view.ConnectionView;
import net.cnam.fleetview.view.ParametrageBddView;
import net.cnam.fleetview.view.components.button.IconLabelButton;
import net.cnam.fleetview.view.cycle.CreationCycleView;
import net.cnam.fleetview.view.cycle.ListeCycleView;

import javax.swing.*;
import java.awt.*;

public class LeftMenuFootPanelView extends JPanel {
    // Composants graphiques
    // Paramètres
    private final IconLabelButton parametresButton;
    // DEBUG 1
    private final IconLabelButton debug1Button;
    // DEBUG 2
    private final IconLabelButton debug2Button;
    // DEBUG 3
    private final IconLabelButton debug3Button;
    // Version
    private final JLabel versionLabel;

    public LeftMenuFootPanelView() {
        super();

        // Layout
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        this.setOpaque(false);

        // Bordure
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.parametresButton = new IconLabelButton("\uF013", "Paramètres");
        this.debug1Button = new IconLabelButton("\uF0AD", "DEBUG 1");
        this.debug2Button = new IconLabelButton("\uF0AD", "DEBUG 2");
        this.debug3Button = new IconLabelButton("\uF0AD", "DEBUG 3");
        this.versionLabel = new JLabel("Version " + App.APP_VERSION);


        // Configuration des éléments de l'interface
        // Bouton de paramètres
        this.parametresButton.addActionListener(e -> {
            // Fermeture de toutes les vues
            RootController.closeAll();

            // Création de la vue des paramètres
            ParametrageBddView parametrageBddView = new ParametrageBddView();

            // Affichage de la vue des paramètres
            RootController.open(parametrageBddView);
        });

        // Bouton de debug 1
        this.debug1Button.addActionListener(e -> {
            // Fermeture de toutes les vues
            RootController.closeAll();

            // Création de la vue des paramètres
            ListeCycleView debugView = new ListeCycleView();

            //Création du controller de la vue
            ListeCycleController debugControl = new ListeCycleController(debugView);

            debugView.setController(debugControl);

            // Affichage de la vue des paramètres
            RootController.open(debugView);
        });

        // Bouton de debug 2
        this.debug2Button.addActionListener(e -> {
            // Fermeture de toutes les vues
            RootController.closeAll();

            // Création de la vue des paramètres
            CreationCycleView debugView = new CreationCycleView();

            //Création du controller de la vue
            CreationCycleController debugControl = new CreationCycleController(debugView);

            debugView.setController(debugControl);

            // Affichage de la vue des paramètres
            RootController.open(debugView);
        });

        // Bouton de debug 3
        this.debug3Button.addActionListener(e -> {
            // Fermeture de toutes les vues
            RootController.closeAll();

            // Création de la vue des paramètres
            ConnectionView debugView = new ConnectionView();

            //Création du controller de la vue
            ConnectionController debugControl = new ConnectionController(debugView);

            debugView.setController(debugControl);

            // Affichage de la vue des paramètres
            RootController.open(debugView);
        });

        // Version
        this.versionLabel.setForeground(App.PRIMARY_COLOR);
        this.versionLabel.setHorizontalAlignment(SwingConstants.CENTER);


        // Ajout des éléments de l'interface
        this.add(this.debug1Button);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(this.debug2Button);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(this.debug3Button);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(this.parametresButton);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(this.versionLabel);
    }

    @Override
    public Component add(Component comp) {
        // 100% de la largeur
        comp.setMaximumSize(new Dimension(Integer.MAX_VALUE, comp.getMaximumSize().height));

        return super.add(comp);
    }
}
