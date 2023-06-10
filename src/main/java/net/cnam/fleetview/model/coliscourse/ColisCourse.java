package net.cnam.fleetview.model.coliscourse;

import java.time.LocalDateTime;

/**
 * Classe ColisCourse
 * <p>
 * Cette classe permet de créer des objets ColisCourse.
 * table concernée : fleetview_colis_course
 */
public class ColisCourse {
    /**
     * Identifiant du colis course
     */
    private int idColisCourse;
    /**
     * Ordre du colis course
     */
    private int ordre;
    /**
     * Date de livraison
     */
    private LocalDateTime dateLivraison;
    /**
     * Date d'archivage
     */
    private LocalDateTime dateArchive;
    /**
     * Identifiant du colis
     */
    private int idColis;
    /**
     * Identifiant de la course
     */
    private int idCourse;

    /**
     * Constructeur par défaut
     */
    public ColisCourse() {
    }

    // GETTERS & SETTERS

    /**
     * Réupère l'identifiant du colis
     *
     * @return idColisCourse
     */
    public int getIdColisCourse() {
        return idColisCourse;
    }

    /**
     * Définit l'identifiant du colis
     *
     * @param idColisCourse idColisCourse
     */
    public void setIdColisCourse(int idColisCourse) {
        this.idColisCourse = idColisCourse;
    }

    /**
     * Récupère l'identifiant du colis
     *
     * @return ordre
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * Définit l'identifiant du colis
     *
     * @param ordre ordre
     */
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    /**
     * Récupère la date de livraison
     *
     * @return dateLivraison
     */
    public LocalDateTime getDateLivraison() {
        return dateLivraison;
    }

    /**
     * Définit la date de livraison
     *
     * @param dateLivraison dateLivraison
     */
    public void setDateLivraison(LocalDateTime dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    /**
     * Récupère la date d'archivage
     *
     * @return dateArchive
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage
     *
     * @param dateArchive dateArchive
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }

    /**
     * Récupère l'identifiant du colis
     *
     * @return idColis
     */
    public int getIdColis() {
        return idColis;
    }

    /**
     * Définit l'identifiant du colis
     *
     * @param idColis idColis
     */
    public void setIdColis(int idColis) {
        this.idColis = idColis;
    }

    /**
     * Récupère l'identifiant de la course
     *
     * @return idCourse
     */
    public int getIdCourse() {
        return idCourse;
    }

    /**
     * Définit l'identifiant de la course
     *
     * @param idCourse idCourse
     */
    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }
}
