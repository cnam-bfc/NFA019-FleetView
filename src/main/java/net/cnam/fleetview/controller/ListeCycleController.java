package net.cnam.fleetview.controller;

import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.cycle.CycleDAO;
import net.cnam.fleetview.view.course.edit.CourseView;
import net.cnam.fleetview.view.cycle.CreationCycleView;
import net.cnam.fleetview.view.cycle.ListeCycleView;

import java.time.LocalDate;
import java.util.List;

public class ListeCycleController extends Controller<ListeCycleView> {

    private final CycleDAO cycleDAO;
    public ListeCycleController(ListeCycleView view) {

        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        this.cycleDAO = new CycleDAO(BDDConnection.getInstance(connector));

    }

    public void refreshCycle() {
        // Chargement des courses dans la vue
        List<Cycle> cycles = cycleDAO.getAllNotArchived();
        for (Cycle cycle : cycles) {
            /* a modif
            String id = String.valueOf(course.getIdCourse());
            String nom = course.getNom();
            String date = course.getDateCourse().toString();
            String distance = String.valueOf(course.getDistance());

            // Ajout des courses dans la vue
            view.ajoutCycle(id, nom, date, distance + " km", "Cycle", "Livreur", "status");
       */ }
    }

    public void ajoutCycle(){
        CreationCycleView creationCycle = new CreationCycleView();
        CreationCycleController cycleController = new CreationCycleController(creationCycle);
        creationCycle.setController(cycleController);

        cycleController.emptyCycle();

        RootController.open(creationCycle);
    }
    public void voirCycle(int id){
        CreationCycleView voirCycle = new CreationCycleView();
        CreationCycleController cycleController = new CreationCycleController(voirCycle);
        voirCycle.setController(cycleController);

        cycleController.loadVoirCycle(id);

        RootController.open(voirCycle);
    }

    public void modifCycle(int id){
        CreationCycleView modificationCycle = new CreationCycleView();
        CreationCycleController cycleController = new CreationCycleController(modificationCycle);
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
