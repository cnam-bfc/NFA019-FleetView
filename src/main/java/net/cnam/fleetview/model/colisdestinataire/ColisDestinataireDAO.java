package net.cnam.fleetview.model.colisdestinataire;

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
 * Classe ColisDestinataireDAO
 * <p>
 * Cette classe permet de créer des objets ColisDestinataire.
 * table concernée : fleetview_colis_destinataire
 */
public class ColisDestinataireDAO extends DAO<ColisDestinataire> implements Archivable<ColisDestinataire> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public ColisDestinataireDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'un ColisDestinataire
     *
     * @param obj  Un objet ColisDestinataire à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(ColisDestinataire obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdColisDestinataire() != null) {
            logger.error("L'objet ColisDestinataire a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_colis_destinataire (prenom, nom, date_archive) VALUES (?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getPrenom());
            statement.setString(2, obj.getNom());
            statement.setObject(3, obj.getDateArchive());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    // On attribue l'id à l'objet
                    obj.setIdColisDestinataire(generatedKeys.getInt(1));
                } else {
                    logger.error("Échec de la création du ColisDestinataire, aucun ID auto-généré retourné.");
                }
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le ColisDestinataire", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du ColisDestinataire, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'un ColisDestinataire
     *
     * @param obj  Un objet ColisDestinataire à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(ColisDestinataire obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdColisDestinataire() == null) {
            logger.error("L'objet ColisDestinataire n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_colis_destinataire WHERE id_colis_destinataire = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getIdColisDestinataire());

            // Récupération de l'objet avant suppression
            ColisDestinataire objAvantSuppression = this.getById(obj.getIdColisDestinataire());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le ColisDestinataire", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du ColisDestinataire, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement du ColisDestinataire
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(ColisDestinataire obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdColisDestinataire() == null) {
            logger.error("L'objet ColisDestinataire n'a pas d'ID");
            return false;
        }

        // Si la date d'archive n'est pas renseignée, on la met à jour
        if (obj.getDateArchive() == null) {
            obj.setDateArchive(LocalDateTime.now());
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_colis_destinataire SET date_archive = ? WHERE id_colis_destinataire = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setObject(2, obj.getIdColisDestinataire());

            // Récupération de l'objet avant mise à jour
            ColisDestinataire objAvantMAJ = this.getById(obj.getIdColisDestinataire());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver le ColisDestinataire", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage du ColisDestinataire, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement du ColisDestinataire
     *
     * @param obj  Un objet ColisDestinataire à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(ColisDestinataire obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdColisDestinataire() == null) {
            logger.error("L'objet ColisDestinataire n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_colis_destinataire SET prenom = ?, nom = ?, date_archive = ? WHERE id_colis_destinataire = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getPrenom());
            statement.setString(2, obj.getNom());
            statement.setObject(3, obj.getDateArchive());
            statement.setObject(4, obj.getIdColisDestinataire());


            // Récupération de l'objet avant modification
            ColisDestinataire objAvantModification = this.getById(obj.getIdColisDestinataire());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le ColisDestinataire", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du ColisDestinataire, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des ColisDestinataire
     *
     * @return Une List d'objets ColisDestinataire, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<ColisDestinataire> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_colis_destinataire";

        // Résultat de la requête
        List<ColisDestinataire> result = new LinkedList<>();
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
                ColisDestinataire colisDestinataire = new ColisDestinataire();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(colisDestinataire, resultSet);

                // On ajoute l'objet au résultat final
                result.add(colisDestinataire);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les ColisDestinataire", ex);

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
     * Méthode de récupération d'un enregistrement d'un colis destinataire par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet ColisDestinataire correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public ColisDestinataire getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_colis_destinataire WHERE id_colis_destinataire = ?";

        // Résultat de la requête
        ColisDestinataire result = null;
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
                result = new ColisDestinataire();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un ColisDestinataire", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet ColisDestinataire avec les valeurs d'un enregistrement de la table fleetview_colis_destinataire
     *
     * @param colisDestinataire L'objet ColisDestinataire à remplir
     * @param resultSet         Le résultat de la requête de sélection
     */
    protected void fillObject(ColisDestinataire colisDestinataire, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            colisDestinataire.setIdColisDestinataire(resultSet.getInt("id_colis_destinataire"));
            colisDestinataire.setNom(resultSet.getString("nom"));
            colisDestinataire.setPrenom(resultSet.getString("prenom"));
            colisDestinataire.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet ColisDestinataire", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, ColisDestinataire before, ColisDestinataire after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdColisDestinataire() : after != null ? after.getIdColisDestinataire() : -1;

        if (before != null && after != null && before.getIdColisDestinataire() != after.getIdColisDestinataire()) {
            logger.error("Impossible de créer l'historique, les deux objets ColisDestinataire ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets ColisDestinataire sont null");
        }

        // Construction des changements
        HistoriqueData nom = this.checkChanges("nom", before != null ? before.getNom() : null, after != null ? after.getNom() : null);
        HistoriqueData prenom = this.checkChanges("prenom", before != null ? before.getPrenom() : null, after != null ? after.getPrenom() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_colis_destinataire", id, nom, prenom, dateArchive);
    }
}
