package net.cnam.fleetview.controller;


import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.utilisateur.Utilisateur;
import net.cnam.fleetview.model.utilisateur.UtilisateurDAO;
import net.cnam.fleetview.view.ConnectionView;

public class ConnectionController extends Controller<ConnectionView>{

    private final UtilisateurDAO utilisateurDAO;
    public ConnectionController(ConnectionView view) {
        super(view);
        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        this.utilisateurDAO = new UtilisateurDAO(BDDConnection.getInstance(connector));
    }

    public void onConnection(String iden, String pass){

        Utilisateur utilisateur = utilisateurDAO.getByIden(iden);

        String identifiant = utilisateur.getIdentifiant();
        String motDePasse = utilisateur.getMotDePasse();

        if(identifiant == null || motDePasse == null){
            view.afficherMessageErreur("Connection", "Connection Refused");
        }
        else{
            view.afficherMessageInformation("Connection", "Connection apply");
            // Définir l'utilisateur actuellement connecté
            RootController.setCurrentUser(utilisateur);
        }


    }
}
