package net.cnam.fleetview.model.coursiertravail;

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
 * Classe CoursierTravailDAO
 * <p>
 * Cette classe permet de créer des objets CoursierTravail.
 * table concernée : fleetview_coursier_travail
 */
public class CoursierTravailDAO extends DAO<CoursierTravail> implements Archivable<CoursierTravail> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public CoursierTravailDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une CoursierTravail
     *
     * @param obj  Un objet CoursierTravail à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(CoursierTravail obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdCoursierTravail() != 0) {
            logger.error("L'objet CoursierTravail a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_coursier_travail (date_saisie, date_archive, id_coursier) VALUES (?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateSaisie());
            statement.setObject(2, obj.getDateArchive());
            statement.setInt(3, obj.getIdCoursier());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                int id = statement.getGeneratedKeys().getInt(1);

                // On met à jour l'objet pour lui attribuer l'id récupéré
                obj.setIdCoursierTravail(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le CoursierTravail", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du CoursierTravail, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'une CoursierTravail
     *
     * @param obj  Un objet CoursierTravail à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(CoursierTravail obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCoursierTravail() == 0) {
            logger.error("L'objet CoursierTravail n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_coursier_travail WHERE id_coursier_travail = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdCoursierTravail());

            // Récupération de l'objet avant suppression
            CoursierTravail objAvantSuppression = this.getById(obj.getIdCoursierTravail());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le CoursierTravail", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du CoursierTravail, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement du CoursierTravail
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(CoursierTravail obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCoursierTravail() == 0) {
            logger.error("L'objet CoursierTravail n'a pas d'ID");
            return false;
        }

        // Si la date d'archive n'est pas renseignée, on la met à jour
        if (obj.getDateArchive() == null) {
            obj.setDateArchive(LocalDateTime.now());
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_coursier_travail SET date_archive = ? WHERE id_coursier_travail = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setInt(2, obj.getIdCoursierTravail());

            // Récupération de l'objet avant mise à jour
            CoursierTravail objAvantMAJ = this.getById(obj.getIdCoursierTravail());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver le CoursierTravail", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage du CoursierTravail, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement du CoursierTravail
     *
     * @param obj  Un objet CoursierTravail à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(CoursierTravail obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCoursierTravail() == 0) {
            logger.error("L'objet CoursierTravail n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_coursier_travail SET date_saisie = ?, date_archive = ?, id_utilisateur = ? WHERE id_coursier_travail = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateSaisie());
            statement.setObject(2, obj.getDateArchive());
            statement.setInt(3, obj.getIdCoursier());
            statement.setInt(4, obj.getIdCoursierTravail());

            // Récupération de l'objet avant modification
            CoursierTravail objAvantModification = this.getById(obj.getIdCoursierTravail());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le CoursierTravail", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du CoursierTravail, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des CoursierTravail
     *
     * @return Une List d'objets CoursierTravail, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<CoursierTravail> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_coursier_travail";

        // Résultat de la requête
        List<CoursierTravail> result = new LinkedList<>();
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
                CoursierTravail coursierTravail = new CoursierTravail();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(coursierTravail, resultSet);

                // On ajoute l'objet au résultat final
                result.add(coursierTravail);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les CoursierTravail", ex);

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
     * Méthode de récupération d'un enregistrement d'un CoursierTravail par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet CoursierTravail correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public CoursierTravail getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_coursier_travail WHERE id_coursier_travail = ?";

        // Résultat de la requête
        CoursierTravail result = null;
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
                // Création d'un objet CycleFournisseur
                result = new CoursierTravail();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un CoursierTravail", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet CoursierTravail avec les valeurs d'un enregistrement de la table fleetview_coursier_travail
     *
     * @param coursierTravail L'objet CoursierTravail à remplir
     * @param resultSet       Le résultat de la requête de sélection
     */
    protected void fillObject(CoursierTravail coursierTravail, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            coursierTravail.setIdCoursierTravail(resultSet.getInt("id_coursier_travail"));
            coursierTravail.setDateSaisie(resultSet.getObject("date_saisie", LocalDateTime.class));
            coursierTravail.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
            coursierTravail.setIdCoursier(resultSet.getInt("id_coursier"));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet CoursierTravail", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, CoursierTravail before, CoursierTravail after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdCoursierTravail() : after != null ? after.getIdCoursierTravail() : -1;

        if (before != null && after != null && before.getIdCoursierTravail() != after.getIdCoursierTravail()) {
            logger.error("Impossible de créer l'historique, les deux objets CoursierTravail ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets CoursierTravail sont null");
        }

        // Construction des changements
        HistoriqueData dateSaisie = this.checkChanges("date_saisie", before != null ? before.getDateSaisie() : null, after != null ? after.getDateSaisie() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);
        HistoriqueData idCoursier = this.checkChanges("id_coursier", before != null ? before.getIdCoursier() : null, after != null ? after.getIdCoursier() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_coursier_travail", id, dateSaisie, dateArchive, idCoursier);
    }
}
