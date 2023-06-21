package net.cnam.fleetview.model.role;

import net.cnam.fleetview.model.DAO;
import net.cnam.fleetview.model.TypeHistorique;
import net.cnam.fleetview.model.historiquedata.HistoriqueData;
import net.cnam.fleetview.model.utilisateur.Utilisateur;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe DAO pour les Role
 * <p>
 * Concerne la table : fleetview_role
 */
public class RoleDAO extends DAO<Role> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public RoleDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une Role
     *
     * @param obj  Un objet Role à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(Role obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdRole() != null) {
            logger.error("L'objet Role a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_role (nom) VALUES (?)";

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
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    // On attribue l'id à l'objet
                    obj.setIdRole(generatedKeys.getInt(1));
                } else {
                    logger.error("Échec de la création du Role en base, aucun ID auto-généré retourné.");
                }
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le Role", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du Role, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'une Role
     *
     * @param obj  Un objet Role à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(Role obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdRole() == null) {
            logger.error("L'objet Role n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_role WHERE id_role = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getIdRole());

            // Récupération de l'objet avant suppression
            Role objAvantSuppression = this.getById(obj.getIdRole());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le Role", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du Role, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement du Role
     *
     * @param obj  Un objet Role à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(Role obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdRole() == null) {
            logger.error("L'objet Role n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_role SET nom = ? WHERE id_role = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());
            statement.setObject(2, obj.getIdRole());

            // Récupération de l'objet avant modification
            Role objAvantModification = this.getById(obj.getIdRole());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le Role", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du Role, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des Role
     *
     * @return Une List d'objets Role, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<Role> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_role";

        // Résultat de la requête
        List<Role> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets Role correspondants
            while (resultSet.next()) {
                // Création d'un objet Role
                Role role = new Role();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(role, resultSet);

                // On ajoute l'objet au résultat final
                result.add(role);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les Role", ex);

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
     * Méthode de récupération d'un enregistrement d'un Role par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet Role correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public Role getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_role WHERE id_role = ?";

        // Résultat de la requête
        Role result = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, id);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On vérifie que le résultat n'est pas vide
            if (resultSet.next()) {
                // Création d'un objet Role
                result = new Role();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un Role", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet Role avec les valeurs d'un enregistrement de la table fleetview_role
     *
     * @param role      L'objet Role à remplir
     * @param resultSet Le résultat de la requête de sélection
     */
    protected void fillObject(Role role, ResultSet resultSet) {
        try {
            // Remplissage de l'objet Role
            role.setIdRole(resultSet.getInt("id_role"));
            role.setNom(resultSet.getString("nom"));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet Role", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, Role before, Role after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdRole() : after != null ? after.getIdRole() : -1;

        if (before != null && after != null && before.getIdRole() != after.getIdRole()) {
            logger.error("Impossible de créer l'historique, les deux objets Role ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets Role sont null");
        }

        // Construction des changements
        HistoriqueData nom = this.checkChanges("nom", before != null ? before.getNom() : null, after != null ? after.getNom() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_role", id, nom);
    }
}
