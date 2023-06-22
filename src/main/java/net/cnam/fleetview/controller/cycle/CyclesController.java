package net.cnam.fleetview.controller.cycle;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.Connector;
import net.cnam.fleetview.database.CustomConnectorGenerator;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.cycle.CycleDAO;
import net.cnam.fleetview.model.cyclecomplet.CycleComplet;
import net.cnam.fleetview.model.cyclecomplet.CycleCompletDAO;
import net.cnam.fleetview.model.utilisateur.Utilisateur;
import net.cnam.fleetview.view.cycle.CycleView;
import net.cnam.fleetview.view.cycle.CyclesView;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class CyclesController extends Controller<CyclesView> {
    // Modèle
    // DAO
    private final CycleDAO cycleDAO;
    private final CycleCompletDAO cycleCompletDAO;

    private CycleChooser cycleChooser;

    public CyclesController(CyclesView view) {
        super(view);

        // Initialisation des DAO
        CustomConnectorGenerator generator = new CustomConnectorGenerator();
        Connector connector = generator.getConnector();
        Connection connection = BDDConnection.getInstance(connector);

        this.cycleDAO = new CycleDAO(connection);
        this.cycleCompletDAO = new CycleCompletDAO(connection);

        Utilisateur connectedUser = RootController.getConnectedUser();
        if (connectedUser.getIdRole() != 2) {
            view.hideEditAndDeleteColumns();
        }
    }

    public void onRefreshCycles() {
        // Suppression des cycles de la vue
        view.removeAllCycles();

        List<Integer> cyclesBlacklist = new LinkedList<>();
        if (cycleChooser != null) {
            List<Integer> blacklist = cycleChooser.getBlacklist();
            if (blacklist != null) {
                cyclesBlacklist.addAll(blacklist);
            }
        }

        // Chargement des cycles dans la vue
        List<CycleComplet> cycleComplets = cycleCompletDAO.getAllNotArchived();
        for (CycleComplet cycleComplet : cycleComplets) {
            if (cyclesBlacklist.contains(cycleComplet.getIdCycle())) {
                continue;
            }

            // Récupération des informations de la cycle
            String id = String.valueOf(cycleComplet.getIdCycle());

            String identifiant = "N/A";
            if (cycleComplet.getIdentifiant() != null) {
                identifiant = cycleComplet.getIdentifiant();
            }

            String chargeMax = "N/A";
            if (cycleComplet.getChargeMaximale() != 0) {
                chargeMax = String.valueOf(cycleComplet.getChargeMaximale());
            }

            String dateAchat = "N/A";
            if (cycleComplet.getDateAcquisition() != null) {
                dateAchat = cycleComplet.getDateAcquisition().toString();
            }

            String revision = "N/A";
            if (cycleComplet.getDateDerniereRevision() != null) {
                revision = cycleComplet.getDateDerniereRevision().toString();
            }

            String etat = "N/A";
            if (cycleComplet.getEtat() != null) {
                etat = cycleComplet.getEtat();
            }


            // Ajout des cycles dans la vue
            view.addCycle(id, identifiant, chargeMax, dateAchat, revision, etat);
        }
    }

    public void bindCycleChooser(CycleChooser cycleChooser) {
        this.cycleChooser = cycleChooser;

        // Ajout de la colonne choisir dans la vue
        view.addChooseColumn();

        // Rafraichissement des courses
        onRefreshCycles();
    }

    public void onAjouterCycle() {
        // Création de la vue
        CycleView cycleView = new CycleView();
        // Créer le contrôleur
        CycleController cycleController = new CycleController(cycleView);
        cycleView.setController(cycleController);

        // On charge un cycle vide
        cycleController.loadEmptyCycle();

        // Ouverture de la vue du cycle
        RootController.open(cycleView);
    }

    public void onVoirCycle(int id) {
        // Création de la vue
        CycleView cycleView = new CycleView();
        // Créer le contrôleur
        CycleController cycleController = new CycleController(cycleView);
        cycleView.setController(cycleController);

        // On charge la cycle avec l'id voulu
        cycleController.loadViewableCycle(id);

        // Ouverture de la vue du cycle
        RootController.open(cycleView);
    }

    public void onEditerCycle(int id) {
        // Création de la vue
        CycleView cycleView = new CycleView();
        // Créer le contrôleur
        CycleController cycleController = new CycleController(cycleView);
        cycleView.setController(cycleController);

        // On charge la cycle avec l'id voulu
        cycleController.loadEditableCycle(id);

        // Ouverture de la vue du cycle
        RootController.open(cycleView);
    }

    public void onSupprimerCycle(int id) {
        // Récupération de la cycle
        Cycle cycle = cycleDAO.getById(id);

        String idCycle = String.valueOf(cycle.getIdCycle());
        String identifiantCycle = cycle.getIdentifiant();

        // Demande de confirmation
        boolean confirm = view.afficherMessageConfirmation("Supprimer le cycle", "Êtes-vous sûr de vouloir supprimer la cycle " + idCycle + " - " + identifiantCycle + " ?");

        if (confirm) {
            // Suppression de la cycle
            cycleDAO.archive(cycle, RootController.getConnectedUser());

            // Suppression de la cycle dans la vue
            view.removeCycle(idCycle);
        }
    }

    public void onChoisirCycle(int id) {
        if (cycleChooser != null) {
            Cycle cycle = cycleDAO.getById(id);

            if (cycle == null) {
                view.afficherMessageErreur("Erreur", "Le cycle n'existe pas.");
                return;
            }

            // Fermeture de la vue
            RootController.close(view);

            // Callback du chooser
            cycleChooser.chooseCycle(cycle);
        }
    }
}
