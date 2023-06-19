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
import javax.swing.table.TableRowSorter;
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
    private final JTable coursesTable;
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
        this.coursesTable = new JTable();
        this.ajouterCourse = new IconLabelButton("\uF067", "Ajouter une course");


        // Configuration des éléments de l'interface
        // Panel du contenu
        BorderLayout contenuLayout = new BorderLayout();
        this.contenu.setLayout(contenuLayout);

        this.contenu.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Barre de recherche
        this.barreDeRecherche.setBorder(BorderFactory.createEmptyBorder(10, 25, 25, 25));
        this.barreDeRecherche.getTextField().setPlaceholder("Rechercher une course");

        // Tableau
        DefaultTableModel coursesTableModel = new DefaultTableModel();
        DefaultTableCellRenderer coursesTableCellRenderer = new DefaultTableCellRenderer();
        TableRowSorter<DefaultTableModel> coursesTableRowSorter = new TableRowSorter<>(coursesTableModel);
        JScrollPane coursesTableScrollPane = new JScrollPane(coursesTable);

        coursesTableModel.addColumn("ID");
        coursesTableModel.addColumn("Nom");
        coursesTableModel.addColumn("Date");
        coursesTableModel.addColumn("Distance");
        coursesTableModel.addColumn("Cycle");
        coursesTableModel.addColumn("Livreur");
        coursesTableModel.addColumn("Statut");
        coursesTableModel.addColumn("Voir");
        coursesTableModel.addColumn("Modifier");
        coursesTableModel.addColumn("Supprimer");

        coursesTable.setModel(coursesTableModel);

        coursesTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Action voir
        ButtonColumn voirButtonColumn = new ButtonColumn(coursesTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onVoirCourse(Integer.parseInt(coursesTableModel.getValueAt(coursesTable.getSelectedRow(), 0).toString()));
            }
        }, 7);

        // Action modifier
        ButtonColumn modifierButtonColumn = new ButtonColumn(coursesTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onEditerCourse(Integer.parseInt(coursesTableModel.getValueAt(coursesTable.getSelectedRow(), 0).toString()));
            }
        }, 8);

        // Action supprimer
        ButtonColumn supprimerButtonColumn = new ButtonColumn(coursesTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSupprimerCourse(Integer.parseInt(coursesTableModel.getValueAt(coursesTable.getSelectedRow(), 0).toString()));
            }
        }, 9);

        coursesTable.setDefaultRenderer(Object.class, coursesTableCellRenderer);

        // Tableau non editable
        coursesTable.setDefaultEditor(Object.class, null);

        // Tri du tableau
        this.barreDeRecherche.getTextField().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                coursesTableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + barreDeRecherche.getTextField().getText()));
            }
        });
        coursesTable.setRowSorter(coursesTableRowSorter);

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
        this.contenu.add(coursesTableScrollPane, BorderLayout.CENTER);
        this.contenu.add(this.ajouterCourse, BorderLayout.SOUTH);
        this.add(this.contenu, BorderLayout.CENTER);
    }

    @Override
    public void onDisplayed() {
        super.onDisplayed();

        // Effacer la tableau
        DefaultTableModel model = (DefaultTableModel) this.coursesTable.getModel();

        model.setRowCount(0);

        // Charger les courses
        controller.onRefreshCourses();
    }

    public void addCourse(String id, String nom, String date, String distance, String cycle, String livreur, String statut) {
        DefaultTableModel model = (DefaultTableModel) this.coursesTable.getModel();

        model.addRow(new Object[]{id, nom, date, distance, cycle, livreur, statut, "\uF06E", "\uF044", "\uF1F8"});
    }

    public void removeCourse(String id) {
        DefaultTableModel model = (DefaultTableModel) this.coursesTable.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(id)) {
                model.removeRow(i);
            }
        }
    }
}
