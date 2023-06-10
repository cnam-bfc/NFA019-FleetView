package net.cnam.fleetview.model.cycleetat;

import java.time.LocalDateTime;

/**
 * Classe CycleEtat
 *
 * Cette classe permet de créer des objets CycleEtat.
 * table concernée : fleetview_cycle_etat
 */
public class CycleEtat {
    /**
     * Identifiant du cycle etat
     */
    private int idCycleEtat;
    /**
     * Date de début de l'état
     */
    private LocalDateTime dateDebut;
    /**
     * Date de fin estimé de l'état
     */
    private LocalDateTime dateFinEstime;
    /**
     * Commentaire
     */
    private String commentaire;
    /**
     * Date d'archivage
     */
    private LocalDateTime dateArchive;
    /**
     * Identifiant du cycle etat type
     */
    private int idCycleEtatType;
    /**
     * Identifiant du cycle
     */
    private int idCycle;

    /**
     * Constructeur par défaut
     */
    public CycleEtat() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant du cycle etat
     *
     * @return l'identifiant du cycle etat
     */
    public int getIdCycleEtat() {
        return idCycleEtat;
    }

    /**
     * Définit l'identifiant du cycle etat
     *
     * @param idCycleEtat le nouvel identifiant du cycle etat
     */
    public void setIdCycleEtat(int idCycleEtat) {
        this.idCycleEtat = idCycleEtat;
    }

    /**
     * Récupère la date de début de l'état
     *
     * @return la date de début de l'état
     */
    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    /**
     * Définit la date de début de l'état
     *
     * @param dateDebut la nouvelle date de début de l'état
     */
    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Récupère la date de fin estimé de l'état
     *
     * @return la date de fin estimé de l'état
     */
    public LocalDateTime getDateFinEstime() {
        return dateFinEstime;
    }

    /**
     * Définit la date de fin estimé de l'état
     *
     * @param dateFinEstime la nouvelle date de fin estimé de l'état
     */
    public void setDateFinEstime(LocalDateTime dateFinEstime) {
        this.dateFinEstime = dateFinEstime;
    }

    /**
     * Récupère le commentaire
     *
     * @return le commentaire
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Définit le commentaire
     *
     * @param commentaire le nouveau commentaire
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Récupère la date d'archivage
     *
     * @return la date d'archivage
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage
     *
     * @param dateArchive la nouvelle date d'archivage
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }

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
     * Récupère l'identifiant du cycle
     *
     * @return l'identifiant du cycle
     */
    public int getIdCycle() {
        return idCycle;
    }

    /**
     * Définit l'identifiant du cycle
     *
     * @param idCycle le nouvel identifiant du cycle
     */
    public void setIdCycle(int idCycle) {
        this.idCycle = idCycle;
    }
}