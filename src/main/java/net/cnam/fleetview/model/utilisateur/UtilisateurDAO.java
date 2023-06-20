package net.cnam.fleetview.model.utilisateur;

import net.cnam.fleetview.model.Archivable;
import net.cnam.fleetview.model.DAO;
import net.cnam.fleetview.model.TypeHistorique;
import net.cnam.fleetview.model.historiquedata.HistoriqueData;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe DAO pour les objets Utilisateur
 * <p>
 * Concerne la table : fleetview_utilisateur
 */
public class UtilisateurDAO extends DAO<Utilisateur> implements Archivable<Utilisateur> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public UtilisateurDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une Utilisateur
     *
     * @param obj  Un objet Utilisateur à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(Utilisateur obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdUtilisateur() != 0) {
            logger.error("L'objet Utilisateur a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_utilisateur (identifiant, mot_de_passe, nom, prenom, date_archive, id_role) VALUES (?, ?, ?, ?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getIdentifiant());
            statement.setString(2, obj.getMotDePasse());
            statement.setString(3, obj.getNom());
            statement.setString(4, obj.getPrenom());
            statement.setObject(5, obj.getDateArchive());
            statement.setInt(6, obj.getIdRole());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                int id = statement.getGeneratedKeys().getInt(1);

                // On met à jour l'objet pour lui attribuer l'id récupéré
                obj.setIdUtilisateur(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer l'Utilisateur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création de l'Utilisateur, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'un Utilisateur
     *
     * @param obj  Un objet Utilisateur à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(Utilisateur obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdUtilisateur() == 0) {
            logger.error("L'objet Utilisateur n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_utilisateur WHERE id_utilisateur = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdUtilisateur());

            // Récupération de l'objet avant suppression
            Utilisateur objAvantSuppression = this.getById(obj.getIdUtilisateur());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer l'Utilisateur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression de l'Utilisateur, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement de l'Utilisateur
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(Utilisateur obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdUtilisateur() == 0) {
            logger.error("L'objet Utilisateur n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_utilisateur SET date_archive = ? WHERE id_utilisateur = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setInt(2, obj.getIdUtilisateur());

            // Récupération de l'objet avant mise à jour
            Utilisateur objAvantMAJ = this.getById(obj.getIdUtilisateur());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver l'Utilisateur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage de l'Utilisateur, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement de l'Utilisateur
     *
     * @param obj  Un objet Utilisateur à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(Utilisateur obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdUtilisateur() == 0) {
            logger.error("L'objet Utilisateur n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_utilisateur SET identifiant = ?, mot_de_passe = ?, nom = ?, prenom = ?, date_archive = ?, id_role = ? WHERE id_utilisateur = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getIdentifiant());
            statement.setString(2, obj.getMotDePasse());
            statement.setString(3, obj.getNom());
            statement.setString(4, obj.getPrenom());
            statement.setObject(5, obj.getDateArchive());
            statement.setInt(6, obj.getIdRole());
            statement.setInt(7, obj.getIdUtilisateur());

            // Récupération de l'objet avant modification
            Utilisateur objAvantModification = this.getById(obj.getIdUtilisateur());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour l'Utilisateur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour de l'Utilisateur, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des Utilisateur
     *
     * @return Une List d'objets Utilisateur, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<Utilisateur> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_utilisateur";

        // Résultat de la requête
        List<Utilisateur> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets Utilisateur correspondants
            while (resultSet.next()) {
                // Création d'un objet Utilisateur
                Utilisateur utilisateur = new Utilisateur();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(utilisateur, resultSet);

                // On ajoute l'objet au résultat final
                result.add(utilisateur);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les Utilisateur", ex);

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
     * Méthode de récupération d'un enregistrement d'une Utilisateur par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet Utilisateur correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public Utilisateur getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_utilisateur WHERE id_utilisateur = ?";

        // Résultat de la requête
        Utilisateur result = null;
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
                // Création d'un objet Utilisateur
                result = new Utilisateur();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un Utilisateur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode de récupération d'un enregistrement d'une Utilisateur par son username.
     *
     * @param iden L'identificateur à rechercher
     * @return Un objet Utilisateur correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    public Utilisateur getByIden(int iden) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_utilisateur WHERE identifiant = ?";

        // Résultat de la requête
        Utilisateur result = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, iden);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On vérifie que le résultat n'est pas vide
            if (resultSet.next()) {
                // Création d'un objet Utilisateur
                result = new Utilisateur();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un Utilisateur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet Utilisateur avec les valeurs d'un enregistrement de la table fleetview_utilisateur
     *
     * @param utilisateur L'objet Utilisateur à remplir
     * @param resultSet   Le résultat de la requête de sélection
     */
    protected void fillObject(Utilisateur utilisateur, ResultSet resultSet) {
        try {
            // Remplissage de l'objet Utilisateur
            utilisateur.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
            utilisateur.setIdentifiant(resultSet.getString("identifiant"));
            utilisateur.setMotDePasse(resultSet.getString("mot_de_passe"));
            utilisateur.setNom(resultSet.getString("nom"));
            utilisateur.setPrenom(resultSet.getString("prenom"));
            utilisateur.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
            utilisateur.setIdRole(resultSet.getInt("id_role"));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet Utilisateur", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, Utilisateur before, Utilisateur after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdUtilisateur() : after != null ? after.getIdUtilisateur() : -1;

        if (before != null && after != null && before.getIdUtilisateur() != after.getIdUtilisateur()) {
            logger.error("Impossible de créer l'historique, les deux objets Utilisateur ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets Utilisateur sont null");
        }

        // Construction des changements
        HistoriqueData identifiant = this.checkChanges("identifiant", before != null ? before.getIdentifiant() : null, after != null ? after.getIdentifiant() : null);
        HistoriqueData motDePasse = this.checkChanges("mot_de_passe", before != null ? before.getMotDePasse() : null, after != null ? after.getMotDePasse() : null);
        HistoriqueData nom = this.checkChanges("nom", before != null ? before.getNom() : null, after != null ? after.getNom() : null);
        HistoriqueData prenom = this.checkChanges("prenom", before != null ? before.getPrenom() : null, after != null ? after.getPrenom() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);
        HistoriqueData idRole = this.checkChanges("id_role", before != null ? before.getIdRole() : null, after != null ? after.getIdRole() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_utilisateur", id, identifiant, motDePasse, nom, prenom, dateArchive, idRole);
    }
}
