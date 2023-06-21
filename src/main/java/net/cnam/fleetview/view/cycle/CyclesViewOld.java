package net.cnam.fleetview.view.cycle;

import net.cnam.fleetview.controller.cycle.CyclesControllerOld;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.utils.ButtonColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CyclesViewOld extends View<CyclesControllerOld> {
    ///Attributs
    //Panel
    private final JPanel panelTable;
    private final JPanel panelBouton;

    //Tritre
    private final IconLabel titrepanel;
    private final JButton BoutonAjoutCycle;

    //Table
    private final JTable table;
    private final JScrollPane scrollPane;
    private final DefaultTableCellRenderer cellRenderer;
    private final DefaultTableModel model;
    private final JTableHeader header;

    //Font
    private final Font font;

    public CyclesViewOld() {

        //Panel
        this.panelTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.panelBouton = new JPanel();
        this.setLayout(new BorderLayout());

        //Titre
        this.titrepanel = new IconLabel("\uF84A", "Cycles");
        this.titrepanel.setForeground(Color.BLACK);
        this.titrepanel.setFont(new Font("Arial", Font.BOLD, 30));
        this.titrepanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 100, 1100));

        //Boutton
        this.BoutonAjoutCycle = new JButton("Ajouter un Cycle");
        this.BoutonAjoutCycle.setPreferredSize(new Dimension(600, 30));
        this.BoutonAjoutCycle.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.ajoutCycle();
            }
        });

        //Style
        this.font = new Font("Arial", Font.BOLD, 12);

        //Table
        this.model = new DefaultTableModel();
        this.table = new JTable(model);
        this.scrollPane = new JScrollPane(this.table);
        this.cellRenderer = new DefaultTableCellRenderer();
        this.header = table.getTableHeader();

        model.addColumn("Nom");
        model.addColumn("Prénom");
        model.addColumn("Numéro de série");
        model.addColumn("Date d'achat");
        model.addColumn("Date de mise en service");
        model.addColumn("Visualiser");
        model.addColumn("Modifier");
        model.addColumn("Supprimer");

        // Action voir
        table.getColumnModel().getColumn(6).setPreferredWidth(20);
        ButtonColumn ColonneBoutonVisu = new ButtonColumn(table, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.voirCycle(Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString()));
            }
        }, 3);

        // Action Modif
        table.getColumnModel().getColumn(6).setPreferredWidth(20);
        ButtonColumn ColonneBoutonModif = new ButtonColumn(table, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.modifCycle(Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString()));
            }
        }, 4);

        //Action Supp
        table.getColumnModel().getColumn(6).setPreferredWidth(20);
        ButtonColumn ColonneBoutonSup = new ButtonColumn(table, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.suppCycle(Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString()));
            }
        }, 4);

        this.table.setFont(font);
        this.cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        this.cellRenderer.setBackground(Color.lightGray);
        this.cellRenderer.setForeground(Color.black);
        this.table.setDefaultRenderer(Object.class, cellRenderer);
        this.scrollPane.setPreferredSize(new Dimension(1000, 100));
        this.header.setBackground(Color.gray);
        this.header.setForeground(Color.white);
        this.header.setFont(font);

        //Panel
        this.panelTable.add(scrollPane);
        this.panelTable.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        //Button
        this.panelBouton.setBorder(BorderFactory.createEmptyBorder(10, 0, 100, 0));
        this.panelBouton.add(BoutonAjoutCycle);

        //Add
        this.add(titrepanel, BorderLayout.NORTH);
        this.add(panelTable, BorderLayout.CENTER);
        this.add(panelBouton, BorderLayout.SOUTH);

    }

    public void Charg() {
        super.onDisplayed();

        // Effacer la tableau
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();

        model.setRowCount(0);

        // Charger les cycles
        controller.refreshCycle();
    }

    public void ajoutCycle(String id, String nom, String numS, String dateA, String chargeMax) {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();

        model.addRow(new Object[]{id, nom, numS, dateA, chargeMax, "\uF06E", "\uF044", "\uF1F8"});
    }

    public void SupprimerCycle(String id) {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(id)) {
                model.removeRow(i);
            }
        }
    }
}

