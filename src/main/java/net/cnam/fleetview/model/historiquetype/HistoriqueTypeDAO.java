package net.cnam.fleetview.model.historiquetype;

import net.cnam.fleetview.model.DAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe HistoriqueTypeDAO
 *
 * Cette classe permet de créer des objets HistoriqueTypeDAO.
 * Permet de faire le lien entre les objets HistoriqueType et la base de données.
 * table concernée : fleetview_historique_type
 */
public class HistoriqueTypeDAO extends DAO<HistoriqueType> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public HistoriqueTypeDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'HistoriqueType
     *
     * @param obj un objet HistoriqueType à écrire dans la base
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(HistoriqueType obj) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdHistoriqueType() != 0) {
            logger.error("L'objet HistoriqueType a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_historique_type (nom) VALUES (?)";

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
                obj.setIdHistoriqueType(id);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer l'HistoriqueType'", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création de l'HistoriqueType, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'HistoriqueType
     *
     * @param obj un objet HistoriqueType à supprimer dans la base
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(HistoriqueType obj) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdHistoriqueType() == 0) {
            logger.error("L'objet HistoriqueType n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_historique_type WHERE id_historique_type = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdHistoriqueType());

            // On exécute la requête
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer l'HistoriqueType", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression de l'HistoriqueType, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement d'HistoriqueType
     *
     * @param obj un objet HistoriqueType à mettre à jour dans la base
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(HistoriqueType obj) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdHistoriqueType() == 0) {
            logger.error("L'objet HistoriqueType n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_historique_type SET nom = ? WHERE id_historique_type = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());
            statement.setInt(2, obj.getIdHistoriqueType());

            // On exécute la requête
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour l'HistoriqueType", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour de l'HistoriqueType, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements d'HistoriqueType
     *
     * @return une List d'objets HistoriqueType, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<HistoriqueType> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_historique_type";

        // Résultat de la requête
        List<HistoriqueType> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets CycleFournisseur correspondants
            while (resultSet.next()) {
                // Création d'un objet CycleFournisseur
                HistoriqueType historiqueType = new HistoriqueType();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(historiqueType, resultSet);

                // On ajoute l'objet au résultat final
                result.add(historiqueType);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les HistoriqueType", ex);

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
     * Méthode de récupération d'un enregistrement d'HistoriqueType par son identifiant.
     *
     * @param id l'identificateur à rechercher
     * @return un objet HistoriqueType correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public HistoriqueType getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_historique_type WHERE id = ?";

        // Résultat de la requête
        HistoriqueType result = null;
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
                // Création d'un objet Historique
                result = new HistoriqueType();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer l'HistoriqueType", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet HistoriqueType avec les valeurs d'un enregistrement de la table fleetview_historique_type
     *
     * @param historiqueType l'objet HistoriqueType à remplir
     * @param resultSet  le résultat de la requête de sélection
     */
    protected void fillObject(HistoriqueType historiqueType, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            historiqueType.setIdHistoriqueType(resultSet.getInt("id"));
            historiqueType.setNom(resultSet.getString("nom"));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet HistoriqueType", ex);
        }
    }
}
