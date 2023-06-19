package net.cnam.fleetview.utils;

/**
 * Enumération des périodes
 */
public enum Periode {
    JOUR("Jour"),
    SEMAINE("Semaine"),
    MOIS("Mois"),
    TRIMESTRE("Trimestre"),
    ANNEE("Année"),
    ALL_TIME ("Depuis toujours");

    private final String name;

    /**
     * Constructeur
     *
     * @param name Nom de la période
     */
    Periode(String name) {
        this.name = name;
    }

    /**
     * Récupérer le nom de la période
     *
     * @return le nom de la période
     */
    public String getName() {
        return name;
    }
}
