package net.cnam.fleetview.controller;

import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.cycle.CycleDAO;
import net.cnam.fleetview.view.cycle.ListeCycleView;

import java.util.List;

public class ListeCycleController extends Controller<ListeCycleView> {

    private final CycleDAO cycleDAO;
    public ListeCycleController(ListeCycleView view) {

        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        this.cycleDAO = new CycleDAO(BDDConnection.getInstance(connector));


        //* Chargement des courses dans la vue
       /* List<Cycle> cycles = CycleDAO.getAllNotArchived();
        for (Cycle cycle : cycles) {
            String id = String.valueOf(cycle.getIdCycle());
            String nom = cycle.getIdentifiant();
            String NR = cycle.getNumeroSerie();
            String DateMS = cycle.getDateAcquisition().toString();

            // Ajout des courses dans la vue
            view.addCycle(id, nom, NR, DateMS);*/
    }
}
