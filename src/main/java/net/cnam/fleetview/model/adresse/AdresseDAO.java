package net.cnam.fleetview.model.adresse;

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
 * Classe DAO pour les adresses
 * <p>
 * Concerne la table : fleetview_adresse
 */
public class AdresseDAO extends DAO<Adresse> implements Archivable<Adresse> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public AdresseDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode de création d'un enregistrement d'une Adresse
     *
     * @param obj  Un objet Adresse à écrire dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la création a réussi, false dans le cas contraire
     */
    @Override
    public boolean create(Adresse obj, Utilisateur user) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getIdAdresse() != null) {
            logger.error("L'objet Adresse a déjà un ID");
            return false;
        }

        // Requête d'insertion
        String query = "INSERT INTO fleetview_adresse (osm_type, osm_id, pays, code_postal, commune, rue, numero_rue, complement, date_archive, id_secteur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête d'insertion
            statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getOsmType());
            statement.setObject(2, obj.getOsmId());
            statement.setString(3, obj.getPays());
            statement.setString(4, obj.getCodePostal());
            statement.setString(5, obj.getCommune());
            statement.setString(6, obj.getRue());
            statement.setString(7, obj.getNumeroDeRue());
            statement.setString(8, obj.getComplement());
            statement.setObject(9, obj.getDateArchive());
            statement.setObject(10, obj.getIdSecteur());

            // On exécute la requête
            result = statement.executeUpdate();

            // Si la requête a réussi
            if (result != 0) {
                // On récupère l'id auto-généré par la requête d'insertion
                int id = statement.getGeneratedKeys().getObject(1, Integer.class);

                // On met à jour l'objet pour lui attribuer l'id récupéré
                obj.setIdAdresse(id);
            }

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ADD, user, null, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'insérer l'Adresse", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la création de l'Adresse, aucune ligne ajoutée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de suppression d'un enregistrement d'une Adresse
     *
     * @param obj  Un objet Adresse à supprimer dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la suppression a réussi, false dans le cas contraire
     */
    @Override
    public boolean delete(Adresse obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdAdresse() == null) {
            logger.error("L'objet Adresse n'a pas d'ID");
            return false;
        }

        // Requête de suppression
        String query = "DELETE FROM fleetview_adresse WHERE id_adresse = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de suppression
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getIdAdresse());

            // Récupération de l'objet avant suppression
            Adresse objAvantSuppression = this.getById(obj.getIdAdresse());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.DELETE, user, objAvantSuppression, null);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de supprimer l'Adresse", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la suppression de l'adresse, aucune ligne supprimée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode d'archivage d'un enregistrement de l'adresse
     *
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    public boolean archive(Adresse obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdAdresse() == null) {
            logger.error("L'objet Adresse n'a pas d'ID");
            return false;
        }

        // Si la date d'archive n'est pas renseignée, on la met à jour
        if (obj.getDateArchive() == null) {
            obj.setDateArchive(LocalDateTime.now());
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_adresse SET date_archive = ? WHERE id_adresse = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setObject(1, obj.getDateArchive());
            statement.setObject(2, obj.getIdAdresse());

            // Récupération de l'objet avant mise à jour
            Adresse objAvantMAJ = this.getById(obj.getIdAdresse());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.ARCHIVE, user, objAvantMAJ, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible d'archiver l'Adresse", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de l'archivage de l'Adresse, aucune ligne mise à jour dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de mise à jour d'un enregistrement de l'Adresse
     *
     * @param obj  Un objet Adresse à mettre à jour dans la base
     * @param user Utilisateur originaire de la modification
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le cas contraire
     */
    @Override
    public boolean update(Adresse obj, Utilisateur user) {
        // On vérifie que l'objet possède un ID
        if (obj.getIdAdresse() == null) {
            logger.error("L'objet Adresse n'a pas d'ID");
            return false;
        }

        // Requête de mise à jour
        String query = "UPDATE fleetview_adresse SET osm_type = ?, osm_id = ?, pays = ?, code_postal = ?, commune = ?, rue = ?, numero_rue = ?, complement = ?, date_archive = ?, id_secteur = ? WHERE id_adresse = ?";

        // Résultat de la requête
        int result = 0;
        PreparedStatement statement = null;

        try {
            // On prépare la requête de mise à jour
            statement = this.connection.prepareStatement(query);
            // On attribue les valeurs aux paramètres
            statement.setString(1, obj.getOsmType());
            statement.setObject(2, obj.getOsmId());
            statement.setString(3, obj.getPays());
            statement.setString(4, obj.getCodePostal());
            statement.setString(5, obj.getCommune());
            statement.setString(6, obj.getRue());
            statement.setString(7, obj.getNumeroDeRue());
            statement.setString(8, obj.getComplement());
            statement.setObject(9, obj.getDateArchive());
            statement.setObject(10, obj.getIdSecteur());
            statement.setObject(11, obj.getIdAdresse());

            // Récupération de l'objet avant modification
            Adresse objAvantModification = this.getById(obj.getIdAdresse());

            // On exécute la requête
            result = statement.executeUpdate();

            // On ajoute l'historique
            this.handleHistorique(TypeHistorique.UPDATE, user, objAvantModification, obj);
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de mettre à jour l'Adresse", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(statement);
        }

        // Si la requête a échoué
        if (result == 0) {
            logger.error("Échec de la mise à jour de l'Adresse, aucune ligne modifiée dans la table.");
            return false;
        }

        return true;
    }

    /**
     * Méthode de récupération de tous les enregistrements des adresses
     *
     * @return Une List d'objets Adresse, vide en cas d'erreur ou si la table est vide
     */
    @Override
    public List<Adresse> getAll() {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_adresse";

        // Résultat de la requête
        List<Adresse> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets Adresse correspondants
            while (resultSet.next()) {
                // Création d'un objet Adresse
                Adresse adresse = new Adresse();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(adresse, resultSet);

                // On ajoute l'objet au résultat final
                result.add(adresse);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les Adresser", ex);

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
     * Méthode de récupération d'un enregistrement d'une adresse par son identifiant.
     *
     * @param id L'identificateur à rechercher
     * @return Un objet Adresse correspondant à l'enregistrement trouvé dans la base, null si aucun enregistrement n'a été trouvé
     */
    @Override
    public Adresse getById(int id) {
        // Requête de sélection
        String query = "SELECT * FROM fleetview_adresse WHERE id_adresse = ?";

        // Résultat de la requête
        Adresse result = null;
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
                // Création d'un objet Adresse
                result = new Adresse();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(result, resultSet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer une Adresse", ex);
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }

    /**
     * Méthode permettant de remplir un objet Adresse avec les valeurs d'un enregistrement de la table fleetview_adresse
     *
     * @param adresse   L'objet Adresse à remplir
     * @param resultSet Le résultat de la requête de sélection
     */
    protected void fillObject(Adresse adresse, ResultSet resultSet) {
        try {
            // Remplissage de l'objet Adresse
            adresse.setIdAdresse(resultSet.getObject("id_adresse", Integer.class));
            adresse.setOsmType(resultSet.getString("osm_type"));
            adresse.setOsmId(resultSet.getObject("osm_id", Long.class));
            adresse.setPays(resultSet.getString("pays"));
            adresse.setCodePostal(resultSet.getString("code_postal"));
            adresse.setCommune(resultSet.getString("commune"));
            adresse.setRue(resultSet.getString("rue"));
            adresse.setNumeroDeRue(resultSet.getString("numero_de_rue"));
            adresse.setComplement(resultSet.getString("complement"));
            adresse.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
            adresse.setIdSecteur(resultSet.getObject("id_secteur", Integer.class));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet Adresse", ex);
        }
    }

    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, Adresse before, Adresse after) {
        // Récupération de l'identifiant unique de l'objet
        int id = before != null ? before.getIdAdresse() : after != null ? after.getIdAdresse() : -1;

        if (before != null && after != null && before.getIdAdresse() != after.getIdAdresse()) {
            logger.error("Impossible de créer l'historique, les deux objets Adresse ont des identifiants différents");
        } else if (id == -1) {
            logger.error("Impossible de créer l'historique, les deux objets Adresse sont null");
        }

        // Construction des changements
        HistoriqueData osmType = this.checkChanges("osm_type", before != null ? before.getOsmType() : null, after != null ? after.getOsmType() : null);
        HistoriqueData osmId = this.checkChanges("osm_id", before != null ? before.getOsmId() : null, after != null ? after.getOsmId() : null);
        HistoriqueData pays = this.checkChanges("pays", before != null ? before.getPays() : null, after != null ? after.getPays() : null);
        HistoriqueData codePostal = this.checkChanges("code_postal", before != null ? before.getCodePostal() : null, after != null ? after.getCodePostal() : null);
        HistoriqueData commune = this.checkChanges("commune", before != null ? before.getCommune() : null, after != null ? after.getCommune() : null);
        HistoriqueData rue = this.checkChanges("rue", before != null ? before.getRue() : null, after != null ? after.getRue() : null);
        HistoriqueData numeroDeRue = this.checkChanges("numero_rue", before != null ? before.getNumeroDeRue() : null, after != null ? after.getNumeroDeRue() : null);
        HistoriqueData complement = this.checkChanges("complement", before != null ? before.getComplement() : null, after != null ? after.getComplement() : null);
        HistoriqueData dateArchive = this.checkChanges("date_archive", before != null ? before.getDateArchive() : null, after != null ? after.getDateArchive() : null);
        HistoriqueData idSecteur = this.checkChanges("id_secteur", before != null ? before.getIdSecteur() : null, after != null ? after.getIdSecteur() : null);

        // Création de l'historique
        this.historique.addHistorique(type, user, "fleetview_adresse", id, osmType, osmId, pays, codePostal, commune, rue, numeroDeRue, complement, dateArchive, idSecteur);
    }
}
