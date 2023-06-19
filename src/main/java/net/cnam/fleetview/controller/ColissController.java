package net.cnam.fleetview.controller;

import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.colis.Colis;
import net.cnam.fleetview.model.colis.ColisDAO;
import net.cnam.fleetview.view.colis.edit.ColisView;
import net.cnam.fleetview.view.colis.list.ColissView;

import java.sql.Connection;
import java.util.List;

public class ColissController extends Controller<ColissView> {
    // Modèle
    // DAO
    private final ColisDAO colisDAO;

    public ColissController(ColissView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        Connection connection = BDDConnection.getInstance(connector);
        this.colisDAO = new ColisDAO(connection);
    }

    public void onRefreshColiss() {
        // Chargement des colis dans la vue
        List<Colis> coliss = colisDAO.getAllNotArchived();
        for (Colis colis : coliss) {
            String id = String.valueOf(colis.getIdColis());
            String numero = colis.getNumero();
            String poids = String.valueOf(colis.getPoids());

            // Ajout des colis dans la vue
            view.addColis(id, numero, poids + " kg", "Cycle", "Livreur", "status");
        }
    }

    public void onAjouterColis() {
        // Création de la vue
        ColisView colisView = new ColisView();
        // Créer le contrôleur
        ColisController colisController = new ColisController(colisView);
        colisView.setController(colisController);

        // On charge un colis vide
        colisController.loadEmptyColis();

        // Ouverture de la vue d'un colis
        RootController.open(colisView);
    }

    public void onVoirColis(int id) {
        // Création de la vue
        ColisView colisView = new ColisView();
        // Créer le contrôleur
        ColisController colisController = new ColisController(colisView);
        colisView.setController(colisController);

        // On charge le colis avec l'id voulu
        colisController.loadViewableColis(id);

        // Ouverture de la vue du colis
        RootController.open(colisView);
    }

    public void onEditerColis(int id) {
        // Création de la vue
        ColisView colisView = new ColisView();
        // Créer le contrôleur
        ColisController colisController = new ColisController(colisView);
        colisView.setController(colisController);

        // On charge le colis avec l'id voulu
        colisController.loadEditableColis(id);

        // Ouverture de la vue du colis
        RootController.open(colisView);
    }

    public void onSupprimerColis(int id) {
        // Récupération du colis
        Colis colis = colisDAO.getById(id);

        String idColis = String.valueOf(colis.getIdColis());

        // Demande de confirmation
        boolean confirm = view.afficherMessageConfirmation("Supprimer le colis", "Êtes-vous sûr de vouloir supprimer le colis " + idColis + " ?");

        if (confirm) {
            // Suppression de la course
            colisDAO.archive(colis, RootController.getCurrentUser());

            // Suppression de la course dans la vue
            view.removeColis(idColis);
        }
    }
}
