package net.cnam.fleetview.model.secteurpoint;

import java.time.LocalDateTime;

/**
 * Classe SecteurPoint
 * <p>
 * Cette classe permet de créer des objets SecteurPoint.
 * table concernée : fleetview_secteurpoint
 */
public class SecteurPoint {
    /**
     * Identifiant du secteur point
     */
    private int idSecteurPoint;
    /**
     * latitude du secteur point
     */
    private double latitude;
    /**
     * longitude du secteur point
     */
    private double longitude;
    /**
     * Date Archive du secteur point
     */
    private LocalDateTime dateArchive;

    /**
     * Constructeur par défaut
     */
    public SecteurPoint() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant du secteur point
     *
     * @return l'identifiant du secteur point
     */
    public int getIdSecteurPoint() {
        return idSecteurPoint;
    }

    /**
     * Définit l'identifiant du secteur point
     *
     * @param idSecteurPoint le nouvel identifiant du secteur point
     */
    public void setIdSecteurPoint(int idSecteurPoint) {
        this.idSecteurPoint = idSecteurPoint;
    }

    /**
     * Récupère la latitude du secteur point
     *
     * @return la latitude du secteur point
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Définit la latitude du secteur point
     *
     * @param latitude la nouvelle latitude du secteur point
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Récupère la longitude du secteur point
     *
     * @return la longitude du secteur point
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Définit la longitude du secteur point
     *
     * @param longitude la nouvelle longitude du secteur point
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Récupère la date d'archivage du secteur point
     *
     * @return la date d'archivage du secteur point
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage du secteur point
     *
     * @param dateArchive la nouvelle date d'archivage du secteur point
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }
}
