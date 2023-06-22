package net.cnam.fleetview.model.courseaccident;

import java.time.LocalDateTime;

/**
 * Classe CourseAccident
 * <p>
 * Cette classe permet de créer des objets CourseAccident.
 * table concernée : fleetview_course_accident
 */
public class CourseAccident {
    /**
     * Identifiant de la course accident
     */
    private Integer idCourseAccident;

    /**
     * Date de l'accident
     */
    private LocalDateTime dateAccident;

    /**
     * Date d'archivage
     */
    private LocalDateTime dateArchive;

    /**
     * Identifiant de l'adresse de l'accident
     */
    private Integer idAdresse;

    /**
     * Identifiant de la course
     */
    private Integer idCourse;

    /**
     * Constructeur par défaut
     */
    public CourseAccident() {

    }

    // GETTERS & SETTERS

    /**
     * Réupère l'identifiant de la course accident
     *
     * @return idCourseAccident
     */
    public Integer getIdCourseAccident() {
        return idCourseAccident;
    }

    /**
     * Définit l'identifiant de la course accident
     *
     * @param idCourseAccident idCourseAccident
     */
    public void setIdCourseAccident(Integer idCourseAccident) {
        this.idCourseAccident = idCourseAccident;
    }

    /**
     * Réupère la date de l'accident
     *
     * @return dateAccident
     */
    public LocalDateTime getDateAccident() {
        return dateAccident;
    }

    /**
     * Définit la date de l'accident
     *
     * @param dateAccident dateAccident
     */
    public void setDateAccident(LocalDateTime dateAccident) {
        this.dateAccident = dateAccident;
    }

    /**
     * Réupère la date d'archivage
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
     * Réupère l'identifiant de l'adresse de l'accident
     *
     * @return idAdresse
     */
    public Integer getIdAdresse() {
        return idAdresse;
    }

    /**
     * Définit l'identifiant de l'adresse de l'accident
     *
     * @param idAdresse idAdresse
     */
    public void setIdAdresse(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }

    /**
     * Réupère l'identifiant de la course
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

