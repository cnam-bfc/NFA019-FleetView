package net.cnam.fleetview.model.cycle;

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
 * Classe DAO pour les Cycles
 * <p>
 * Cette classe permet de créer des objets Cycle
 * Concerne la table : fleetview_cycle
 */
public class CycleDAO extends DAO<Cycle> implements Archivable<Cycle> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public CycleDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'un Cycle
     *
     * @param obj  Un objet Cycle à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(Cycle obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdCycle() != null) {
            logger.error("L'objet Cycle a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_cycle (identifiant, numero_serie, charge_maximale, prix_achat, date_acquisition, date_archive, id_cycle_fournisseur, id_cycle_modele, id_cycle_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getIdentifiant());
            statement.setString(2, obj.getNumeroSerie());
            statement.setObject(3, obj.getChargeMaximale());
            statement.setObject(4, obj.getPrixAchat());
            statement.setObject(5, obj.getDateAcquisition());
            statement.setObject(6, obj.getDateArchive());
            statement.setObject(7, obj.getIdCycleFournisseur());
            statement.setObject(8, obj.getIdCycleModele());
            statement.setObject(9, obj.getIdCycleType());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                int id = statement.getGeneratedKeys().getInt(1);

                // On met à jour l'objet pour lui attribuer l'id récupéré
                obj.setIdCycle(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le Cycle", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du Cycle, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'un Cycle
     *
     * @param obj  Un objet Cycle à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(Cycle obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycle() == null) {
            logger.error("L'objet Cycle n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_cycle WHERE id_cycle = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getIdCycle());

            // Récupération de l'objet avant suppression
            Cycle objAvantSuppression = this.getById(obj.getIdCycle());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le Cycle", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression de le Cycle, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement du Cycle
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(Cycle obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycle() == null) {
            logger.error("L'objet Cycle n'a pas d'ID");
            return false;
        }

        // Si la date d'archive n'est pas renseignée, on la met à jour
        if (obj.getDateArchive() == null) {
            obj.setDateArchive(LocalDateTime.now());
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle SET date_archive = ? WHERE id_cycle = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setObject(2, obj.getIdCycle());

            // Récupération de l'objet avant mise à jour
            Cycle objAvantMAJ = this.getById(obj.getIdCycle());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver le Cycle", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage du Cycle, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement du Cycle
     *
     * @param obj  Un objet Cycle à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(Cycle obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycle() == null) {
            logger.error("L'objet Cycle n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle SET identifiant = ?, numero_serie = ?, charge_maximale = ?, prix_achat = ?, date_acquisition = ?, date_archive = ?, id_cycle_fournisseur = ?, id_cycle_modele = ?, id_cycle_type = ? WHERE id_cyle = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getIdentifiant());
            statement.setString(2, obj.getNumeroSerie());
            statement.setObject(3, obj.getChargeMaximale());
            statement.setObject(4, obj.getPrixAchat());
            statement.setObject(5, obj.getDateAcquisition());
            statement.setObject(6, obj.getDateArchive());
            statement.setObject(7, obj.getIdCycleFournisseur());
            statement.setObject(8, obj.getIdCycleModele());
            statement.setObject(9, obj.getIdCycleType());
            statement.setObject(10, obj.getIdCycle());

            // Récupération de l'objet avant modification
            Cycle objAvantModification = this.getById(obj.getIdCycle());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le Cycle", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du Cycle, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des Cycle
     *
     * @return Une List d'objets Cycle, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<Cycle> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle";

        // Résultat de la requête
        List<Cycle> result = new LinkedList<>();
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
                Cycle cycle = new Cycle();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(cycle, resultSet);

                // On ajoute l'objet au résultat final
                result.add(cycle);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les Cycle", ex);

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
     * Méthode de récupération d'un enregistrement d'un Cycle par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet Cycle correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public Cycle getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle WHERE id_cycle = ?";

        // Résultat de la requête
        Cycle result = null;
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
                result = new Cycle();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer une Cycle", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de savoir si une course est disponible
     *
     * @param course Course à vérifier
     * @return true si la course est disponible, false sinon
     */
    public boolean estDisponible(Cycle cycle) {
        String query = "SELECT * FROM fleetview_course AS fc WHERE fc.date_archive IS NULL AND fc.date_debut_course IS NULL AND fc.id_cycle = ?;";
        boolean result = true;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, cycle.getIdCycle());

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On vérifie que le résultat n'est pas vide
            if (resultSet.next()) {
                result = false;
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de vérifier si la course est disponible", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }
        return result;
    }

    /**
     * Méthode permettant de remplir un objet Cycle avec les valeurs d'un enregistrement de la table fleetview_cycle
     *
     * @param cycle     L'objet Cycle à remplir
     * @param resultSet Le résultat de la requête de sélection
     */
    protected void fillObject(Cycle cycle, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            cycle.setIdCycle(resultSet.getInt("id_cycle"));
            cycle.setIdentifiant(resultSet.getString("identifiant"));
            cycle.setNumeroSerie(resultSet.getString("numero_serie"));
            cycle.setChargeMaximale(resultSet.getObject("charge_maximale", Double.class));
            cycle.setPrixAchat(resultSet.getObject("prix_achat", Double.class));
            cycle.setDateAcquisition(resultSet.getObject("date_acquisition", LocalDateTime.class));
            cycle.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
            cycle.setIdCycleFournisseur(resultSet.getObject("id_cycle_fournisseur", Integer.class));
            cycle.setIdCycleModele(resultSet.getObject("id_cycle_modele", Integer.class));
            cycle.setIdCycleType(resultSet.getObject("id_cycle_type", Integer.class));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet Cycle", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, Cycle before, Cycle after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdCycle() : after != null ? after.getIdCycle() : -1;

        if (before != null && after != null && before.getIdCycle() != after.getIdCycle()) {
            logger.error("Impossible de créer l'historique, les deux objets Cycle ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets Cycle sont null");
        }

        // Construction des changements
        HistoriqueData identifiant = this.checkChanges("identifiant", before != null ? before.getIdentifiant() : null, after != null ? after.getIdentifiant() : null);
        HistoriqueData numeroSerie = this.checkChanges("numero_serie", before != null ? before.getNumeroSerie() : null, after != null ? after.getNumeroSerie() : null);
        HistoriqueData chargeMaximale = this.checkChanges("charge_maximale", before != null ? before.getChargeMaximale() : null, after != null ? after.getChargeMaximale() : null);
        HistoriqueData prixAchat = this.checkChanges("prix_achat", before != null ? before.getPrixAchat() : null, after != null ? after.getPrixAchat() : null);
        HistoriqueData dateAcquisition = this.checkChanges("date_acquisition", before != null ? before.getDateAcquisition() : null, after != null ? after.getDateAcquisition() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);
        HistoriqueData idCycleFournisseur = this.checkChanges("id_cycle_fournisseur", before != null ? before.getIdCycleFournisseur() : null, after != null ? after.getIdCycleFournisseur() : null);
        HistoriqueData idCycleModele = this.checkChanges("id_cycle_modele", before != null ? before.getIdCycleModele() : null, after != null ? after.getIdCycleModele() : null);
        HistoriqueData idCycleType = this.checkChanges("id_cycle_type", before != null ? before.getIdCycleType() : null, after != null ? after.getIdCycleType() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_cycle", id, identifiant, numeroSerie, chargeMaximale, prixAchat, dateAcquisition, dateArchive, idCycleFournisseur, idCycleModele, idCycleType);
    }
}
