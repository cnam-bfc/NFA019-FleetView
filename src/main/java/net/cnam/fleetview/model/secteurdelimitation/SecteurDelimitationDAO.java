package net.cnam.fleetview.model.secteurdelimitation;

import net.cnam.fleetview.model.DAO;
import net.cnam.fleetview.model.TypeHistorique;
import net.cnam.fleetview.model.utilisateur.Utilisateur;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe DAO pour les objets SecteurDelimitation
 * <p>
 * Concerne la table : fleetview_secteur_delimitation
 */
public class SecteurDelimitationDAO extends DAO<SecteurDelimitation> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public SecteurDelimitationDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une SecteurDelimitation
     *
     * @param obj  Un objet SecteurDelimitation à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(SecteurDelimitation obj, Utilisateur user) {
        // On vérifie que l'objet a les id d'ID
        if (obj.getIdSecteur() == null || obj.getIdSecteurPoint() == null) {
            logger.error("L'objet SecteurDelimitation manque d'ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_secteur_delimitation (id_secteur, id_secteur_point, ordre) VALUES (?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getIdSecteur());
            statement.setObject(2, obj.getIdSecteurPoint());
            statement.setObject(3, obj.getOrdre());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer le SecteurDelimitation", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création du SecteurDelimitation, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'une= SecteurDelimitation
     *
     * @param obj  Un objet SecteurDelimitation à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(SecteurDelimitation obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdSecteur() == null || obj.getIdSecteurPoint() == null) {
            logger.error("L'objet SecteurDelimitation n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_secteur_delimitation WHERE id_secteur = ? AND id_secteur_point = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getIdSecteur());
            statement.setObject(2, obj.getIdSecteurPoint());

            // Récupération de l'objet avant suppression
            SecteurDelimitation objAvantSuppression = this.getByIds(obj.getIdSecteur(), obj.getIdSecteurPoint());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer le SecteurDelimitation", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression du SecteurDelimitation, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement de SecteurDelimitation
     *
     * @param obj  Un objet SecteurDelimitation à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(SecteurDelimitation obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdSecteur() == null || obj.getIdSecteurPoint() == null) {
            logger.error("L'objet SecteurDelimitation n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_secteur_delimitation SET ordre = ?, id_secteur = ?, id_secteur = ? WHERE id_secteur = ? AND id_secteur_point = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getOrdre());
            statement.setObject(2, obj.getIdSecteur());
            statement.setObject(3, obj.getIdSecteurPoint());
            statement.setObject(4, obj.getIdSecteur());
            statement.setObject(5, obj.getIdSecteurPoint());

            // Récupération de l'objet avant modification
            SecteurDelimitation objAvantModification = this.getByIds(obj.getIdSecteur(), obj.getIdSecteurPoint());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour le SecteurDelimitation", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour du SecteurDelimitation, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des SecteurDelimitation
     *
     * @return Une List d'objets SecteurDelimitation, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<SecteurDelimitation> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_secteur_delimitation";

        // Résultat de la requête
        List<SecteurDelimitation> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets SecteurDelimitation correspondants
            while (resultSet.next()) {
                // Création d'un objet SecteurDelimitation
                SecteurDelimitation secteurDelimitation = new SecteurDelimitation();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(secteurDelimitation, resultSet);

                // On ajoute l'objet au résultat final
                result.add(secteurDelimitation);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les SecteurDelimitation", ex);

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
     * Méthode impossible : la table utilise une clé primaire composite, il faut utiliser getByIds
     * <p>
     * Exception levée systématiquement
     */
    @Override
    public SecteurDelimitation getById(int id) {
        throw new UnsupportedOperationException("Impossible de récupérer une SecteurDelimitation par son ID, il faut utiliser getByIds");
    }

    /**
     * Méthode de récupération d'un enregistrement de SecteurDelimitation par son ID
     *
     * @param idSecteur      ID du SecteurDelimitation
     * @param idSecteurPoint ID du SecteurDelimitation
     * @return Un objet SecteurDelimitation correspondant à l'enregistrement récupéré, null si aucun enregistrement trouvé
     */
    public SecteurDelimitation getByIds(int idSecteur, int idSecteurPoint) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_secteur_delimitation WHERE id_secteur = ? AND id_secteur_point = ?";

        // Résultat de la requête
        SecteurDelimitation result = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, idSecteur);
            statement.setObject(2, idSecteurPoint);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On vérifie que le résultat n'est pas vide
            if (resultSet.next()) {
                // Création d'un objet SecteurDelimitation
                result = new SecteurDelimitation();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer le SecteurDelimitation", ex);

            // Si une erreur s'est produite, on renvoie null
            result = null;
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet SecteurDelimitation avec les valeurs d'un enregistrement de la table fleetview_secteur_delimitation
     *
     * @param secteurDelimitation L'objet SecteurDelimitation à remplir
     * @param resultSet           Le résultat de la requête de sélection
     */
    protected void fillObject(SecteurDelimitation secteurDelimitation, ResultSet resultSet) {
        try {
            // Remplissage de l'objet SecteurDelimitation
            secteurDelimitation.setIdSecteur(resultSet.getInt("id_secteur"));
            secteurDelimitation.setIdSecteurPoint(resultSet.getObject("id_secteur_point", Integer.class));
            secteurDelimitation.setOrdre(resultSet.getObject("ordre", Integer.class));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet SecteurDelimitation", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, SecteurDelimitation before, SecteurDelimitation after) {
        // Corps à faire, pour le moment lève une exception
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
