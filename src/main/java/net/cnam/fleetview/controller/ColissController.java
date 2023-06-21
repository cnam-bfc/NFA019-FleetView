package net.cnam.fleetview.controller;

import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.adresse.Adresse;
import net.cnam.fleetview.model.adresse.AdresseDAO;
import net.cnam.fleetview.model.colis.Colis;
import net.cnam.fleetview.model.colis.ColisDAO;
import net.cnam.fleetview.model.colisdestinataire.ColisDestinataire;
import net.cnam.fleetview.model.colisdestinataire.ColisDestinataireDAO;
import net.cnam.fleetview.view.colis.edit.ColisView;
import net.cnam.fleetview.view.colis.list.ColissView;

import java.sql.Connection;
import java.util.List;

public class ColissController extends Controller<ColissView> {
    // Modèle
    // DAO
    private final ColisDAO colisDAO;
    private final AdresseDAO adresseDAO;
    private final ColisDestinataireDAO colisDestinataireDAO;

    public ColissController(ColissView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        Connection connection = BDDConnection.getInstance(connector);
        this.colisDAO = new ColisDAO(connection);
        this.adresseDAO = new AdresseDAO(connection);
        this.colisDestinataireDAO = new ColisDestinataireDAO(connection);
    }

    public void onRefreshColiss() {
        // Suppression des colis de la vue
        view.removeAllColis();

        // Chargement des colis dans la vue
        List<Colis> coliss = colisDAO.getAllNotArchived();
        for (Colis colis : coliss) {
            // Récupération de l'adresse du colis
            Adresse colisAdresse = adresseDAO.getById(colis.getIdAdresse());
            // Récupération du destinataire du colis
            ColisDestinataire colisDestinataire = colisDestinataireDAO.getById(colis.getIdColisDestinataire());

            if (colisAdresse == null || colisDestinataire == null) {
                continue;
            }

            String id = String.valueOf(colis.getIdColis());
            String numero = colis.getNumero();
            String poids = String.valueOf(colis.getPoids());

            String adresse;
            if (colisAdresse.getNumeroRue() != null) {
                adresse = colisAdresse.getNumeroRue() + " " + colisAdresse.getRue() + ", " + colisAdresse.getCodePostal() + " " + colisAdresse.getCommune();
            } else {
                adresse = colisAdresse.getRue() + ", " + colisAdresse.getCodePostal() + " " + colisAdresse.getCommune();
            }

            String destinataire = colisDestinataire.getNom() + " " + colisDestinataire.getPrenom();

            // Ajout des colis dans la vue
            view.addColis(id, numero, poids + " kg", adresse, destinataire);
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
