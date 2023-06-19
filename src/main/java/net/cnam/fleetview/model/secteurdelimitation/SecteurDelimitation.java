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
    private Integer idSecteur;
    /**
     * Identifiant du secteur point
     */
    private Integer idSecteurPoint;
    /**
     * Ordre du secteur delimitation
     */
    private Integer ordre;

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
    public Integer getIdSecteur() {
        return idSecteur;
    }

    /**
     * Définit l'identifiant du secteur
     *
     * @param idSecteur le nouvel identifiant du secteur
     */
    public void setIdSecteur(Integer idSecteur) {
        this.idSecteur = idSecteur;
    }

    /**
     * Récupère l'identifiant du secteur point
     *
     * @return l'identifiant du secteur point
     */
    public Integer getIdSecteurPoint() {
        return idSecteurPoint;
    }

    /**
     * Définit l'identifiant du secteur point
     *
     * @param idSecteurPoint le nouvel identifiant du secteur point
     */
    public void setIdSecteurPoint(Integer idSecteurPoint) {
        this.idSecteurPoint = idSecteurPoint;
    }

    /**
     * Récupère l'ordre du secteur delimitation
     *
     * @return l'ordre du secteur delimitation
     */
    public Integer getOrdre() {
        return ordre;
    }

    /**
     * Définit l'ordre du secteur delimitation
     *
     * @param ordre le nouvel ordre du secteur delimitation
     */
    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }
}
