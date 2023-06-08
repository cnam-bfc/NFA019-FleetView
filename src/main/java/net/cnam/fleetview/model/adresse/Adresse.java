package net.cnam.fleetview.model.adresse;

import java.time.LocalDateTime;

/**
 * Classe Adresse
 *
 * Cette classe permet de créer des objets Adresse.
 * table concernée : fleetview_adresse
 */
public class Adresse {
    /**
     * Identifiant de l'adresse
     */
    private int idAdresse;
    /**
     * Type OSM
     */
    private String osmType;
    /**
     * Identifiant OSM
     */
    private int osmId;
    /**
     * Pays
     */
    private String pays;
    /**
     * Code postal
     */
    private String codePostal;
    /**
     * Commune
     */
    private String commune;
    /**
     * Rue
     */
    private String rue;
    /**
     * Numéro de rue
     */
    private String numeroDeRue;
    /**
     * Complément
     */
    private String complement;
    /**
     * Date d'archivage
     */
    private LocalDateTime dateArchive;
    /**
     * Identifiant du secteur
     */
    private int idSecteur;

    /**
     * Constructeur par défaut
     */
    public Adresse() {
    }

    // GETTERS & SETTERS

    /**
     * Récupère l'identifiant de l'adresse
     * @return idAdresse
     */
    public int getIdAdresse() {
        return idAdresse;
    }

    /**
     * Définit l'identifiant de l'adresse
     * @param idAdresse idAdresse
     */
    public void setIdAdresse(int idAdresse) {
        this.idAdresse = idAdresse;
    }

    /**
     * Récupère le type OSM
     * @return osmType
     */
    public String getOsmType() {
        return osmType;
    }


    /**
     * Définit le type OSM
     * @param osmType osmType
     */
    public void setOsmType(String osmType) {
        this.osmType = osmType;
    }


    /**
     * Récupère l'identifiant OSM
     * @return osmId
     */
    public int getOsmId() {
        return osmId;
    }

    /**
     * Définit l'identifiant OSM
     * @param osmId osmId
     */
    public void setOsmId(int osmId) {
        this.osmId = osmId;
    }

    /**
     * Récupère le pays
     * @return
     */
    public String getPays() {
        return pays;
    }

    /**
     * Définit le pays
     * @param pays
     */
    public void setPays(String pays) {
        this.pays = pays;
    }

    /**
     * Récupère le code postal
     * @return codePostal
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Définit le code postal
     * @param codePostal
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Récupère la commune
     * @return
     */
    public String getCommune() {
        return commune;
    }

    /**
     * Définit la commune
     * @param commune
     */
    public void setCommune(String commune) {
        this.commune = commune;
    }

    /**
     * Récupère la rue
     * @return rue
     */
    public String getRue() {
        return rue;
    }

    /**
     * Définit la rue
     * @param rue
     */
    public void setRue(String rue) {
        this.rue = rue;
    }

    /**
     * Récupère le numéro de rue
     * @return numeroDeRue
     */
    public String getNumeroDeRue() {
        return numeroDeRue;
    }

    /**
     * Définit le numéro de rue
     * @param numeroDeRue
     */
    public void setNumeroDeRue(String numeroDeRue) {
        this.numeroDeRue = numeroDeRue;
    }

    /**
     * Récupère le complément
     * @return complement
     */
    public String getComplement() {
        return complement;
    }

    /**
     * Définit le complément
     * @param complement
     */
    public void setComplement(String complement) {
        this.complement = complement;
    }

    /**
     * Récupère la date d'archivage
     * @return dateArchive
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Définit la date d'archivage
     * @param dateArchive
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }

    /**
     * Récupère l'identifiant du secteur
     * @return idSecteur
     */
    public int getIdSecteur() {
        return idSecteur;
    }

    /**
     * Définit l'identifiant du secteur
     * @param idSecteur
     */
    public void setIdSecteur(int idSecteur) {
        this.idSecteur = idSecteur;
    }
}
