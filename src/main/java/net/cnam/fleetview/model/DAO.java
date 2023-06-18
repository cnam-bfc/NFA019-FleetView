package net.cnam.fleetview.model;

import net.cnam.fleetview.model.historique.HistoriqueDAO;
import net.cnam.fleetview.model.historiquedata.HistoriqueData;
import net.cnam.fleetview.model.historiquedata.HistoriqueDataDAO;
import net.cnam.fleetview.model.historiquetype.HistoriqueTypeDAO;
import net.cnam.fleetview.model.utilisateur.Utilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 * Classe abstraite générique DAO permettant de déclarer des objets d'accès à une base de données
 *
 * @param <T> Représente la classe des objets Java à manipuler
 */
public abstract class DAO<T> {
    /**
     * Logger
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    /**
     * Historique
     */
    protected final HistoriqueManager historique;

    /**
     * Attribut connection
     */
    protected Connection connection;

    /**
     * Constructeur de la classe DAO
     *
     * @param connection permet l'initialisation du champ connection
     */
    public DAO(Connection connection) {
        this.connection = connection;
        if (!(this instanceof HistoriqueDAO) && !(this instanceof HistoriqueDataDAO) && !(this instanceof HistoriqueTypeDAO)) {
            this.historique = HistoriqueManager.getInstance(connection);
        } else {
            this.historique = null;
        }
    }

    /**
     * Méthode de création d'un enregistrement
     *
     * @param obj  Un objet à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas
     * contraire
     */
    public abstract boolean create(T obj, Utilisateur user);

    /**
     * Méthode de suppression d'un enregistrement
     *
     * @param obj  Un objet à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le
     * cas contraire
     */
    public abstract boolean delete(T obj, Utilisateur user);

    /**
     * Méthode de mise à jour d'un enregistrement
     *
     * @param obj  Un objet à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le
     * cas contraire
     */
    public abstract boolean update(T obj, Utilisateur user);

    /**
     * Retourne tous les objets de la table sous forme de liste
     *
     * @return Tous les objets de la table
     */
    public abstract List<T> getAll();

    /**
     * Méthode de recherche des informations par l'id
     *
     * @param id L'identificateur à rechercher
     * @return T Un objet de la classe T
     */
    public abstract T getById(int id);

    /**
     * Méthode de remplissage d'un objet à partir d'un ResultSet
     *
     * @param obj       T
     * @param resultSet java.sql.ResultSet
     */
    protected abstract void fillObject(T obj, ResultSet resultSet);

    /**
     * Méthode permettant de gérer l'historique des changements
     *
     * @param type   Type d'historique
     * @param user   Utilisateur originaire de la modification
     * @param before Objet avant modification
     * @param after  Objet après modification
     */
    protected abstract void handleHistorique(TypeHistorique type, Utilisateur user, T before, T after);

    /**
     * Méthode permettant de générer l'historique de changement entre 2 valeurs si elles sont différentes
     *
     * @param champName   Nom du champ
     * @param champBefore Valeur avant modification
     * @param champAfter  Valeur après modification
     * @return HistoriqueData ou null si pas de changements
     */
    protected HistoriqueData checkChanges(String champName, Object champBefore, Object champAfter) {
        // Si les 2 valeurs sont null, pas de changement
        if (champBefore == null && champAfter == null) {
            return null;
        }

        // Si les 2 valeurs sont identiques, pas de changement
        if (champBefore != null && champBefore.equals(champAfter)) {
            return null;
        }

        // Sinon changement
        HistoriqueData historiqueData = new HistoriqueData();
        historiqueData.setNomChamp(champName);
        historiqueData.setValeurChamp(champAfter.toString());

        return historiqueData;
    }

    /**
     * Méthode de fermeture d'une ressource fermable
     *
     * @param resource
     * @return boolean qui vaut true si la fermeture a réussi, false dans le cas
     */
    protected boolean closeResource(AutoCloseable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
            return true;
        } catch (Exception e) {
            logger.error("Erreur lors de la fermeture de la ressource", e);
            return false;
        }
    }
}
