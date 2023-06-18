package net.cnam.fleetview.model.coursierutilisateur;

/**
 * Classe Coursier
 * <p>
 * Cette classe permet de créer des objets Coursier.
 * table concernée : fleetview_coursier
 */
public class CoursierUtilisateur {
    /**
     * Identifiant du coursier
     */
    private int idCoursier;

    /**
     * Identifiant utilisateur du coursier
     */
    private int idUtilisateur;

    /**
     * Matricule du coursier
     */
    private String matricule;

    /**
     * Nom du coursier
     */
    private String nom;

    /**
     * Prénom du coursier
     */
    private String prenom;

    /**
     * Constructeur par défaut
     */
    public CoursierUtilisateur() {
    }

    // GETTERS & SETTERS

    /**
     * Réupère l'identifiant du coursier
     *
     * @return idCoursier
     */
    public int getIdCoursier() {
        return idCoursier;
    }

    /**
     * Définit l'identifiant du coursier
     *
     * @param idCoursier idCoursier
     */
    public void setIdCoursier(int idCoursier) {
        this.idCoursier = idCoursier;
    }

    /**
     * Réupère l'identifiant utilisateur du coursier
     *
     * @return idUtilisateur
     */
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * Définit l'identifiant utilisateur du coursier
     *
     * @param idUtilisateur idUtilisateur
     */
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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
     * Réupère le nom du coursier
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du coursier
     *
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Réupère le prénom du coursier
     *
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom du coursier
     *
     * @param prenom prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
