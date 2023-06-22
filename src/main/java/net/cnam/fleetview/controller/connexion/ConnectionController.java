package net.cnam.fleetview.controller.connexion;


import at.favre.lib.crypto.bcrypt.BCrypt;
import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.Connector;
import net.cnam.fleetview.database.CustomConnectorGenerator;
import net.cnam.fleetview.model.utilisateur.Utilisateur;
import net.cnam.fleetview.model.utilisateur.UtilisateurDAO;
import net.cnam.fleetview.view.connexion.ConnectionView;

import java.sql.Connection;

public class ConnectionController extends Controller<ConnectionView> {

    private final UtilisateurDAO utilisateurDAO;

    public ConnectionController(ConnectionView view) {
        super(view);
        // Initialisation des DAO
        CustomConnectorGenerator generator = new CustomConnectorGenerator();
        Connector connector = generator.getConnector();
        Connection connection = BDDConnection.getInstance(connector);

        this.utilisateurDAO = new UtilisateurDAO(connection);
    }

    public void onConnection(String iden, String pass) {
        Utilisateur utilisateur = utilisateurDAO.getByIdentifiant(iden);
        if (utilisateur == null) {
            view.afficherMessageErreur("L'utilisateur n'existe pas");
        }

        String identifiant = utilisateur.getIdentifiant();
        // Algorithme de hashage du mot de passe
        BCrypt.Result result = BCrypt.verifyer().verify(pass.toCharArray(), utilisateur.getMotDePasse());
        if (result.verified == false) {
            view.afficherMessageErreur("Mot de passe incorrect");
        } else {
            // Définir l'utilisateur actuellement connecté
            RootController.setCurrentUser(utilisateur);
            // Ouvrir la vue d'accueil
            RootController.start();
        }
    }
}
