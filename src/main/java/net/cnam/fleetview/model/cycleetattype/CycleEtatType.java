package net.cnam.fleetview.model.cycleetattype;

import java.time.LocalDateTime;

/**
 * Classe CycleEtatType
 * <p>
 * Cette classe permet de créer des objets CycleEtatType.
 * table concernée : fleetview_cycle_etat_type
 */
public class CycleEtatType {
    /**
     * Identifiant du cycle etat type
     */
    private int idCycleEtatType;
    /**
     * Nom du cycle etat type
     */
    private String nom;
    /**
     * Boolean permettant de définir si le cyle est utilisable
     */
    private boolean utilisable;
    /**
     * Date d'archivage du cycle etat type
     */
    private LocalDateTime dateArchive;

    /**
     * Constructeur par défaut
     */
    public CycleEtatType() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant du cycle etat type
     *
     * @return l'identifiant du cycle etat type
     */
    public int getIdCycleEtatType() {
        return idCycleEtatType;
    }

    /**
     * Définit l'identifiant du cycle etat type
     *
     * @param idCycleEtatType le nouvel identifiant du cycle etat type
     */
    public void setIdCycleEtatType(int idCycleEtatType) {
        this.idCycleEtatType = idCycleEtatType;
    }

    /**
     * Récupère le nom du cycle etat type
     *
     * @return le nom du cycle etat type
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du cycle etat type
     *
     * @param nom le nouveau nom du cycle etat type
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le boolean permettant de définir si le cyle est utilisable
     *
     * @return le boolean permettant de définir si le cyle est utilisable
     */
    public boolean isUtilisable() {
        return utilisable;
    }

    /**
     * Définit le boolean permettant de définir si le cyle est utilisable
     *
     * @param utilisable le nouveau boolean permettant de définir si le cyle est utilisable
     */
    public void setUtilisable(boolean utilisable) {
        this.utilisable = utilisable;
    }

    /**
     * Récupère la date d'archivage du cycle etat type
     *
     * @return la date d'archivage du cycle etat type
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage du cycle etat type
     *
     * @param dateArchive la nouvelle date d'archivage du cycle etat type
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }
}
