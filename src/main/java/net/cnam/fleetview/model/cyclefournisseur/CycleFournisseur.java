package net.cnam.fleetview.model.cyclefournisseur;

import java.time.LocalDateTime;

/**
 * Classe CycleFournisseur
 *
 * Cette classe permet de créer des objets CycleFournisseur.
 * table concernée : fleetview_cycle_fournisseur
 */
public class CycleFournisseur {
    private Integer idCycleFournisseur;
    private String nom;
    private String mail;
    private String telephone;
    private LocalDateTime dateArchive;

    /**
     * Constructeur
     */
    public CycleFournisseur() {
    }

    // GETTERS & SETTERS

    /**
     * Getter sur l'id
     *
     * @return
     */
    public Integer getIdCycleFournisseur() {
        return idCycleFournisseur;
    }

    /**
     * Setter sur l'id
     *
     * @param idCycleFournisseur
     */
    public void setId(Integer idCycleFournisseur) {
        this.idCycleFournisseur = idCycleFournisseur;
    }

    /**
     * Getter sur le nom
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter sur le nom
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter sur le mail
     *
     * @return
     */
    public String getMail() {
        return mail;
    }

    /**
     * Setter sur le mail
     *
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Getter sur le téléphone
     *
     * @return
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Setter sur le téléphone
     *
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Getter sur la date d'arrivée
     *
     * @return
     */
    public LocalDateTime getDateArchive() {
        return dateArchive;
    }

    /**
     * Setter sur la date d'arrivée
     *
     * @param dateArchive
     */
    public void setDateArchive(LocalDateTime dateArchive) {
        this.dateArchive = dateArchive;
    }
}
