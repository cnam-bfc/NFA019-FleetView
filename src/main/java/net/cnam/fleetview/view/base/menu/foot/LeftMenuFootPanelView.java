package net.cnam.fleetview.view.base.menu.foot;

import net.cnam.fleetview.App;
import net.cnam.fleetview.view.ParametrageBddView;
import net.cnam.fleetview.view.components.button.IconLabelButton;

import javax.swing.*;
import java.awt.*;

public class LeftMenuFootPanelView extends JPanel {
    // Composants graphiques
    // Paramètres
    private final IconLabelButton parametresButton;
    // Version
    private final JLabel versionLabel;

    public LeftMenuFootPanelView() {
        super();

        // Layout
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        // Bordure
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.parametresButton = new IconLabelButton("\uF013", "Paramètres");
        this.versionLabel = new JLabel("Version " + App.APP_VERSION);


        // Configuration des éléments de l'interface
        // Bouton de paramètres
        this.parametresButton.addActionListener(e -> {
            // Création de la vue des paramètres
            ParametrageBddView parametrageBddView = new ParametrageBddView();

            // Affichage de la vue des paramètres
            App.INSTANCE.getPanel().getMainPanel().setContentPanelView(parametrageBddView);
        });

        // Version
        this.versionLabel.setForeground(App.PRIMARY_COLOR);
        this.versionLabel.setHorizontalAlignment(SwingConstants.CENTER);


        // Ajout des éléments de l'interface
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
