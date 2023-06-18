package net.cnam.fleetview.view;

import net.cnam.fleetview.controller.Controller;

import javax.swing.*;

public abstract class View<C extends Controller> extends JPanel {
    protected C controller;

    /**
     * Méthode permettant de définir le controlleur de la vue
     *
     * @param controller le controlleur de la vue
     */
    public void setController(C controller) {
        this.controller = controller;
    }

    /**
     * Méthode appelée lorsqu'une nouvelle vue veut s'ouvrir
     *
     * @return true si la vue peut s'ouvrir, false sinon
     */
    public boolean canOpenNewView() {
        return true;
    }

    /**
     * Méthode appelée lorsque cette vue veut se fermer
     *
     * @return true si la vue peut se fermer, false sinon
     */
    public boolean canCloseCurrentView() {
        return true;
    }

    /**
     * Méthode appelée lorsque l'on veut switcher vers une autre vue
     *
     * @return true si la vue peut switcher, false sinon
     */
    public boolean canSwitchView() {
        return true;
    }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
