package net.cnam.fleetview.model.cycletype;

import net.cnam.fleetview.model.Archivable;
import net.cnam.fleetview.model.DAO;
import net.cnam.fleetview.model.TypeHistorique;
import net.cnam.fleetview.model.historiquedata.HistoriqueData;
import net.cnam.fleetview.model.utilisateur.Utilisateur;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe DAO pour les CycleType
 * <p>
 * Concerne la table : fleetview_cycle_type
 */
public class CycleTypeDAO extends DAO<CycleType> implements Archivable<CycleType> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public CycleTypeDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une CycleType
     *
     * @param obj  Un objet CycleType à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(CycleType obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdCycleType() != 0) {
            logger.error("L'objet CycleType a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_cycle_type (nom, date_archive) VALUES (?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());
            statement.setObject(2, obj.getDateArchive());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                int id = statement.getGeneratedKeys().getInt(1);

                // On met à jour l'objet pour lui attribuer l'id récupéré
                obj.setIdCycleType(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le CycleType", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du CycleType, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'une CycleType
     *
     * @param obj  Un objet CycleType à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(CycleType obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleType() == 0) {
            logger.error("L'objet CycleType n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_cycle_type WHERE id_cycle_type = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdCycleType());

            // Récupération de l'objet avant suppression
            CycleType objAvantSuppression = this.getById(obj.getIdCycleType());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le CycleType", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression de le CycleType, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement du CycleType
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(CycleType obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleType() == 0) {
            logger.error("L'objet CycleType n'a pas d'ID");
            return false;
        }

        // Si la date d'archive n'est pas renseignée, on la met à jour
        if (obj.getDateArchive() == null) {
            obj.setDateArchive(LocalDateTime.now());
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle_type SET date_archive = ? WHERE id_cycle_type = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setInt(2, obj.getIdCycleType());

            // Récupération de l'objet avant mise à jour
            CycleType objAvantMAJ = this.getById(obj.getIdCycleType());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver le CycleType", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage du CycleType, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement du CycleType
     *
     * @param obj  Un objet CycleType à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(CycleType obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleType() == 0) {
            logger.error("L'objet CycleType n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle_type SET nom = ?, date_archive = ?, WHERE id_cycle_type = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());
            statement.setObject(2, obj.getDateArchive());
            statement.setInt(3, obj.getIdCycleType());

            // Récupération de l'objet avant modification
            CycleType objAvantModification = this.getById(obj.getIdCycleType());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le CycleType", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du CycleType, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des CycleType
     *
     * @return Une List d'objets CycleType, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<CycleType> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle_type";

        // Résultat de la requête
        List<CycleType> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets CycleType correspondants
            while (resultSet.next()) {
                // Création d'un objet CycleType
                CycleType cycleType = new CycleType();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(cycleType, resultSet);

                // On ajoute l'objet au résultat final
                result.add(cycleType);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les CycleType", ex);

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
     * Méthode de récupération d'un enregistrement d'un CycleType par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet CycleType correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public CycleType getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle_type WHERE id_cycle_type = ?";

        // Résultat de la requête
        CycleType result = null;
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
                // Création d'un objet CycleType
                result = new CycleType();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un CycleType", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet CycleType avec les valeurs d'un enregistrement de la table fleetview_cycle_type
     *
     * @param cycleType L'objet CycleType à remplir
     * @param resultSet Le résultat de la requête de sélection
     */
    protected void fillObject(CycleType cycleType, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleType
            cycleType.setIdCycleType(resultSet.getInt("id_cycle_type"));
            cycleType.setNom(resultSet.getString("nom"));
            cycleType.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet CycleType", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, CycleType before, CycleType after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdCycleType() : after != null ? after.getIdCycleType() : -1;

        if (before != null && after != null && before.getIdCycleType() != after.getIdCycleType()) {
            logger.error("Impossible de créer l'historique, les deux objets CycleType ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets CycleType sont null");
        }

        // Construction des changements
        HistoriqueData nom = this.checkChanges("nom", before != null ? before.getNom() : null, after != null ? after.getNom() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_cycle_type", id, nom, dateArchive);
    }
}
