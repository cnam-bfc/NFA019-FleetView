package net.cnam.fleetview.model.basededonnees;

public class BaseDeDonnees {
    private String adresseIP;
    private String port;
    private String nomBase;
    private String identifiant;
    private String motDePasse;

    public BaseDeDonnees() {
    }

    public BaseDeDonnees(String adresseIP, String port, String nomBase, String identifiant, String motDePasse) {
        this.adresseIP = adresseIP;
        this.port = port;
        this.nomBase = nomBase;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
    }

    public String getAdresseIP() {
        return adresseIP;
    }

    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getNomBase() {
        return nomBase;
    }

    public void setNomBase(String nomBase) {
        this.nomBase = nomBase;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
