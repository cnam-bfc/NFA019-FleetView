package net.cnam.fleetview.model;

import java.time.LocalDateTime;

/**
 * Classe CycleMarque
 * <p>
 * Cette classe permet de créer des objets CycleMarque.
 * table concernée : fleetview_cycle_marque
 */
public class CycleMarque {
    /**
     * Identifiant du cycle marque
     */
    private int idCycleMarque;
    /**
     * Nom du cycle marque
     */
    private String nom;
    /**
     * Date d'archivage du cycle marque
     */
    private LocalDateTime dateArchive;

    /**
     * Constructeur par défaut
     */

    public CycleMarque() {

    }
// GETTERS & SETTERS

    /**
     * Récupère l'identifiant du cycle marque
     *
     * @return l'identifiant du cycle marque
     */
    public int getIdCycleMarque() {
        return idCycleMarque;
    }

    /**
     * Définit l'identifiant du cycle marque
     *
     * @param idCycleMarque le nouvel identifiant du cycle marque
     */
    public void setIdCycleMarque(int idCycleMarque) {
        this.idCycleMarque = idCycleMarque;
    }

    /**
     * Récupère le nom du cycle marque
     *
     * @return le nom du cycle marque
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du cycle marque
     *
     * @param nom le nouveau nom du cycle marque
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la date d'archivage du cycle marque
     *
     * @return la date d'archivage du cycle marque
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage du cycle marque
     *
     * @param dateArchive la nouvelle date d'archivage du cycle marque
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }
}
