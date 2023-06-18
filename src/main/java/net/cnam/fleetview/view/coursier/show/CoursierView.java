package net.cnam.fleetview.view.coursier.show;

import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.button.LabelButton;
import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CoursierView extends View {
    // ÉLÉMENTS DE L'INTERFACE - Panel de contenu et les éléments
    // Ligne 1 titre
    private final IconLabel titre;

    // Ligne 2 redirection
    private final JPanel redirection;
    private final LabelButton redirectionFicheAccident;
    private final LabelButton redirectionStatistiques;
    private final LabelButton redirectionRapportQuotidien;

    // Ligne 3 matricule
    private final JPanel matricule;
    private final JLabel matriculeLabel;
    private final JLabel matriculeContenu;

    // Ligne 4 Sous titre
    private final JPanel sousTitreUn;
    private final JLabel sousTitreUnLabel;

    // Ligne 5 contenu
    private final JPanel contenuUn;
    private final JLabel contenuUnLabelUn;
    private final JLabel contenuUnLabelDeux;
    private final JLabel contenuUnLabelTrois;
    private final JLabel contenuUnContenuUn;
    private final JLabel contenuUnContenuDeux;
    private final JLabel contenuUnContenuTrois;

    // Ligne 6 Sous titre
    private final JPanel sousTitreDeux;
    private final JLabel sousTitreDeuxLabel;

    // Ligne 7 contenu
    private final JPanel contenuDeux;
    private final JLabel contenuDeuxLabelUn;
    private final JLabel contenuDeuxLabelDeux;
    private final JLabel contenuDeuxLabelTrois;
    private final JLabel contenuDeuxContenuUn;
    private final JLabel contenuDeuxContenuDeux;
    private final JLabel contenuDeuxContenuTrois;

    // Ligne 8 Sous titre
    private final JPanel sousTitreTrois;
    private final JLabel sousTitreTroisLabel;

    // Ligne 9 contenu
    private final JPanel contenuTrois;
    private final JLabel contenuTroisLabelUn;
    private final JLabel contenuTroisLabelDeux;
    private final JLabel contenuTroisLabelTrois;
    private final JLabel contenuTroisContenuUn;
    private final JLabel contenuTroisContenuDeux;
    private final JLabel contenuTroisContenuTrois;

    public CoursierView() {
        super();

        // Layout
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        // On met de l'espace à gauche et à droite
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface et ajout à l'interface
        // Ligne 1 titre
        this.titre = new IconLabel("\uF84A", "COURSIER");
        titre.getTexteLabel().setFont(new Font("Roboto", Font.BOLD, 32));
        this.add(this.titre);

        // Ligne 2 redirection
        this.redirection = new JPanel();
        FlowLayout redirectionLayout = new FlowLayout(FlowLayout.CENTER);
        redirectionLayout.setHgap(100);
        this.redirection.setLayout(redirectionLayout);
        this.redirectionFicheAccident = new LabelButton("Fiche accident");
        this.redirectionStatistiques = new LabelButton("Statistiques");
        this.redirectionRapportQuotidien = new LabelButton("Rapport quotidien");
        this.redirection.add(this.redirectionFicheAccident);
        this.redirection.add(this.redirectionStatistiques);
        this.redirection.add(this.redirectionRapportQuotidien);
        this.add(this.redirection);

        // Ligne 3 matricule
        this.matricule = new JPanel();
        FlowLayout matriculeLayout = new FlowLayout(FlowLayout.LEFT);
        matriculeLayout.setHgap(10);
        this.matricule.setLayout(matriculeLayout);
        this.matriculeLabel = new JLabel("Matricule : ");
        this.matriculeContenu = new JLabel("123456789");
        this.matricule.add(this.matriculeLabel);
        this.matricule.add(this.matriculeContenu);
        this.add(this.matricule);

        // Ligne 4 Sous titre
        this.sousTitreUn = new JPanel();
        FlowLayout sousTitreUnLayout = new FlowLayout(FlowLayout.LEFT);
        sousTitreUnLayout.setHgap(10);
        this.sousTitreUn.setLayout(sousTitreUnLayout);
        this.sousTitreUnLabel = new JLabel("Informations personnelles");
        this.sousTitreUn.add(this.sousTitreUnLabel);
        this.add(this.sousTitreUn);

        // Ligne 5 contenu
        this.contenuUn = new JPanel();
        GridLayout contenuUnLayout = new GridLayout(2, 3);
        contenuUnLayout.setHgap(10);
        this.contenuUn.setLayout(contenuUnLayout);
        this.contenuUnLabelUn = new JLabel("Nom : ");
        this.contenuUnLabelDeux = new JLabel("Prénom : ");
        this.contenuUnLabelTrois = new JLabel("Date de naissance : ");
        this.contenuUnContenuUn = new JLabel("Doe");
        this.contenuUnContenuDeux = new JLabel("John");
        this.contenuUnContenuTrois = new JLabel("01/01/1970");
        this.contenuUn.add(this.contenuUnLabelUn);
        this.contenuUn.add(this.contenuUnLabelDeux);
        this.contenuUn.add(this.contenuUnLabelTrois);
        this.contenuUn.add(this.contenuUnContenuUn);
        this.contenuUn.add(this.contenuUnContenuDeux);
        this.contenuUn.add(this.contenuUnContenuTrois);
        this.add(this.contenuUn);

        // Ligne 6 Sous titre
        this.sousTitreDeux = new JPanel();
        FlowLayout sousTitreDeuxLayout = new FlowLayout(FlowLayout.LEFT);
        sousTitreDeuxLayout.setHgap(10);
        this.sousTitreDeux.setLayout(sousTitreDeuxLayout);
        this.sousTitreDeuxLabel = new JLabel("Informations professionnelles");
        this.sousTitreDeux.add(this.sousTitreDeuxLabel);
        this.add(this.sousTitreDeux);

        // Ligne 7 contenu
        this.contenuDeux = new JPanel();
        GridLayout contenuDeuxLayout = new GridLayout(3, 2);
        contenuDeuxLayout.setHgap(10);
        this.contenuDeux.setLayout(contenuDeuxLayout);
        this.contenuDeuxLabelUn = new JLabel("Date d'embauche : ");
        this.contenuDeuxLabelDeux = new JLabel("Nombre de livraisons : ");
        this.contenuDeuxLabelTrois = new JLabel("Nombre d'accidents : ");
        this.contenuDeuxContenuUn = new JLabel("01/01/1970");
        this.contenuDeuxContenuDeux = new JLabel("123");
        this.contenuDeuxContenuTrois = new JLabel("123");
        this.contenuDeux.add(this.contenuDeuxLabelUn);
        this.contenuDeux.add(this.contenuDeuxLabelDeux);
        this.contenuDeux.add(this.contenuDeuxLabelTrois);
        this.contenuDeux.add(this.contenuDeuxContenuUn);
        this.contenuDeux.add(this.contenuDeuxContenuDeux);
        this.contenuDeux.add(this.contenuDeuxContenuTrois);
        this.add(this.contenuDeux);

        // Ligne 8 Sous titre
        this.sousTitreTrois = new JPanel();
        FlowLayout sousTitreTroisLayout = new FlowLayout(FlowLayout.LEFT);
        sousTitreTroisLayout.setHgap(10);
        this.sousTitreTrois.setLayout(sousTitreTroisLayout);
        this.sousTitreTroisLabel = new JLabel("Informations de connexion");
        this.sousTitreTrois.add(this.sousTitreTroisLabel);
        this.add(this.sousTitreTrois);

        // Ligne 9 contenu
        this.contenuTrois = new JPanel();
        GridLayout contenuTroisLayout = new GridLayout(2, 2);
        contenuTroisLayout.setHgap(10);
        this.contenuTrois.setLayout(contenuTroisLayout);
        this.contenuTroisLabelUn = new JLabel("Identifiant : ");
        this.contenuTroisLabelDeux = new JLabel("Mot de passe : ");
        this.contenuTroisLabelTrois = new JLabel("Rôle : ");
        this.contenuTroisContenuUn = new JLabel("123456789");
        this.contenuTroisContenuDeux = new JLabel("123456789");
        this.contenuTroisContenuTrois = new JLabel("Coursier");
        this.contenuTrois.add(this.contenuTroisLabelUn);
        this.contenuTrois.add(this.contenuTroisLabelDeux);
        this.contenuTrois.add(this.contenuTroisLabelTrois);
        this.contenuTrois.add(this.contenuTroisContenuUn);
        this.contenuTrois.add(this.contenuTroisContenuDeux);
        this.contenuTrois.add(this.contenuTroisContenuTrois);
        this.add(this.contenuTrois);
    }
}
