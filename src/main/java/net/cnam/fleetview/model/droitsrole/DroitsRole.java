package net.cnam.fleetview.model.droitsrole;

/**
 * Classe DroitsRole
 * <p>
 * Cette classe permet de créer des objets DroitsRole.
 * table concernée : fleetview_droits_role
 */
public class DroitsRole {
    /**
     * Identifiant du role
     */
    private Integer idRole;
    /**
     * Identifiant du droits
     */
    private Integer idDroits;
    /**
     * Boolean permettant de définir si le droit est autorisé
     */
    private Boolean autorise;

    /**
     * Constructeur par défaut
     */
    public DroitsRole() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant du role
     *
     * @return l'identifiant du role
     */
    public Integer getIdRole() {
        return idRole;
    }

    /**
     * Définit l'identifiant du role
     *
     * @param idRole le nouvel identifiant du role
     */
    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

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
     * Récupère le boolean autorise
     *
     * @return le boolean autorise
     */
    public Boolean isAutorise() {
        return autorise;
    }

    /**
     * Définit le boolean autorise
     *
     * @param autorise le nouveau boolean autorise
     */
    public void setAutorise(Boolean autorise) {
        this.autorise = autorise;
    }
}
