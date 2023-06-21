package net.cnam.fleetview.controller.cycles;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.cycle.CycleDAO;
import net.cnam.fleetview.model.cyclefournisseur.CycleFournisseur;
import net.cnam.fleetview.model.cyclefournisseur.CycleFournisseurDAO;
import net.cnam.fleetview.view.cycle.CycleView;

public class CycleController extends Controller<CycleView> {
    private final CycleDAO cycleDAO;
    private final CycleFournisseurDAO cycleFournisseurDAO;
    private Cycle cycle;
    public CycleController(CycleView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        this.cycleDAO = new CycleDAO(BDDConnection.getInstance(connector));
        this.cycleFournisseurDAO = new CycleFournisseurDAO(BDDConnection.getInstance(connector));
    }

    public void loademptyCycle(){
        view.editField(true);
    }

    public void loadVoirCycle(int id){
        this.cycle = cycleDAO.getById(id);
        this.cycleFourn = cycleFournisseurDAO.getById(id);

        //récuperer les textFields
        String idCycle = cycle.getIdCycle(id);
        String nom = cycle.getIdentifiant();
        String numSerie = cycle.getNumeroSerie(id);
        String chargeMax = cycle.getChargeMaximale().toString();
        String dateAchat = cycle.getDateAcquisition().toString();
        String miseEnService = cycle.;
        String marque = cycle.get;

        // renvoie
        view.envoie(/* mettre les données a renvoyer */);//méthode a refaire
        view.editField(false);
    }

    public void loadModifCycle(int id){
        this.cycle = cycleDAO.getById(id);

        ///récuperer les textFields

        // renvoie
        view.envoie(/* mettre les données a renvoyer */);//méthode a refaire
        view.editField(true);
    }
}
