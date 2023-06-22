package net.cnam.fleetview.controller.utilisateur;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.coursier.Coursier;
import net.cnam.fleetview.model.coursiertravail.CoursierTravail;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.utilisateur.Utilisateur;
import net.cnam.fleetview.model.utilisateur.UtilisateurDAO;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.accueil.AccueilView;
import net.cnam.fleetview.view.administrateur.UsersView;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class UsersController extends Controller<UsersView> {
    private final UtilisateurDAO utilisateurDAO;
    public UsersController(UsersView view) {
        super(view);

        DefaultConnector connector = new DefaultConnector();
        Connection connection = BDDConnection.getInstance(connector);
        this.utilisateurDAO = new UtilisateurDAO(connection);
    }
    public void onRefreshUsers() {
        // Suppression des courses de la vue
        view.removeAllUsers();

        // Chargement des utilisateurs dans la vue
        List<Utilisateur> utilisateurs = utilisateurDAO.getAll();
        for (Utilisateur utilisateur : utilisateurs) {

            String id = utilisateur.getIdentifiant();
            String nom = utilisateur.getNom();
            String prenom = utilisateur.getPrenom();
            int role = utilisateur.getIdRole();

            // Ajout des courses dans la vue
            view.addUser(id, nom, prenom, role);
        }
    }
}
