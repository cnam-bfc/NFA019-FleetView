package net.cnam.fleetview.model.role;

/**
 * Classe Role
 * <p>
 * Cette classe permet de créer des objets Role.
 * table concernée : fleetview_role
 */
public class Role {
    /**
     * Identifiant du role
     */
    private int idRole;
    /**
     * Nom du role
     */
    private String nom;

    /**
     * Constructeur par défaut
     */
    public Role() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant du role
     *
     * @return l'identifiant du role
     */
    public int getIdRole() {
        return idRole;
    }

    /**
     * Définit l'identifiant du role
     *
     * @param idRole le nouvel identifiant du role
     */
    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    /**
     * Récupère le nom du role
     *
     * @return le nom du role
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du role
     *
     * @param nom le nouveau nom du role
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
