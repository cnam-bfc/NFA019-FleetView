package net.cnam.fleetview.view.course.list;

import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.button.IconLabelButton;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.utils.ButtonColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CoursesView extends View {
    // ÉLÉMENTS DE L'INTERFACE
    // Titre
    private final IconLabel titre;
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
        this.tableau = new JTable();
        this.ajouterCourse = new IconLabelButton("\uF067", "Ajouter une course");


        // Configuration des éléments de l'interface
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

        model.addRow(new Object[]{"1", "01/01/2021", "10 km", "1", "Jean", "En cours", "\uF06E", "\uF044", "\uF1F8"});
        model.addRow(new Object[]{"2", "02/01/2021", "20 km", "2", "Jean", "En cours", "\uF06E", "\uF044", "\uF1F8"});
        model.addRow(new Object[]{"3", "03/01/2021", "30 km", "3", "Jean", "En cours", "\uF06E", "\uF044", "\uF1F8"});

        tableau.setModel(model);

        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

        tableau.setDefaultRenderer(Object.class, cellRenderer);

        // Action voir
        ButtonColumn voirButtonColumn = new ButtonColumn(tableau, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(CoursesView.this, "Visualiser");
            }
        }, 6);

        // Action modifier
        ButtonColumn modifierButtonColumn = new ButtonColumn(tableau, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(CoursesView.this, "Modifier");
            }
        }, 7);

        // Action supprimer
        ButtonColumn supprimerButtonColumn = new ButtonColumn(tableau, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(CoursesView.this, "Supprimer");
            }
        }, 8);

        // Ajout des éléments de l'interface
        this.add(this.titre, BorderLayout.NORTH);
        this.add(tableauScrollPane, BorderLayout.CENTER);
        this.add(this.ajouterCourse, BorderLayout.SOUTH);
    }
}
