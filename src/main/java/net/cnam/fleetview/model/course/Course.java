package net.cnam.fleetview.model.course;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe Course
 * <p>
 * Cette classe permet de créer des objets Course.
 * table concernée : fleetview_course
 */
public class Course {
    /**
     * Id de la course
     */
    private Integer idCourse;

    /**
     * Nom de la course
     */
    private String nom;

    /**
     * Distance de la course
     */
    private Double distance;

    /**
     * Date de la course
     */
    private LocalDate dateCourse;

    /**
     * Date du début de la course
     */
    private LocalDateTime dateDebutCourse;

    /**
     * Date du passage de la course en archive
     */
    private LocalDateTime dateArchive;

    /**
     * Id du travail coursier
     */
    private Integer idCoursierTravail;

    /**
     * Id du cycle utilisé pour la course
     */
    private Integer idCycle;

    /**
     * Constructeur par défaut
     */
    public Course() {
    }

    // GETTERS & SETTERS

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

    /**
     * Récupère le nom de la course
     *
     * @return nomCourse
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la course
     *
     * @param nom nomCourse
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la distance de la course
     *
     * @return distance
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * Définit la distance de la course
     *
     * @param distance distance
     */
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    /**
     * Récupère la date de la course
     *
     * @return dateCourse
     */
    public LocalDate getDateCourse() {
        return dateCourse;
    }

    /**
     * Définit la date de la course
     *
     * @param dateCourse dateCourse
     */
    public void setDateCourse(LocalDate dateCourse) {
        this.dateCourse = dateCourse;
    }

    /**
     * Récupère la date du début de la course
     *
     * @return dateDebutCourse
     */
    public LocalDateTime getDateDebutCourse() {
        return dateDebutCourse;
    }

    /**
     * Définit la date du début de la course
     *
     * @param dateDebutCourse dateDebutCourse
     */
    public void setDateDebutCourse(LocalDateTime dateDebutCourse) {
        this.dateDebutCourse = dateDebutCourse;
    }

    /**
     * Récupère la date du passage de la course en archive
     *
     * @return dateArchive
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date du passage de la course en archive
     *
     * @param dateArchive dateArchive
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }

    /**
     * Récupère l'identifiant du travail coursier
     *
     * @return idCoursierTravail
     */
    public Integer getIdCoursierTravail() {
        return idCoursierTravail;
    }

    /**
     * Définit l'identifiant du travail coursier
     *
     * @param idCoursierTravail idCoursierTravail
     */
    public void setIdCoursierTravail(Integer idCoursierTravail) {
        this.idCoursierTravail = idCoursierTravail;
    }

    /**
     * Récupère l'identifiant du cycle utilisé pour la course
     *
     * @return idCycle
     */
    public Integer getIdCycle() {
        return idCycle;
    }

    /**
     * Définit l'identifiant du cycle utilisé pour la course
     *
     * @param idCycle idCycle
     */
    public void setIdCycle(Integer idCycle) {
        this.idCycle = idCycle;
    }
}
