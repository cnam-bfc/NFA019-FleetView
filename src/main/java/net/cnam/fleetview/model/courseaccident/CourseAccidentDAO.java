package net.cnam.fleetview.model.courseaccident;

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
 * Classe CourseAccidentDAO
 * <p>
 * Cette classe permet de créer des objets CourseAccident.
 * table concernée : fleetview_course_accident
 */
public class CourseAccidentDAO extends DAO<CourseAccident> implements Archivable<CourseAccident> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public CourseAccidentDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une CourseAccident
     *
     * @param obj  Un objet CourseAccident à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(CourseAccident obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdCourseAccident() != null) {
            logger.error("L'objet CourseAccident a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_course_accident (date_accident, date_archive, id_adresse, id_course) VALUES (?, ?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateAccident());
            statement.setObject(2, obj.getDateArchive());
            statement.setObject(3, obj.getIdAdresse());
            statement.setObject(4, obj.getIdCourse());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                int id = statement.getGeneratedKeys().getInt(1);

                // On met à jour l'objet pour lui attribuer l'id récupéré
                obj.setIdCourseAccident(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer la CourseAccident", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création de la CourseAccident, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'une CourseAccident
     *
     * @param obj  Un objet CourseAccident à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(CourseAccident obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCourseAccident() == null) {
            logger.error("L'objet CourseAccident n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_course_accident WHERE id_course_accident = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getIdCourseAccident());

            // Récupération de l'objet avant suppression
            CourseAccident objAvantSuppression = this.getById(obj.getIdCourseAccident());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer la CourseAccident", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression de la CourseAccident, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement de la CourseAccident
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(CourseAccident obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCourseAccident() == null) {
            logger.error("L'objet CourseAccident n'a pas d'ID");
            return false;
        }

        // Si la date d'archive n'est pas renseignée, on la met à jour
        if (obj.getDateArchive() == null) {
            obj.setDateArchive(LocalDateTime.now());
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_course_accident SET date_archive = ? WHERE id_course_accident = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setObject(2, obj.getIdCourseAccident());

            // Récupération de l'objet avant mise à jour
            CourseAccident objAvantMAJ = this.getById(obj.getIdCourseAccident());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver la CourseAccident", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage de la CourseAccident, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement de la CourseAccident
     *
     * @param obj  Un objet CourseAccident à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(CourseAccident obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCourseAccident() == null) {
            logger.error("L'objet CourseAccident n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_course_accident SET date_accident = ?, date_archive = ?, id_adresse = ?, id_course = ? WHERE id_course_accident = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateAccident());
            statement.setObject(2, obj.getDateArchive());
            statement.setObject(3, obj.getIdAdresse());
            statement.setObject(4, obj.getIdCourse());
            statement.setObject(5, obj.getIdCourseAccident());

            // Récupération de l'objet avant modification
            CourseAccident objAvantModification = this.getById(obj.getIdCourseAccident());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour la CourseAccident", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour de la CourseAccident, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des CourseAccident
     *
     * @return Une List d'objets CourseAccident, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<CourseAccident> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_course_accident";

        // Résultat de la requête
        List<CourseAccident> result = new LinkedList<>();
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
                CourseAccident courseAccident = new CourseAccident();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(courseAccident, resultSet);

                // On ajoute l'objet au résultat final
                result.add(courseAccident);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les CourseAccident", ex);

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
     * Méthode de récupération d'un enregistrement d'une CourseAccident par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet CourseAccident correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public CourseAccident getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_course_accident WHERE id_course_accident = ?";

        // Résultat de la requête
        CourseAccident result = null;
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
                // Création d'un objet CycleFournisseur
                result = new CourseAccident();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer une CourseAccident", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet CourseAccident avec les valeurs d'un enregistrement de la table fleetview_course_accident
     *
     * @param courseAccident L'objet CourseAccident à remplir
     * @param resultSet      Le résultat de la requête de sélection
     */
    protected void fillObject(CourseAccident courseAccident, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            courseAccident.setIdCourseAccident(resultSet.getInt("id_course_accident"));
            courseAccident.setIdCourse(resultSet.getObject("id_course", Integer.class));
            courseAccident.setIdAdresse(resultSet.getObject("id_adresse", Integer.class));
            courseAccident.setDateAccident(resultSet.getObject("date_accident", LocalDateTime.class));
            courseAccident.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));

        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet CourseAccident", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, CourseAccident before, CourseAccident after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdCourseAccident() : after != null ? after.getIdCourseAccident() : -1;

        if (before != null && after != null && before.getIdCourseAccident() != after.getIdCourseAccident()) {
            logger.error("Impossible de créer l'historique, les deux objets CourseAccident ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets CourseAccident sont null");
        }

        // Construction des changements
        HistoriqueData idCourse = this.checkChanges("id_course", before != null ? before.getIdCourse() : null, after != null ? after.getIdCourse() : null);
        HistoriqueData idAdresse = this.checkChanges("id_adresse", before != null ? before.getIdAdresse() : null, after != null ? after.getIdAdresse() : null);
        HistoriqueData dateAccident = this.checkChanges("date_accident", before != null ? before.getDateAccident() : null, after != null ? after.getDateAccident() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_course_accident", id, idCourse, idAdresse, dateAccident, dateArchive);
    }
}
