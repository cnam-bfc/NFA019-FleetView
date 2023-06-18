package net.cnam.fleetview.view.course.list;

import net.cnam.fleetview.controller.CoursesController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.button.IconLabelButton;
import net.cnam.fleetview.view.components.field.IconTextField;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.utils.ButtonColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CoursesView extends View<CoursesController> {
    // ÉLÉMENTS DE L'INTERFACE
    // Titre
    private final IconLabel titre;
    // Panel du contenu
    private final JPanel contenu;
    // Barre de recherche
    private final IconTextField barreDeRecherche;
    // Tableau
    private final JTable tableau;
    // Ajout d'une course
    private final IconLabelButton ajouterCourse;

    public CoursesView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Bordures
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.titre = new IconLabel("\uF0D1", "Courses");
        this.contenu = new JPanel();
        this.barreDeRecherche = new IconTextField("\uF002");
        this.tableau = new JTable();
        this.ajouterCourse = new IconLabelButton("\uF067", "Ajouter une course");


        // Configuration des éléments de l'interface
        // Panel du contenu
        BorderLayout contenuLayout = new BorderLayout();
        this.contenu.setLayout(contenuLayout);

        this.contenu.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Barre de recherche
        // Set placeholder
        this.barreDeRecherche.getTextField().setToolTipText("Rechercher une course");

        // Tableau
        DefaultTableModel model = new DefaultTableModel();
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        JScrollPane tableauScrollPane = new JScrollPane(tableau);

        model.addColumn("ID");
        model.addColumn("Date");
        model.addColumn("Distance");
        model.addColumn("Cycle");
        model.addColumn("Livreur");
        model.addColumn("Statut");
        model.addColumn("Voir");
        model.addColumn("Modifier");
        model.addColumn("Supprimer");

        tableau.setModel(model);

        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Action voir
        ButtonColumn voirButtonColumn = new ButtonColumn(tableau, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onVoirCourse(Integer.parseInt(model.getValueAt(tableau.getSelectedRow(), 0).toString()));
            }
        }, 6);

        // Action modifier
        ButtonColumn modifierButtonColumn = new ButtonColumn(tableau, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onEditerCourse(Integer.parseInt(model.getValueAt(tableau.getSelectedRow(), 0).toString()));
            }
        }, 7);

        // Action supprimer
        ButtonColumn supprimerButtonColumn = new ButtonColumn(tableau, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSupprimerCourse(Integer.parseInt(model.getValueAt(tableau.getSelectedRow(), 0).toString()));
            }
        }, 8);

        tableau.setDefaultRenderer(Object.class, cellRenderer);

        // Tableau non editable
        tableau.setDefaultEditor(Object.class, null);

        // Ajout d'une course
        this.ajouterCourse.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onAjouterCourse();
            }
        });


        // Ajout des éléments de l'interface
        this.add(this.titre, BorderLayout.NORTH);
        this.contenu.add(this.barreDeRecherche, BorderLayout.NORTH);
        this.contenu.add(tableauScrollPane, BorderLayout.CENTER);
        this.contenu.add(this.ajouterCourse, BorderLayout.SOUTH);
        this.add(this.contenu, BorderLayout.CENTER);
    }

    public void addCourse(String id, String date, String distance, String cycle, String livreur, String statut) {
        DefaultTableModel model = (DefaultTableModel) this.tableau.getModel();

        model.addRow(new Object[]{id, date, distance, cycle, livreur, statut, "\uF06E", "\uF044", "\uF1F8"});
    }
}
