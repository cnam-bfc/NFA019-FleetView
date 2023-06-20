package net.cnam.fleetview.view.cycle;

import net.cnam.fleetview.controller.ListeCycleController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.utils.ButtonColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class ListeCycleView extends View<ListeCycleController> {
   public ListeCycleView() {

        //JLabel LabelTitrePage = new JLabel("Cycles");
        JPanel panelTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelBouton = new JPanel();
        IconLabel titrepanel = new IconLabel("\uF84A", "Cycles");
        JButton BoutonAjoutCycle = new JButton("Ajouter un Cycle");
        JTable table = new JTable(model);
        Font font = new Font("Arial", Font.BOLD, 12);
        JScrollPane scrollPane = new JScrollPane(table);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();

        this.setLayout(new BorderLayout());

        titrepanel.setForeground(Color.BLACK);
        titrepanel.setFont(new Font("Arial", Font.BOLD, 30));

        model.addColumn("Nom");
        model.addColumn("Prénom");
        model.addColumn("Autres infos...");
        model.addColumn("Visualiser");
        model.addColumn("Modifier");
        model.addColumn("Supprimer");

        ButtonColumn ColonneBoutonVisu = new ButtonColumn(table, new Action() {

        }, 3);
        ButtonColumn ColonneBoutonModif = new ButtonColumn(table, new Action() {

        }, 4);
        ButtonColumn ColonneBoutonSup = new ButtonColumn(table, new Action() {

        }, 5);

        table.setFont(font);
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        cellRenderer.setBackground(Color.lightGray);
        cellRenderer.setForeground(Color.black);
        table.setDefaultRenderer(Object.class, cellRenderer);
        scrollPane.setPreferredSize(new Dimension(1000, 100));
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.gray);
        header.setForeground(Color.white);
        header.setFont(font);
        BoutonAjoutCycle.setPreferredSize(new Dimension(600, 30));

        titrepanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 100, 1100));
        this.add(titrepanel, BorderLayout.NORTH);
        panelTable.add(scrollPane);
        panelTable.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.add(panelTable, BorderLayout.CENTER);
        panelBouton.setBorder(BorderFactory.createEmptyBorder(10, 0, 100, 0));
        panelBouton.add(BoutonAjoutCycle);
        this.add(panelBouton, BorderLayout.SOUTH);

    }
    public void addCycle(String id, String nom, String NR, String DateMS) {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();

        model.addRow(new Object[]{id, nom, NR, DateMS, "\uF06E", "\uF044", "\uF1F8"});
    }
}

