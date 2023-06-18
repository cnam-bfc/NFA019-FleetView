package net.cnam.fleetview.model.coursierutilisateur;

import net.cnam.fleetview.model.DAO;
import net.cnam.fleetview.model.TypeHistorique;
import net.cnam.fleetview.model.coursier.Coursier;
import net.cnam.fleetview.model.historiquedata.HistoriqueData;
import net.cnam.fleetview.model.utilisateur.Utilisateur;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe CoursierUtilisateurDAO
 * <p>
 * Cette classe permet de créer des objets CoursierUtilisateur.
 * table concernée : fleetview_coursier et fleetview_utilisateur
 * Uniquement pour de la lecture
 */
public class CoursierUtilisateurDAO extends DAO<CoursierUtilisateur>  {

    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public CoursierUtilisateurDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode non implantée pour cette classe
     *
     * Exception levée si la méthode est appelée
     */
    @Override
    public boolean create(CoursierUtilisateur obj, Utilisateur user) {
        throw new UnsupportedOperationException("Méthode non implantée.");
    }

    /**
     * Méthode non implantée pour cette classe
     *
     * Exception levée si la méthode est appelée
     */
    @Override
    public boolean delete(CoursierUtilisateur obj, Utilisateur user) {
        throw new UnsupportedOperationException("Méthode non implantée.");
    }

    /**
     * Méthode non implantée pour cette classe
     *
     * Exception levée si la méthode est appelée
     */
    @Override
    public boolean update(CoursierUtilisateur obj, Utilisateur user) {
        throw new UnsupportedOperationException("Méthode non implantée.");
    }

    /**
     * Méthode de récupération de tous les enregistrements des CoursierUtilisateur
     *
     * @return Une List d'objets CoursierUtilisateur, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<CoursierUtilisateur> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_coursier, fleetview_utilisateur WHERE fleetview_coursier.id_utilisateur = fleetview_utilisateur.id_utilisateur";

        // Résultat de la requête
        List<CoursierUtilisateur> result = new LinkedList<>();
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
                CoursierUtilisateur coursierUtilisateur = new CoursierUtilisateur();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(coursierUtilisateur, resultSet);

                // On ajoute l'objet au résultat final
                result.add(coursierUtilisateur);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les CoursierUtilisateur", ex);

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
     * Méthode de récupération de tous les enregistrements des CoursierUtilisateur non archivés
     *
     * @return Une List d'objets CoursierUtilisateur, vide en cas d'erreur ou si la table est vide
     */
    public List<CoursierUtilisateur> getAllNotArchived() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_coursier, fleetview_utilisateur WHERE fleetview_coursier.id_utilisateur = fleetview_utilisateur.id_utilisateur AND (fleetview_utilisateur.date_archive IS NULL OR fleetview_utilisateur.date_archive < NOW())";

        // Résultat de la requête
        List<CoursierUtilisateur> result = new LinkedList<>();
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
                CoursierUtilisateur coursierUtilisateur = new CoursierUtilisateur();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(coursierUtilisateur, resultSet);

                // On ajoute l'objet au résultat final
                result.add(coursierUtilisateur);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les CoursierUtilisateur", ex);

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
     * Méthode non implantée pour cette classe.
     * Utiliser : getAllByIdUtilisateur(Utilisateur user)
     * ou
     * getAllByIdCoursier(Coursier coursier)
     *
     * Exception levée si la méthode est appelée
     */
    @Override
    public CoursierUtilisateur getById(int id) {
        throw new UnsupportedOperationException("Méthode non implantée.");
    }

    /**
     * Méthode permettant de récupérer un coursier par son IdCoursier
     *
     * @param idCoursier L'IdCoursier du coursier à récupérer
     * @return Un objet CoursierUtilisateur correspondant, null si aucun coursier ne correspond à l'IdCoursier
     */
    public CoursierUtilisateur getByIdCoursier(int idCoursier) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_coursier, fleetview_utilisateur WHERE fleetview_coursier.id_utilisateur = fleetview_utilisateur.id_utilisateur AND fleetview_coursier.id_coursier = ?";

        // Résultat de la requête
        CoursierUtilisateur result = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);
            statement.setInt(1, idCoursier);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On vérifie que le résultat n'est pas vide
            if (resultSet.next()) {
                // Création d'un objet CycleFournisseur
                result = new CoursierUtilisateur();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer le CoursierUtilisateur", ex);

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
     * Méthode permettant de remplir un objet CoursierUtilisateur avec les valeurs d'un enregistrement de la table fleetview_coursier et fleetview_utilisateur
     *
     * @param coursierUtilisateur  L'objet CoursierUtilisateur à remplir
     * @param resultSet Le résultat de la requête de sélection
     */
    protected void fillObject(CoursierUtilisateur coursierUtilisateur, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            coursierUtilisateur.setIdCoursier(resultSet.getInt("id_coursier"));
            coursierUtilisateur.setMatricule(resultSet.getString("matricule"));
            coursierUtilisateur.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
            coursierUtilisateur.setNom(resultSet.getString("nom"));
            coursierUtilisateur.setPrenom(resultSet.getString("prenom"));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet CoursierUtilisateur", ex);
        }
    }

    /**
     * Méthode non supportée pour cette DAO
     *
     * Exception levée si la méthode est appelée
     */
    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, CoursierUtilisateur before, CoursierUtilisateur after) {
        throw new UnsupportedOperationException("Méthode non supportée.");
    }
}
