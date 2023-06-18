package net.cnam.fleetview.model.droits;

import net.cnam.fleetview.model.DAO;
import net.cnam.fleetview.model.TypeHistorique;
import net.cnam.fleetview.model.historiquedata.HistoriqueData;
import net.cnam.fleetview.model.utilisateur.Utilisateur;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe DAO pour les droits
 * <p>
 * Permet de créer des objets Droits
 * Concerne la table : fleetview_droits
 */
public class DroitsDAO extends DAO<Droits> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public DroitsDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une Droits
     *
     * @param obj  Un objet Droits à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(Droits obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdDroits() != 0) {
            logger.error("L'objet Droits a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_droits (nom) VALUES (?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                int id = statement.getGeneratedKeys().getInt(1);

                // On met à jour l'objet pour lui attribuer l'id récupéré
                obj.setIdDroits(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le Droits", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du Droits, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'un Droits
     *
     * @param obj  Un objet Droits à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(Droits obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdDroits() == 0) {
            logger.error("L'objet Droits n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_droits WHERE id_droits = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdDroits());

            // Récupération de l'objet avant suppression
            Droits objAvantSuppression = this.getById(obj.getIdDroits());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le Droits", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du Droits, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement du Droits
     *
     * @param obj  Un objet Droits à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(Droits obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdDroits() == 0) {
            logger.error("L'objet Droits n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_droits SET nom = ? WHERE id_droits = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());

            // Récupération de l'objet avant modification
            Droits objAvantModification = this.getById(obj.getIdDroits());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le Droits", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour de le Droits, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des Droits
     *
     * @return Une List d'objets Droits, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<Droits> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_droits";

        // Résultat de la requête
        List<Droits> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets Droits correspondants
            while (resultSet.next()) {
                // Création d'un objet Droits
                Droits droits = new Droits();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(droits, resultSet);

                // On ajoute l'objet au résultat final
                result.add(droits);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les Droits", ex);

            // Si une erreur s'est produite, on renvoie la liste vide
            result = null;
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode de récupération d'un enregistrement d'un Droits par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet Droits correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public Droits getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_droits WHERE id_droits = ?";

        // Résultat de la requête
        Droits result = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, id);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On vérifie que le résultat n'est pas vide
            if (resultSet.next()) {
                // Création d'un objet Droits
                result = new Droits();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer une Droits", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet Droits avec les valeurs d'un enregistrement de la table fleetview_droits
     *
     * @param droits    L'objet Droits à remplir
     * @param resultSet Le résultat de la requête de sélection
     */
    protected void fillObject(Droits droits, ResultSet resultSet) {
        try {
            // Remplissage de l'objet Droits
            droits.setIdDroits(resultSet.getInt("id_droits"));
            droits.setNom(resultSet.getString("nom"));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet Droits", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, Droits before, Droits after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdDroits() : after != null ? after.getIdDroits() : -1;

        if (before != null && after != null && before.getIdDroits() != after.getIdDroits()) {
            logger.error("Impossible de créer l'historique, les deux objets Droits ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets Droits sont null");
        }

        // Construction des changements
        HistoriqueData nom = this.checkChanges("nom", before != null ? before.getNom() : null, after != null ? after.getNom() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_droits", id, nom);
    }
}
