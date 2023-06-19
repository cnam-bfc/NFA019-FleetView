package net.cnam.fleetview.model.secteur;

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
 * Classe DAO pour les objets Secteur
 * <p>
 * Concerne la table : fleetview_secteur
 */
public class SecteurDAO extends DAO<Secteur> implements Archivable<Secteur> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public SecteurDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une Secteur
     *
     * @param obj  Un objet Secteur à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(Secteur obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdSecteur() != 0) {
            logger.error("L'objet Secteur a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_secteur (nom, date_archive) VALUES (?, ?)";

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
                obj.setIdSecteur(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le Secteur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du Secteur, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'une Secteur
     *
     * @param obj  Un objet Secteur à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(Secteur obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdSecteur() == 0) {
            logger.error("L'objet Secteur n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_secteur WHERE id_secteur = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setInt(1, obj.getIdSecteur());

            // Récupération de l'objet avant suppression
            Secteur objAvantSuppression = this.getById(obj.getIdSecteur());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le Secteur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du Secteur, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement du Secteur
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(Secteur obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdSecteur() == 0) {
            logger.error("L'objet Secteur n'a pas d'ID");
            return false;
        }

        // Si la date d'archive n'est pas renseignée, on la met à jour
        if (obj.getDateArchive() == null) {
            obj.setDateArchive(LocalDateTime.now());
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_secteur SET date_archive = ? WHERE id_secteur = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setInt(2, obj.getIdSecteur());

            // Récupération de l'objet avant mise à jour
            Secteur objAvantMAJ = this.getById(obj.getIdSecteur());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver le Secteur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage du Secteur, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement du Secteur
     *
     * @param obj  Un objet Secteur à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(Secteur obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdSecteur() == 0) {
            logger.error("L'objet Secteur n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_secteur SET nom = ?, date_archive = ? WHERE id_secteur = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());
            statement.setObject(2, obj.getDateArchive());

            // Récupération de l'objet avant modification
            Secteur objAvantModification = this.getById(obj.getIdSecteur());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le Secteur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du Secteur, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des Secteur
     *
     * @return Une List d'objets Secteur, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<Secteur> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_secteur";

        // Résultat de la requête
        List<Secteur> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets Secteur correspondants
            while (resultSet.next()) {
                // Création d'un objet Secteur
                Secteur secteur = new Secteur();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(secteur, resultSet);

                // On ajoute l'objet au résultat final
                result.add(secteur);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les Secteur", ex);

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
     * Méthode de récupération d'un enregistrement d'un Secteur par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet Secteur correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public Secteur getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_secteur WHERE id_secteur = ?";

        // Résultat de la requête
        Secteur result = null;
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
                // Création d'un objet Secteur
                result = new Secteur();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer un Secteur", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet Secteur avec les valeurs d'un enregistrement de la table fleetview_secteur
     *
     * @param secteur   L'objet Secteur à remplir
     * @param resultSet Le résultat de la requête de sélection
     */
    protected void fillObject(Secteur secteur, ResultSet resultSet) {
        try {
            // Remplissage de l'objet Secteur
            secteur.setIdSecteur(resultSet.getInt("id_secteur"));
            secteur.setNom(resultSet.getString("nom"));
            secteur.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet Secteur", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, Secteur before, Secteur after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdSecteur() : after != null ? after.getIdSecteur() : -1;

        if (before != null && after != null && before.getIdSecteur() != after.getIdSecteur()) {
            logger.error("Impossible de créer l'historique, les deux objets Secteur ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets Secteur sont null");
        }

        // Construction des changements
        HistoriqueData nom = this.checkChanges("nom", before != null ? before.getNom() : null, after != null ? after.getNom() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_secteur", id, nom, dateArchive);
    }
}
