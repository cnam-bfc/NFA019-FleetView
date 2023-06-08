package net.cnam.fleetview.model.colisdestinataire;

import java.time.LocalDateTime;

/**
 * Classe ColisDestinataire
 * <p>
 * Cette classe permet de créer des objets ColisDestinataire.
 * table concernée : fleetview_colis_destinataire
 */
public class ColisDestinataire {
    private int idColisDestinataire;
    private String prenom;
    private String nom;
    private LocalDateTime dateArchive;

    /**
     * Constructeur
     */
    public ColisDestinataire() {
    }

    // GETTERS & SETTERS

    /**
     * Getter sur l'id
     *
     * @return idColisDestinataire
     */
    public int getIdColisDestinataire() {
        return idColisDestinataire;
    }

    /**
     * Setter sur l'id
     *
     * @param idColisDestinataire idColisDestinataire
     */
    public void setIdColisDestinataire(int idColisDestinataire) {
        this.idColisDestinataire = idColisDestinataire;
    }

    /**
     * Getter sur le prénom
     *
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter sur le prénom
     *
     * @param prenom prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Getter sur le nom
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter sur le nom
     *
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter sur la date d'archivage
     *
     * @return dateArchive
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Setter sur la date d'archivage
     *
     * @param dateArchive dateArchive
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }
}
