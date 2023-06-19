package net.cnam.fleetview.view.coursier.show;

import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.button.LabelButton;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.components.label.LabelInformation;
import net.cnam.fleetview.view.components.row.RowLabelInformationForCoursierView;

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

    // Ligne 4 contenu 1
    private final RowLabelInformationForCoursierView contenuUnRow;

    // Ligne 5 contenu 2
    private final RowLabelInformationForCoursierView contenuDeuxRow;

    // Ligne 6 contenu 3
    private final RowLabelInformationForCoursierView contenuTroisRow;

    public CoursierView() {
        super();

        // Layout
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        // On met de l'espace à gauche et à droite
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface et ajout à l'interface
        // Ligne 1 titre
        this.titre = new IconLabel("\uF84A", "");
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
        this.matriculeContenu = new JLabel("");
        this.matricule.add(this.matriculeLabel);
        this.matricule.add(this.matriculeContenu);
        this.add(this.matricule);

        // Ligne 4 contenu
        JLabel contenuUnTitle = new JLabel("Courses");
        LabelInformation contenuUnLabelUn = new LabelInformation("Semaine", "");
        LabelInformation contenuUnLabelDeux = new LabelInformation("Mois" , "");
        LabelInformation contenuUnLabelTrois = new LabelInformation("Année" , "");
        this.contenuUnRow = new RowLabelInformationForCoursierView(contenuUnTitle, contenuUnLabelUn, contenuUnLabelDeux, contenuUnLabelTrois);
        this.add(this.contenuUnRow);

        // Ligne 5 contenu
        JLabel contenuDeuxTitle = new JLabel("Colis");
        LabelInformation contenuDeuxLabelUn = new LabelInformation("Semaine", "");
        LabelInformation contenuDeuxLabelDeux = new LabelInformation("Mois" , "");
        LabelInformation contenuDeuxLabelTrois = new LabelInformation("Année" , "");
        this.contenuDeuxRow = new RowLabelInformationForCoursierView(contenuDeuxTitle, contenuDeuxLabelUn, contenuDeuxLabelDeux, contenuDeuxLabelTrois);
        this.add(this.contenuDeuxRow);

        // Ligne 6 contenu
        JLabel contenuTroisTitle = new JLabel("Autre");
        LabelInformation contenuTroisLabelUn = new LabelInformation("Accident(s)", "");
        LabelInformation contenuTroisLabelDeux = new LabelInformation("Course en cours" , "");
        LabelInformation contenuTroisLabelTrois = new LabelInformation("Cycle en cours" , "");
        this.contenuTroisRow = new RowLabelInformationForCoursierView(contenuTroisTitle, contenuTroisLabelUn, contenuTroisLabelDeux, contenuTroisLabelTrois);
        this.add(this.contenuTroisRow);
    }

    public void setTitre (String titre) {
        this.titre.getTexteLabel().setText(titre);
    }

    public void setMatricule (String matricule) {
        this.matriculeContenu.setText(matricule);
    }

    public void setCoursesSemaine(String coursesSemaine) {
        this.contenuUnRow.getFirstLabel().getSecondLabel().setText(coursesSemaine);
    }

    public void setCoursesMois(String coursesMois) {
        this.contenuUnRow.getSecondLabel().getSecondLabel().setText(coursesMois);
    }

    public void setCoursesAnnee(String coursesAnnee) {
        this.contenuUnRow.getThirdLabel().getSecondLabel().setText(coursesAnnee);
    }

    public void setColisSemaine(String colisSemaine) {
        this.contenuDeuxRow.getFirstLabel().getSecondLabel().setText(colisSemaine);
    }

    public void setColisMois(String colisMois) {
        this.contenuDeuxRow.getSecondLabel().getSecondLabel().setText(colisMois);
    }

    public void setColisAnnee(String colisAnnee) {
        this.contenuDeuxRow.getThirdLabel().getSecondLabel().setText(colisAnnee);
    }

    public void setAccidents(String accidents) {
        this.contenuTroisRow.getFirstLabel().getSecondLabel().setText(accidents);
    }

    public void setCourseEnCours(String courseEnCours) {
        this.contenuTroisRow.getSecondLabel().getSecondLabel().setText(courseEnCours);
    }

    public void setCycleEnCours(String cycleEnCours) {
        this.contenuTroisRow.getThirdLabel().getSecondLabel().setText(cycleEnCours);
    }
}
