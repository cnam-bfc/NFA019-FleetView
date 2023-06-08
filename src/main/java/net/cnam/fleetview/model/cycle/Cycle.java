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
    private int idCycle;

    /**
     * Identifiant spécifique du cycle
     */
    private String idCycleSpecifique;

    /**
     * Numéro de série du cycle
     */
    private String numeroSerie;

    /**
     * Charge maximale du cycle
     */
    private double chargeMaximale;

    /**
     * Prix d'achat du cycle
     */
    private double prixAchat;

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
    private int idCycleFournisseur;

    /**
     * Identifiant du modele de cycle
     */
    private int idCycleModele;

    /**
     * Identifiant du type de cycle
     */
    private int idTypeCycle;

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
    public int getIdCycle() {
        return idCycle;
    }

    /**
     * Définit l'identifiant du cycle
     *
     * @param idCycle idCycle
     */
    public void setIdCycle(int idCycle) {
        this.idCycle = idCycle;
    }

    /**
     * Récupère l'identifiant spécifique du cycle
     *
     * @return idCycleSpecifique
     */
    public String getIdCycleSpecifique() {
        return idCycleSpecifique;
    }

    /**
     * Définit l'identifiant spécifique du cycle
     *
     * @param idCycleSpecifique idCycleSpecifique
     */
    public void setIdCycleSpecifique(String idCycleSpecifique) {
        this.idCycleSpecifique = idCycleSpecifique;
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
    public double getChargeMaximale() {
        return chargeMaximale;
    }

    /**
     * Définit la charge maximale du cycle
     *
     * @param chargeMaximale chargeMaximale
     */
    public void setChargeMaximale(double chargeMaximale) {
        this.chargeMaximale = chargeMaximale;
    }

    /**
     * Récupère le prix d'achat du cycle
     *
     * @return prixAchat
     */
    public double getPrixAchat() {
        return prixAchat;
    }

    /**
     * Définit le prix d'achat du cycle
     *
     * @param prixAchat prixAchat
     */
    public void setPrixAchat(double prixAchat) {
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
    public int getIdCycleFournisseur() {
        return idCycleFournisseur;
    }

    /**
     * Définit l'identifiant du fournisseur de cycle
     *
     * @param idCycleFournisseur idCycleFournisseur
     */
    public void setIdCycleFournisseur(int idCycleFournisseur) {
        this.idCycleFournisseur = idCycleFournisseur;
    }

    /**
     * Récupère l'identifiant du modele de cycle
     *
     * @return idCycleModele
     */
    public int getIdCycleModele() {
        return idCycleModele;
    }

    /**
     * Définit l'identifiant du modele de cycle
     *
     * @param idCycleModele idCycleModele
     */
    public void setIdCycleModele(int idCycleModele) {
        this.idCycleModele = idCycleModele;
    }

    /**
     * Récupère l'identifiant du type de cycle
     *
     * @return idTypeCycle
     */
    public int getIdTypeCycle() {
        return idTypeCycle;
    }

    /**
     * Définit l'identifiant du type de cycle
     *
     * @param idTypeCycle idTypeCycle
     */
    public void setIdTypeCycle(int idTypeCycle) {
        this.idTypeCycle = idTypeCycle;
    }
}
