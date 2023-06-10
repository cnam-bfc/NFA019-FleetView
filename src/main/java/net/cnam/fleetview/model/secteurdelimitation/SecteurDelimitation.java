package net.cnam.fleetview.model.secteurdelimitation;

/**
 * Classe SecteurDelimitation
 * <p>
 * Cette classe permet de créer des objets SecteurDelimitation.
 * table concernée : fleetview_secteurdelimitation
 */
public class SecteurDelimitation {
    /**
     * Identifiant du secteur
     */
    private int idSecteur;
    /**
     * Identifiant du secteur point
     */
    private int idSecteurPoint;
    /**
     * Ordre du secteur delimitation
     */
    private int ordre;

    /**
     * Constructeur par défaut
     */
    public SecteurDelimitation() {
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
     * Récupère l'ordre du secteur delimitation
     *
     * @return l'ordre du secteur delimitation
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * Définit l'ordre du secteur delimitation
     *
     * @param ordre le nouvel ordre du secteur delimitation
     */
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }
}
