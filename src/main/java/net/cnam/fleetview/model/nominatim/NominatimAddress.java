package net.cnam.fleetview.model.nominatim;

import java.util.Objects;

public class NominatimAddress {
    /**
     * Identifiant nominatim
     */
    private Identifier nominatimId;
    /**
     * Latitude
     */
    private Double lat;
    /**
     * Longitude
     */
    private Double lon;
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
     * Récupère l'identifiant nominatim
     *
     * @return nominatimId
     */
    public Identifier getNominatimId() {
        return nominatimId;
    }

    /**
     * Définit l'identifiant nominatim
     *
     * @param nominatimId
     */
    public void setNominatimId(Identifier nominatimId) {
        this.nominatimId = nominatimId;
    }

    /**
     * Récupère la latitude
     *
     * @return lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     * Définit la latitude
     *
     * @param lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * Récupère la longitude
     *
     * @return lon
     */
    public Double getLon() {
        return lon;
    }

    /**
     * Définit la longitude
     *
     * @param lon
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * Récupère le pays
     *
     * @return
     */
    public String getPays() {
        return pays;
    }

    /**
     * Définit le pays
     *
     * @param pays
     */
    public void setPays(String pays) {
        this.pays = pays;
    }

    /**
     * Récupère le code postal
     *
     * @return codePostal
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Définit le code postal
     *
     * @param codePostal
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Récupère la commune
     *
     * @return
     */
    public String getCommune() {
        return commune;
    }

    /**
     * Définit la commune
     *
     * @param commune
     */
    public void setCommune(String commune) {
        this.commune = commune;
    }

    /**
     * Récupère la rue
     *
     * @return rue
     */
    public String getRue() {
        return rue;
    }

    /**
     * Définit la rue
     *
     * @param rue
     */
    public void setRue(String rue) {
        this.rue = rue;
    }

    /**
     * Récupère le numéro de rue
     *
     * @return numeroDeRue
     */
    public String getNumeroDeRue() {
        return numeroDeRue;
    }

    /**
     * Définit le numéro de rue
     *
     * @param numeroDeRue
     */
    public void setNumeroDeRue(String numeroDeRue) {
        this.numeroDeRue = numeroDeRue;
    }

    /**
     * Récupère le complément
     *
     * @return complement
     */
    public String getComplement() {
        return complement;
    }

    /**
     * Définit le complément
     *
     * @param complement
     */
    public void setComplement(String complement) {
        this.complement = complement;
    }

    public static class Identifier implements Comparable<Identifier> {
        /**
         * Type OSM
         */
        private String osmType;
        /**
         * Identifiant OSM
         */
        private long osmId;

        /**
         * Constructeur par défaut
         */
        public Identifier() {
        }

        /**
         * Constructeur
         *
         * @param osmType
         * @param osmId
         */
        public Identifier(String osmType, long osmId) {
            osmType = osmType.substring(0, 1).toUpperCase();
            this.osmType = osmType;
            this.osmId = osmId;
        }

        /**
         * Récupère le type OSM
         *
         * @return osmType
         */
        public String getOsmType() {
            return osmType;
        }

        /**
         * Définit le type OSM
         *
         * @param osmType osmType
         */
        public void setOsmType(String osmType) {
            osmType = osmType.substring(0, 1).toUpperCase();
            this.osmType = osmType;
        }

        /**
         * Récupère l'identifiant OSM
         *
         * @return osmId
         */
        public long getOsmId() {
            return osmId;
        }

        /**
         * Définit l'identifiant OSM
         *
         * @param osmId osmId
         */
        public void setOsmId(long osmId) {
            this.osmId = osmId;
        }

        @Override
        public int compareTo(Identifier o) {
            if (this.osmType.equals(o.osmType)) {
                return Long.compare(this.osmId, o.osmId);
            } else {
                return this.osmType.compareTo(o.osmType);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Identifier that = (Identifier) o;
            return Objects.equals(osmType, that.osmType) && Objects.equals(osmId, that.osmId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(osmType, osmId);
        }
    }
}
