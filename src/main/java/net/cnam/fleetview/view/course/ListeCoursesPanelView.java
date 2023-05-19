package net.cnam.fleetview.view.course;

import net.cnam.fleetview.view.TitrePanel;

import javax.swing.*;

public class ListeCoursesPanelView extends JPanel {
    // ÉLÉMENTS DE L'INTERFACE
    private final TitrePanel titrePanel;

    public ListeCoursesPanelView() {
        super();

        // Création des éléments de l'interface
        this.titrePanel = new TitrePanel("\uF0D1", "COURSES");

        // Ajout des éléments de l'interface
        this.add(this.titrePanel);
        this.add(new TitrePanel("\uF08C", "Linkedin"));
        this.add(new TitrePanel("\uF09A", "Facebook"));
        this.add(new TitrePanel("\uF099", "Twitter"));
        this.add(new TitrePanel("\uF0E1", "Google"));
        this.add(new TitrePanel("\uF0D6", "Github"));
    }
}
