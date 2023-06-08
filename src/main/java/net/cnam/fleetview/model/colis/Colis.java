package net.cnam.fleetview.model.colis;

import java.time.LocalDateTime;

/**
 * Classe Colis
 *
 * Cette classe permet de créer des objets Colis.
 * table concernée : fleetview_colis
 */
public class Colis {
    private int idColis;
    private String numero;
    private double poids;
    private LocalDateTime dateArchive;
    private int idAdresse;
    private int idColisDestinataire;

    /**
     * Constructeur
     */
    public Colis() {
    }

    // GETTERS & SETTERS

    /**
     * Getter sur l'id
     *
     * @return idColis
     */
    public int getIdColis() {
        return idColis;
    }

    /**
     * Setter sur l'id
     *
     * @param idColis idColis
     */
    public void setIdColis(int idColis) {
        this.idColis = idColis;
    }

    /**
     * Getter sur le numéro
     *
     * @return numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Setter sur le numéro
     *
     * @param numero numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Getter sur le poids
     *
     * @return poids
     */
    public double getPoids() {
        return poids;
    }

    /**
     * Setter sur le poids
     *
     * @param poids poids
     */
    public void setPoids(double poids) {
        this.poids = poids;
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

    /**
     * Getter sur l'id de l'adresse
     *
     * @return idAdresse
     */
    public int getIdAdresse() {
        return idAdresse;
    }

    /**
     * Setter sur l'id de l'adresse
     *
     * @param idAdresse idAdresse
     */
    public void setIdAdresse(int idAdresse) {
        this.idAdresse = idAdresse;
    }

    /**
     * Getter sur l'id du colis destinataire
     *
     * @return idColisDestinataire
     */
    public int getIdColisDestinataire() {
        return idColisDestinataire;
    }

    /**
     * Setter sur l'id du colis destinataire
     *
     * @param idColisDestinataire idColisDestinataire
     */
    public void setIdColisDestinataire(int idColisDestinataire) {
        this.idColisDestinataire = idColisDestinataire;
    }
}
