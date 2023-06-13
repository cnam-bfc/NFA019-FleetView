package net.cnam.fleetview.model.course;

import net.cnam.fleetview.model.Archivable;
import net.cnam.fleetview.model.DAO;
import net.cnam.fleetview.model.TypeHistorique;
import net.cnam.fleetview.model.historiquedata.HistoriqueData;
import net.cnam.fleetview.model.utilisateur.Utilisateur;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe CourseDAO
 * <p>
 * Cette classe permet de créer des objets Course.
 * table concernée : fleetview_course
 */
public class CourseDAO extends DAO<Course> implements Archivable<Course> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public CourseDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une Course
     *
     * @param obj  Un objet Course à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(Course obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdCourse() != 0) {
            logger.error("L'objet Course a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_course (distance, date_course, date_debut_course, date_archive, id_coursier_travail, id_cycle) VALUES (?, ?, ?, ?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setDouble(1, obj.getDistance());
            statement.setDate(2, Date.valueOf(obj.getDateCourse()));
            statement.setObject(3, obj.getDateDebutCourse());
            statement.setObject(4, obj.getDateArchive());
            statement.setInt(5, obj.getIdCoursierTravail());
            statement.setInt(6, obj.getIdCycle());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                int id = statement.getGeneratedKeys().getInt(1);

                // On met à jour l'objet pour lui attribuer l'id récupéré
                obj.setIdCourse(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer la Course", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création de la Course, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'une Course
     *
     * @param obj  Un objet Course à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(Course obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCourse() == 0) {
            logger.error("L'objet Course n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_course WHERE id_course = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdCourse());

            // Récupération de l'objet avant suppression
            Course objAvantSuppression = this.getById(obj.getIdCourse());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer l'Course", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression de la Course, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement de la Course
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(Course obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCourse() == 0) {
            logger.error("L'objet Course n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_course SET date_archive = ? WHERE id_course = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setInt(2, obj.getIdCourse());

            // Récupération de l'objet avant mise à jour
            Course objAvantMAJ = this.getById(obj.getIdCourse());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver la Course", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage de la Course, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement de la Course
     *
     * @param obj  Un objet Course à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(Course obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCourse() == 0) {
            logger.error("L'objet Course n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_course SET distance = ?, date_course = ?, date_debut_course = ?, date_archive = ?, id_coursier_travail = ?, id_cycle = ? WHERE id_course = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setDouble(1, obj.getDistance());
            statement.setObject(2, obj.getDateCourse());
            statement.setObject(3, obj.getDateDebutCourse());
            statement.setObject(4, obj.getDateArchive());
            statement.setInt(5, obj.getIdCoursierTravail());
            statement.setInt(6, obj.getIdCycle());

            // Récupération de l'objet avant modification
            Course objAvantModification = this.getById(obj.getIdCourse());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour la Course", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour de la Course, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des Course
     *
     * @return Une List d'objets Course, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<Course> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_course";

        // Résultat de la requête
        List<Course> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets Course correspondants
            while (resultSet.next()) {
                // Création d'un objet CycleFournisseur
                Course course = new Course();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(course, resultSet);

                // On ajoute l'objet au résultat final
                result.add(course);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les Course", ex);

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
     * Méthode de récupération d'un enregistrement d'une Course par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet Course correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public Course getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_course WHERE id_course = ?";

        // Résultat de la requête
        Course result = null;
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
                result = new Course();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer une Course", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet Course avec les valeurs d'un enregistrement de la table fleetview_course
     *
     * @param course    L'objet Course à remplir
     * @param resultSet Le résultat de la requête de sélection
     */
    protected void fillObject(Course course, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            course.setIdCourse(resultSet.getInt("id_course"));
            course.setDistance(resultSet.getDouble("distance"));
            course.setDateCourse(resultSet.getObject("date_course", LocalDate.class));
            course.setDateDebutCourse(resultSet.getObject("date_debut_course", LocalDateTime.class));
            course.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
            course.setIdCoursierTravail(resultSet.getInt("id_coursier_travail"));
            course.setIdCycle(resultSet.getInt("id_cycle"));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet Course", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, Course before, Course after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdCourse() : after != null ? after.getIdCourse() : -1;

        if (before != null && after != null && before.getIdCourse() != after.getIdCourse()) {
            logger.error("Impossible de créer l'historique, les deux objets Course ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets Course sont null");
        }

        // Construction des changements
        HistoriqueData distance = this.checkChanges("distance", before != null ? before.getDistance() : null, after != null ? after.getDistance() : null);
        HistoriqueData dateCourse = this.checkChanges("date_course", before != null ? before.getDateCourse() : null, after != null ? after.getDateCourse() : null);
        HistoriqueData dateDebutCourse = this.checkChanges("date_debut_course", before != null ? before.getDateDebutCourse() : null, after != null ? after.getDateDebutCourse() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);
        HistoriqueData idCoursierTravail = this.checkChanges("id_coursier_travail", before != null ? before.getIdCoursierTravail() : null, after != null ? after.getIdCoursierTravail() : null);
        HistoriqueData idCycle = this.checkChanges("id_cycle", before != null ? before.getIdCycle() : null, after != null ? after.getIdCycle() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_course", id, distance, dateCourse, dateDebutCourse, dateArchive, idCoursierTravail, idCycle);
    }
}