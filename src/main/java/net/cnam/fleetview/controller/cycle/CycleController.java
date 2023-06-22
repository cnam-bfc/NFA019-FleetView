package net.cnam.fleetview.controller.cycle;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.Connector;
import net.cnam.fleetview.database.CustomConnectorGenerator;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.cycle.CycleDAO;
import net.cnam.fleetview.model.cycleetat.CycleEtat;
import net.cnam.fleetview.model.cycleetat.CycleEtatDAO;
import net.cnam.fleetview.model.cyclefournisseur.CycleFournisseur;
import net.cnam.fleetview.model.cyclefournisseur.CycleFournisseurDAO;
import net.cnam.fleetview.model.cyclemarque.CycleMarque;
import net.cnam.fleetview.model.cyclemarque.CycleMarqueDAO;
import net.cnam.fleetview.view.cycle.CycleView;

import java.sql.Connection;

public class CycleController extends Controller<CycleView> {
    private final CycleDAO cycleDAO;
    private final CycleMarqueDAO cycleMarqueDAO;
    private final CycleEtatDAO cycleEtatDAO;

    private final CycleFournisseurDAO cycleFournisseurDAO;
    private Cycle cycle;
    private CycleMarque cycleMarque;
    private CycleEtat cycleEtat;
    private CycleFournisseur cycleFournisseur;

    public CycleController(CycleView view) {
        super(view);

        // Initialisation des DAO
        CustomConnectorGenerator generator = new CustomConnectorGenerator();
        Connector connector = generator.getConnector();
        Connection connection = BDDConnection.getInstance(connector);

        this.cycleDAO = new CycleDAO(connection);
        this.cycleMarqueDAO = new CycleMarqueDAO(connection);
        this.cycleEtatDAO = new CycleEtatDAO(connection);
        this.cycleFournisseurDAO = new CycleFournisseurDAO(connection);
    }

    public void loadEmptyCycle() {
        view.editField(true);
    }

    public void loadCycle(int id) {
        this.cycle = cycleDAO.getById(id);
        this.cycleMarque = cycleMarqueDAO.getMarqueByIdCycle(this.cycle.getIdCycle());
        this.cycleEtat = cycleEtatDAO.getFirstEtatByIdCycle(this.cycle.getIdCycle());
        this.cycleFournisseur = cycleFournisseurDAO.getById(this.cycle.getIdCycleFournisseur());

        //récuperer les textFields
        String idCycle = String.valueOf(this.cycle.getIdCycle());
        String nom = this.cycle.getIdentifiant();
        String numSerie = this.cycle.getNumeroSerie();
        String chargeMax = String.valueOf(this.cycle.getChargeMaximale());
        String dateAchat = String.valueOf(this.cycle.getDateAcquisition());
        String miseEnService = String.valueOf(this.cycleEtat.getDateDebut()); // Premier état
        String marque = this.cycleMarque.getNom();
        String fournisseur = this.cycleFournisseur.getNom();

        // renvoie
        this.view.fill(idCycle, nom, numSerie, chargeMax, dateAchat, miseEnService, marque, fournisseur);
        this.view.editField(false);
    }

    public void loadViewableCycle(int id) {
        loadCycle(id);
        view.editField(false);
    }

    public void loadEditableCycle(int id) {

        ///récuperer les textFields
        loadCycle(id);

        // renvoie
        view.editField(true);
    }
}
