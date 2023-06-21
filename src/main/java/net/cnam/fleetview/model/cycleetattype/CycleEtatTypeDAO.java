package net.cnam.fleetview.model.cycleetattype;

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
 * Classe DAO pour les CycleEtatType
 * <p>
 * Permets de créer des objets CycleEtatType
 * Concerne la table : fleetview_cycle_etat_type
 */
public class CycleEtatTypeDAO extends DAO<CycleEtatType> implements Archivable<CycleEtatType> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public CycleEtatTypeDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une CycleEtatType
     *
     * @param obj  Un objet CycleEtatType à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(CycleEtatType obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdCycleEtatType() != null) {
            logger.error("L'objet CycleEtatType a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_cycle_etat_type (nom, utilisable, date_archive) VALUES (?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());
            statement.setObject(2, obj.isUtilisable());
            statement.setObject(3, obj.getDateArchive());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    // On attribue l'id à l'objet
                    obj.setIdCycleEtatType(generatedKeys.getInt(1));
                } else {
                    logger.error("Échec de la création du CycleEtatType, aucun ID auto-généré retourné.");
                }
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le Cycle Etat Type", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du CycleEtatType, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'une CycleEtatType
     *
     * @param obj  Un objet CycleEtatType à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(CycleEtatType obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleEtatType() == null) {
            logger.error("L'objet CycleEtatType n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_cycle_etat_type WHERE id_cycle_etat_type = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getIdCycleEtatType());

            // Récupération de l'objet avant suppression
            CycleEtatType objAvantSuppression = this.getById(obj.getIdCycleEtatType());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le CycleEtatType", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du CycleEtatType, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement d'un CycleEtatType
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(CycleEtatType obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleEtatType() == null) {
            logger.error("L'objet CycleEtatType n'a pas d'ID");
            return false;
        }

        // Si la date d'archive n'est pas renseignée, on la met à jour
        if (obj.getDateArchive() == null) {
            obj.setDateArchive(LocalDateTime.now());
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle_etat_type SET date_archive = ? WHERE id_cycle_etat_type = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setObject(2, obj.getIdCycleEtatType());

            // Récupération de l'objet avant mise à jour
            CycleEtatType objAvantMAJ = this.getById(obj.getIdCycleEtatType());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver le CycleEtatType", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage du CycleEtatType, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement d'un CycleEtatType
     *
     * @param obj  Un objet CycleEtatType à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(CycleEtatType obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleEtatType() == null) {
            logger.error("L'objet CycleEtatType n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle_etat_type SET nom = ?, utilisable = ?, date_archive = ? WHERE id_cycle_etat_type = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());
            statement.setObject(2, obj.isUtilisable());
            statement.setObject(3, obj.getDateArchive());
            statement.setObject(4, obj.getIdCycleEtatType());

            // Récupération de l'objet avant modification
            CycleEtatType objAvantModification = this.getById(obj.getIdCycleEtatType());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le CycleEtatType", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du CycleEtatType, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des CycleEtatType
     *
     * @return Une List d'objets CycleEtatType, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<CycleEtatType> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle_etat_type";

        // Résultat de la requête
        List<CycleEtatType> result = new LinkedList<>();
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
                CycleEtatType cycleEtatType = new CycleEtatType();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(cycleEtatType, resultSet);

                // On ajoute l'objet au résultat final
                result.add(cycleEtatType);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les CycleEtatType", ex);

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
     * Méthode de récupération d'un enregistrement d'un CycleEtatType par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet CycleEtatType correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public CycleEtatType getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle_etat_type WHERE id_cycle_etat_type = ?";

        // Résultat de la requête
        CycleEtatType result = null;
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
                result = new CycleEtatType();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un CycleEtatType", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet CycleEtatType avec les valeurs d'un enregistrement de la table fleetview_cycle_etat_type
     *
     * @param cycleEtatType L'objet CycleEtatType à remplir
     * @param resultSet     Le résultat de la requête de sélection
     */
    protected void fillObject(CycleEtatType cycleEtatType, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            cycleEtatType.setIdCycleEtatType(resultSet.getInt("id_cycle_etat_type"));
            cycleEtatType.setNom(resultSet.getString("nom"));
            cycleEtatType.setUtilisable(resultSet.getObject("utilisable", Boolean.class));
            cycleEtatType.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet CycleEtatType", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, CycleEtatType before, CycleEtatType after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdCycleEtatType() : after != null ? after.getIdCycleEtatType() : -1;

        if (before != null && after != null && before.getIdCycleEtatType() != after.getIdCycleEtatType()) {
            logger.error("Impossible de créer l'historique, les deux objets CycleEtatType ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets CycleEtatType sont null");
        }

        // Construction des changements
        HistoriqueData nom = this.checkChanges("nom", before != null ? before.getNom() : null, after != null ? after.getNom() : null);
        HistoriqueData utilisable = this.checkChanges("utilisable", before != null ? before.isUtilisable() : null, after != null ? after.isUtilisable() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_cycle_etat_type", id, nom, utilisable, dateArchive);
    }
}
