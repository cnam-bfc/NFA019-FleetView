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
    private Integer idColisCourse;
    /**
     * Ordre du colis course
     */
    private Integer ordre;
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
    private Integer idColis;
    /**
     * Identifiant de la course
     */
    private Integer idCourse;

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
    public Integer getIdColisCourse() {
        return idColisCourse;
    }

    /**
     * Définit l'identifiant du colis
     *
     * @param idColisCourse idColisCourse
     */
    public void setIdColisCourse(Integer idColisCourse) {
        this.idColisCourse = idColisCourse;
    }

    /**
     * Récupère l'identifiant du colis
     *
     * @return ordre
     */
    public Integer getOrdre() {
        return ordre;
    }

    /**
     * Définit l'identifiant du colis
     *
     * @param ordre ordre
     */
    public void setOrdre(Integer ordre) {
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
    public Integer getIdColis() {
        return idColis;
    }

    /**
     * Définit l'identifiant du colis
     *
     * @param idColis idColis
     */
    public void setIdColis(Integer idColis) {
        this.idColis = idColis;
    }

    /**
     * Récupère l'identifiant de la course
     *
     * @return idCourse
     */
    public Integer getIdCourse() {
        return idCourse;
    }

    /**
     * Définit l'identifiant de la course
     *
     * @param idCourse idCourse
     */
    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }
}
