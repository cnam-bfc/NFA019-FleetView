package net.cnam.fleetview.model.historiquetype;

/**
 * Classe HistoriqueType
 * <p>
 * Cette classe permet de créer des objets HistoriqueType.
 * table concernée : fleetview_historique_type
 */
public class HistoriqueType {
    /**
     * Identifiant du type d'historique
     */
    private int idHistoriqueType;
    /**
     * Libellé du type d'historique
     */
    private String nom;

    /**
     * Constructeur par défaut
     */
    public HistoriqueType() {
    }

    // GETTERS & SETTERS

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
     * Récupère le libellé du type d'historique
     *
     * @return le libellé du type d'historique
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le libellé du type d'historique
     *
     * @param nom le nouveau libellé du type d'historique
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
