package net.cnam.fleetview.model.cycletype;

import java.time.LocalDateTime;

/**
 * Classe CycleType
 * <p>
 * Cette classe permet de créer des objets CycleType.
 * table concernée : fleetview_cycle_type
 */
public class CycleType {
    /**
     * Identifiant du cycle type
     */
    private Integer idCycleType;
    /**
     * Nom du cycle type
     */
    private String nom;
    /**
     * Date d'archivage du cycle type
     */
    private LocalDateTime dateArchive;

    /**
     * Constructeur par défaut
     */
    public CycleType() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant du cycle type
     *
     * @return l'identifiant du cycle type
     */
    public Integer getIdCycleType() {
        return idCycleType;
    }

    /**
     * Définit l'identifiant du cycle type
     *
     * @param idCycleType le nouvel identifiant du cycle type
     */
    public void setIdCycleType(Integer idCycleType) {
        this.idCycleType = idCycleType;
    }

    /**
     * Récupère le nom du cycle type
     *
     * @return le nom du cycle type
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du cycle type
     *
     * @param nom le nouveau nom du cycle type
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la date d'archivage du cycle type
     *
     * @return la date d'archivage du cycle type
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage du cycle type
     *
     * @param dateArchive la nouvelle date d'archivage du cycle type
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }
}