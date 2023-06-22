package net.cnam.fleetview.view.base.menu.foot;

import net.cnam.fleetview.App;
import net.cnam.fleetview.controller.ParametrageBddController;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.controller.utilisateur.UsersController;
import net.cnam.fleetview.view.ParametrageBddView;
import net.cnam.fleetview.view.administrateur.UsersView;
import net.cnam.fleetview.view.components.button.IconLabelButton;

import javax.swing.*;
import java.awt.*;

public class LeftMenuFootPanelView extends JPanel {
    // Composants graphiques
    // Utilisateurs
    private final IconLabelButton utilisateursButton;
    // Paramètres
    private final IconLabelButton parametresButton;
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
        this.utilisateursButton = new IconLabelButton("\uF0C0", "Utilisateurs");
        this.parametresButton = new IconLabelButton("\uF013", "Paramètres");
        this.versionLabel = new JLabel("Version " + App.APP_VERSION);


        // Configuration des éléments de l'interface
        // Bouton d'utilisateurs
        this.utilisateursButton.addActionListener(e -> {
            // Création de la vue des utilisateurs
            UsersView utilisateursView = new UsersView();
            // Ajout du contrôleur
            UsersController utilisateursController = new UsersController(utilisateursView);
            utilisateursView.setController(utilisateursController);

            // Affichage de la vue des utilisateurs
            RootController.open(utilisateursView);
        });
        // Bouton de paramètres
        this.parametresButton.addActionListener(e -> {
            // Création de la vue des paramètres
            ParametrageBddView parametrageBddView = new ParametrageBddView();
            // Ajout du contrôleur
            ParametrageBddController parametrageBddController = new ParametrageBddController(parametrageBddView);
            parametrageBddView.setController(parametrageBddController);

            // Affichage de la vue des paramètres
            RootController.open(parametrageBddView);
        });

        // Version
        this.versionLabel.setForeground(App.PRIMARY_COLOR);
        this.versionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Désactivation des boutons
        this.utilisateursButton.setVisible(false);
        this.parametresButton.setVisible(false);

        // Ajout des éléments de l'interface
        refreshMenu();
    }

    @Override
    public Component add(Component comp) {
        // Ajout d'une bordure entre chaque composant
        if (this.getComponentCount() > 0 && comp instanceof IconLabelButton) {
            super.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // 100% de la largeur
        comp.setMaximumSize(new Dimension(Integer.MAX_VALUE, comp.getMaximumSize().height));

        return super.add(comp);
    }

    /**
     * Méthode permettant de rendre visible les boutons pour l'administrateur
     */
    public void showAdmin() {
        this.utilisateursButton.setVisible(true);
        this.parametresButton.setVisible(true);

        this.refreshMenu();
    }

    /**
     * Méthode permettant de rendre visible les boutons pour le gestionnaire de flotte
     */
    public void showGestionnaireFlotte() {
        this.utilisateursButton.setVisible(true);

        this.refreshMenu();
    }

    /**
     * Méthode permettant de rendre visible les boutons pour le coursier
     */
    public void showCoursier() {
        this.refreshMenu();
    }

    /**
     * Méthode permettant de rendre visible ou non le bouton de paramètres
     *
     * @param visible boolean
     */
    public void setVisibleParametres(boolean visible) {
        this.parametresButton.setVisible(visible);

        this.refreshMenu();
    }

    private void refreshMenu() {
        this.removeAll();

        if (utilisateursButton.isVisible()) {
            this.add(this.utilisateursButton);
        }
        if (parametresButton.isVisible()) {
            this.add(this.parametresButton);
        }

        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(this.versionLabel);
    }
}
