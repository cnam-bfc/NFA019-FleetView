package net.cnam.fleetview.controller.cycles;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.cycle.CycleDAO;
import net.cnam.fleetview.model.cycleetat.CycleEtat;
import net.cnam.fleetview.model.cycleetat.CycleEtatDAO;
import net.cnam.fleetview.model.cyclemarque.CycleMarque;
import net.cnam.fleetview.model.cyclemarque.CycleMarqueDAO;
import net.cnam.fleetview.view.cycle.CycleView;

public class CycleController extends Controller<CycleView> {
    private final CycleDAO cycleDAO;
    private final CycleMarqueDAO cycleMarqueDAO;
    private final CycleEtatDAO cycleEtatDAO;

    private Cycle cycle;
    private CycleMarque cycleMarque;
    private CycleEtat cycleEtat;
    public CycleController(CycleView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        this.cycleDAO = new CycleDAO(BDDConnection.getInstance(connector));
        this.cycleMarqueDAO = new CycleMarqueDAO(BDDConnection.getInstance(connector));
        this.cycleEtatDAO = new CycleEtatDAO(BDDConnection.getInstance(connector));
    }

    public void loademptyCycle(){
        view.editField(true);
    }

    public void loadVoirCycle(int id){
        this.cycle = cycleDAO.getById(id);
        this.cycleMarque = cycleMarqueDAO.getMarqueByIdCycle(this.cycle.getIdCycle());
        this.cycleEtat = cycleEtatDAO.getFirstEtatByIdCycle(this.cycle.getIdCycle());

        //récuperer les textFields
        String idCycle = String.valueOf(this.cycle.getIdCycle());
        String nom = this.cycle.getIdentifiant();
        String numSerie = this.cycle.getNumeroSerie();
        String chargeMax = String.valueOf(this.cycle.getChargeMaximale());
        String dateAchat = String.valueOf(this.cycle.getDateAcquisition());
        String miseEnService = String.valueOf(this.cycleEtat.getDateDebut()); // Premier état
        String marque = this.cycleMarque.getNom();; // Maruqe

        // renvoie
        this.view.envoie(idCycle, nom, numSerie, chargeMax, dateAchat, miseEnService, marque);
        this.view.editField(false);
    }

    public void loadModifCycle(int id){
        this.cycle = cycleDAO.getById(id);

        ///récuperer les textFields

        // renvoie
        view.envoie(/* mettre les données a renvoyer */);//méthode a refaire
        view.editField(true);
    }
}
