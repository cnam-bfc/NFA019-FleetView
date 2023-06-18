package net.cnam.fleetview.view.course.edit;

import net.cnam.fleetview.controller.CourseController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CourseView extends View {
    // Controlleur
    private final CourseController controller = new CourseController(this);

    // ÉLÉMENTS DE L'INTERFACE
    // Titre
    private final IconLabel titre;
    // Panel du contenu
    private final JPanel contenu;
    // Champ de saisie de l'id
    private final JTextField idField;
    // Champ de saisie de la date
    private final JTextField dateField;

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
        this.dateField = new JTextField();


        // Configuration des éléments de l'interface
        // Panel du contenu
        BoxLayout contenuLayout = new BoxLayout(this.contenu, BoxLayout.Y_AXIS);
        this.contenu.setLayout(contenuLayout);

        this.contenu.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Champ de saisie de l'id
        this.idField.setEditable(false);
        this.idField.setBorder(BorderFactory.createTitledBorder("Id"));
        this.idField.setPreferredSize(new Dimension(200, 50));

        // Champ de saisie de la date
        this.dateField.setBorder(BorderFactory.createTitledBorder("Date"));
        this.dateField.setPreferredSize(new Dimension(200, 50));


        // Ajout des éléments de l'interface
        this.add(this.titre, BorderLayout.NORTH);
        this.contenu.add(this.idField);
        this.contenu.add(this.dateField);
        this.add(this.contenu, BorderLayout.CENTER);

        controller.onViewLoaded();
    }
}
