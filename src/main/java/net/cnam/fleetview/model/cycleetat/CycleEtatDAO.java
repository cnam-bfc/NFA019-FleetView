package net.cnam.fleetview.model.cycleetat;

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
 * Classe DAO pour les Cycle Etat
 * <p>
 * Permet la création d'objet CycleEtat
 * Concerne la table : fleetview_cycle_etat
 */
public class CycleEtatDAO extends DAO<CycleEtat> implements Archivable<CycleEtat> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public CycleEtatDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une CycleEtat
     *
     * @param obj  Un objet CycleEtat à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(CycleEtat obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdCycleEtat() != null) {
            logger.error("L'objet CycleEtat a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_cycle_etat (date_debut, date_fin_estime, commentaire, date_archive, id_cycle_etat_type, id_cycle) VALUES (?, ?, ?, ?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateDebut());
            statement.setObject(2, obj.getDateFinEstime());
            statement.setString(3, obj.getCommentaire());
            statement.setObject(4, obj.getDateArchive());
            statement.setInt(5, obj.getIdCycleEtatType());
            statement.setInt(6, obj.getIdCycle());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    // On attribue l'id à l'objet
                    obj.setIdCycleEtat(generatedKeys.getInt(1));
                } else {
                    logger.error("Échec de la création du CycleEtat en base, aucun ID auto-généré retourné.");
                }
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le CycleEtat", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du CycleEtat, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'un CycleEtat
     *
     * @param obj  Un objet CycleEtat à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(CycleEtat obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleEtat() == null) {
            logger.error("L'objet CycleEtat n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_cycle_etat WHERE id_cycle_etat = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdCycleEtat());

            // Récupération de l'objet avant suppression
            CycleEtat objAvantSuppression = this.getById(obj.getIdCycleEtat());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le CycleEtat", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du CycleEtat, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement du CycleEtat
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(CycleEtat obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleEtat() == null) {
            logger.error("L'objet CycleEtat n'a pas d'ID");
            return false;
        }

        // Si la date d'archive n'est pas renseignée, on la met à jour
        if (obj.getDateArchive() == null) {
            obj.setDateArchive(LocalDateTime.now());
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle_etat SET date_archive = ? WHERE id_cycle_etat = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setObject(2, obj.getIdCycleEtat());

            // Récupération de l'objet avant mise à jour
            CycleEtat objAvantMAJ = this.getById(obj.getIdCycleEtat());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver le CycleEtat", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage du CycleEtat, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement du CycleEtat
     *
     * @param obj  Un objet CycleEtat à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(CycleEtat obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleEtat() == null) {
            logger.error("L'objet CycleEtat n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle_etat SET date_debut = ?, date_fin_estime = ?, commentaire = ?, date_archive = ?, id_cycle_etat_type = ?, id_cycle = ? WHERE id_cycle_etat = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateDebut());
            statement.setObject(2, obj.getDateFinEstime());
            statement.setString(3, obj.getCommentaire());
            statement.setObject(4, obj.getDateArchive());
            statement.setObject(5, obj.getIdCycleEtatType());
            statement.setObject(6, obj.getIdCycle());
            statement.setObject(7, obj.getIdCycleEtat());

            // Récupération de l'objet avant modification
            CycleEtat objAvantModification = this.getById(obj.getIdCycleEtat());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le CycleEtat", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du CycleEtat, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des CycleEtat
     *
     * @return Une List d'objets CycleEtat, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<CycleEtat> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle_etat";

        // Résultat de la requête
        List<CycleEtat> result = new LinkedList<>();
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
                CycleEtat cycleEtat = new CycleEtat();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(cycleEtat, resultSet);

                // On ajoute l'objet au résultat final
                result.add(cycleEtat);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les CycleEtat", ex);

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
     * Méthode de récupération d'un enregistrement d'un CycleEtat par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet CycleEtat correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public CycleEtat getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle_etat WHERE id_cycle_etat = ?";

        // Résultat de la requête
        CycleEtat result = null;
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
                result = new CycleEtat();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer une CycleEtat", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet CycleEtat avec les valeurs d'un enregistrement de la table fleetview_cycle_etat
     *
     * @param cycleEtat L'objet CycleEtat à remplir
     * @param resultSet Le résultat de la requête de sélection
     */
    protected void fillObject(CycleEtat cycleEtat, ResultSet resultSet) {
        try {
            // Remplissage de l'objet
            cycleEtat.setIdCycleEtat(resultSet.getInt("id_cycle_etat"));
            cycleEtat.setDateDebut(resultSet.getObject("date_debut", LocalDateTime.class));
            cycleEtat.setDateFinEstime(resultSet.getObject("date_fin_estime", LocalDateTime.class));
            cycleEtat.setCommentaire(resultSet.getString("commentaire"));
            cycleEtat.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
            cycleEtat.setIdCycle(resultSet.getObject("id_cycle", Integer.class));
            cycleEtat.setIdCycleEtatType(resultSet.getObject("id_cycle_etat_type", Integer.class));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet CycleEtat", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, CycleEtat before, CycleEtat after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdCycleEtat() : after != null ? after.getIdCycleEtat() : -1;

        if (before != null && after != null && before.getIdCycleEtat() != after.getIdCycleEtat()) {
            logger.error("Impossible de créer l'historique, les deux objets CycleEtat ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets CycleEtat sont null");
        }

        // Construction des changements
        HistoriqueData dateDebut = this.checkChanges("date_debut", before != null ? before.getDateDebut() : null, after != null ? after.getDateDebut() : null);
        HistoriqueData dateFinEstime = this.checkChanges("date_fin_estime", before != null ? before.getDateFinEstime() : null, after != null ? after.getDateFinEstime() : null);
        HistoriqueData commentaire = this.checkChanges("commentaire", before != null ? before.getCommentaire() : null, after != null ? after.getCommentaire() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);
        HistoriqueData idCycle = this.checkChanges("id_cycle", before != null ? before.getIdCycle() : null, after != null ? after.getIdCycle() : null);
        HistoriqueData idCycleEtatType = this.checkChanges("id_cycle_etat_type", before != null ? before.getIdCycleEtatType() : null, after != null ? after.getIdCycleEtatType() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_cycle_etat", id, dateDebut, dateFinEstime, commentaire, dateArchive, idCycle, idCycleEtatType);
    }
}
