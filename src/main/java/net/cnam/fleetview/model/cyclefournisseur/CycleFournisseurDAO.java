package net.cnam.fleetview.model.cyclefournisseur;

import net.cnam.fleetview.model.DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CycleFournisseurDAO extends DAO<CycleFournisseur> {

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
     * @param obj un objet CycleFournisseur à écrire dans la base
     * @return boolean qui vaut true si la création a réussi, false dans le cas
     * contraire
     */
    @Override
    public boolean create(CycleFournisseur obj) {
        // On vérifie que l'objet n'a pas d'ID
        if (obj.getId() != 0) {
            System.out.println("Objet possédant déjà un ID enregistré");
            return false;
        }
        try {
            // on prépare la requête d'insertion
            String query = "INSERT INTO fleetview_cycle_fournisseur (nom, mail, telephone, date_archive) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // on attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getMail());
            statement.setString(3, obj.getTelephone());
            statement.setObject(4, obj.getDateArchive());
            // on exécute la requête
            int result = statement.executeUpdate();
            // Vérification du nombre de lignes affectées par la requête d'insertion
            if (result == 0) {
                throw new SQLException("Echec de la création du cycle fournisseur, aucune ligne ajoutée dans la table.");
            }
            // on récupère l'id auto-généré par la requête d'insertion
            int id = statement.getGeneratedKeys().getInt(1);
            // on met à jour l'objet pour lui attribuer l'id récupéré
            obj.setId(id);
            statement.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    /**
     * Méthode de suppression d'un enregistrement de cycle fournisseur
     *
     * @param obj un objet CycleFournisseur à supprimer dans la base
     * @return boolean qui vaut true si la suppression a réussi, false dans le
     * cas contraire
     */
    @Override
    public boolean delete(CycleFournisseur obj) {
        // On vérifie que l'objet possède un ID
        if (obj.getId() == 0) {
            System.out.println("Objet ne possédant pas d'ID enregistré");
            return false;
        }
        try {
            // on prépare la requête de suppression
            String query = "DELETE FROM fleetview_cycle_fournisseur WHERE id = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            // on attribue les valeurs aux paramètres
            statement.setInt(1, obj.getId());
            // on exécute la requête
            int result = statement.executeUpdate();
            // Vérification du nombre de lignes affectées par la requête de suppression
            if (result == 0) {
                throw new SQLException("Echec de la suppression du cycle fournisseur, aucune ligne supprimée de la table.");
            }
            statement.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    /**
     * Méthode de mise à jour d'un enregistrement de cycle fournisseur
     *
     * @param obj un objet CycleFournisseur à mettre à jour dans la base
     * @return boolean qui vaut true si la mise à jour a réussi, false dans le
     * cas contraire
     */
    @Override
    public boolean update(CycleFournisseur obj) {
        // On vérifie que l'objet possède un ID
        if (obj.getId() == 0) {
            System.out.println("Objet ne possédant pas d'ID enregistré");
            return false;
        }
        try {
            // on prépare la requête de mise à jour
            String query = "UPDATE fleetview_cycle_fournisseur SET nom = ?, mail = ?, telephone = ?, date_archive = ? WHERE id = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            // on attribue les valeurs aux paramètres
            statement.setString(1, obj.getNom());
            statement.setString(2, obj.getMail());
            statement.setString(3, obj.getTelephone());
            statement.setObject(4, obj.getDateArchive());
            statement.setInt(5, obj.getId());
            // on exécute la requête
            int result = statement.executeUpdate();
            // Vérification du nombre de lignes affectées par la requête de mise à jour
            if (result == 0) {
                throw new SQLException("Echec de la mise à jour du cycle fournisseur, aucune ligne modifiée dans la table.");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    /**
     * Méthode de récupération de tous les enregistrements de cycle fournisseur
     *
     * @return une List d'objets CycleFournisseur
     */
    @Override
    public List<CycleFournisseur> getAll() {
        try {
            // on prépare la requête de sélection
            String query = "SELECT * FROM fleetview_cycle_fournisseur";
            PreparedStatement statement = this.connection.prepareStatement(query);
            // on exécute la requête et on récupère le résultat
            ResultSet resultSet = statement.executeQuery();
            // on parcourt le résultat pour créer les objets CycleFournisseur correspondants
            List<CycleFournisseur> cycleFournisseurs = null;
            while (resultSet.next()) {
                if (cycleFournisseurs == null) {
                    cycleFournisseurs = new ArrayList<>();
                }
                cycleFournisseurs.add(fillObject(new CycleFournisseur(), resultSet));
            }
            return cycleFournisseurs;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    /**
     * Méthode de récupération d'un enregistrement de cycle fournisseur par son identifiant.
     *
     * @param id l'identificateur à rechercher
     * @return un objet CycleFournisseur
     */
    @Override
    public CycleFournisseur selectById(int id) {
        try {
            // on prépare la requête de sélection
            String query = "SELECT * FROM fleetview_cycle_fournisseur WHERE id = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            // on attribue les valeurs aux paramètres
            statement.setInt(1, id);
            // on exécute la requête et on récupère le résultat
            ResultSet resultSet = statement.executeQuery();
            // on vérifie que le résultat n'est pas vide, si oui on retourne null
            if (!resultSet.next()) {
                return null;
            }
            // on crée l'objet CycleFournisseur correspondant à la première ligne du résultat
            return fillObject(new CycleFournisseur(), resultSet);
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    /**
     * Méthode permettant de remplir un objet CycleFournisseur avec les valeurs d'un enregistrement de la table fleetview_cycle_fournisseur
     *
     * @param cycleFournisseur l'objet CycleFournisseur à remplir
     * @param resultSet        le résultat de la requête de sélection
     * @return un objet CycleFournisseur rempli
     */
    private CycleFournisseur fillObject(CycleFournisseur cycleFournisseur, ResultSet resultSet) {
        try {
            cycleFournisseur.setId(resultSet.getInt("id"));
            cycleFournisseur.setNom(resultSet.getString("nom"));
            cycleFournisseur.setMail(resultSet.getString("mail"));
            cycleFournisseur.setTelephone(resultSet.getString("telephone"));
            cycleFournisseur.setDateArchive(resultSet.getObject("date_archive", LocalDateTime.class));
            return cycleFournisseur;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null; // lever exception ?
        }
    }
}
