package net.cnam.fleetview.view.coursier.visualisation;

import com.github.lgooddatepicker.components.DatePicker;
import net.cnam.fleetview.controller.coursier.CoursierController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.button.IconLabelButton;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.components.label.LabelInformation;
import net.cnam.fleetview.view.components.row.RowLabelInformationForCoursierView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;

/**
 * Vue de la page permettant de visualiser les rapports d'activité d'un coursier
 */
public class CoursierView extends View<CoursierController> {
    // ÉLÉMENTS DE L'INTERFACE - Panel de contenu et les éléments
    // Ligne 1 titre
    private final IconLabel titre;

    // Ligne 2 redirection
    private final JPanel redirection;
    private final IconLabelButton redirectionFicheAccident;
    private final IconLabelButton redirectionRapportQuotidien;

    // Ligne 3 matricule
    private final JPanel matricule;
    private final IconLabel matriculeLabel;
    private final JLabel matriculeContenu;

    // Ligne 4 - Course et cycle en cours
    private final JPanel courseEtCycleEnCours;
    private final LabelInformation courseEnCours;
    private final LabelInformation cycleEnCours;

    // Ligne 5 - Titre rapport activité
    private final IconLabel rapportActiviteLabel;

    // Ligne 6 - Choix date début et date fin
    private final JPanel choixDate;
    private final JLabel dateDebutLabel;
    private final JLabel dateFinLabel;
    private final DatePicker dateDebut;
    private final DatePicker dateFin;

    // Ligne 7 : rapport activité - 1
    private final RowLabelInformationForCoursierView contenuUnRow;

    // Ligne 8 : rapport activité - 2
    private final RowLabelInformationForCoursierView contenuDeuxRow;

    // Ligne 9 : Bouton d'export
    private final JPanel export;
    private final IconLabelButton exportButton;


