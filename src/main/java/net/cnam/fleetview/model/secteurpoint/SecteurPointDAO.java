package net.cnam.fleetview.model.secteurpoint;

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
 * Classe DAO pour les SecteurPoint
 * <p>
 * Concerne la table : fleetview_secteur_point
 */
public class SecteurPointDAO extends DAO<SecteurPoint> implements Archivable<SecteurPoint> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public SecteurPointDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une SecteurPoint
     *
     * @param obj  Un objet SecteurPoint à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(SecteurPoint obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdSecteurPoint() != 0) {
            logger.error("L'objet SecteurPoint a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_secteur_point (latitude, longitude, date_archive) VALUES (?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setDouble(1, obj.getLatitude());
            statement.setDouble(2, obj.getLongitude());
            statement.setObject(3, obj.getDateArchive());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                int id = statement.getGeneratedKeys().getInt(1);

                // On met à jour l'objet pour lui attribuer l'id récupéré
                obj.setIdSecteurPoint(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le SecteurPoint", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du SecteurPoint, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'un SecteurPoint
     *
     * @param obj  Un objet SecteurPoint à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(SecteurPoint obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdSecteurPoint() == 0) {
            logger.error("L'objet SecteurPoint n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_secteur_point WHERE id_secteur_point = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdSecteurPoint());

            // Récupération de l'objet avant suppression
            SecteurPoint objAvantSuppression = this.getById(obj.getIdSecteurPoint());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le SecteurPoint", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du SecteurPoint, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement d'un SecteurPoint
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(SecteurPoint obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdSecteurPoint() == 0) {
            logger.error("L'objet SecteurPoint n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_secteur_point SET date_archive = ? WHERE id_secteur_point = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setInt(2, obj.getIdSecteurPoint());

            // Récupération de l'objet avant mise à jour
            SecteurPoint objAvantMAJ = this.getById(obj.getIdSecteurPoint());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver le SecteurPoint", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage du SecteurPoint, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement d'un SecteurPoint
     *
     * @param obj  Un objet SecteurPoint à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(SecteurPoint obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdSecteurPoint() == 0) {
            logger.error("L'objet SecteurPoint n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_secteur_point SET latitude = ?, longitude = ?, date_archive = ? WHERE id_secteur_point = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setDouble(1, obj.getLatitude());
            statement.setDouble(2, obj.getLongitude());
            statement.setObject(3, obj.getDateArchive());

            // Récupération de l'objet avant modification
            SecteurPoint objAvantModification = this.getById(obj.getIdSecteurPoint());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le SecteurPoint", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du SecteurPoint, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des SecteurPoint
     *
     * @return Une List d'objets SecteurPoint, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<SecteurPoint> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_secteur_point";

        // Résultat de la requête
        List<SecteurPoint> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets SecteurPoint correspondants
            while (resultSet.next()) {
                // Création d'un objet SecteurPoint
                SecteurPoint secteurPoint = new SecteurPoint();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(secteurPoint, resultSet);

                // On ajoute l'objet au résultat final
                result.add(secteurPoint);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les SecteurPoint", ex);

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
     * Méthode de récupération d'un enregistrement d'un SecteurPoint par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet SecteurPoint correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public SecteurPoint getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_secteur_point WHERE id_secteur_point = ?";

        // Résultat de la requête
        SecteurPoint result = null;
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
                // Création d'un objet SecteurPoint
                result = new SecteurPoint();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un SecteurPoint", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet SecteurPoint avec les valeurs d'un enregistrement de la table fleetview_secteur_point
     *
     * @param secteurPoint L'objet SecteurPoint à remplir
     * @param resultSet    Le résultat de la requête de sélection
     */
    protected void fillObject(SecteurPoint secteurPoint, ResultSet resultSet) {
        try {
            // Remplissage de l'objet SecteurPoint
            secteurPoint.setIdSecteurPoint(resultSet.getInt("id_secteur_point"));
            secteurPoint.setLatitude(resultSet.getDouble("latitude"));
            secteurPoint.setLongitude(resultSet.getDouble("longitude"));
            secteurPoint.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet SecteurPoint", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, SecteurPoint before, SecteurPoint after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdSecteurPoint() : after != null ? after.getIdSecteurPoint() : -1;

        if (before != null && after != null && before.getIdSecteurPoint() != after.getIdSecteurPoint()) {
            logger.error("Impossible de créer l'historique, les deux objets SecteurPoint ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets SecteurPoint sont null");
        }

        // Construction des changements
        HistoriqueData latitude = this.checkChanges("latitude", before != null ? before.getLatitude() : null, after != null ? after.getLatitude() : null);
        HistoriqueData longitude = this.checkChanges("longitude", before != null ? before.getLongitude() : null, after != null ? after.getLongitude() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_secteur_point", id, latitude, longitude, dateArchive);
    }
}
