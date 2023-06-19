package net.cnam.fleetview.model.colis;

import java.time.LocalDateTime;

/**
 * Classe Colis
 *
 * Cette classe permet de créer des objets Colis.
 * table concernée : fleetview_colis
 */
public class Colis {
    /**
     * Identifiant du colis
     */
    private Integer idColis;
    /**
     * Numéro du colis
     */
    private String numero;
    /**
     * Poids du colis
     */
    private Double poids;
    /**
     * Date d'archivage
     */
    private LocalDateTime dateArchive;
    /**
     * Identifiant de l'adresse
     */
    private Integer idAdresse;
    /**
     * Identifiant du colis destinataire
     */
    private Integer idColisDestinataire;

    /**
     * Constructeur par défaut
     */
    public Colis() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant du colis
     *
     * @return idColis
     */
    public Integer getIdColis() {
        return idColis;
    }

    /**
     * Définit l'identifiant du colis
     *
     * @param idColis idColis
     */
    public void setIdColis(Integer idColis) {
        this.idColis = idColis;
    }

    /**
     * Récupère le numéro du colis
     *
     * @return numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Définit le numéro du colis
     *
     * @param numero numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Récupère le poids du colis
     *
     * @return poids
     */
    public Double getPoids() {
        return poids;
    }

    /**
     * Définit le poids du colis
     *
     * @param poids poids
     */
    public void setPoids(Double poids) {
        this.poids = poids;
    }

    /**
     * Récupère la date d'archivage du colis
     *
     * @return dateArchive
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage du colis
     *
     * @param dateArchive dateArchive
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }

    /**
     * Récupère l'identifiant de l'adresse
     *
     * @return idAdresse
     */
    public Integer getIdAdresse() {
        return idAdresse;
    }

    /**
     * Définit l'identifiant de l'adresse
     *
     * @param idAdresse idAdresse
     */
    public void setIdAdresse(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }

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
}
