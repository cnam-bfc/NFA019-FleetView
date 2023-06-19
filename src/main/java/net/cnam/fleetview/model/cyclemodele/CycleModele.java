package net.cnam.fleetview.model.cyclemodele;

import java.time.LocalDateTime;

/**
 * Classe CycleModele
 * <p>
 * Cette classe permet de créer des objets CycleModele.
 * table concernée : fleetview_cycle_modele
 */
public class CycleModele {
    /**
     * Identifiant du cycle modele
     */
    private Integer idCycleModele;
    /**
     * Nom du cycle modele
     */
    private String nom;
    /**
     * Date d'archivage du cycle modele
     */
    private LocalDateTime dateArchive;
    /**
     * Identifiant de la marque du cycle
     */
    private Integer idCycleMarque;

    /**
     * Constructeur par défaut
     */
    public CycleModele() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant du cycle modele
     *
     * @return l'identifiant du cycle modele
     */
    public Integer getIdCycleModele() {
        return idCycleModele;
    }

    /**
     * Définit l'identifiant du cycle modele
     *
     * @param idCycleModele le nouvel identifiant du cycle modele
     */
    public void setIdCycleModele(Integer idCycleModele) {
        this.idCycleModele = idCycleModele;
    }

    /**
     * Récupère le nom du cycle modele
     *
     * @return le nom du cycle modele
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du cycle modele
     *
     * @param nom le nouveau nom du cycle modele
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la date d'archivage du cycle modele
     *
     * @return la date d'archivage du cycle modele
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage du cycle modele
     *
     * @param dateArchive la nouvelle date d'archivage du cycle modele
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }

    /**
     * Récupère l'identifiant de la marque du cycle
     *
     * @return l'identifiant de la marque du cycle
     */
    public Integer getIdCycleMarque() {
        return idCycleMarque;
    }

    /**
     * Définit l'identifiant de la marque du cycle
     *
     * @param idCycleMarque le nouvel identifiant de la marque du cycle
     */
    public void setIdCycleMarque(Integer idCycleMarque) {
        this.idCycleMarque = idCycleMarque;
    }
}
