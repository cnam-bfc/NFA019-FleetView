package net.cnam.fleetview.view;

import net.cnam.fleetview.controller.FastStartupController;
import net.cnam.fleetview.view.components.button.IconLabelButton;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.components.label.LabelInformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CoursierRecapitulatifCourseView extends View<FastStartupController> {
    private IconLabel titre;
    private JPanel contenu;
    // Course
    private JPanel contenuCourse;
    private IconLabel titreCourse;
    private JPanel contenuCourseInfos;
    private LabelInformation distance;
    private LabelInformation poidsTotal;
    private IconLabelButton changeCourse;
    // Cycle
    private JPanel contenuCycle;
    private IconLabel titreCycle;
    private JPanel contenuCycleInfos;
    private LabelInformation cycleIdentifiant;
    private LabelInformation cycleChargeMax;
    private IconLabelButton changeCycle;
    private IconLabelButton validerCourse;

    public CoursierRecapitulatifCourseView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Bordures
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.titre = new IconLabel("\uF52E", "Menu de lancement de course");
        this.contenu = new JPanel();
        this.contenuCourse = new JPanel();
        this.titreCourse = new IconLabel("\uF0D1", "Course");
        this.distance = new LabelInformation("Distance de la course", "Course non sélectionnée");
        this.poidsTotal = new LabelInformation("Poids total de la course", "Course non sélectionnée");
        this.changeCourse = new IconLabelButton("\uF0D1", "Changer de course");
        this.contenuCycle = new JPanel();
        this.titreCycle = new IconLabel("\uF206", "Cycle");
        this.cycleIdentifiant = new LabelInformation("Identifiant du cycle", "Cycle non sélectionné");
        this.cycleChargeMax = new LabelInformation("Charge maximale du cycle", "Cycle non sélectionné");
        this.changeCycle = new IconLabelButton("\uF206", "Changer de cycle");
        this.validerCourse = new IconLabelButton("\uF11E", "Valider la course");

        this.titre.getTexteLabel().setFont(new Font("Roboto", Font.BOLD, 36));
        this.titreCourse.getTexteLabel().setFont(new Font("Roboto", Font.BOLD, 24));
        this.titreCycle.getTexteLabel().setFont(new Font("Roboto", Font.BOLD, 24));

        distance.getSecondLabel().setPreferredSize(new Dimension(100, 25));
        poidsTotal.getSecondLabel().setPreferredSize(new Dimension(100, 25));
        cycleIdentifiant.getSecondLabel().setPreferredSize(new Dimension(100, 25));
        cycleChargeMax.getSecondLabel().setPreferredSize(new Dimension(100, 25));

        // Panels
        // Panel de contenu, 2 colonne 1 ligne
        this.contenu = new JPanel(new GridLayout(1, 2));
        this.contenu.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        // Bordure entre les grilles
        this.contenu.setBorder(BorderFactory.createCompoundBorder(
                this.contenu.getBorder(),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)));

        // Panel de contenu de la course
        this.contenuCourse = new JPanel(new BorderLayout());
        this.contenuCourseInfos = new JPanel(new GridLayout(0, 1));

        // Panel de contenu de du cycle
        this.contenuCycle = new JPanel(new BorderLayout());
        this.contenuCycleInfos = new JPanel(new GridLayout(0, 1));

        // *** ÉVÈNEMENTS ***
        // Évènement permettant de changer de course
        this.changeCourse.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // On passe à la vue de la course
                controller.onAjouterCourse();
            }
        });

        // Évènement permettant de changer de cycle
        this.changeCycle.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // On passe à la vue du cycle
                controller.onAjouterCycle();
            }
        });

        // Évènement permettant de valider la course (vérifie également si elle et le cycle sont toujours dispo)
        validerCourse.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // On passe à la vue de la course
                controller.lancerCourse();
            }
        });

        // *** AJOUT DES ÉLÉMENTS ***

        // Panels de centrage
        JPanel centrerTitreCourse = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centrerTitreCourse.add(this.titreCourse);

        JPanel centrerTitreCycle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centrerTitreCycle.add(this.titreCycle);

        JPanel centrerChangeCourse = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centrerChangeCourse.add(this.changeCourse);

        JPanel centrerChangeCycle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centrerChangeCycle.add(this.changeCycle);

        JPanel centrerTitre = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centrerTitre.add(this.titre);
        centrerTitre.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JPanel validationCoursePanel = new JPanel();
        validationCoursePanel.add(this.validerCourse);
        validationCoursePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JPanel centrerContenuCourseInfos = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centrerContenuCourseInfos.add(this.contenuCourseInfos);

        JPanel centrerContenuCycleInfos = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centrerContenuCycleInfos.add(this.contenuCycleInfos);

        // contenu
        this.contenu.add(this.contenuCourse);
        this.contenu.add(this.contenuCycle);

        // contenuCourse
        this.contenuCourse.add(centrerTitreCourse, BorderLayout.NORTH);
        this.contenuCourse.add(centrerContenuCourseInfos);
        this.contenuCourse.add(centrerChangeCourse, BorderLayout.SOUTH);
        this.contenuCourseInfos.add(this.distance);
        this.contenuCourseInfos.add(this.poidsTotal);

        // contenuCycle
        this.contenuCycle.add(centrerTitreCycle, BorderLayout.NORTH);
        this.contenuCycle.add(centrerContenuCycleInfos);
        this.contenuCycle.add(centrerChangeCycle, BorderLayout.SOUTH);
        this.contenuCycleInfos.add(this.cycleIdentifiant);
        this.contenuCycleInfos.add(this.cycleChargeMax);

        // Principale
        this.add(centrerTitre, BorderLayout.NORTH);
        this.add(this.contenu, BorderLayout.CENTER);
        this.add(validationCoursePanel, BorderLayout.SOUTH);
    }

    public void fillCourse(String distance, String poidsTotal) {
        this.distance.getSecondLabel().setText(distance + " km");
        this.poidsTotal.getSecondLabel().setText(poidsTotal + " kg");

        this.distance.setFont(new Font("Roboto", Font.PLAIN, 18));
        this.poidsTotal.setFont(new Font("Roboto", Font.PLAIN, 18));

        this.contenuCourseInfos.add(this.distance);
        this.contenuCourseInfos.add(this.poidsTotal);
    }

    public void fillCycle(String cycleIdentifiant, String cycleChargeMax) {
        this.cycleIdentifiant.getSecondLabel().setText(cycleIdentifiant + " kg");
        this.cycleChargeMax.getSecondLabel().setText(cycleChargeMax + " kg");

        this.cycleIdentifiant.setFont(new Font("Roboto", Font.PLAIN, 18));
        this.cycleChargeMax.setFont(new Font("Roboto", Font.PLAIN, 18));

        this.contenuCycleInfos.add(this.cycleIdentifiant);
        this.contenuCycleInfos.add(this.cycleChargeMax);
    }
}

