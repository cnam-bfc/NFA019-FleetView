package net.cnam.fleetview.model.cyclerevision;

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
 * Classe d'accès aux données des cycles de révision
 * <p>
 * concerne la table fleetview_cycle_revision
 */
public class CycleRevisionDAO extends DAO<CycleRevision> implements Archivable<CycleRevision> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public CycleRevisionDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'un CycleRevision
     *
     * @param obj  Un objet CycleRevision à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(CycleRevision obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdCycleRevision() != 0) {
            logger.error("L'objet CycleRevision a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_cycle_revision (date_revision, commentaire, date_archive, id_cycle) VALUES (?, ?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateRevision());
            statement.setString(2, obj.getCommentaire());
            statement.setObject(3, obj.getDateArchive());
            statement.setInt(4, obj.getIdCycle());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                int id = statement.getGeneratedKeys().getInt(1);

                // On met à jour l'objet pour lui attribuer l'id récupéré
                obj.setIdCycleRevision(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le CycleRevision", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du CycleRevision, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'une CycleRevision
     *
     * @param obj  Un objet CycleRevision à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(CycleRevision obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleRevision() == 0) {
            logger.error("L'objet CycleRevision n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_cycle_revision WHERE id_cycle_revision = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdCycleRevision());

            // Récupération de l'objet avant suppression
            CycleRevision objAvantSuppression = this.getById(obj.getIdCycleRevision());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le CycleRevision", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du CycleRevision, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement du CycleRevision
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(CycleRevision obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleRevision() == 0) {
            logger.error("L'objet CycleRevision n'a pas d'ID");
            return false;
        }

        // Si la date d'archive n'est pas renseignée, on la met à jour
        if (obj.getDateArchive() == null) {
            obj.setDateArchive(LocalDateTime.now());
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle_revision SET date_archive = ? WHERE id_cycle_revision = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setInt(2, obj.getIdCycleRevision());

            // Récupération de l'objet avant mise à jour
            CycleRevision objAvantMAJ = this.getById(obj.getIdCycleRevision());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver le CycleRevision", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage du CycleRevision, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement du CycleRevision
     *
     * @param obj  Un objet CycleRevision à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(CycleRevision obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleRevision() == 0) {
            logger.error("L'objet CycleRevision n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycke_revision SET date_revision = ?, commentaire = ?, date_archive = ?, id_cycle = ? WHERE id_cycle_revision = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateRevision());
            statement.setString(2, obj.getCommentaire());
            statement.setObject(3, obj.getDateArchive());
            statement.setInt(4, obj.getIdCycle());
            statement.setInt(5, obj.getIdCycleRevision());

            // Récupération de l'objet avant modification
            CycleRevision objAvantModification = this.getById(obj.getIdCycleRevision());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le CycleRevision", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du CycleRevision, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des CycleRevision
     *
     * @return Une List d'objets CycleRevision, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<CycleRevision> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycke_revision";

        // Résultat de la requête
        List<CycleRevision> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets CycleRevision correspondants
            while (resultSet.next()) {
                // Création d'un objet CycleRevision
                CycleRevision cycleRevision = new CycleRevision();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(cycleRevision, resultSet);

                // On ajoute l'objet au résultat final
                result.add(cycleRevision);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les CycleRevision", ex);

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
     * Méthode de récupération d'un enregistrement d'une CycleRevision par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet CycleRevision correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public CycleRevision getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle_revision WHERE id_cycle_revision = ?";

        // Résultat de la requête
        CycleRevision result = null;
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
                // Création d'un objet CycleRevision
                result = new CycleRevision();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un CycleRevision", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet CycleRevision avec les valeurs d'un enregistrement de la table fleetview_cycle_revision
     *
     * @param cycleRevision L'objet CycleRevision à remplir
     * @param resultSet     Le résultat de la requête de sélection
     */
    protected void fillObject(CycleRevision cycleRevision, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleRevision
            cycleRevision.setIdCycleRevision(resultSet.getInt("id_cycle_revision"));
            cycleRevision.setDateRevision(resultSet.getObject("date_revision", LocalDateTime.class));
            cycleRevision.setCommentaire(resultSet.getString("commentaire"));
            cycleRevision.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
            cycleRevision.setIdCycle(resultSet.getInt("id_cycle"));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet CycleRevision", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, CycleRevision before, CycleRevision after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdCycleRevision() : after != null ? after.getIdCycleRevision() : -1;

        if (before != null && after != null && before.getIdCycleRevision() != after.getIdCycleRevision()) {
            logger.error("Impossible de créer l'historique, les deux objets CycleRevision ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets CycleRevision sont null");
        }

        // Construction des changements
        HistoriqueData dateRevision = this.checkChanges("date_revision", before != null ? before.getDateRevision() : null, after != null ? after.getDateRevision() : null);
        HistoriqueData commentaire = this.checkChanges("commentaire", before != null ? before.getCommentaire() : null, after != null ? after.getCommentaire() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);
        HistoriqueData idCycle = this.checkChanges("id_cycle", before != null ? before.getIdCycle() : null, after != null ? after.getIdCycle() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_cycle_revision", id, dateRevision, commentaire, dateArchive, idCycle);
    }
}
