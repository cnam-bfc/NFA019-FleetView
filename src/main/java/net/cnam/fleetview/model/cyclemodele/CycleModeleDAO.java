package net.cnam.fleetview.model.cyclemodele;

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
 * Classe DAO pour les CycleModele
 * <p>
 * Concerne la table : fleetview_cycle_modele
 */
public class CycleModeleDAO extends DAO<CycleModele> implements Archivable<CycleModele> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public CycleModeleDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une CycleModele
     *
     * @param obj  Un objet CycleModele à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(CycleModele obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdCycleModele() != 0) {
            logger.error("L'objet CycleModele a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_cycle_modele (nom, date_archive, id_cycle_marque) VALUES (?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());
            statement.setObject(2, obj.getDateArchive());
            statement.setInt(3, obj.getIdCycleMarque());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                int id = statement.getGeneratedKeys().getInt(1);

                // On met à jour l'objet pour lui attribuer l'id récupéré
                obj.setIdCycleModele(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le CycleModele", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du CycleModele, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'une CycleModele
     *
     * @param obj  Un objet CycleModele à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(CycleModele obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleModele() == 0) {
            logger.error("L'objet CycleModele n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_cycle_modele WHERE id_cycle_modele = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdCycleModele());

            // Récupération de l'objet avant suppression
            CycleModele objAvantSuppression = this.getById(obj.getIdCycleModele());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le CycleModele", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du CycleModele, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement du CycleModele
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(CycleModele obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleModele() == 0) {
            logger.error("L'objet CycleModele n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle_modele SET date_archive = ? WHERE id_cycle_modele = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setInt(2, obj.getIdCycleModele());

            // Récupération de l'objet avant mise à jour
            CycleModele objAvantMAJ = this.getById(obj.getIdCycleModele());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver le CycleModele", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage du CycleModele, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement du CycleModele
     *
     * @param obj  Un objet CycleModele à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(CycleModele obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleModele() == 0) {
            logger.error("L'objet CycleModele n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle_modele SET nom = ?, date_archive = ?, id_cycle_marque = ? WHERE id_cycle_modele = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());
            statement.setObject(2, obj.getDateArchive());
            statement.setInt(3, obj.getIdCycleMarque());


            // Récupération de l'objet avant modification
            CycleModele objAvantModification = this.getById(obj.getIdCycleModele());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le CycleModele", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour de le CycleModele, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des CycleModele
     *
     * @return Une List d'objets CycleModele, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<CycleModele> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle_modele";

        // Résultat de la requête
        List<CycleModele> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets CycleModele correspondants
            while (resultSet.next()) {
                // Création d'un objet CycleModele
                CycleModele cycleModele = new CycleModele();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(cycleModele, resultSet);

                // On ajoute l'objet au résultat final
                result.add(cycleModele);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les CycleModele", ex);

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
     * Méthode de récupération d'un enregistrement d'un CycleModele par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet CycleModele correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public CycleModele getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle_modele WHERE id_cycle_modele = ?";

        // Résultat de la requête
        CycleModele result = null;
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
                // Création d'un objet CycleModele
                result = new CycleModele();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un CycleModele", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet CycleModele avec les valeurs d'un enregistrement de la table fleetview_cycle_modele
     *
     * @param cycleModele L'objet CycleModele à remplir
     * @param resultSet   Le résultat de la requête de sélection
     */
    protected void fillObject(CycleModele cycleModele, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleModele
            cycleModele.setIdCycleModele(resultSet.getInt("id_cycle_modele"));
            cycleModele.setNom(resultSet.getString("nom"));
            cycleModele.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
            cycleModele.setIdCycleMarque(resultSet.getInt("id_cycle_marque"));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet CycleModele", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, CycleModele before, CycleModele after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdCycleModele() : after != null ? after.getIdCycleModele() : -1;

        if (before != null && after != null && before.getIdCycleModele() != after.getIdCycleModele()) {
            logger.error("Impossible de créer l'historique, les deux objets CycleModele ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets CycleModele sont null");
        }

        // Construction des changements
        HistoriqueData nom = this.checkChanges("nom", before != null ? before.getNom() : null, after != null ? after.getNom() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);
        HistoriqueData idCycleMarque = this.checkChanges("id_cycle_marque", before != null ? before.getIdCycleMarque() : null, after != null ? after.getIdCycleMarque() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_cycle_modele", id, nom, dateArchive, idCycleMarque);
    }
}
