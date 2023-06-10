package net.cnam.fleetview.model.droitsrole;

import net.cnam.fleetview.model.DAO;
import net.cnam.fleetview.model.TypeHistorique;
import net.cnam.fleetview.model.utilisateur.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe DAO pour les DroitsRole
 * <p>
 * Permet de créer des objets DroitsRole
 * Concerne la table : fleetview_droits_role
 */
public class DroitsRoleDAO extends DAO<DroitsRole> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public DroitsRoleDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une DroitsRole
     *
     * @param obj  Un objet DroitsRole à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(DroitsRole obj, Utilisateur user) {
        // On vérifie que l'objet a les id d'ID
        if (obj.getIdRole() == 0 || obj.getIdDroits() == 0) {
            logger.error("L'objet DroitsRole manque d'ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_droits_role (id_role, id_droits, autorise) VALUES (?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdRole());
            statement.setInt(2, obj.getIdDroits());
            statement.setBoolean(3, obj.isAutorise());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer un DroitsRole", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création d'un DroitsRole, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'un DroitsRole
     *
     * @param obj  Un objet DroitsRole à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(DroitsRole obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdRole() == 0 || obj.getIdDroits() == 0) {
            logger.error("L'objet DroitsRole n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_droits_role WHERE id_role = ? AND id_droits = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdRole());
            statement.setInt(2, obj.getIdDroits());

            // Récupération de l'objet avant suppression
            DroitsRole objAvantSuppression = this.getByIds(obj.getIdRole(), obj.getIdDroits());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le DroitsRole", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du DroitsRole, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement du DroitsRole
     *
     * @param obj  Un objet DroitsRole à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(DroitsRole obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdRole() == 0 || obj.getIdDroits() == 0) {
            logger.error("L'objet DroitsRole n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_droits_role SET autorise WHERE id_role = ? AND id_droits = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setBoolean(1, obj.isAutorise());
            statement.setInt(2, obj.getIdRole());
            statement.setInt(3, obj.getIdDroits());


            // Récupération de l'objet avant modification
            DroitsRole objAvantModification = this.getByIds(obj.getIdRole(), obj.getIdDroits());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le DroitsRole", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du DroitsRole, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des DroitsRole
     *
     * @return Une List d'objets DroitsRole, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<DroitsRole> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_droits_role";

        // Résultat de la requête
        List<DroitsRole> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets DroitsRole correspondants
            while (resultSet.next()) {
                // Création d'un objet DroitsRole
                DroitsRole droitsRole = new DroitsRole();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(droitsRole, resultSet);

                // On ajoute l'objet au résultat final
                result.add(droitsRole);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les DroitsRole", ex);

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
     * La table nécessitant une clé composée, cette méthode n'est pas implémentée
     * Exception levée à chaque appel
     */
    @Override
    public DroitsRole getById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Méthode de récupération d'un enregistrement des DroitsRole
     *
     * @param idRole   L'ID du rôle
     * @param idDroits L'ID des droits
     * @return Un objet DroitsRole, null en cas d'erreur ou si l'enregistrement n'existe pas
     */
    public DroitsRole getByIds(int idRole, int idDroits) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_droits_role WHERE id_role = ? AND id_droits = ?";

        // Résultat de la requête
        DroitsRole result = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, idRole);
            statement.setInt(2, idDroits);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On vérifie que le résultat n'est pas vide
            if (resultSet.next()) {
                // Création d'un objet DroitsRole
                result = new DroitsRole();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer le DroitsRole", ex);

            // Si une erreur s'est produite, on renvoie null
            result = null;
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet DroitsRole avec les valeurs d'un enregistrement de la table fleetview_droits_role
     *
     * @param droitsRole L'objet DroitsRole à remplir
     * @param resultSet  Le résultat de la requête de sélection
     */
    protected void fillObject(DroitsRole droitsRole, ResultSet resultSet) {
        try {
            // Remplissage de l'objet DroitsRole
            droitsRole.setIdRole(resultSet.getInt("id_role"));
            droitsRole.setIdDroits(resultSet.getInt("id_droits"));
            droitsRole.setAutorise(resultSet.getBoolean("autorise"));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet DroitsRole", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, DroitsRole before, DroitsRole after) {
        // Corps à faire, pour le moment lève une exception
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
