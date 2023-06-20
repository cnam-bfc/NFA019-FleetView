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

    public void onConnection(){

        String identi = view.getIdent();
        String pass = view.getPass();

        Utilisateur utilisateur = utilisateurDAO.getByIden(identi);

        String identifiant = utilisateur.getIdentifiant();
        String motDePasse = utilisateur.getMotDePasse();

        if(identifiant == "null" || motDePasse == "null"){

        }
        else{
            view.afficherMessage("c'est ok");
        }


    }
}