    /**
     * Constructeur de la vue
     */
    public CoursierView() {
        super();

        // Active le scroll vertical quand nécessaire
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Layout
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        // On met de l'espace à gauche et à droite
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface et ajout à l'interface
        // Ligne 1 titre
        this.titre = new IconLabel("\uF84A", "");
        titre.getTexteLabel().setFont(new Font("Roboto", Font.BOLD, 36));
        titre.getTexteLabel().setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        this.add(this.titre);

        // Ligne 2 redirection
        this.redirection = new JPanel();
        FlowLayout redirectionLayout = new FlowLayout(FlowLayout.CENTER);
        redirectionLayout.setHgap(150);
        this.redirection.setLayout(redirectionLayout);
        this.redirectionFicheAccident = new IconLabelButton("\uF5E1", "Voir fiches accident");
        this.redirectionFicheAccident.getIconLabel().setFont(this.redirectionFicheAccident.getIconLabel().getFont().deriveFont(20f));
        this.redirectionFicheAccident.getIconLabel().revalidate();
        this.redirectionFicheAccident.setPreferredSize(new Dimension(200, 40));
        this.redirectionFicheAccident.setMaximumSize(new Dimension(200, 40));
        this.redirectionFicheAccident.setMinimumSize(new Dimension(200, 40));
        this.redirectionRapportQuotidien = new IconLabelButton("\uF31C", "Voir rapports quotidien");
        this.redirectionRapportQuotidien.getIconLabel().setFont(this.redirectionRapportQuotidien.getIconLabel().getFont().deriveFont(20f));
        this.redirectionRapportQuotidien.getIconLabel().revalidate();
        this.redirectionRapportQuotidien.setPreferredSize(new Dimension(200, 40));
        this.redirectionRapportQuotidien.setMaximumSize(new Dimension(200, 40));
        this.redirectionRapportQuotidien.setMinimumSize(new Dimension(200, 40));
        this.redirection.add(this.redirectionFicheAccident);
        this.redirection.add(this.redirectionRapportQuotidien);
        this.add(this.redirection);

        // Ligne 3 matricule
        this.matricule = new JPanel();
        FlowLayout matriculeLayout = new FlowLayout(FlowLayout.LEFT);
        matriculeLayout.setHgap(10);
        this.matricule.setLayout(matriculeLayout);
        this.matriculeLabel = new IconLabel("\uF2BB", "Matricule : ");
        this.matriculeContenu = new JLabel("");
        this.matricule.add(this.matriculeLabel);
        this.matricule.add(this.matriculeContenu);
        this.add(this.matricule);

        // Ligne 4 - Course et cycle en cours.
        this.courseEtCycleEnCours = new JPanel();
        FlowLayout courseEtCycleEnCoursLayout = new FlowLayout(FlowLayout.CENTER);
        courseEtCycleEnCoursLayout.setHgap(200);
        this.courseEtCycleEnCours.setLayout(courseEtCycleEnCoursLayout);
        this.courseEnCours = new LabelInformation("Course en cours", "");
        this.cycleEnCours = new LabelInformation("Cycle en cours", "");
        this.courseEtCycleEnCours.add(this.courseEnCours);
        this.courseEtCycleEnCours.add(this.cycleEnCours);
        this.add(this.courseEtCycleEnCours);

        // Ligne 5 : Titre rapport activité
        JPanel rapportActivite = new JPanel();
        FlowLayout rapportActiviteLayout = new FlowLayout(FlowLayout.CENTER);
        this.rapportActiviteLabel = new IconLabel("\uE473", "Rapport d'activité");
        this.rapportActiviteLabel.getTexteLabel().setFont(new Font("Roboto", Font.BOLD, 32));
        rapportActivite.add(this.rapportActiviteLabel);
        this.add(rapportActivite);

        // Ligne 6 : Choix date rapport activité en mettant des calendriers pour choisir la date de début et de fin
        this.choixDate = new JPanel();
        FlowLayout choixDateLayout = new FlowLayout(FlowLayout.CENTER);
        JPanel dateUne = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel dateDeux = new JPanel(new FlowLayout(FlowLayout.CENTER));
        choixDateLayout.setHgap(100);
        this.choixDate.setLayout(choixDateLayout);
        this.dateDebutLabel = new JLabel("Date début : ");
        this.dateFinLabel = new JLabel("Date fin : ");
        this.dateDebut = new DatePicker();
        this.dateFin = new DatePicker();
        dateUne.add(this.dateDebutLabel);
        dateUne.add(this.dateDebut);
        dateDeux.add(this.dateFinLabel);
        dateDeux.add(this.dateFin);
        this.choixDate.add(dateUne);
        this.choixDate.add(dateDeux);
        this.add(this.choixDate);

        // On gère les dates
        this.dateDebut.addDateChangeListener((dateChangeEvent) -> {
            if (this.dateDebut.getDate() != null && this.dateFin.getDate() != null) {
                // Si la date de début est après la date de fin, on met la date de début à la date de fin
                if (this.dateDebut.getDate().isAfter(this.dateFin.getDate())) {
                    this.dateFin.setDate(this.dateDebut.getDate());
                }
                actualiserInformations();
            }
        });
        this.dateFin.addDateChangeListener((dateChangeEvent) -> {
            if (this.dateDebut.getDate() != null && this.dateFin.getDate() != null) {
                // Si la date de fin est avant la date de début, on met la date de fin à la date de début
                if (this.dateFin.getDate().isBefore(this.dateDebut.getDate())) {
                    this.dateDebut.setDate(this.dateFin.getDate());
                }
                actualiserInformations();
            }
        });

        // Ligne 7 : Rapport activité - 1
        LabelInformation contenuUnLabelUn = new LabelInformation("Paquets livrés", "");
        LabelInformation contenuUnLabelDeux = new LabelInformation("Poids livré", "");
        LabelInformation contenuUnLabelTrois = new LabelInformation("Poids moyens", "");
        this.contenuUnRow = new RowLabelInformationForCoursierView(contenuUnLabelUn, contenuUnLabelDeux, contenuUnLabelTrois);
        this.add(this.contenuUnRow);

        // Ligne 8 : Rapport activité - 2
        LabelInformation contenuDeuxLabelUn = new LabelInformation("Nombre de course", "");
        LabelInformation contenuDeuxLabelDeux = new LabelInformation("Distance parcourue", "");
        LabelInformation contenuDeuxLabelTrois = new LabelInformation("Nombre d'accidents", "");
        this.contenuDeuxRow = new RowLabelInformationForCoursierView(contenuDeuxLabelUn, contenuDeuxLabelDeux, contenuDeuxLabelTrois);
        this.add(this.contenuDeuxRow);

        // Ligne 9 : Bouton d'exportation
        this.export = new JPanel();
        FlowLayout exportationLayout = new FlowLayout(FlowLayout.CENTER);
        this.export.setLayout(exportationLayout);
        this.exportButton = new IconLabelButton("\uF56E", "Exporter");
        this.export.add(this.exportButton);
        this.add(this.export);
        this.exportButton.addActionListener((actionEvent) -> {
            this.exporterRapportActivite();
        });
    }

