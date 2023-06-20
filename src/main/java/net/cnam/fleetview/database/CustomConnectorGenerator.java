package net.cnam.fleetview.database;

import net.cnam.fleetview.controller.ParametrageBddController;
import net.cnam.fleetview.model.basededonnees.BaseDeDonnees;

import javax.swing.*;

public class CustomConnectorGenerator {

    private final Connector connector;
    private final BaseDeDonnees db;


    public class CustomDBConnector extends Connector {

        /**
         * @param url
         * @param user
         * @param pwd
         * @param nom
         */
        public CustomDBConnector(String url, String user, String pwd, String nom) {
            super(url,user, pwd, nom);

            // Chargement du driver
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Erreur de chargement du driver : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                System.err.println("Erreur de chargement du driver : " + e.getMessage());
            }
        }
    }


    /**
     * @param db
     * Génère un Connecteur avec la base passé en paramettre
     * (utilisé pour testé la connexion a une nouvelle bdd)
     */
   public CustomConnectorGenerator(BaseDeDonnees db) {
        this.db = db;

       String url = "jdbc:mysql://"+this.db.getAdresseIP()+":"+this.db.getPort()+"/"+this.db.getNomBase();
        String user = this.db.getIdentifiant();
        String pwd = this.db.getMotDePasse();
        String nom = this.db.getNomBase();
        this.connector = new CustomDBConnector(url,user,pwd,nom);
    }

    /**
     * Génère un Connecteur basé sur le paramétrage de l'application
     */
    public CustomConnectorGenerator() {
        this.db = ParametrageBddController.getDatabase();

        String url = "jdbc:mysql://"+this.db.getAdresseIP()+":"+this.db.getPort()+"/"+this.db.getNomBase();
        String user = this.db.getIdentifiant();
        String pwd = this.db.getMotDePasse();
        String nom = this.db.getNomBase();
        this.connector = new CustomDBConnector(url,user,pwd,nom);
    }

    /**
     * Récupère le connecteur
     * @return Connector
     */
    public Connector getConnector() {
        return connector;
    }

}
