package net.cnam.fleetview.model.courseaccident;

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
    private int idCourseAccident;

    /**
     * Date de l'accident
     */
    private String dateAccident;

    /**
     * Date d'archivage
     */
    private String dateArchive;

    /**
     * Identifiant de l'adresse de l'accident
     */
    private int idAdresse;

    /**
     * Identifiant de la course
     */
    private int idCourse;

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
    public int getIdCourseAccident() {
        return idCourseAccident;
    }

    /**
     * Définit l'identifiant de la course accident
     *
     * @param idCourseAccident idCourseAccident
     */
    public void setIdCourseAccident(int idCourseAccident) {
        this.idCourseAccident = idCourseAccident;
    }

    /**
     * Réupère la date de l'accident
     *
     * @return dateAccident
     */
    public String getDateAccident() {
        return dateAccident;
    }

    /**
     * Définit la date de l'accident
     *
     * @param dateAccident dateAccident
     */
    public void setDateAccident(String dateAccident) {
        this.dateAccident = dateAccident;
    }

    /**
     * Réupère la date d'archivage
     *
     * @return dateArchive
     */
    public String getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage
     *
     * @param dateArchive dateArchive
     */
    public void setDateArchive(String dateArchive) {
        this.dateArchive = dateArchive;
    }

    /**
     * Réupère l'identifiant de l'adresse de l'accident
     *
     * @return idAdresse
     */
    public int getIdAdresse() {
        return idAdresse;
    }

    /**
     * Définit l'identifiant de l'adresse de l'accident
     *
     * @param idAdresse idAdresse
     */
    public void setIdAdresse(int idAdresse) {
        this.idAdresse = idAdresse;
    }

    /**
     * Réupère l'identifiant de la course
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

