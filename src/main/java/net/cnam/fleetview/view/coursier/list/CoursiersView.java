package net.cnam.fleetview.view.coursier.list;

import net.cnam.fleetview.controller.coursier.CoursiersController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.field.IconTextField;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.utils.ButtonColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CoursiersView extends View<CoursiersController> {
    // ÉLÉMENTS DE L'INTERFACE
    // Titre
    private final IconLabel titre;
    // Panel de contenu
    private final JPanel contenu;
    // Barre de recherche
    private final IconTextField barreDeRecherche;
    // Tableau
    private final JTable tableau;

    public CoursiersView() {
        super();

        // Layout
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        // Bordures
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.titre = new IconLabel("\uF84A", "COURSIERS");
        this.contenu = new JPanel();
        this.barreDeRecherche = new IconTextField("\uF002");
        this.tableau = new JTable();

        // Configuration des éléments de l'interface
        // Panel du contenu
        BorderLayout contenuLayout = new BorderLayout();
        this.contenu.setLayout(contenuLayout);

        this.contenu.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Barre de recherche
        // Set placeholder
        this.barreDeRecherche.getTextField().setToolTipText("Rechercher un coursier");

        // Tableau
        DefaultTableModel model = new DefaultTableModel();
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        JScrollPane tableauScrollPane = new JScrollPane(tableau);

        model.addColumn("ID Coursier");
        model.addColumn("Matricule");
        model.addColumn("Nom");
        model.addColumn("Prénom");
        model.addColumn("ID Utilisateur");
        model.addColumn("Voir");

        tableau.setModel(model);

        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Action voir
        ButtonColumn voirButtonColumn = new ButtonColumn(tableau, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onShowCoursier(model.getValueAt(tableau.getSelectedRow(), 0).toString(), model.getValueAt(tableau.getSelectedRow(), 1).toString(), model.getValueAt(tableau.getSelectedRow(), 2).toString(), model.getValueAt(tableau.getSelectedRow(), 3).toString());
            }
        }, 5);

        tableau.setDefaultRenderer(Object.class, cellRenderer);

        // Tableau non editable
        tableau.setDefaultEditor(Object.class, null);

        // Ajout des éléments de l'interface
        this.add(this.titre, BorderLayout.NORTH);
        this.contenu.add(this.barreDeRecherche, BorderLayout.NORTH);
        this.contenu.add(tableauScrollPane, BorderLayout.CENTER);
        this.add(this.contenu, BorderLayout.CENTER);
    }

    /**
     * Méthode permettant de remplir le tableau des coursiers
     *
     * @param id
     * @param matricule
     * @param nom
     * @param prenom
     * @param idUtilisateur
     */
    public void fillCoursierTable(String id, String matricule, String nom, String prenom, String idUtilisateur) {
        DefaultTableModel model = (DefaultTableModel) this.tableau.getModel();

        model.addRow(new Object[]{id, matricule, nom, prenom, idUtilisateur, "\uF06E", "\uF044", "\uF1F8"});
    }
}
