package net.cnam.fleetview.model.coliscourse;

import java.time.LocalDateTime;

/**
 * Classe ColisCourse
 * <p>
 * Cette classe permet de créer des objets ColisCourse.
 * table concernée : fleetview_colis_course
 */
public class ColisCourse {
    private int idColisCourse;
    private int ordre;
    private LocalDateTime dateLivraison;
    private LocalDateTime dateArchive;
    private int idColis;
    private int idCourse;

    /**
     * Constructeur
     */
    public ColisCourse() {
    }

    // GETTERS & SETTERS

    /**
     * Getter sur l'id
     *
     * @return idColisCourse
     */
    public int getIdColisCourse() {
        return idColisCourse;
    }

    /**
     * Setter sur l'id
     *
     * @param idColisCourse idColisCourse
     */
    public void setIdColisCourse(int idColisCourse) {
        this.idColisCourse = idColisCourse;
    }

    /**
     * Getter sur l'ordre
     *
     * @return ordre
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * Setter sur l'ordre
     *
     * @param ordre ordre
     */
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    /**
     * Getter sur la date de livraison
     *
     * @return dateLivraison
     */
    public LocalDateTime getDateLivraison() {
        return dateLivraison;
    }

    /**
     * Setter sur la date de livraison
     *
     * @param dateLivraison dateLivraison
     */
    public void setDateLivraison(LocalDateTime dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    /**
     * Getter sur la date d'archivage
     *
     * @return dateArchive
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Setter sur la date d'archivage
     *
     * @param dateArchive dateArchive
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }

    /**
     * Getter sur l'id du colis
     *
     * @return idColis
     */
    public int getIdColis() {
        return idColis;
    }

    /**
     * Setter sur l'id du colis
     *
     * @param idColis idColis
     */
    public void setIdColis(int idColis) {
        this.idColis = idColis;
    }

    /**
     * Getter sur l'id de la course
     *
     * @return idCourse
     */
    public int getIdCourse() {
        return idCourse;
    }

    /**
     * Setter sur l'id de la course
     *
     * @param idCourse idCourse
     */
    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }
}
