package net.cnam.fleetview.view.administrateur;

import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.field.IconTextField;
import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewUsers extends View {
    private final IconLabel iconLabel;

    private final JPanel contenu;
    // Barre de recherche
    private final IconTextField barreDeRecherche;
    // Tableau
    private final JTable tableau;
    public ViewUsers(){
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
        model.addColumn("Nom");
        model.addColumn("Prénom");
        model.addColumn("Rôle");

        tableau.setModel(model);

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
}
