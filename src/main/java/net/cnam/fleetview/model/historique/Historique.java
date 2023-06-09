package net.cnam.fleetview.model.historique;

import java.time.LocalDateTime;

/**
 * Classe Historique
 * <p>
 * Cette classe permet de créer des objets Historique.
 * table concernée : fleetview_historique
 */
public class Historique {
    /**
     * Identifiant de l'historique
     */
    private int idHistorique;
    /**
     * Table concernée par l'historique
     */
    private String tableTuple;
    /**
     * Identifiant du tuple concerné par l'historique
     */
    private String idTuple;
    /**
     * Date de l'historique du tuple
     */
    private LocalDateTime dateChangementTuple;
    /**
     * Identifiant du type d'historique
     */
    private int idHistoriqueType;
    /**
     * Identifiant de l'utilisateur ayant effectué l'historique
     */
    private int idUtilisateur;

    /**
     * Constructeur par défaut
     */
    public Historique() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant de l'historique
     *
     * @return l'identifiant de l'historique
     */
    public int getIdHistorique() {
        return idHistorique;
    }

    /**
     * Définit l'identifiant de l'historique
     *
     * @param idHistorique le nouvel identifiant de l'historique
     */
    public void setIdHistorique(int idHistorique) {
        this.idHistorique = idHistorique;
    }

    /**
     * Récupère la table concernée par l'historique
     *
     * @return la table concernée par l'historique
     */
    public String getTableTuple() {
        return tableTuple;
    }

    /**
     * Définit la table concernée par l'historique
     *
     * @param tableTuple la nouvelle table concernée par l'historique
     */
    public void setTableTuple(String tableTuple) {
        this.tableTuple = tableTuple;
    }

    /**
     * Récupère l'identifiant du tuple concerné par l'historique
     *
     * @return l'identifiant du tuple concerné par l'historique
     */
    public String getIdTuple() {
        return idTuple;
    }

    /**
     * Définit l'identifiant du tuple concerné par l'historique
     *
     * @param idTuple le nouvel identifiant du tuple concerné par l'historique
     */
    public void setIdTuple(String idTuple) {
        this.idTuple = idTuple;
    }

    /**
     * Récupère la date de l'historique du tuple
     *
     * @return la date de l'historique du tuple
     */
    public LocalDateTime getDateChangementTuple() {
        return dateChangementTuple;
    }

    /**
     * Définit la date de l'historique du tuple
     *
     * @param dateChangementTuple la nouvelle date de l'historique du tuple
     */
    public void setDateChangementTuple(LocalDateTime dateChangementTuple) {
        this.dateChangementTuple = dateChangementTuple;
    }

    /**
     * Récupère l'identifiant du type d'historique
     *
     * @return l'identifiant du type d'historique
     */
    public int getIdHistoriqueType() {
        return idHistoriqueType;
    }

    /**
     * Définit l'identifiant du type d'historique
     *
     * @param idHistoriqueType le nouvel identifiant du type d'historique
     */
    public void setIdHistoriqueType(int idHistoriqueType) {
        this.idHistoriqueType = idHistoriqueType;
    }

    /**
     * Récupère l'identifiant de l'utilisateur ayant effectué l'historique
     *
     * @return l'identifiant de l'utilisateur ayant effectué l'historique
     */
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * Définit l'identifiant de l'utilisateur ayant effectué l'historique
     *
     * @param idUtilisateur le nouvel identifiant de l'utilisateur ayant effectué l'historique
     */
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
