package net.cnam.fleetview.model.droits;

/**
 * Classe Droits
 * <p>
 * Cette classe permet de créer des objets Droits.
 * table concernée : fleetview_droits
 */
public class Droits {
    /**
     * Identifiant du droits
     */
    private Integer idDroits;
    /**
     * Nom du droits
     */
    private String nom;

    /**
     * Constructeur par défaut
     */
    public Droits() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant du droits
     *
     * @return l'identifiant du droits
     */
    public Integer getIdDroits() {
        return idDroits;
    }

    /**
     * Définit l'identifiant du droits
     *
     * @param idDroits le nouvel identifiant du droits
     */
    public void setIdDroits(Integer idDroits) {
        this.idDroits = idDroits;
    }

    /**
     * Récupère le nom du droits
     *
     * @return le nom du droits
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du droits
     *
     * @param nom le nouveau nom du droits
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
