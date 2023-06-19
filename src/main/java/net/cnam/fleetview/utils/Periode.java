package net.cnam.fleetview.utils;

/**
 * Enumération des périodes
 */
public enum Periode {
    JOUR("Jour", "DAY"),
    SEMAINE("Semaine", "WEEK"),
    MOIS("Mois", "MONTH"),
    TRIMESTRE("Trimestre", "QUARTER"),
    ANNEE("Année", "YEAR"),
    ALL_TIME ("Depuis toujours", "ALL_TIME");

    private final String name;
    private final String englishName;

    /**
     * Constructeur
     *
     * @param name Nom de la période
     */
    Periode(String name, String englishName) {
        this.name = name;
        this.englishName = englishName;
    }

    /**
     * Récupérer le nom de la période
     *
     * @return le nom de la période
     */
    public String getName() {
        return name;
    }

    /**
     * Récupérer le nom anglais de la période
     *
     * @return le nom anglais de la période
     */
    public String getEnglishName() {
        return englishName;
    }
}
