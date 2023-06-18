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
    private int idCycleModele;
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
    private int idCycleMarque;

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
    public int getIdCycleModele() {
        return idCycleModele;
    }

    /**
     * Définit l'identifiant du cycle modele
     *
     * @param idCycleModele le nouvel identifiant du cycle modele
     */
    public void setIdCycleModele(int idCycleModele) {
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
    public int getIdCycleMarque() {
        return idCycleMarque;
    }

    /**
     * Définit l'identifiant de la marque du cycle
     *
     * @param idCycleMarque le nouvel identifiant de la marque du cycle
     */
    public void setIdCycleMarque(int idCycleMarque) {
        this.idCycleMarque = idCycleMarque;
    }
}
