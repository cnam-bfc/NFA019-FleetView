package net.cnam.fleetview.model.nominatim;

public class NominatimAddress {
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
    private String numeroDeRue;
    /**
     * Complément
     */
    private String complement;

    /**
     * Constructeur par défaut
     */
    public NominatimAddress() {
    }

    // GETTERS & SETTERS

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
}
