package net.cnam.fleetview.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe basé sur le Design Pattern Singleton Permet de limiter l'instanciation
 * des objets de connection à la base. Version générique de la classe délégant
 * le paramétrage de la base de donnée à un champ connector d'un type générique
 * dérivé de la classe abstraite connector
 *
 * @param <T> type générique dérivé de la classe abstraite Connector
 */
public class BDDConnection<T extends Connector> {

    /**
     * Objet contenant les informations de connexion à la base
     */
    private final T connector;

    /**
     * Objet Connexion (champ d'instance : le seul)
     */
    private Connection connect;

    /**
     * Champ de classe destiné à recevoir l'instance unique de la classe
     * Connection BDD
     */
    private static BDDConnection laBDDConnection;

    /**
     * Constructeur privé de la classe ConnectionBDD
     * Permet de créer l'instance unique de la classe ConnectionBDD
     *
     * @param connector le connecteur contenant les informations de connection
     */
    private BDDConnection(T connector) {

        this.connector = connector;
        try {
            connect = DriverManager.getConnection(this.connector.getURL(), this.connector.getUSER(), this.connector.getPWD());
        } catch (SQLException ex) {
            connect = null;
        }
    }

    /**
     * Méthode d'obtention de l'instance unique de la connection.
     * Permet de créer l'instance si elle n'existe pas encore et donc de respecter le design pattern Singleton
     *
     * @return le champ connection de l'instance unique de ConnectionBDD
     */
    public static Connection getInstance(Connector connector) {
        if (laBDDConnection == null) {
            laBDDConnection = new BDDConnection(connector);
        }
        return laBDDConnection.connect;
    }

   

    /**
     * Vérifie si l'instance de la base de donnée existe pour ce connecteur.
     *
     * @param connector le connecteur contenant les informations de connection
     * @return true si l'instance existe, false sinon
     */
    public static boolean isInstanceOf(Connector connector) {

        return laBDDConnection != null && laBDDConnection.connector.equals(connector);
    }
    
    /**
     * Ferme la connexion en cours.
     *
     * @param connector1 le connecteur contenant les informations de connection
     * @return true si la connexion a été fermée, false sinon
     */
    public static boolean killInstance(Connector connector1) {

        if (isInstanceOf(connector1)) {
            laBDDConnection = null;
            return true;
        } else {
            return false;
        }
    }
}
