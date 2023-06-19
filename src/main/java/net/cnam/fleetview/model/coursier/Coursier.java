package net.cnam.fleetview.model.coursier;

/**
 * Classe Coursier
 * <p>
 * Cette classe permet de créer des objets Coursier.
 * table concernée : fleetview_coursier
 */
public class Coursier {
    /**
     * Identifiant du coursier
     */
    private Integer idCoursier;

    /**
     * Matricule du coursier
     */
    private String matricule;

    /**
     * Identifiant de l'utilisateur
     */
    private Integer idUtilisateur;

    /**
     * Constructeur par défaut
     */
    public Coursier() {
    }

    // GETTERS & SETTERS

    /**
     * Réupère l'identifiant du coursier
     *
     * @return idCoursier
     */
    public Integer getIdCoursier() {
        return idCoursier;
    }

    /**
     * Définit l'identifiant du coursier
     *
     * @param idCoursier idCoursier
     */
    public void setIdCoursier(Integer idCoursier) {
        this.idCoursier = idCoursier;
    }

    /**
     * Réupère le matricule du coursier
     *
     * @return matricule
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Définit le matricule du coursier
     *
     * @param matricule matricule
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Réupère l'identifiant de l'utilisateur
     *
     * @return idUtilisateur
     */
    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * Définit l'identifiant de l'utilisateur
     *
     * @param idUtilisateur idUtilisateur
     */
    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
