package net.cnam.fleetview.controller.cycles;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.cycle.CycleDAO;
import net.cnam.fleetview.view.cycle.CycleView;
import net.cnam.fleetview.view.cycle.CyclesView;

import java.util.List;

public class CyclesController extends Controller<CyclesView> {

    private final CycleDAO cycleDAO;
    public CyclesController(CyclesView view) {

        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        this.cycleDAO = new CycleDAO(BDDConnection.getInstance(connector));

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

    public void ajoutCycle(){
        CycleView creationCycle = new CycleView();
        CycleController cycleController = new CycleController(creationCycle);
        creationCycle.setController(cycleController);

        cycleController.loademptyCycle();

        RootController.open(creationCycle);
    }
    public void voirCycle(int id){
        CycleView voirCycle = new CycleView();
        CycleController cycleController = new CycleController(voirCycle);
        voirCycle.setController(cycleController);

        cycleController.loadVoirCycle(id);

        RootController.open(voirCycle);
    }

    public void modifCycle(int id){
        CycleView modificationCycle = new CycleView();
        CycleController cycleController = new CycleController(modificationCycle);
        modificationCycle.setController(cycleController);

        cycleController.loadModifCycle(id);

        RootController.open(modificationCycle);
    }

    public void suppCycle(int id){
        Cycle cycle = cycleDAO.getById(id);

        String idCycle = String.valueOf(cycle.getIdCycle());
        String nomCycle = cycle.getIdentifiant();

        //Confirmation
        boolean valid = view.afficherMessageConfirmation("Confirmation Suppression", "Supprimer le Cycle portant l'identifiant : "+ nomCycle+"  ?");
        if (valid) {
            // Suppression de la course
            cycleDAO.archive(cycle, RootController.getCurrentUser());

            // Suppression de la course dans la vue
            view.SupprimerCycle(idCycle);
        }
    }

}
