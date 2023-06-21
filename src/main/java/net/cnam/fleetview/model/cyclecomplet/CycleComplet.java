package net.cnam.fleetview.model.cyclecomplet;

import java.time.LocalDateTime;

public class CycleComplet {
    /**
     * Identifiant du cycle
     */
    private Integer idCycle;

    /**
     * Identifiant spécifique du cycle
     */
    private String identifiant;

    /**
     * Charge maximale du cycle
     */
    private Double chargeMaximale;

    /**
     * Date d'acquisition du cycle
     */
    private LocalDateTime dateAcquisition;

    /**
     * Marque du cycle
     */
    private String marque;

    /**
     * Fournisseur du cycle
     */
    private String nomFournisseur;

    /**
     * Type du cycle
     */
    private String type;

    /**
     * Date de dernière révision du cycle
     */
    private LocalDateTime dateDerniereRevision;

    /**
     * Etat du cycle
     */
    private String etat;

    /**
     * Constructeur de la classe CycleComplet
     */
    public CycleComplet() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant du cycle
     *
     * @return idCycle
     */
    public Integer getIdCycle() {
        return idCycle;
    }

    /**
     * Définit l'identifiant du cycle
     *
     * @param idCycle idCycle
     */
    public void setIdCycle(Integer idCycle) {
        this.idCycle = idCycle;
    }

    /**
     * Récupère l'identifiant spécifique du cycle
     *
     * @return idCycleSpecifique
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Définit l'identifiant spécifique du cycle
     *
     * @param identifiant idCycleSpecifique
     */
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Récupère la charge maximale du cycle
     *
     * @return chargeMaximale
     */
    public Double getChargeMaximale() {
        return chargeMaximale;
    }

    /**
     * Définit la charge maximale du cycle
     *
     * @param chargeMaximale chargeMaximale
     */
    public void setChargeMaximale(Double chargeMaximale) {
        this.chargeMaximale = chargeMaximale;
    }

    /**
     * Récupère la date d'acquisition du cycle
     *
     * @return dateAcquisition
     */
    public LocalDateTime getDateAcquisition() {
        return dateAcquisition;
    }

    /**
     * Définit la date d'acquisition du cycle
     *
     * @param dateAcquisition dateAcquisition
     */
    public void setDateAcquisition(LocalDateTime dateAcquisition) {
        this.dateAcquisition = dateAcquisition;
    }

    /**
     * Récupère la marque du cycle
     *
     * @return marque
     */
    public String getMarque() {
        return marque;
    }

    /**
     * Définit la marque du cycle
     *
     * @param marque marque
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * Récupère le nom du fournisseur du cycle
     *
     * @return nomFournisseur
     */
    public String getNomFournisseur() {
        return nomFournisseur;
    }

    /**
     * Définit le nom du fournisseur du cycle
     *
     * @param nomFournisseur nomFournisseur
     */
    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    /**
     * Récupère le type du cycle
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Définit le type du cycle
     *
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Récupère la date de dernière révision du cycle
     *
     * @return dateDerniereRevision
     */
    public LocalDateTime getDateDerniereRevision() {
        return dateDerniereRevision;
    }

    /**
     * Définit la date de dernière révision du cycle
     *
     * @param dateDerniereRevision dateDerniereRevision
     */
    public void setDateDerniereRevision(LocalDateTime dateDerniereRevision) {
        this.dateDerniereRevision = dateDerniereRevision;
    }

    /**
     * Récupère l'état du cycle
     *
     * @return etat
     */
    public String getEtat() {
        return etat;
    }

    /**
     * Définit l'état du cycle
     *
     * @param etat etat
     */
    public void setEtat(String etat) {
        this.etat = etat;
    }
}




