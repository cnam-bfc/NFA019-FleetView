package net.cnam.fleetview.view.colis.list;

import net.cnam.fleetview.controller.ColissController;
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

public class ColissView extends View<ColissController> {
    // ÉLÉMENTS DE L'INTERFACE
    // Titre
    private final IconLabel titre;
    // Panel du contenu
    private final JPanel contenu;
    // Barre de recherche
    private final IconTextField barreDeRecherche;
    // Tableau
    private final JTable colissTable;
    // Ajout d'un colis
    private final IconLabelButton ajouterColis;

    public ColissView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Bordures
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.titre = new IconLabel("\uF466", "Colis");
        this.contenu = new JPanel();
        this.barreDeRecherche = new IconTextField("\uF002");
        this.colissTable = new JTable();
        this.ajouterColis = new IconLabelButton("\uF067", "Ajouter un colis");


        // Configuration des éléments de l'interface
        // Panel du contenu
        BorderLayout contenuLayout = new BorderLayout();
        this.contenu.setLayout(contenuLayout);

        // Barre de recherche
        this.barreDeRecherche.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.barreDeRecherche.getTextField().setPlaceholder("Rechercher un colis");

        // Tableau
        DefaultTableModel colissTableModel = new DefaultTableModel();
        DefaultTableCellRenderer colissTableCellRenderer = new DefaultTableCellRenderer();
        TableRowSorter<DefaultTableModel> colissTableRowSorter = new TableRowSorter<>(colissTableModel);
        JScrollPane colissTableScrollPane = new JScrollPane(colissTable);

        colissTableModel.addColumn("ID");
        colissTableModel.addColumn("Numéro");
        colissTableModel.addColumn("Poids");
        colissTableModel.addColumn("Adresse destination");
        colissTableModel.addColumn("Destinataire");
        colissTableModel.addColumn("Voir");
        colissTableModel.addColumn("Modifier");
        colissTableModel.addColumn("Supprimer");

        colissTable.setModel(colissTableModel);

        colissTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Action voir
        colissTable.getColumnModel().getColumn(5).setPreferredWidth(30);
        ButtonColumn voirButtonColumn = new ButtonColumn(colissTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onVoirColis(Integer.parseInt(colissTableModel.getValueAt(colissTable.getSelectedRow(), 0).toString()));
            }
        }, 5);

        // Action modifier
        colissTable.getColumnModel().getColumn(6).setPreferredWidth(30);
        ButtonColumn modifierButtonColumn = new ButtonColumn(colissTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onEditerColis(Integer.parseInt(colissTableModel.getValueAt(colissTable.getSelectedRow(), 0).toString()));
            }
        }, 6);

        // Action supprimer
        colissTable.getColumnModel().getColumn(7).setPreferredWidth(30);
        ButtonColumn supprimerButtonColumn = new ButtonColumn(colissTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSupprimerColis(Integer.parseInt(colissTableModel.getValueAt(colissTable.getSelectedRow(), 0).toString()));
            }
        }, 7);

        colissTable.setDefaultRenderer(Object.class, colissTableCellRenderer);
        colissTable.setRowHeight(30);

        // Tableau non editable
        colissTable.setDefaultEditor(Object.class, null);

        // Tri du tableau
        this.barreDeRecherche.getTextField().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                colissTableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + barreDeRecherche.getTextField().getText()));
            }
        });
        colissTable.setRowSorter(colissTableRowSorter);

        // Désactiver le dragging des colonnes
        colissTable.getTableHeader().setReorderingAllowed(false);

        // Ajout d'un colis
        JPanel ajouterColisPanel = new JPanel();
        this.ajouterColis.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onAjouterColis();
            }
        });
        ajouterColisPanel.add(this.ajouterColis);


        // Ajout des éléments de l'interface
        this.add(this.titre, BorderLayout.NORTH);
        this.contenu.add(this.barreDeRecherche, BorderLayout.NORTH);
        this.contenu.add(colissTableScrollPane, BorderLayout.CENTER);
        this.contenu.add(ajouterColisPanel, BorderLayout.SOUTH);
        this.add(this.contenu, BorderLayout.CENTER);
    }

    @Override
    public void onDisplayed() {
        super.onDisplayed();

        // Recharger les colis
        controller.onRefreshColiss();
    }

    public void addColis(String id, String nom, String distance, String adresseDestinataire, String destinataire) {
        DefaultTableModel model = (DefaultTableModel) this.colissTable.getModel();

        model.addRow(new Object[]{id, nom, distance, adresseDestinataire, destinataire, "\uF06E", "\uF044", "\uF1F8"});
    }

    public void removeColis(String id) {
        DefaultTableModel model = (DefaultTableModel) this.colissTable.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(id)) {
                model.removeRow(i);
            }
        }
    }

    public void removeAllColis() {
        DefaultTableModel model = (DefaultTableModel) this.colissTable.getModel();

        model.setRowCount(0);
    }
}
