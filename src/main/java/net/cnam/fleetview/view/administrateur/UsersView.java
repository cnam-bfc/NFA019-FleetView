package net.cnam.fleetview.view.administrateur;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.controller.utilisateur.UsersController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.field.IconTextField;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.utils.ButtonColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UsersView extends View<UsersController> {
    private final IconLabel iconLabel;

    private final JPanel contenu;
    // Barre de recherche
    private final IconTextField barreDeRecherche;
    // Tableau
    private final JTable tableau;
    public UsersView(){
        super();



        // Layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Bordures
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.iconLabel = new IconLabel("\uF013", "Visualisation des utilisateurs");
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
        this.barreDeRecherche.getTextField().setToolTipText("Rechercher un utilisateur");

        // Tableau
        DefaultTableModel model = new DefaultTableModel();
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        JScrollPane tableauScrollPane = new JScrollPane(tableau);

        model.addColumn("ID Utilisateur");
        model.addColumn("Identifiant");
        model.addColumn("Nom");
        model.addColumn("Prénom");
        model.addColumn("Rôle");
        model.addColumn("Modifier");

        tableau.setModel(model);

        TableColumn modifierColumn = tableau.getColumnModel().getColumn(5);
        modifierColumn.setMinWidth(60);
        modifierColumn.setMaxWidth(60);
        ButtonColumn modifierButtonColumn = new ButtonColumn(tableau, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onEditerUtilisateur(Integer.parseInt(model.getValueAt(tableau.getSelectedRow(), 0).toString()));
            }
        }, 5);



        cellRenderer.setHorizontalAlignment(JLabel.CENTER);


        tableau.setDefaultRenderer(Object.class, cellRenderer);

        // Tableau non editable
        tableau.setDefaultEditor(Object.class, null);

        // Ajout des éléments de l'interface
        this.contenu.add(this.barreDeRecherche, BorderLayout.NORTH);
        this.contenu.add(tableauScrollPane, BorderLayout.CENTER);
        mainPanel.add(this.iconLabel, BorderLayout.NORTH);
        mainPanel.add(this.contenu, BorderLayout.CENTER);

        this.add(mainPanel);
    }
    public void addUser(int id, String identifiant, String nom, String prenom, int role) {
        DefaultTableModel model = (DefaultTableModel) this.tableau.getModel();

        model.addRow(new Object[]{id, identifiant, nom, prenom, role});
    }
    public void removeAllUsers() {
        DefaultTableModel model = (DefaultTableModel) this.tableau.getModel();

        model.setRowCount(0);
    }

    @Override
    public void onDisplayed() {
        super.onDisplayed();
        controller.onRefreshUsers();
    }
}
