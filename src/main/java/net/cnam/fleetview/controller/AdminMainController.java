package net.cnam.fleetview.controller;

import net.cnam.fleetview.model.utilisateur.Utilisateur;
import net.cnam.fleetview.view.accueil.AdminMainView;

public class AdminMainController extends Controller<AdminMainView> {
    public AdminMainController(AdminMainView view) {
        super(view);
    }

    public void choixAffichage() {
        Utilisateur user = RootController.getCurrentUser();

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
