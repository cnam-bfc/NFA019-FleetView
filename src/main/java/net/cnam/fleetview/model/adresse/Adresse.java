package net.cnam.fleetview.model.adresse;

import java.time.LocalDateTime;

/**
 * Classe Adresse
 *
 * Cette classe permet de créer des objets Adresse.
 * table concernée : fleetview_adresse
 */
public class Adresse {
    private int idAdresse;
    private String osmType;
    private int osmId;
    private String pays;
    private String codePostal;
    private String commune;
    private String rue;
    private String numeroDeRue;
    private String complement;
    private LocalDateTime dateArchive;
    private int idSecteur;

    /**
     * Constructeur
     */
    public Adresse() {
    }

    // GETTERS & SETTERS

    /**
     * Getter sur l'id
     * @return idAdresse
     */
    public int getIdAdresse() {
        return idAdresse;
    }

    /**
     * Setter sur l'id
     * @param idAdresse idAdresse
     */
    public void setIdAdresse(int idAdresse) {
        this.idAdresse = idAdresse;
    }

    /**
     * Getter sur le type OSM
     * @return osmType
     */
    public String getOsmType() {
        return osmType;
    }


    /**
     * Setter sur le type OSM
     * @param osmType osmType
     */
    public void setOsmType(String osmType) {
        this.osmType = osmType;
    }


    /**
     * Getter sur l'identifiant OSM
     * @return osmId
     */
    public int getOsmId() {
        return osmId;
    }

    /**
     * Setter sur l'identifiant OSM
     * @param osmId osmId
     */
    public void setOsmId(int osmId) {
        this.osmId = osmId;
    }

    /**
     * Getter sur le pays
     * @return
     */
    public String getPays() {
        return pays;
    }

    /**
     * Setter sur le pays
     * @param pays
     */
    public void setPays(String pays) {
        this.pays = pays;
    }

    /**
     * Getter sur le code postal
     * @return
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Setter sur le code postal
     * @param codePostal
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Getter sur la commune
     * @return
     */
    public String getCommune() {
        return commune;
    }

    /**
     * Setter sur la commune
     * @param commune
     */
    public void setCommune(String commune) {
        this.commune = commune;
    }

    /**
     * Getter sur la rue
     * @return rue
     */
    public String getRue() {
        return rue;
    }

    /**
     * Setter sur la rue
     * @param rue
     */
    public void setRue(String rue) {
        this.rue = rue;
    }

    /**
     * Getter sur le numéro de rue
     * @return numeroDeRue
     */
    public String getNumeroDeRue() {
        return numeroDeRue;
    }

    /**
     * Setter sur le numéro de rue
     * @param numeroDeRue
     */
    public void setNumeroDeRue(String numeroDeRue) {
        this.numeroDeRue = numeroDeRue;
    }

    /**
     * Getter sur le complément
     * @return complement
     */
    public String getComplement() {
        return complement;
    }

    /**
     * Setter sur le complément
     * @param complement
     */
    public void setComplement(String complement) {
        this.complement = complement;
    }

    /**
     * Getter sur la date d'archivage
     * @return dateArchive
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Setter sur la date d'archivage
     * @param dateArchive
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }

    /**
     * Getter sur l'id du secteur
     * @return idSecteur
     */
    public int getIdSecteur() {
        return idSecteur;
    }

    /**
     * Setter sur l'id du secteur
     * @param idSecteur
     */
    public void setIdSecteur(int idSecteur) {
        this.idSecteur = idSecteur;
    }
}
