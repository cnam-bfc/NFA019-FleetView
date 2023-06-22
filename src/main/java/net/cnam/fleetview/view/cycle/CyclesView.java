package net.cnam.fleetview.view.cycle;

import net.cnam.fleetview.controller.cycle.CyclesController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.button.IconLabelButton;
import net.cnam.fleetview.view.components.field.IconTextField;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.utils.ButtonColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CyclesView extends View<CyclesController> {
    // ÉLÉMENTS DE L'INTERFACE
    // Titre
    private final IconLabel titre;
    // Panel du contenu
    private final JPanel contenu;
    // Barre de recherche
    private final IconTextField barreDeRecherche;
    // Tableau
    private final JTable cyclesTable;
    // Ajout d'un cycle
    private final IconLabelButton ajouterCycle;

    public CyclesView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Bordures
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.titre = new IconLabel("\uF0D1", "Cycles");
        this.contenu = new JPanel();
        this.barreDeRecherche = new IconTextField("\uF002");
        this.cyclesTable = new JTable();
        this.ajouterCycle = new IconLabelButton("\uF067", "Ajouter un cycle");


        // Configuration des éléments de l'interface
        // Panel du contenu
        BorderLayout contenuLayout = new BorderLayout();
        this.contenu.setLayout(contenuLayout);

        // Barre de recherche
        this.barreDeRecherche.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.barreDeRecherche.getTextField().setPlaceholder("Rechercher un cycle");

        // Tableau
        DefaultTableModel cyclesTableModel = new DefaultTableModel();
        DefaultTableCellRenderer cyclesTableCellRenderer = new DefaultTableCellRenderer();
        TableRowSorter<DefaultTableModel> cyclesTableRowSorter = new TableRowSorter<>(cyclesTableModel);
        JScrollPane cyclesTableScrollPane = new JScrollPane(cyclesTable);

        cyclesTableModel.addColumn("ID"); // Id du cycle
        cyclesTableModel.addColumn("Identifiant"); // Identifiant du cycle
        cyclesTableModel.addColumn("Charge maximal"); // Numéro de série du cycle
        cyclesTableModel.addColumn("Date d'achat");
        cyclesTableModel.addColumn("Revision");
        cyclesTableModel.addColumn("Etat");
        cyclesTableModel.addColumn("Voir");
        cyclesTableModel.addColumn("Modifier");
        cyclesTableModel.addColumn("Supprimer");
        cyclesTableModel.addColumn("Choisir");

        cyclesTable.setModel(cyclesTableModel);

        cyclesTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Id
        TableColumn idColumn = cyclesTable.getColumnModel().getColumn(0);
        idColumn.setMinWidth(60);
        idColumn.setMaxWidth(100);

        // Action voir
        TableColumn voirColumn = cyclesTable.getColumnModel().getColumn(6);
        voirColumn.setMinWidth(60);
        voirColumn.setMaxWidth(60);
        ButtonColumn voirButtonColumn = new ButtonColumn(cyclesTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onVoirCycle(Integer.parseInt(cyclesTableModel.getValueAt(cyclesTable.getSelectedRow(), 0).toString()));
            }
        }, 6);

        // Action modifier
        TableColumn modifierColumn = cyclesTable.getColumnModel().getColumn(7);
        modifierColumn.setMinWidth(60);
        modifierColumn.setMaxWidth(60);
        ButtonColumn modifierButtonColumn = new ButtonColumn(cyclesTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onEditerCycle(Integer.parseInt(cyclesTableModel.getValueAt(cyclesTable.getSelectedRow(), 0).toString()));
            }
        }, 7);

        // Action supprimer
        TableColumn supprimerColumn = cyclesTable.getColumnModel().getColumn(8);
        supprimerColumn.setMinWidth(60);
        supprimerColumn.setMaxWidth(60);
        ButtonColumn supprimerButtonColumn = new ButtonColumn(cyclesTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSupprimerCycle(Integer.parseInt(cyclesTableModel.getValueAt(cyclesTable.getSelectedRow(), 0).toString()));
            }
        }, 8);

        // Action choisir
        TableColumn choisirColumn = cyclesTable.getColumnModel().getColumn(9);
        choisirColumn.setMinWidth(0);
        choisirColumn.setMaxWidth(0);

        cyclesTable.setDefaultRenderer(Object.class, cyclesTableCellRenderer);
        cyclesTable.setRowHeight(30);

        // Tableau non editable
        cyclesTable.setDefaultEditor(Object.class, null);

        // Tri du tableau
        this.barreDeRecherche.getTextField().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cyclesTableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + barreDeRecherche.getTextField().getText()));
            }
        });
        cyclesTable.setRowSorter(cyclesTableRowSorter);

        // Désactiver le dragging des colonnes
        cyclesTable.getTableHeader().setReorderingAllowed(false);

        // Ajout d'un cycle
        JPanel ajouterCyclePanel = new JPanel();
        this.ajouterCycle.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onAjouterCycle();
            }
        });
        ajouterCyclePanel.add(this.ajouterCycle);


        // Ajout des éléments de l'interface
        this.add(this.titre, BorderLayout.NORTH);
        this.contenu.add(this.barreDeRecherche, BorderLayout.NORTH);
        this.contenu.add(cyclesTableScrollPane, BorderLayout.CENTER);
        this.contenu.add(ajouterCyclePanel, BorderLayout.SOUTH);
        this.add(this.contenu, BorderLayout.CENTER);
    }

    @Override
    public void onDisplayed() {
        super.onDisplayed();

        // Recharger les cycles
        this.controller.onRefreshCycles();
    }

    /**
     * Ajoute un cycle dans le tableau
     *
     * @param id          Identifiant du cycle
     * @param identifiant Identifiant du cycle
     * @param chargeMax   Charge maximal du cycle
     * @param dateAchat   Date d'achat du cycle
     * @param revision    Révision du cycle
     * @param etat        Etat du cycle
     */
    public void addCycle(String id, String identifiant, String chargeMax, String dateAchat, String revision, String etat) {
        DefaultTableModel model = (DefaultTableModel) this.cyclesTable.getModel();

        model.addRow(new Object[]{id, identifiant, chargeMax, dateAchat, revision, etat, "\uF06E", "\uF044", "\uF1F8", "\uF00C"});
    }

    /**
     * Supprime un cycle du tableau
     *
     * @param id Identifiant du cycle
     */
    public void removeCycle(String id) {
        DefaultTableModel model = (DefaultTableModel) this.cyclesTable.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(id)) {
                model.removeRow(i);
            }
        }
    }

    /**
     * Supprime tous les cycles du tableau
     */
    public void removeAllCycles() {
        DefaultTableModel model = (DefaultTableModel) this.cyclesTable.getModel();

        model.setRowCount(0);
    }

    /**
     * Ajoute une colonne "Choisir" au tableau
     */
    public void addChooseColumn() {
        DefaultTableModel model = (DefaultTableModel) this.cyclesTable.getModel();

        TableColumn choisirColumn = cyclesTable.getColumnModel().getColumn(9);
        choisirColumn.setMinWidth(60);
        choisirColumn.setMaxWidth(60);
        choisirColumn.setPreferredWidth(60);
        ButtonColumn choisirButtonColumn = new ButtonColumn(cyclesTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onChoisirCycle(Integer.parseInt(model.getValueAt(cyclesTable.getSelectedRow(), 0).toString()));
            }
        }, 9);
    }

    /**
     * Cache les colonnes de modification et de suppression
     */
    public void hideEditAndDeleteColumns() {
        ButtonColumn modifierButtonColumn = (ButtonColumn) this.cyclesTable.getColumnModel().getColumn(7).getCellEditor();
        modifierButtonColumn.setButtonEnabled(false);

        ButtonColumn supprimerButtonColumn = (ButtonColumn) this.cyclesTable.getColumnModel().getColumn(8).getCellEditor();
        supprimerButtonColumn.setButtonEnabled(false);
    }
}