    /**
     * Méthode permettant d'exporter le rapport d'activité en pdf
     */
    private void exporterRapportActivite() {
        JFileChooser fileChooser = new JFileChooser();
        // Filtrer les fichiers pour n'afficher que les fichiers PDF
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers PDF", "pdf");
        fileChooser.setFileFilter(filter);

        // Afficher la boîte de dialogue pour choisir un fichier
        int returnValue = fileChooser.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // Récupérer le fichier sélectionné
            File selectedFile = fileChooser.getSelectedFile();
            String selectedFilePath = selectedFile.getParent();
            System.out.println("Chemin du fichier : " + selectedFilePath);

            // Récupérer le nom du fichier sans extension
            String selectedFileName = selectedFile.getName();
            int extensionIndex = selectedFileName.lastIndexOf('.');
            if (extensionIndex > 0) {
                selectedFileName = selectedFileName.substring(0, extensionIndex);
            }
            System.out.println("Nom du fichier : " + selectedFileName);

            // On appelle la méthode d'exportation du rapport d'activité
            this.controller.exporterRapportActivite(selectedFilePath, selectedFileName,
                    this.contenuUnRow.getFirstLabel().getSecondLabel().getText(),
                    this.contenuUnRow.getSecondLabel().getSecondLabel().getText(),
                    this.contenuUnRow.getThirdLabel().getSecondLabel().getText(),
                    this.contenuDeuxRow.getFirstLabel().getSecondLabel().getText(),
                    this.contenuDeuxRow.getSecondLabel().getSecondLabel().getText(),
                    this.contenuDeuxRow.getThirdLabel().getSecondLabel().getText()
            );
        } else {
            System.out.println("Aucun fichier sélectionné");
        }
    }

    /**
     * Méthode permettant d'actualiser les informations du rapport d'activité
     */
    private void actualiserInformations() {
        this.controller.actualiserInformations();
    }

    /**
     * Méthode permettant de mettre à jour le titre du rapport d'activité
     *
     * @param titre Le titre du rapport d'activité
     */
    public void setTitre(String titre) {
        this.titre.getTexteLabel().setText(titre);
    }

    /**
     * Méthode permettant de mettre à jour le matricule du coursier
     *
     * @param matricule Le matricule du coursier
     */
    public void setMatricule(String matricule) {
        this.matriculeContenu.setText(matricule);
    }

    /**
     * Méthode permettant de mettre à jour la course en cours
     *
     * @param courseEnCours La course en cours
     */
    public void setCourseEnCours(String courseEnCours) {
        this.courseEnCours.getSecondLabel().setText(courseEnCours);
    }

    /**
     * Méthode permettant de mettre à jour le cycle en cours
     *
     * @param cycleEnCours Le cycle en cours
     */
    public void setCycleEnCours(String cycleEnCours) {
        this.cycleEnCours.getSecondLabel().setText(cycleEnCours);
    }

    /**
     * Méthode permettant de mettre à jour le nombre de paquets livrés
     *
     * @param paquetLivre Le nombre de paquets livrés
     */
    public void setPaquetLivre(String paquetLivre) {
        this.contenuUnRow.getFirstLabel().getSecondLabel().setText(paquetLivre);
    }

    /**
     * Méthode permettant de mettre à jour le poids livré
     *
     * @param poidLivre Le poids livré
     */
    public void setPoidLivre(String poidLivre) {
        this.contenuUnRow.getSecondLabel().getSecondLabel().setText(poidLivre);
    }

    /**
     * Méthode permettant de mettre à jour le poids moyen
     *
     * @param poidMoyen Le poids moyen
     */
    public void setPoidMoyen(String poidMoyen) {
        this.contenuUnRow.getThirdLabel().getSecondLabel().setText(poidMoyen);
    }

    /**
     * Méthode permettant de mettre à jour le nombre de course
     *
     * @param nombreCourse Le nombre de course
     */
    public void setNombreCourse(String nombreCourse) {
        this.contenuDeuxRow.getFirstLabel().getSecondLabel().setText(nombreCourse);
    }

    /**
     * Méthode permettant de mettre à jour la distance parcourue
     *
     * @param distanceParcourue La distance parcourue
     */
    public void setDistanceParcourue(String distanceParcourue) {
        this.contenuDeuxRow.getSecondLabel().getSecondLabel().setText(distanceParcourue);
    }

    /**
     * Méthode permettant de mettre à jour le nombre d'accidents
     *
     * @param nombreAccident Le nombre d'accidents
     */
    public void setNombreAccident(String nombreAccident) {
        this.contenuDeuxRow.getThirdLabel().getSecondLabel().setText(nombreAccident);
    }

    /**
     * Méthode permettant de mettre à jour la date de début
     *
     * @return La date de début
     */
    public String getDateDebut() {
        return this.dateDebut.getDateStringOrEmptyString();
    }

    /**
     * Méthode permettant de mettre à jour la date de fin
     *
     * @return La date de fin
     */
    public String getDateFin() {
        return this.dateFin.getDateStringOrEmptyString();
    }

    /**
     * Méthode permettant de mettre à jour la date de début
     *
     * @param dateDebut La date de début
     */
    public void setDateDebut(String dateDebut) {
        this.dateDebut.setDate(LocalDate.parse(dateDebut));
    }

    /**
     * Méthode permettant de mettre à jour la date de fin
     *
     * @param dateFin La date de fin
     */
    public void setDateFin(String dateFin) {
        this.dateFin.setDate(LocalDate.parse(dateFin));
    }
}
