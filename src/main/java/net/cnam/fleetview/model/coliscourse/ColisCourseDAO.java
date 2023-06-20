package net.cnam.fleetview.model.coliscourse;

import net.cnam.fleetview.model.Archivable;
import net.cnam.fleetview.model.DAO;
import net.cnam.fleetview.model.TypeHistorique;
import net.cnam.fleetview.model.historiquedata.HistoriqueData;
import net.cnam.fleetview.model.utilisateur.Utilisateur;
import net.cnam.fleetview.utils.Periode;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe ColisCourseDAO
 * <p>
 * Cette classe permet de créer des objets ColisCourse.
 * table concernée : fleetview_colis_course
 */
public class ColisCourseDAO extends DAO<ColisCourse> implements Archivable<ColisCourse> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public ColisCourseDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une ColisCourse
     *
     * @param obj  Un objet ColisCourse à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(ColisCourse obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdColisCourse() != null) {
            logger.error("L'objet ColisCourse a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_colis_course (ordre, date_livraison, date_archive, id_colis, id_course) VALUES (?, ?, ?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getOrdre());
            statement.setObject(2, obj.getDateLivraison());
            statement.setObject(3, obj.getDateArchive());
            statement.setObject(4, obj.getIdColis());
            statement.setObject(5, obj.getIdCourse());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                int id = statement.getGeneratedKeys().getInt(1);

                // On met à jour l'objet pour lui attribuer l'id récupéré
                obj.setIdColisCourse(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer l'objet ColisCourse", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création de l'objet ColisCourse, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'une ColisCourse
     *
     * @param obj  Un objet ColisCourse à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(ColisCourse obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdColisCourse() == null) {
            logger.error("L'objet ColisCourse n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_colis_course WHERE id_colis_course = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getIdColisCourse());

            // Récupération de l'objet avant suppression
            ColisCourse objAvantSuppression = this.getById(obj.getIdColisCourse());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer l'objet ColisCourse", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression de l'objet ColisCourse, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement de l'un ColisCourse
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(ColisCourse obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdColisCourse() == null) {
            logger.error("L'objet ColisCourse n'a pas d'ID");
            return false;
        }

        // Si la date d'archive n'est pas renseignée, on la met à jour
        if (obj.getDateArchive() == null) {
            obj.setDateArchive(LocalDateTime.now());
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_colis_course SET date_archive = ? WHERE id_colis_course = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setObject(2, obj.getIdColisCourse());

            // Récupération de l'objet avant mise à jour
            ColisCourse objAvantMAJ = this.getById(obj.getIdColisCourse());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver le ColisCourse", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage du ColisCourse, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement de ColisCourse
     *
     * @param obj  Un objet ColisCourse à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(ColisCourse obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdColisCourse() == null) {
            logger.error("L'objet ColisCourse n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_colis_course SET ordre = ?, date_livraison = ?, date_archive = ?, id_colis = ?, id_course = ? WHERE id_colis_course = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getOrdre());
            statement.setObject(2, obj.getDateLivraison());
            statement.setObject(3, obj.getDateArchive());
            statement.setObject(4, obj.getIdColis());
            statement.setObject(5, obj.getIdCourse());
            statement.setObject(6, obj.getIdColisCourse());

            // Récupération de l'objet avant modification
            ColisCourse objAvantModification = this.getById(obj.getIdColisCourse());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le ColisCourse", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour de l'objet ColisCourse', aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des ColisCourse
     *
     * @return Une List d'objets ColisCourse, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<ColisCourse> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_colis_course";

        // Résultat de la requête
        List<ColisCourse> result = new LinkedList<>();
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
                ColisCourse colisCourse = new ColisCourse();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(colisCourse, resultSet);

                // On ajoute l'objet au résultat final
                result.add(colisCourse);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les ColisCourse", ex);

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
     * Méthode de récupération d'un enregistrement d'un ColisCourse par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet ColisCourse correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public ColisCourse getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_colis_course WHERE id_colis_course = ?";

        // Résultat de la requête
        ColisCourse result = null;
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
                result = new ColisCourse();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un ColisCourse", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet ColisCourse avec les valeurs d'un enregistrement de la table fleetview_colis_course
     *
     * @param colisCourse L'objet ColisCourse à remplir
     * @param resultSet   Le résultat de la requête de sélection
     */
    protected void fillObject(ColisCourse colisCourse, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            colisCourse.setIdColisCourse(resultSet.getInt("id_colis_course"));
            colisCourse.setOrdre(resultSet.getObject("ordre", Integer.class));
            colisCourse.setDateLivraison(resultSet.getObject("date_livraison", LocalDateTime.class));
            colisCourse.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
            colisCourse.setIdColis(resultSet.getObject("id_colis", Integer.class));
            colisCourse.setIdCourse(resultSet.getObject("id_course", Integer.class));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet ColisCourse", ex);
        }
    }

    /**
     * Méthode qui retoune le nombre de colis d'un coursier de periode jusqu'à aujourd'hui
     *
     * @param idCoursier idCoursier
     * @param periode    periode
     */
    public int getNbColisCoursier(int idCoursier, Periode periode) {
        String queryCompletion = "";
        switch (periode) {
            case JOUR:
                // Requête
                queryCompletion = "AND fc.date_archive = CURDATE()";
                break;
            case SEMAINE:
            case MOIS:
            case ANNEE:
                queryCompletion = "AND fc.date_archive >= DATE_SUB(CURDATE(), INTERVAL 1 " + periode.getEnglishName() + ")" +
                        " AND fc.date_archive <= CURDATE()";
        }
        String query = "SELECT IFNULL(COUNT(*),0) AS nbCourse FROM fleetview_colis_course AS fcc LEFT JOIN fleetview_course AS fc ON fcc.id_course = fc.id_course LEFT JOIN fleetview_coursier_travail AS fct ON fc.id_coursier_travail = fct.id_coursier_travail WHERE fct.id_coursier = ? AND fcc.date_livraison IS NOT NULL AND fc.date_course IS NOT NULL AND fc.date_debut_course IS NOT NULL " + queryCompletion + ";";

        // Résultat de la requête
        int result = -1;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, idCoursier);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On vérifie que le résultat n'est pas vide
            if (resultSet.next()) {
                result = resultSet.getInt("nbCourse");
            }
        } catch (SQLException ex) {
            // On log l'erreur
            result = -999;
            logger.error("Impossible de récupérer le nombre de course d'un coursier", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, ColisCourse before, ColisCourse after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdColisCourse() : after != null ? after.getIdColisCourse() : -1;

        if (before != null && after != null && before.getIdColisCourse() != after.getIdColisCourse()) {
            logger.error("Impossible de créer l'historique, les deux objets ColisCourse ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets ColisCourse sont null");
        }

        // Construction des changements
        HistoriqueData ordre = this.checkChanges("ordre", before != null ? before.getOrdre() : null, after != null ? after.getOrdre() : null);
        HistoriqueData dateLivraison = this.checkChanges("date_livraison", before != null ? before.getDateLivraison() : null, after != null ? after.getDateLivraison() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);
        HistoriqueData idColis = this.checkChanges("id_colis", before != null ? before.getIdColis() : null, after != null ? after.getIdColis() : null);
        HistoriqueData idCourse = this.checkChanges("id_course", before != null ? before.getIdCourse() : null, after != null ? after.getIdCourse() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_colis_course", id, ordre, dateLivraison, dateArchive, idColis, idCourse);
    }
}
