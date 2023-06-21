package net.cnam.fleetview.model.cyclecomplet;

import net.cnam.fleetview.model.DAO;
import net.cnam.fleetview.model.TypeHistorique;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.utilisateur.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CycleCompletDAO extends DAO<CycleComplet> {
    /**
     * Constructeur d'un objet d'accès à la base
     *
     * @param connection un objet connection de java.sql
     */
    public CycleCompletDAO(Connection connection) {
        super(connection);
    }

    /**
     * Méthode non supportée par cette classe
     */
    @Override
    public boolean create(CycleComplet obj, Utilisateur user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Méthode non supportée par cette classe
     */
    @Override
    public boolean delete(CycleComplet obj, Utilisateur user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
     * Méthode non supportée par cette classe
     */
    @Override
    public boolean update(CycleComplet obj, Utilisateur user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Méthode non supportée par cette classe
     */
    @Override
    public List<CycleComplet> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Méthode non supportée par cette classe
     */
    @Override
    public CycleComplet getById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Méthode permettant de remplir un objet CycleComplet
     *
     * @param cycleComplet L'objet CycleComplet à remplir
     * @param resultSet    Le résultat de la requête de sélection
     */
    protected void fillObject(CycleComplet cycleComplet, ResultSet resultSet) {
        try {
            // Remplissage de l'objet CycleFournisseur
            cycleComplet.setIdCycle(resultSet.getInt("id_cycle"));
            cycleComplet.setIdentifiant(resultSet.getString("identifiant"));
            cycleComplet.setChargeMaximale(resultSet.getObject("charge_maximale", Double.class));
            cycleComplet.setDateAcquisition(resultSet.getObject("date_acquisition", LocalDateTime.class));
            cycleComplet.setMarque(resultSet.getString("marque_nom"));
            cycleComplet.setNomFournisseur(resultSet.getString("fournisseur_nom"));
            cycleComplet.setType(resultSet.getString("type_nom"));
            cycleComplet.setDateDerniereRevision(resultSet.getObject("date_revision", LocalDateTime.class));
            cycleComplet.setEtat(resultSet.getString("etat_nom"));
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de remplir l'objet CycleComplet", ex);
        }
    }

    /**
     * Méthode non supportée par cette classe
     */
    @Override
    protected void handleHistorique(TypeHistorique type, Utilisateur user, CycleComplet before, CycleComplet after) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Méthode de récupération de tous les enregistrements des Cycle non archivés
     *
     * @return Une List d'objets Cycle, vide en cas d'erreur ou si la table est vide
     */
    public List<CycleComplet> getAllNotArchived() {
        // Requête de sélection
        String query = "SELECT fc.id_cycle, fc.identifiant, fc.charge_maximale, fc.date_acquisition, fcma.nom AS marque_nom, fcf.nom AS fournisseur_nom, fct.nom AS type_nom, MAX(fcr.date_revision) AS date_revision, fcet.nom AS etat_nom" +
                "       FROM fleetview_cycle AS fc" +
                "       LEFT JOIN fleetview_cycle_modele AS fcmo ON fc.id_cycle_modele = fcmo.id_cycle_modele" +
                "       LEFT JOIN fleetview_cycle_marque AS fcma ON fcmo.id_cycle_marque = fcma.id_cycle_marque" +
                "       LEFT JOIN fleetview_cycle_fournisseur AS fcf ON fc.id_cycle_fournisseur = fcf.id_cycle_fournisseur" +
                "       LEFT JOIN fleetview_cycle_type AS fct ON fc.id_cycle_type = fct.id_cycle_type" +
                "       LEFT JOIN fleetview_cycle_revision AS fcr ON fc.id_cycle = fcr.id_cycle" +
                "       LEFT JOIN fleetview_cycle_etat AS fce ON fc.id_cycle = fce.id_cycle" +
                "       LEFT JOIN (SELECT fce2.id_cycle AS ids_cycle, MAX(fce2.date_debut) AS date_max " +
                "       FROM fleetview_cycle_etat AS fce2 GROUP BY fce2.id_cycle) AS fce3 ON fc.id_cycle = fce3.ids_cycle" +
                "       LEFT JOIN fleetview_cycle_etat_type AS fcet ON fcet.id_cycle_etat_type = fce.id_cycle_etat_type" +
                "       WHERE fc.date_archive IS NULL" +
                "       AND (fce.date_debut = fce3.date_max OR fce3.ids_cycle IS NULL)" +
                "       GROUP BY fc.id_cycle;";

        // Résultat de la requête
        List<CycleComplet> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // On prépare la requête de sélection
            statement = this.connection.prepareStatement(query);

            // On exécute la requête et on récupère le résultat
            resultSet = statement.executeQuery();

            // On parcourt le résultat pour créer les objets Course correspondants
            while (resultSet.next()) {
                // Création d'un objet CycleFournisseur
                CycleComplet cycleComplet = new CycleComplet();

                // On remplit l'objet avec les informations issues de la requête
                this.fillObject(cycleComplet, resultSet);

                // On ajoute l'objet au résultat final
                result.add(cycleComplet);
            }
        } catch (SQLException ex) {
            // On log l'erreur
            logger.error("Impossible de récupérer les CycleComplet", ex);

            // Si une erreur s'est produite, on renvoie la liste vide
            result = null;
        } finally {
            // On ferme les ressources ouvertes par la requête
            this.closeResource(resultSet);
            this.closeResource(statement);
        }

        return result;
    }
}
