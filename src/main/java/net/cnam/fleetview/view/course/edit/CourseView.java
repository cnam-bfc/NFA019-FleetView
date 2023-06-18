package net.cnam.fleetview.view.course.edit;

import net.cnam.fleetview.controller.CourseController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import java.awt.*;

public class CourseView extends View<CourseController> {
    // ÉLÉMENTS DE L'INTERFACE
    // Titre
    private final IconLabel titre;
    // Panel du contenu
    private final JPanel contenu;
    // Champ de saisie de l'id
    private final JTextField idField;
    // Champ de saisie du nom
    private final JTextField nomField;
    // Champ de saisie de la date
    private final JTextField dateField;
    // Tableau des colis
    //private final JTable colisTable;

    public CourseView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Bordures
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.titre = new IconLabel("\uF0D1", "Course");
        this.contenu = new JPanel();
        this.idField = new JTextField();
        this.nomField = new JTextField();
        this.dateField = new JTextField();


        // Configuration des éléments de l'interface
        // Panel du contenu
        BoxLayout contenuLayout = new BoxLayout(this.contenu, BoxLayout.Y_AXIS);
        this.contenu.setLayout(contenuLayout);

        this.contenu.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Champ de saisie de l'id
        this.idField.setEditable(false);

        // Champ de saisie du nom

        // Champ de saisie de la date


        // Ajout des éléments de l'interface
        this.add(this.titre, BorderLayout.NORTH);
        this.contenu.add(this.idField);
        this.contenu.add(this.nomField);
        this.contenu.add(this.dateField);
        this.add(this.contenu, BorderLayout.CENTER);
    }

    /**
     * Rendre les champs modifiables ou non
     *
     * @param editable
     */
    public void setFieldsEditable(boolean editable) {
        this.nomField.setEditable(editable);
        this.dateField.setEditable(editable);
    }

    /**
     * Remplir les champs de la vue
     *
     * @param id
     * @param nom
     * @param date
     */
    public void fill(String id, String nom, String date) {
        // Titre
        this.titre.getTexteLabel().setText("Course n°" + id + " - " + nom);

        // Champs
        this.idField.setText(id);
        this.nomField.setText(nom);
        this.dateField.setText(date);
    }
}
