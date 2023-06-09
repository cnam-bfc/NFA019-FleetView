package net.cnam.fleetview.model.historiquedata;

import net.cnam.fleetview.model.DAO;
import net.cnam.fleetview.model.TypeHistorique;
import net.cnam.fleetview.model.utilisateur.Utilisateur;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe HistoriqueDataDAO
 */
public class HistoriqueDataDAO extends DAO<HistoriqueData> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public HistoriqueDataDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'HistoriqueData
     *
     * @param obj  Un objet HistoriqueData à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(HistoriqueData obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdHistoriqueData() != 0) {
            logger.error("L'objet HistoriqueData a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_historique_data (nom_champ, valeur_champ, id_historique) VALUES (?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNomChamp());
            statement.setString(2, obj.getValeurChamp());
            statement.setInt(3, obj.getIdHistorique());

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
            logger.error("Impossible d'insérer l'HistoriqueData'", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création de l'HistoriqueData, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'HistoriqueData
     *
     * @param obj  Un objet HistoriqueData à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(HistoriqueData obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdHistoriqueData() == 0) {
            logger.error("L'objet Historique n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_historique_data WHERE id_historique_data = ?";

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
            logger.error("Impossible de supprimer l'HistoriqueData", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression de l'HistoriqueData, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement d'HistoriqueData
     *
     * @param obj  Un objet HistoriqueData à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(HistoriqueData obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdHistorique() == 0) {
            logger.error("L'objet HistoriqueData n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_historique_data SET nom_champ = ?, valeur_champ = ?, id_historique = ? WHERE id_historique_data = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNomChamp());
            statement.setString(2, obj.getValeurChamp());
            statement.setInt(3, obj.getIdHistorique());
            statement.setInt(4, obj.getIdHistoriqueData());

            // On exécute la requête
            result = statement.executeUpdate();
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour l'HistoriqueData", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour de l'HistoriqueData, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements d'HistoriqueData
     *
     * @return Une List d'objets HistoriqueData, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<HistoriqueData> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_historique_data";

        // Résultat de la requête
        List<HistoriqueData> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets HistoriqueData correspondants
            while (resultSet.next()) {
                // Création d'un objet CycleFournisseur
                HistoriqueData historiqueData = new HistoriqueData();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(historiqueData, resultSet);

                // On ajoute l'objet au résultat final
                result.add(historiqueData);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les HistoriqueData", ex);

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
     * Méthode de récupération d'un enregistrement d'HistoriqueData par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet HistoriqueData correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public HistoriqueData getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_historique_data WHERE id_historique_data = ?";

        // Résultat de la requête
        HistoriqueData result = null;
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
                result = new HistoriqueData();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer l'HistoriqueData", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet HistoriqueData avec les valeurs d'un enregistrement de la table fleetview_historique_data
     *
     * @param historiqueData L'objet HistoriqueData à remplir
     * @param resultSet      Le résultat de la requête de sélection
     */
    protected void fillObject(HistoriqueData historiqueData, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            historiqueData.setIdHistoriqueData(resultSet.getInt("id_historique_data"));
            historiqueData.setNomChamp(resultSet.getString("nom_champ"));
            historiqueData.setValeurChamp(resultSet.getString("valeur_champ"));
            historiqueData.setIdHistorique(resultSet.getInt("id_historique"));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet historiqueData", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, HistoriqueData before, HistoriqueData after) {
        // Ne rien faire puisque l'historique n'est pas géré pour cette table
    }
}
