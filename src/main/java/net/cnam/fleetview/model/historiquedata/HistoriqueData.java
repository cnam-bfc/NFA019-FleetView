package net.cnam.fleetview.model.historiquedata;

/**
 * Classe HistoriqueData
 * <p>
 * Cette classe permet de créer des objets HistoriqueData.
 * table concernée : fleetview_historique_data
 */
public class HistoriqueData {
    /**
     * Identifiant de l'historique
     */
    private int idHistoriqueData;
    /**
     * Nom du champ concerné par l'historique
     */
    private String nomChamp;
    /**
     * Valeur du champ concerné par l'historique après modification
     */
    private String valeurChamp;
    /**
     * Identifiant de l'historique
     */
    private int idHistorique;

    /**
     * Constructeur par défaut
     */
    public HistoriqueData() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant de l'historique
     *
     * @return l'identifiant de l'historique
     */
    public int getIdHistoriqueData() {
        return idHistoriqueData;
    }

    /**
     * Définit l'identifiant de l'historique
     *
     * @param idHistoriqueData le nouvel identifiant de l'historique
     */
    public void setIdHistoriqueData(int idHistoriqueData) {
        this.idHistoriqueData = idHistoriqueData;
    }

    /**
     * Récupère le nom du champ concerné par l'historique
     *
     * @return le nom du champ concerné par l'historique
     */
    public String getNomChamp() {
        return nomChamp;
    }

    /**
     * Définit le nom du champ concerné par l'historique
     *
     * @param nomChamp le nouveau nom du champ concerné par l'historique
     */
    public void setNomChamp(String nomChamp) {
        this.nomChamp = nomChamp;
    }

    /**
     * Récupère la valeur du champ concerné par l'historique après modification
     *
     * @return la valeur du champ concerné par l'historique après modification
     */
    public String getValeurChamp() {
        return valeurChamp;
    }

    /**
     * Définit la valeur du champ concerné par l'historique après modification
     *
     * @param valeurChamp la nouvelle valeur du champ concerné par l'historique après modification
     */
    public void setValeurChamp(String valeurChamp) {
        this.valeurChamp = valeurChamp;
    }

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
}
