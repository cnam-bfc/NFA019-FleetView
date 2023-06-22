package net.cnam.fleetview.controller;

import net.cnam.fleetview.model.utilisateur.Utilisateur;
import net.cnam.fleetview.view.accueil.AccueilView;

public class AccueilController extends Controller<AccueilView> {
    public AccueilController(AccueilView view) {
        super(view);
    }

    public void choixAffichage() {
        Utilisateur user = RootController.getConnectedUser();

        // Effacer les boutons
        view.clearButtons();

        if (user.getIdRole() == 1) {
            view.afficherAdmin();
        }
        if (user.getIdRole() == 2) {
            view.afficherGestionnaireFlotte();
        }
        if (user.getIdRole() == 3) {
            view.afficherCoursier();
        }
    }
}
