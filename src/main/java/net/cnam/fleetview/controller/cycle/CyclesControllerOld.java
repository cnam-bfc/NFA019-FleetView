package net.cnam.fleetview.controller.cycle;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.controller.ParametrageBddController;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.CustomConnectorGenerator;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.cycle.CycleDAO;
import net.cnam.fleetview.view.cycle.CycleView;
import net.cnam.fleetview.view.cycle.CyclesViewOld;

import java.sql.Connection;
import java.util.List;

public class CyclesControllerOld extends Controller<CyclesViewOld> {

    private final CycleDAO cycleDAO;

    public CyclesControllerOld(CyclesViewOld view) {

        super(view);

        // Initialisation des DAO
        CustomConnectorGenerator dbGenerator = new CustomConnectorGenerator(ParametrageBddController.getDatabase());
        Connection connection = BDDConnection.getInstance(dbGenerator.getConnector());
        this.cycleDAO = new CycleDAO(connection);
    }

    public void refreshCycle() {
        // Chargement des cycles dans la vue
        List<Cycle> cycles = cycleDAO.getAllNotArchived();
        for (Cycle cycle : cycles) {
            String id = String.valueOf(cycle.getIdCycle());
            String nom = cycle.getIdentifiant();
            String numSerie = cycle.getNumeroSerie();
            String dateAchat = cycle.getDateAcquisition().toString();
            String chargeMax = cycle.getChargeMaximale().toString();

            // Ajout des cycles dans la vue
            view.ajoutCycle(id, nom, numSerie, dateAchat, chargeMax);
        }
    }

    public void ajoutCycle() {
        CycleView creationCycle = new CycleView();
        CycleController cycleController = new CycleController(creationCycle);
        creationCycle.setController(cycleController);

        cycleController.loadEmptyCycle();

        RootController.open(creationCycle);
    }

    public void voirCycle(int id) {
        CycleView voirCycle = new CycleView();
        CycleController cycleController = new CycleController(voirCycle);
        voirCycle.setController(cycleController);

        cycleController.loadViewableCycle(id);

        RootController.open(voirCycle);
    }

    public void modifCycle(int id) {
        CycleView modificationCycle = new CycleView();
        CycleController cycleController = new CycleController(modificationCycle);
        modificationCycle.setController(cycleController);

        cycleController.loadEditableCycle(id);

        RootController.open(modificationCycle);
    }

    public void suppCycle(int id) {
        Cycle cycle = cycleDAO.getById(id);

        String idCycle = String.valueOf(cycle.getIdCycle());
        String nomCycle = cycle.getIdentifiant();

        //Confirmation
        boolean valid = view.afficherMessageConfirmation("Confirmation Suppression", "Supprimer le Cycle portant l'identifiant : " + nomCycle + "  ?");
        if (valid) {
            // Suppression de la course
            cycleDAO.archive(cycle, RootController.getCurrentUser());

            // Suppression de la course dans la vue
            view.SupprimerCycle(idCycle);
        }
    }

}
