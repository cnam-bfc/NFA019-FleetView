package net.cnam.fleetview.model.colis;

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
 * Classe ColisDAO
 * <p>
 * Cette classe permet de créer des objets Colis.
 * table concernée : fleetview_colis
 */
public class ColisDAO extends DAO<Colis> implements Archivable<Colis> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public ColisDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'un Colis
     *
     * @param obj  Un objet Colis à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(Colis obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdColis() != null) {
            logger.error("L'objet Colis a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_colis (numero, poids, date_archive, id_adresse, id_colis_destinataire) VALUES (?, ?, ?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNumero());
            statement.setObject(2, obj.getPoids());
            statement.setObject(3, obj.getDateArchive());
            statement.setObject(4, obj.getIdAdresse());
            statement.setObject(5, obj.getIdColisDestinataire());


            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    // On attribue l'id à l'objet
                    obj.setIdColis(generatedKeys.getInt(1));
                } else {
                    logger.error("Échec de la création du Colis en base, aucun ID auto-généré retourné.");
                }
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer l'Colis", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du colis, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'un colis
     *
     * @param obj  Un objet Colis à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(Colis obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdColis() == null) {
            logger.error("L'objet Colis n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_colis WHERE id_colis = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getIdColis());

            // Récupération de l'objet avant suppression
            Colis objAvantSuppression = this.getById(obj.getIdColis());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer un colis", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du colis, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement d'un colis
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(Colis obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdColis() == null) {
            logger.error("L'objet Colis n'a pas d'ID");
            return false;
        }

        // Si la date d'archive n'est pas renseignée, on la met à jour
        if (obj.getDateArchive() == null) {
            obj.setDateArchive(LocalDateTime.now());
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_colis SET date_archive = ? WHERE id_colis = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setObject(2, obj.getIdColis());

            // Récupération de l'objet avant mise à jour
            Colis objAvantMAJ = this.getById(obj.getIdColis());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver le Colis", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage de le Colis, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement d'un colis
     *
     * @param obj  Un objet Colis à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(Colis obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdColis() == null) {
            logger.error("L'objet Colis n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_colis SET numero = ?, poids = ?, date_archive = ?, id_adresse = ?, id_colis_destinataire = ?  WHERE id_colis = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNumero());
            statement.setObject(2, obj.getPoids());
            statement.setObject(3, obj.getDateArchive());
            statement.setObject(4, obj.getIdAdresse());
            statement.setObject(5, obj.getIdColisDestinataire());
            statement.setObject(6, obj.getIdColis());


            // Récupération de l'objet avant modification
            Colis objAvantModification = this.getById(obj.getIdColis());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le colis", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du colis, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des colis
     *
     * @return Une List d'objets Colis, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<Colis> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_colis";

        // Résultat de la requête
        List<Colis> result = new LinkedList<>();
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
                Colis colis = new Colis();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(colis, resultSet);

                // On ajoute l'objet au résultat final
                result.add(colis);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les colis", ex);

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
     * Méthode de récupération de tous les enregistrements des colis non archivés
     *
     * @return Une List d'objets Colis, vide en cas d'erreur ou si la table est vide
     */
    public List<Colis> getAllNotArchived() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_colis WHERE date_archive IS NULL";

        // Résultat de la requête
        List<Colis> result = new LinkedList<>();
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
                Colis colis = new Colis();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(colis, resultSet);

                // On ajoute l'objet au résultat final
                result.add(colis);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les colis", ex);

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
     * Méthode de récupération d'un enregistrement d'un colis par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet Colis correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public Colis getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_colis WHERE id_colis = ?";

        // Résultat de la requête
        Colis result = null;
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
                result = new Colis();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un colis", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet Colis avec les valeurs d'un enregistrement de la table fleetview_colis
     *
     * @param colis     L'objet Colis à remplir
     * @param resultSet Le résultat de la requête de sélection
     */
    protected void fillObject(Colis colis, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            colis.setIdColis(resultSet.getInt("id_colis"));
            colis.setNumero(resultSet.getString("numero"));
            colis.setPoids(resultSet.getObject("poids", Double.class));
            colis.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
            colis.setIdAdresse(resultSet.getObject("id_adresse", Integer.class));
            colis.setIdColisDestinataire(resultSet.getObject("id_colis_destinataire", Integer.class));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet colis", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, Colis before, Colis after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdColis() : after != null ? after.getIdColis() : -1;

        if (before != null && after != null && before.getIdColis() != after.getIdColis()) {
            logger.error("Impossible de créer l'historique, les deux objets Colis ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets Colis sont null");
        }

        // Construction des changements
        HistoriqueData numero = this.checkChanges("numero", before != null ? before.getNumero() : null, after != null ? after.getNumero() : null);
        HistoriqueData poids = this.checkChanges("poids", before != null ? before.getPoids() : null, after != null ? after.getPoids() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);
        HistoriqueData idAdresse = this.checkChanges("id_adresse", before != null ? before.getIdAdresse() : null, after != null ? after.getIdAdresse() : null);
        HistoriqueData idColisDestinataire = this.checkChanges("id_colis_destinataire", before != null ? before.getIdColisDestinataire() : null, after != null ? after.getIdColisDestinataire() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_colis", id, numero, poids, dateArchive, idAdresse, idColisDestinataire);
    }
}
