package net.cnam.fleetview.model.utilisateur;

import java.time.LocalDateTime;

/**
 * Classe Utilisateur
 * <p>
 * Cette classe permet de créer des objets Utilisateur.
 * table concernée : fleetview_utilisateur
 */
public class Utilisateur {
    /**
     * Identifiant de l'utilisateur
     */
    private Integer idUtilisateur;
    /**
     * Identifiant pour la connexion
     */
    private String identifiant;
    /**
     * Mot de passe pour la connexion
     */
    private String motDePasse;
    /**
     * Nom de l'utilisateur
     */
    private String nom;
    /**
     * Prénom de l'utilisateur
     */
    private String prenom;
    /**
     * Date d'archivage de l'utilisateur
     */
    private LocalDateTime dateArchive;
    /**
     * Identifiant du rôle de l'utilisateur
     */
    private Integer idRole;

    /**
     * Constructeur par défaut
     */
    public Utilisateur() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant de l'utilisateur
     *
     * @return l'identifiant de l'utilisateur
     */
    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * Définit l'identifiant de l'utilisateur
     *
     * @param idUtilisateur le nouvel identifiant de l'utilisateur
     */
    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    /**
     * Récupère l'identifiant pour la connexion
     *
     * @return l'identifiant pour la connexion
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Définit l'identifiant pour la connexion
     *
     * @param identifiant le nouvel identifiant pour la connexion
     */
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Récupère le mot de passe pour la connexion
     *
     * @return le mot de passe pour la connexion
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Définit le mot de passe pour la connexion
     *
     * @param motDePasse le nouveau mot de passe pour la connexion
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Récupère le nom de l'utilisateur
     *
     * @return le nom de l'utilisateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'utilisateur
     *
     * @param nom le nouveau nom de l'utilisateur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le prénom de l'utilisateur
     *
     * @return le prénom de l'utilisateur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom de l'utilisateur
     *
     * @param prenom le nouveau prénom de l'utilisateur
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Récupère la date d'archivage de l'utilisateur
     *
     * @return la date d'archivage de l'utilisateur
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage de l'utilisateur
     *
     * @param dateArchive la nouvelle date d'archivage de l'utilisateur
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }

    /**
     * Récupère l'identifiant du rôle de l'utilisateur
     *
     * @return l'identifiant du rôle de l'utilisateur
     */
    public Integer getIdRole() {
        return idRole;
    }

    /**
     * Définit l'identifiant du rôle de l'utilisateur
     *
     * @param idRole le nouvel identifiant du rôle de l'utilisateur
     */
    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }
}
