package net.cnam.fleetview.model.cyclefournisseur;

import net.cnam.fleetview.model.Archivable;
import net.cnam.fleetview.model.DAO;
import net.cnam.fleetview.model.TypeHistorique;
import net.cnam.fleetview.model.historiquedata.HistoriqueData;
import net.cnam.fleetview.model.utilisateur.Utilisateur;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CycleFournisseurDAO extends DAO<CycleFournisseur> implements Archivable<CycleFournisseur> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public CycleFournisseurDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement de cycle fournisseur
     *
     * @param obj  Un objet CycleFournisseur à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(CycleFournisseur obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdCycleFournisseur() != 0) {
            logger.error("L'objet CycleFournisseur a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_cycle_fournisseur (nom, mail, telephone, date_archive) VALUES (?, ?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getMail());
            statement.setString(3, obj.getTelephone());
            statement.setObject(4, obj.getDateArchive());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                int id = statement.getGeneratedKeys().getInt(1);

                // On met à jour l'objet pour lui attribuer l'id récupéré
                obj.setId(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le Cycle Fournisseur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du cycle fournisseur, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement de cycle fournisseur
     *
     * @param obj  Un objet CycleFournisseur à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(CycleFournisseur obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleFournisseur() == 0) {
            logger.error("L'objet CycleFournisseur n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_cycle_fournisseur WHERE id = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdCycleFournisseur());

            // Récupération de l'objet avant suppression
            CycleFournisseur objAvantSuppression = this.getById(obj.getIdCycleFournisseur());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le Cycle Fournisseur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du cycle fournisseur, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement de cycle fournisseur
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(CycleFournisseur obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleFournisseur() == 0) {
            logger.error("L'objet CycleFournisseur n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle_fournisseur SET date_archive = ? WHERE id = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setInt(2, obj.getIdCycleFournisseur());

            // Récupération de l'objet avant mise à jour
            CycleFournisseur objAvantMAJ = this.getById(obj.getIdCycleFournisseur());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver le Cycle Fournisseur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage du cycle fournisseur, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement de cycle fournisseur
     *
     * @param obj  Un objet CycleFournisseur à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(CycleFournisseur obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCycleFournisseur() == 0) {
            logger.error("L'objet CycleFournisseur n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_cycle_fournisseur SET nom = ?, mail = ?, telephone = ?, date_archive = ? WHERE id = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getMail());
            statement.setString(3, obj.getTelephone());
            statement.setObject(4, obj.getDateArchive());
            statement.setInt(5, obj.getIdCycleFournisseur());

            // Récupération de l'objet avant modification
            CycleFournisseur objAvantModification = this.getById(obj.getIdCycleFournisseur());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le Cycle Fournisseur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du cycle fournisseur, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements de cycle fournisseur
     *
     * @return Une List d'objets CycleFournisseur, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<CycleFournisseur> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle_fournisseur";

        // Résultat de la requête
        List<CycleFournisseur> result = new LinkedList<>();
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
                CycleFournisseur cycleFournisseur = new CycleFournisseur();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(cycleFournisseur, resultSet);

                // On ajoute l'objet au résultat final
                result.add(cycleFournisseur);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les Cycle Fournisseur", ex);

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
     * Méthode de récupération d'un enregistrement de cycle fournisseur par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet CycleFournisseur correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public CycleFournisseur getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_cycle_fournisseur WHERE id = ?";

        // Résultat de la requête
        CycleFournisseur result = null;
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
                // Création d'un objet CycleFournisseur
                result = new CycleFournisseur();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer le Cycle Fournisseur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet CycleFournisseur avec les valeurs d'un enregistrement de la table fleetview_cycle_fournisseur
     *
     * @param cycleFournisseur L'objet CycleFournisseur à remplir
     * @param resultSet        Le résultat de la requête de sélection
     */
    protected void fillObject(CycleFournisseur cycleFournisseur, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            cycleFournisseur.setId(resultSet.getInt("id"));
            cycleFournisseur.setNom(resultSet.getString("nom"));
            cycleFournisseur.setMail(resultSet.getString("mail"));
            cycleFournisseur.setTelephone(resultSet.getString("telephone"));
            cycleFournisseur.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet CycleFournisseur", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, CycleFournisseur before, CycleFournisseur after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdCycleFournisseur() : after != null ? after.getIdCycleFournisseur() : -1;

        if (before != null && after != null && before.getIdCycleFournisseur() != after.getIdCycleFournisseur()) {
            logger.error("Impossible de créer l'historique, les deux objets CycleFournisseur ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets CycleFournisseur sont null");
        }

        // Construction des changements
        HistoriqueData nom = this.checkChanges("nom", before != null ? before.getNom() : null, after != null ? after.getNom() : null);
        HistoriqueData mail = this.checkChanges("mail", before != null ? before.getMail() : null, after != null ? after.getMail() : null);
        HistoriqueData telephone = this.checkChanges("telephone", before != null ? before.getTelephone() : null, after != null ? after.getTelephone() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_cycle_fournisseur", id, nom, mail, telephone, dateArchive);
    }
}
