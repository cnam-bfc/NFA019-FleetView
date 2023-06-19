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
     * Méthode appelée lorsque la vue est affichée (ou réaffichée)
     */
    public void onDisplayed() {

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

    /**
     * Méthode permettant d'afficher un message à l'utilisateur
     *
     * @param message le message à afficher
     */
    public void afficherMessageInformation(String message) {
        this.afficherMessageInformation("Information", message);
    }

    /**
     * Méthode permettant d'afficher un message à l'utilisateur
     *
     * @param titre   le titre du message
     * @param message le message à afficher
     */
    public void afficherMessageInformation(String titre, String message) {
        JOptionPane.showMessageDialog(this, message, titre, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Méthode permettant d'afficher un message d'erreur à l'utilisateur
     *
     * @param message le message à afficher
     */
    public void afficherMessageErreur(String message) {
        this.afficherMessageErreur("Erreur", message);
    }

    /**
     * Méthode permettant d'afficher un message d'erreur à l'utilisateur
     *
     * @param titre   le titre du message
     * @param message le message à afficher
     */
    public void afficherMessageErreur(String titre, String message) {
        JOptionPane.showMessageDialog(this, message, titre, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Méthode permettant d'afficher un message de confirmation à l'utilisateur (Avec boutons Oui/Non)
     *
     * @param message le message à afficher
     * @return true si l'utilisateur a cliqué sur Oui, false sinon
     */
    public boolean afficherMessageConfirmation(String message) {
        return this.afficherMessageConfirmation("Confirmation", message);
    }

    /**
     * Méthode permettant d'afficher un message de confirmation à l'utilisateur (Avec boutons Oui/Non)
     *
     * @param titre   le titre du message
     * @param message le message à afficher
     * @return true si l'utilisateur a cliqué sur Oui, false sinon
     */
    public boolean afficherMessageConfirmation(String titre, String message) {
        int reponse = JOptionPane.showConfirmDialog(this, message, titre, JOptionPane.YES_NO_OPTION);
        return reponse == JOptionPane.YES_OPTION;
    }

    /**
     * Méthode permettant de demander une valeur à l'utilisateur
     *
     * @param message le message à afficher
     * @return la valeur saisie par l'utilisateur
     */
    public String demanderValeur(String message) {
        return this.demanderValeur("Valeur", message);
    }

    /**
     * Méthode permettant de demander une valeur à l'utilisateur
     *
     * @param titre   le titre du message
     * @param message le message à afficher
     * @return la valeur saisie par l'utilisateur
     */
    public String demanderValeur(String titre, String message) {
        return JOptionPane.showInputDialog(this, message, titre, JOptionPane.QUESTION_MESSAGE);
    }
}
