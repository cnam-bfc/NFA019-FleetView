package net.cnam.fleetview.model.cycle;

import java.time.LocalDateTime;

/**
 * Classe Cycle
 * <p>
 * Cette classe permet de créer des objets Cycle.
 * table concernée : fleetview_cycle
 */
public class Cycle {
    /**
     * Identifiant du cycle
     */
    private Integer idCycle;

    /**
     * Identifiant spécifique du cycle
     */
    private String identifiant;

    /**
     * Numéro de série du cycle
     */
    private String numeroSerie;

    /**
     * Charge maximale du cycle
     */
    private Double chargeMaximale;

    /**
     * Prix d'achat du cycle
     */
    private Double prixAchat;

    /**
     * Date d'acquisition du cycle
     */
    private LocalDateTime dateAcquisition;

    /**
     * Date d'archivage du cycle
     */
    private LocalDateTime dateArchive;

    /**
     * Identifiant du fournisseur de cycle
     */
    private Integer idCycleFournisseur;

    /**
     * Identifiant du modele de cycle
     */
    private Integer idCycleModele;

    /**
     * Identifiant du type de cycle
     */
    private Integer idCycleType;

    /**
     * Constructeur par défaut
     */
    public Cycle() {
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
     * Récupère le numéro de série du cycle
     *
     * @return numeroSerie
     */
    public String getNumeroSerie() {
        return numeroSerie;
    }

    /**
     * Définit le numéro de série du cycle
     *
     * @param numeroSerie numeroSerie
     */
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
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
     * Récupère le prix d'achat du cycle
     *
     * @return prixAchat
     */
    public Double getPrixAchat() {
        return prixAchat;
    }

    /**
     * Définit le prix d'achat du cycle
     *
     * @param prixAchat prixAchat
     */
    public void setPrixAchat(Double prixAchat) {
        this.prixAchat = prixAchat;
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
     * Récupère la date d'archivage du cycle
     *
     * @return dateArchive
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage du cycle
     *
     * @param dateArchive dateArchive
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }

    /**
     * Récupère l'identifiant du fournisseur de cycle
     *
     * @return idCycleFournisseur
     */
    public Integer getIdCycleFournisseur() {
        return idCycleFournisseur;
    }

    /**
     * Définit l'identifiant du fournisseur de cycle
     *
     * @param idCycleFournisseur idCycleFournisseur
     */
    public void setIdCycleFournisseur(Integer idCycleFournisseur) {
        this.idCycleFournisseur = idCycleFournisseur;
    }

    /**
     * Récupère l'identifiant du modele de cycle
     *
     * @return idCycleModele
     */
    public Integer getIdCycleModele() {
        return idCycleModele;
    }

    /**
     * Définit l'identifiant du modele de cycle
     *
     * @param idCycleModele idCycleModele
     */
    public void setIdCycleModele(Integer idCycleModele) {
        this.idCycleModele = idCycleModele;
    }

    /**
     * Récupère l'identifiant du type de cycle
     *
     * @return idTypeCycle
     */
    public Integer getIdCycleType() {
        return idCycleType;
    }

    /**
     * Définit l'identifiant du type de cycle
     *
     * @param idCycleType idTypeCycle
     */
    public void setIdCycleType(Integer idCycleType) {
        this.idCycleType = idCycleType;
    }
}
