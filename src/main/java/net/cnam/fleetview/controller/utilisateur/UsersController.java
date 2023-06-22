package net.cnam.fleetview.controller.utilisateur;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.controller.courses.CourseController;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.utilisateur.Utilisateur;
import net.cnam.fleetview.model.utilisateur.UtilisateurDAO;
import net.cnam.fleetview.view.administrateur.CreateModifyUserView;
import net.cnam.fleetview.view.administrateur.UsersView;

import java.sql.Connection;
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

            int id = utilisateur.getIdUtilisateur();
            String identifiant = utilisateur.getIdentifiant();
            String nom = utilisateur.getNom();
            String prenom = utilisateur.getPrenom();
            int role = utilisateur.getIdRole();

            // Ajout des courses dans la vue
            view.addUser(id, identifiant, nom, prenom, role);
        }
    }

    public void onEditerUtilisateur(int id) {
        // Création de la vue
        CreateModifyUserView createModifyUserView = new CreateModifyUserView();
        // Créer le contrôleur
        CreateModifyUserController createModifyUserController = new CreateModifyUserController(createModifyUserView);
        createModifyUserView.setController(createModifyUserController);

        // On charge la course avec l'id voulu
        //courseController.loadEditableCourse(id);

        // Ouverture de la vue du cycle
        RootController.open(createModifyUserView);
    }
}
