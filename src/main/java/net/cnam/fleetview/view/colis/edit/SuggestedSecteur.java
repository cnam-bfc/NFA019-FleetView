package net.cnam.fleetview.view.colis.edit;

public class SuggestedSecteur {
    /**
     * Identifiant du secteur
     */
    private int idSecteur;
    /**
     * Nom du secteur
     */
    private String nom;

    /**
     * Constructeur par défaut
     *
     * @param idSecteur l'identifiant du secteur
     * @param nom       le nom du secteur
     */
    public SuggestedSecteur(int idSecteur, String nom) {
        this.idSecteur = idSecteur;
        this.nom = nom;
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
     * Récupère le nom du secteur
     *
     * @return le nom du secteur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du secteur
     *
     * @param nom le nouveau nom du secteur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
