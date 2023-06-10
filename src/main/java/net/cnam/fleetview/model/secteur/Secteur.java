package net.cnam.fleetview.model.secteur;

import java.time.LocalDateTime;

/**
 * Classe Secteur
 * <p>
 * Cette classe permet de créer des objets Secteur.
 * table concernée : fleetview_secteur
 */
public class Secteur {
    /**
     * Identifiant du secteur
     */
    private int idSecteur;
    /**
     * Nom du secteur
     */
    private String nom;
    /**
     * Date d'archivage du secteur
     */
    private LocalDateTime dateArchive;

    /**
     * Constructeur par défaut
     */
    public Secteur() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant du secteur
     *
     * @return l'identifiant du secteur
     */
    public int getIdSecteur() {
        return idSecteur;
    }

    /**
     * Définit l'identifiant du secteur
     *
     * @param idSecteur le nouvel identifiant du secteur
     */
    public void setIdSecteur(int idSecteur) {
        this.idSecteur = idSecteur;
    }

    /**
     * Récupère le nom du secteur
     *
     * @return le nom du secteur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du secteur
     *
     * @param nom le nouveau nom du secteur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la date d'archivage du secteur
     *
     * @return la date d'archivage du secteur
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage du secteur
     *
     * @param dateArchive la nouvelle date d'archivage du secteur
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }
}
