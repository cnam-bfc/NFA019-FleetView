package net.cnam.fleetview.model.cyclemarque;

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
 * Classe d'accès aux données des CycleMarque
 * <p>
 * Concerne la table : fleetview_cycle_marque
 */
public class CycleMarqueDAO extends DAO<CycleMarque> implements Archivable<CycleMarque> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public CycleMarqueDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'un CycleMarque
     *
     * @param obj  Un objet CycleMarque à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(CycleMarque obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdCycleMarque() != null) {
            logger.error("L'objet CycleMarque a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_cycle_marque (nom, date_archive) VALUES (?, ?)";

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
                obj.setIdCycleMarque(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le CycleMarque", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du CycleMarque, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'un CycleMarque
     *
     * @param obj  Un objet CycleMarque à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(CycleMarque obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleMarque() == null) {
            logger.error("L'objet CycleMarque n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_cycle_marque WHERE id_cycle_marque = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getIdCycleMarque());

            // Récupération de l'objet avant suppression
            CycleMarque objAvantSuppression = this.getById(obj.getIdCycleMarque());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le CycleMarque", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du CycleMarque, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement d'un CycleMarque
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(CycleMarque obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleMarque() == null) {
            logger.error("L'objet CycleMarque n'a pas d'ID");
            return false;
        }

        // Si la date d'archive n'est pas renseignée, on la met à jour
        if (obj.getDateArchive() == null) {
            obj.setDateArchive(LocalDateTime.now());
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle_marque SET date_archive = ? WHERE id_cycle_marque = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setObject(2, obj.getIdCycleMarque());

            // Récupération de l'objet avant mise à jour
            CycleMarque objAvantMAJ = this.getById(obj.getIdCycleMarque());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver le CycleMarque", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage du CycleMarque, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement d'un CycleMarque
     *
     * @param obj  Un objet CycleMarque à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(CycleMarque obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleMarque() == null) {
            logger.error("L'objet CycleMarque n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle_marque SET nom = ?, date_archive = ? WHERE id_cycle_marque = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());
            statement.setObject(2, obj.getDateArchive());
            statement.setObject(3, obj.getIdCycleMarque());

            // Récupération de l'objet avant modification
            CycleMarque objAvantModification = this.getById(obj.getIdCycleMarque());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le CycleMarque", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour d'un CycleMarque, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des CycleMarque
     *
     * @return Une List d'objets CycleMarque, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<CycleMarque> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle_marque";

        // Résultat de la requête
        List<CycleMarque> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets CycleMarque correspondants
            while (resultSet.next()) {
                // Création d'un objet CycleFournisseur
                CycleMarque cycleMarque = new CycleMarque();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(cycleMarque, resultSet);

                // On ajoute l'objet au résultat final
                result.add(cycleMarque);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les CycleMarque", ex);

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
     * Méthode de récupération d'un enregistrement d'un CycleMarque par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet CycleMarque correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public CycleMarque getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle_marque WHERE id_cycle_marque = ?";

        // Résultat de la requête
        CycleMarque result = null;
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
                result = new CycleMarque();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un CycleMarque", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet CycleMarque avec les valeurs d'un enregistrement de la table fleetview_cycle_marque
     *
     * @param cycleMarque L'objet CycleMarque à remplir
     * @param resultSet   Le résultat de la requête de sélection
     */
    protected void fillObject(CycleMarque cycleMarque, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            cycleMarque.setIdCycleMarque(resultSet.getInt("id_cycle_marque"));
            cycleMarque.setNom(resultSet.getString("nom"));
            cycleMarque.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet CycleMarque", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, CycleMarque before, CycleMarque after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdCycleMarque() : after != null ? after.getIdCycleMarque() : -1;

        if (before != null && after != null && before.getIdCycleMarque() != after.getIdCycleMarque()) {
            logger.error("Impossible de créer l'historique, les deux objets CycleMarque ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets CycleMarque sont null");
        }

        // Construction des changements
        HistoriqueData nom = this.checkChanges("nom", before != null ? before.getNom() : null, after != null ? after.getNom() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_cycle_marque", id, nom, dateArchive);
    }
}
