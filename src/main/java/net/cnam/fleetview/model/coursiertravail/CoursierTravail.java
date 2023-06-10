package net.cnam.fleetview.model.coursiertravail;

import java.time.LocalDateTime;

/**
 * Classe CoursierTravail
 * <p>
 * Cette classe permet de créer des objets CoursierTravail.
 * Représente une fiche quotidienne de travail d'un coursier.
 * table concernée : fleetview_coursier_travail
 */
public class CoursierTravail {
    /**
     * Identifiant du coursier travail
     */
    private int idCoursierTravail;

    /**
     * Date de saisie du travail
     */
    private LocalDateTime dateSaisie;

    /**
     * Date d'archivage
     */
    private LocalDateTime dateArchive;

    /**
     * Identifiant du coursier
     */
    private int idCoursier;

    /**
     * Constructeur par défaut
     */
    public CoursierTravail() {
    }

    // GETTERS & SETTERS

    /**
     * Réupère l'identifiant du coursier travail
     *
     * @return idCoursierTravail
     */
    public int getIdCoursierTravail() {
        return idCoursierTravail;
    }

    /**
     * Définit l'identifiant du coursier travail
     *
     * @param idCoursierTravail idCoursierTravail
     */
    public void setIdCoursierTravail(int idCoursierTravail) {
        this.idCoursierTravail = idCoursierTravail;
    }

    /**
     * Récupère la date de saisie du travail
     *
     * @return dateSaisie
     */
    public LocalDateTime getDateSaisie() {
        return dateSaisie;
    }

    /**
     * Définit la date de saisie du travail
     *
     * @param dateSaisie dateSaisie
     */
    public void setDateSaisie(LocalDateTime dateSaisie) {
        this.dateSaisie = dateSaisie;
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
     * Récupère l'identifiant du coursier
     *
     * @return idCoursier
     */
    public int getIdCoursier() {
        return idCoursier;
    }

    /**
     * Définit l'identifiant du coursier
     *
     * @param idCoursier idCoursier
     */
    public void setIdCoursier(int idCoursier) {
        this.idCoursier = idCoursier;
    }
}
