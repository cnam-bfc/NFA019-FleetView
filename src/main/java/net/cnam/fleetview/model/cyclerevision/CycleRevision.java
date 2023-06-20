package net.cnam.fleetview.model.cyclerevision;

import java.time.LocalDateTime;

/**
 * Classe CycleRevision
 * <p>
 * Cette classe permet de créer des objets CycleRevision.
 * table concernée : fleetview_cycle_revision
 */
public class CycleRevision {
    /**
     * Identifiant du cycle revision
     */
    private Integer idCycleRevision;
    /**
     * Date de révision
     */
    private LocalDateTime dateRevision;
    /**
     * Commentaire
     */
    private String commentaire;
    /**
     * Date d'archivage du cycle revision
     */
    private LocalDateTime dateArchive;
    /**
     * Identifiant du cycle
     */
    private Integer idCycle;

    /**
     * Constructeur par défaut
     */
    public CycleRevision() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant du cycle revision
     *
     * @return l'identifiant du cycle revision
     */
    public Integer getIdCycleRevision() {
        return idCycleRevision;
    }

    /**
     * Définit l'identifiant du cycle revision
     *
     * @param idCycleRevision le nouvel identifiant du cycle revision
     */
    public void setIdCycleRevision(Integer idCycleRevision) {
        this.idCycleRevision = idCycleRevision;
    }

    /**
     * Récupère la date de révision
     *
     * @return la date de révision
     */
    public LocalDateTime getDateRevision() {
        return dateRevision;
    }

    /**
     * Définit la date de révision
     *
     * @param dateRevision la nouvelle date de révision
     */
    public void setDateRevision(LocalDateTime dateRevision) {
        this.dateRevision = dateRevision;
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
     * Récupère la date d'archivage du cycle revision
     *
     * @return la date d'archivage du cycle revision
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage du cycle revision
     *
     * @param dateArchive la nouvelle date d'archivage du cycle revision
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }

    /**
     * Récupère l'identifiant du cycle
     *
     * @return l'identifiant du cycle
     */
    public Integer getIdCycle() {
        return idCycle;
    }

    /**
     * Définit l'identifiant du cycle
     *
     * @param idCycle le nouvel identifiant du cycle
     */
    public void setIdCycle(Integer idCycle) {
        this.idCycle = idCycle;
    }
}
