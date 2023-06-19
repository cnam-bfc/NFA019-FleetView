package net.cnam.fleetview.model.colisdestinataire;

import java.time.LocalDateTime;

/**
 * Classe ColisDestinataire
 * <p>
 * Cette classe permet de créer des objets ColisDestinataire.
 * table concernée : fleetview_colis_destinataire
 */
public class ColisDestinataire {
    /**
     * Identifiant du colis destinataire
     */
    private Integer idColisDestinataire;
    /**
     * Prénom du destinataire
     */
    private String prenom;
    /**
     * Nom du destinataire
     */
    private String nom;
    /**
     * Date d'archivage
     */
    private LocalDateTime dateArchive;

    /**
     * Constructeur par défaut
     */
    public ColisDestinataire() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant du colis destinataire
     *
     * @return idColisDestinataire
     */
    public Integer getIdColisDestinataire() {
        return idColisDestinataire;
    }

    /**
     * Définit l'identifiant du colis destinataire
     *
     * @param idColisDestinataire idColisDestinataire
     */
    public void setIdColisDestinataire(Integer idColisDestinataire) {
        this.idColisDestinataire = idColisDestinataire;
    }

    /**
     * Récupère le prénom du destinataire
     *
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom du destinataire
     *
     * @param prenom prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Récupère le nom du destinataire
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du destinataire
     *
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la date d'archivage
     *
     * @return dateArchive
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage
     *
     * @param dateArchive dateArchive
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }
}
