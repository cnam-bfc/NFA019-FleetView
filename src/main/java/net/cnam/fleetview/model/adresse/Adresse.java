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
    private Integer idAdresse;
    /**
     * Type OSM
     */
    private String osmType;
    /**
     * Identifiant OSM
     */
    private Long osmId;
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
    private String numeroRue;
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
    private Integer idSecteur;

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
    public Integer getIdAdresse() {
        return idAdresse;
    }

    /**
     * Définit l'identifiant de l'adresse
     * @param idAdresse idAdresse
     */
    public void setIdAdresse(Integer idAdresse) {
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
    public Long getOsmId() {
        return osmId;
    }

    /**
     * Définit l'identifiant OSM
     * @param osmId osmId
     */
    public void setOsmId(Long osmId) {
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
    public String getNumeroRue() {
        return numeroRue;
    }

    /**
     * Définit le numéro de rue
     * @param numeroRue
     */
    public void setNumeroRue(String numeroRue) {
        this.numeroRue = numeroRue;
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
    public Integer getIdSecteur() {
        return idSecteur;
    }

    /**
     * Définit l'identifiant du secteur
     * @param idSecteur
     */
    public void setIdSecteur(Integer idSecteur) {
        this.idSecteur = idSecteur;
    }
}
