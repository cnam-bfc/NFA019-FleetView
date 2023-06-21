package net.cnam.fleetview.model.coursier;

import net.cnam.fleetview.model.DAO;
import net.cnam.fleetview.model.TypeHistorique;
import net.cnam.fleetview.model.historiquedata.HistoriqueData;
import net.cnam.fleetview.model.utilisateur.Utilisateur;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe CoursierDAO
 * <p>
 * Cette classe permet de créer des objets Coursier.
 * table concernée : fleetview_coursier
 */
public class CoursierDAO extends DAO<Coursier> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public CoursierDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'un Coursier
     *
     * @param obj  Un objet Coursier à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(Coursier obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdCoursier() != null) {
            logger.error("L'objet Coursier a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_coursier (matricule, id_utilisateur) VALUES (?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getMatricule());
            statement.setObject(2, obj.getIdUtilisateur());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    // On attribue l'id à l'objet
                    obj.setIdCoursier(generatedKeys.getInt(1));
                } else {
                    logger.error("Échec de la création du Coursier, aucun ID auto-généré retourné.");
                }
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le Coursier", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du Coursier, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'un Coursier
     *
     * @param obj  Un objet Coursier à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(Coursier obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCoursier() == null) {
            logger.error("L'objet Coursier n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_coursier WHERE id_coursier = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getIdCoursier());

            // Récupération de l'objet avant suppression
            Coursier objAvantSuppression = this.getById(obj.getIdCoursier());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le Coursier", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du Coursier, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement du Coursier
     *
     * @param obj  Un objet Coursier à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(Coursier obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdCoursier() == null) {
            logger.error("L'objet Coursier n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_coursier SET matricule = ?, id_utilisateur = ? WHERE id_coursier = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getMatricule());
            statement.setObject(2, obj.getIdUtilisateur());
            statement.setObject(3, obj.getIdCoursier());

            // Récupération de l'objet avant modification
            Coursier objAvantModification = this.getById(obj.getIdCoursier());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour du Coursier", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du Coursier, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des Coursier
     *
     * @return Une List d'objets Coursier, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<Coursier> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_coursier";

        // Résultat de la requête
        List<Coursier> result = new LinkedList<>();
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
                Coursier coursier = new Coursier();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(coursier, resultSet);

                // On ajoute l'objet au résultat final
                result.add(coursier);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les Coursier", ex);

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
     * Méthode de récupération d'un enregistrement d'un Coursier par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet Coursier correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public Coursier getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_coursier WHERE id_coursier = ?";

        // Résultat de la requête
        Coursier result = null;
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
                result = new Coursier();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un Coursier", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet Coursier avec les valeurs d'un enregistrement de la table fleetview_coursier
     *
     * @param coursier  L'objet Coursier à remplir
     * @param resultSet Le résultat de la requête de sélection
     */
    protected void fillObject(Coursier coursier, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            coursier.setIdCoursier(resultSet.getInt("id_coursier"));
            coursier.setMatricule(resultSet.getString("matricule"));
            coursier.setIdUtilisateur(resultSet.getObject("id_utilisateur", Integer.class));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet Coursier", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, Coursier before, Coursier after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdCoursier() : after != null ? after.getIdCoursier() : -1;

        if (before != null && after != null && before.getIdCoursier() != after.getIdCoursier()) {
            logger.error("Impossible de créer l'historique, les deux objets Coursier ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets Coursier sont null");
        }

        // Construction des changements
        HistoriqueData matricule = this.checkChanges("matricule", before != null ? before.getMatricule() : null, after != null ? after.getMatricule() : null);
        HistoriqueData idUtilisateur = this.checkChanges("id_utilisateur", before != null ? before.getIdUtilisateur() : null, after != null ? after.getIdUtilisateur() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_coursier", id, matricule, idUtilisateur);
    }
}
