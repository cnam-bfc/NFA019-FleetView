package net.cnam.fleetview.controller.colis;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.controller.ParametrageBddController;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.CustomConnectorGenerator;
import net.cnam.fleetview.model.adresse.Adresse;
import net.cnam.fleetview.model.adresse.AdresseDAO;
import net.cnam.fleetview.model.colis.Colis;
import net.cnam.fleetview.model.colis.ColisDAO;
import net.cnam.fleetview.model.colisdestinataire.ColisDestinataire;
import net.cnam.fleetview.model.colisdestinataire.ColisDestinataireDAO;
import net.cnam.fleetview.view.colis.edit.ColisView;
import net.cnam.fleetview.view.colis.list.ColissView;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class ColissController extends Controller<ColissView> {
    // Modèle
    // DAO
    private final ColisDAO colisDAO;
    private final AdresseDAO adresseDAO;
    private final ColisDestinataireDAO colisDestinataireDAO;

    private ColisChooser colisChooser;

    public ColissController(ColissView view) {
        super(view);

        // Initialisation des DAO
        CustomConnectorGenerator dbGenerator = new CustomConnectorGenerator(ParametrageBddController.getDatabase());
        Connection connection = BDDConnection.getInstance(dbGenerator.getConnector());
        this.colisDAO = new ColisDAO(connection);
        this.adresseDAO = new AdresseDAO(connection);
        this.colisDestinataireDAO = new ColisDestinataireDAO(connection);
    }

    public void onRefreshColiss() {
        // Suppression des colis de la vue
        view.removeAllColis();

        List<Integer> colisBlacklist = new LinkedList<>();
        if (colisChooser != null) {
            List<Integer> blacklist = colisChooser.getBlacklist();
            if (blacklist != null) {
                colisBlacklist.addAll(blacklist);
            }
        }

        // Chargement des colis dans la vue
        List<Colis> coliss = colisDAO.getAllNotArchived();
        for (Colis colis : coliss) {
            if (colisBlacklist.contains(colis.getIdColis())) {
                continue;
            }

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

    public void bindColisChooser(ColisChooser colisChooser) {
        this.colisChooser = colisChooser;

        // Ajout de la colonne choisir dans la vue
        view.addChooseColumn();

        // Rafraichissement des colis
        onRefreshColiss();
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

    public void onChoisirColis(int id) {
        if (colisChooser != null) {
            Colis colis = colisDAO.getById(id);

            if (colis == null) {
                view.afficherMessageErreur("Erreur", "Le colis n'existe pas.");
                return;
            }

            // Fermeture de la vue
            RootController.close(view);

            // Callback du chooser
            colisChooser.chooseColis(colis);
        }
    }
}
