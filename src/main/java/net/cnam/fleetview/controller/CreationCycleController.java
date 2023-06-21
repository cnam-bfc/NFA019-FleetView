package net.cnam.fleetview.controller;

import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.cycle.CycleDAO;
import net.cnam.fleetview.view.cycle.CreationCycleView;

public class CreationCycleController extends Controller<CreationCycleView> {
    private final CycleDAO cycleDAO;
    private Cycle cycle;
    public CreationCycleController(CreationCycleView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        this.cycleDAO = new CycleDAO(BDDConnection.getInstance(connector));
    }

    public void emptyCycle(){
        view.editField(true);
    }

    public void loadVoirCycle(int id){
        this.cycle = cycleDAO.getById(id);

        ///récuperer les textFields

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
