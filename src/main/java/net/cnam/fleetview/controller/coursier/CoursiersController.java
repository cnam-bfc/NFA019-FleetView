package net.cnam.fleetview.controller.coursier;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.controller.CourseController;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.coursierutilisateur.CoursierUtilisateur;
import net.cnam.fleetview.model.coursierutilisateur.CoursierUtilisateurDAO;
import net.cnam.fleetview.view.course.edit.CourseView;
import net.cnam.fleetview.view.coursier.list.CoursiersView;
import net.cnam.fleetview.view.coursier.show.CoursierView;

import java.util.List;

public class CoursiersController extends Controller<CoursiersView> {
    // Modèle
    // DAO
    private final CoursierUtilisateurDAO coursierUtilisateurDAO;

    public CoursiersController(CoursiersView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        this.coursierUtilisateurDAO = new CoursierUtilisateurDAO(BDDConnection.getInstance(connector));

        // Chargement des courses dans la vue
        List<CoursierUtilisateur> coursierUtilisateurs = coursierUtilisateurDAO.getAllNotArchived();
        for (CoursierUtilisateur coursierUtilisateur : coursierUtilisateurs) {
            String idCoursier = String.valueOf(coursierUtilisateur.getIdCoursier());
            String idUtilisateur = String.valueOf(coursierUtilisateur.getIdUtilisateur());
            String matricule = coursierUtilisateur.getMatricule();
            String nom = coursierUtilisateur.getNom();
            String prenom = coursierUtilisateur.getPrenom();

            // Ajout des coursiers dans la vue
            view.fillCoursierTable(idCoursier, matricule, nom, prenom, idUtilisateur);
        }
    }

    public void onShowCoursier(String id, String matricule, String nom, String prenom) {
        // Création de la vue
        CoursierView coursierView = new CoursierView();
        // Créer le contrôleur
        CoursierController coursierController = new CoursierController(coursierView);
        coursierView.setController(coursierController);

        // Mettre la méthode loadViewableCoursier
        coursierController.loadViewableCoursier(id, matricule, nom, prenom);

        // Ouverture de la vue du cycle
        RootController.open(coursierView);
    }
}