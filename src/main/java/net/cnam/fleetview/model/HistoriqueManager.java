package net.cnam.fleetview.model;

import net.cnam.fleetview.model.historique.Historique;
import net.cnam.fleetview.model.historique.HistoriqueDAO;
import net.cnam.fleetview.model.historiquedata.HistoriqueData;
import net.cnam.fleetview.model.historiquedata.HistoriqueDataDAO;
import net.cnam.fleetview.model.historiquetype.HistoriqueType;
import net.cnam.fleetview.model.historiquetype.HistoriqueTypeDAO;
import net.cnam.fleetview.model.utilisateur.Utilisateur;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.HashMap;

public class HistoriqueManager {
    private static final HashMap<Connection, HistoriqueManager> instances = new HashMap<>();

    public static HistoriqueManager getInstance(Connection connection) {
        if (!instances.containsKey(connection)) {
            instances.put(connection, new HistoriqueManager(connection));
        }
        return instances.get(connection);
    }

    // Connection à la base de données
    private final Connection connection;

    // DAO
    private final HistoriqueTypeDAO historiqueTypeDAO;
    private final HistoriqueDAO historiqueDAO;
    private final HistoriqueDataDAO historiqueDataDAO;

    // Types d'historique
    private HistoriqueType typeAjout;
    private HistoriqueType typeModification;
    private HistoriqueType typeSuppression;
    private HistoriqueType typeArchivage;

    private HistoriqueManager(Connection connection) {
        this.connection = connection;
        this.historiqueTypeDAO = new HistoriqueTypeDAO(connection);
        this.historiqueDAO = new HistoriqueDAO(connection);
        this.historiqueDataDAO = new HistoriqueDataDAO(connection);

        checkTypes();
    }

    /**
     * Méthode permettant de vérifier que les types d'historique sont présents en base
     */
    private void checkTypes() {
        // Vérification des types d'historique en base
        typeAjout = historiqueTypeDAO.getById(1);
        typeModification = historiqueTypeDAO.getById(2);
        typeSuppression = historiqueTypeDAO.getById(3);
        typeArchivage = historiqueTypeDAO.getById(4);

        // Création des types d'historique en base si inexistants
        if (typeAjout == null) {
            typeAjout = new HistoriqueType();
            typeAjout.setNom("Ajout");
            historiqueTypeDAO.create(typeAjout, null);
        }

        if (typeModification == null) {
            typeModification = new HistoriqueType();
            typeModification.setNom("Modification");
            historiqueTypeDAO.create(typeModification, null);
        }

        if (typeSuppression == null) {
            typeSuppression = new HistoriqueType();
            typeSuppression.setNom("Suppression");
            historiqueTypeDAO.create(typeSuppression, null);
        }

        if (typeArchivage == null) {
            typeArchivage = new HistoriqueType();
            typeArchivage.setNom("Archivage");
            historiqueTypeDAO.create(typeArchivage, null);
        }

        // Vérification des types d'historique en base
        if (!typeAjout.getNom().equals("Ajout")) {
            typeAjout.setNom("Ajout");
            historiqueTypeDAO.update(typeAjout, null);
        }

        if (!typeModification.getNom().equals("Modification")) {
            typeModification.setNom("Modification");
            historiqueTypeDAO.update(typeModification, null);
        }

        if (!typeSuppression.getNom().equals("Suppression")) {
            typeSuppression.setNom("Suppression");
            historiqueTypeDAO.update(typeSuppression, null);
        }

        if (!typeArchivage.getNom().equals("Archivage")) {
            typeArchivage.setNom("Archivage");
            historiqueTypeDAO.update(typeArchivage, null);
        }
    }

    /**
     * Méthode permettant d'ajouter un historique
     *
     * @param type   le type d'historique
     * @param user   l'utilisateur ayant effectué l'action
     * @param table  la table concernée par l'historique
     * @param id     l'identifiant de la ligne concernée par l'historique
     * @param champs les données de l'historique
     */
    public void addHistorique(TypeHistorique type, Utilisateur user, String table, int id, HistoriqueData... champs) {
        addHistorique(type, user, table, String.valueOf(id), champs);
    }

    /**
     * Méthode permettant d'ajouter un historique
     *
     * @param type   le type d'historique
     * @param user   l'utilisateur ayant effectué l'action
     * @param table  la table concernée par l'historique
     * @param id     l'identifiant de la ligne concernée par l'historique
     * @param champs les données de l'historique
     */
    public void addHistorique(TypeHistorique type, Utilisateur user, String table, String id, HistoriqueData... champs) {
        // Création de l'historique
        Historique historique = new Historique();
        historique.setTableTuple(table);
        historique.setIdentifiantTuple(id);
        historique.setDateChangementTuple(LocalDateTime.now());
        switch (type) {
            case ADD -> historique.setIdHistoriqueType(typeAjout.getIdHistoriqueType());
            case UPDATE -> historique.setIdHistoriqueType(typeModification.getIdHistoriqueType());
            case DELETE -> historique.setIdHistoriqueType(typeSuppression.getIdHistoriqueType());
            case ARCHIVE -> historique.setIdHistoriqueType(typeArchivage.getIdHistoriqueType());
        }
        historique.setIdUtilisateur(user.getIdUtilisateur());
        historiqueDAO.create(historique, null);

        // Ajout des données de l'historique
        for (HistoriqueData champ : champs) {
            if (champ == null) {
                continue;
            }

            champ.setIdHistorique(historique.getIdHistorique());
            historiqueDataDAO.create(champ, null);
        }
    }
}
