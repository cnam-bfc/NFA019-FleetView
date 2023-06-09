package net.cnam.fleetview.model.historique;

import net.cnam.fleetview.model.DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe HistoriqueDAO
 *
 * Cette classe permet de créer des objets d'accès à la base de données pour les objets Historique.
 * Fais la jonction entre les objets Historique et la base de données.
 * table concernée : fleetview_historique
 */
public class HistoriqueDAO extends DAO<Historique> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public HistoriqueDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'Historique
     *
     * @param obj un objet Historique à écrire dans la base
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(Historique obj) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdHistorique() != 0) {
            logger.error("L'objet Historique a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_historique (table_tuple, identifiant_tuple, date_changement_tuple, id_historique_type, id_utilisateur) VALUES (?, ?, ?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getTableTuple());
            statement.setString(2, obj.getIdentifiantTuple());
            statement.setObject(3, obj.getDateChangementTuple());
            statement.setInt(4, obj.getIdHistoriqueType());
            statement.setInt(5, obj.getIdUtilisateur());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                int id = statement.getGeneratedKeys().getInt(1);

                // On met à jour l'objet pour lui attribuer l'id récupéré
                obj.setIdHistorique(id);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer l'Historique'", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création de l'Historique, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'Historique
     *
     * @param obj un objet Historique à supprimer dans la base
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(Historique obj) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdHistorique() == 0) {
            logger.error("L'objet Historique n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_historique WHERE id_historique = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdHistorique());

            // On exécute la requête
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer l'Historique", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression de l'Historique, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement d'Historique
     *
     * @param obj un objet Historique à mettre à jour dans la base
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(Historique obj) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdHistorique() == 0) {
            logger.error("L'objet Historique n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_historique SET table_tuple = ?, identifiant_tuple = ?, date_changement_tuple = ?, id_historique_type = ?, id_utilisateur = ? WHERE id_historique = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getTableTuple());
            statement.setString(2, obj.getIdentifiantTuple());
            statement.setObject(3, obj.getDateChangementTuple());
            statement.setInt(4, obj.getIdHistoriqueType());
            statement.setInt(5, obj.getIdUtilisateur());
            statement.setInt(6, obj.getIdHistorique());

            // On exécute la requête
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour l'Historique", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour de l'Historique, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements d'Historique
     *
     * @return une List d'objets Historique, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<Historique> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_historique";

        // Résultat de la requête
        List<Historique> result = new LinkedList<>();
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
                Historique historique = new Historique();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(historique, resultSet);

                // On ajoute l'objet au résultat final
                result.add(historique);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les Historique", ex);

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
     * Méthode de récupération d'un enregistrement d'Historique par son identifiant.
     *
     * @param id l'identificateur à rechercher
     * @return un objet Historique correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public Historique getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_historique WHERE id_historique = ?";

        // Résultat de la requête
        Historique result = null;
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
                result = new Historique();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer l'Historique", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet Historique avec les valeurs d'un enregistrement de la table fleetview_historique
     *
     * @param historique l'objet Historique à remplir
     * @param resultSet  le résultat de la requête de sélection
     */
    protected void fillObject(Historique historique, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            historique.setIdHistorique(resultSet.getInt("id_historique"));
            historique.setTableTuple(resultSet.getString("table_tuple"));
            historique.setIdentifiantTuple(resultSet.getString("identifiant_tuple"));
            historique.setDateChangementTuple(resultSet.getObject("date_changement_tuple", LocalDateTime.class));
            historique.setIdHistoriqueType(resultSet.getInt("id_historique_type"));
            historique.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet Historique", ex);
        }
    }
}
