package net.cnam.fleetview.model;

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
    }

    /**
     * Méthode de création d'un enregistrement
     *
     * @param obj un objet Auteur à écrire dans la base
     * @return boolean qui vaut true si la création a réussi, false dans le cas
     * contraire
     */
    public abstract boolean create(T obj);

    /**
     * Méthode de suppression d'un enregistrement
     *
     * @param obj un objet Auteur à supprimer dans la base
     * @return boolean qui vaut true si la suppression a réussi, false dans le
     * cas contraire
     */
    public abstract boolean delete(T obj);

    /**
     * Méthode de mise à jour d'un enregistrement
     *
     * @param obj un objet Auteur à mettre à jour dans la base
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le
     * cas contraire
     */
    public abstract boolean update(T obj);

    /**
     * Retourne tous les objets de la table sous forme de liste
     *
     * @return
     */
    public abstract List<T> getAll();

    /**
     * Méthode de recherche des informations par l'id
     *
     * @param id l'identificateur à rechercher
     * @return T
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
