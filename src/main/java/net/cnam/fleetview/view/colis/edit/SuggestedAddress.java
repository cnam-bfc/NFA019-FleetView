package net.cnam.fleetview.view.colis.edit;

public class SuggestedAddress {
    /**
     * Type OSM
     */
    private final String osmType;
    /**
     * Identifiant OSM
     */
    private final Long osmId;
    /**
     * Pays
     */
    private final String pays;
    /**
     * Code postal
     */
    private final String codePostal;
    /**
     * Commune
     */
    private final String commune;
    /**
     * Rue
     */
    private final String rue;
    /**
     * Numéro de rue
     */
    private final String numeroRue;

    /**
     * Constructeur par défaut
     *
     * @param osmType
     * @param osmId
     * @param pays
     * @param codePostal
     * @param commune
     * @param rue
     * @param numeroRue
     */
    public SuggestedAddress(String osmType, Long osmId, String pays, String codePostal, String commune, String rue, String numeroRue) {
        this.osmType = osmType;
        this.osmId = osmId;
        this.pays = pays;
        this.codePostal = codePostal;
        this.commune = commune;
        this.rue = rue;
        this.numeroRue = numeroRue;
    }

    // GETTERS & SETTERS

    /**
     * Récupère le type OSM
     *
     * @return osmType
     */
    public String getOsmType() {
        return osmType;
    }

    /**
     * Récupère l'identifiant OSM
     *
     * @return osmId
     */
    public Long getOsmId() {
        return osmId;
    }

    @Override
    public String toString() {
        if (numeroRue != null) {
            return numeroRue + " " + rue + ", " + codePostal + " " + commune + ", " + pays;
        } else {
            return rue + ", " + codePostal + " " + commune + ", " + pays;
        }
    }
}
