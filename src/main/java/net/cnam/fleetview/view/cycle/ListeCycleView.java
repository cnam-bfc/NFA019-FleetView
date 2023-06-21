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
     private final IconLabel titrepanel;
     private final JButton BoutonAjoutCycle;
     private final JTable table;
     private final JScrollPane scrollPane;
   public ListeCycleView() {

        //JLabel LabelTitrePage = new JLabel("Cycles");
        JPanel panelTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelBouton = new JPanel();
        this.titrepanel = new IconLabel("\uF84A", "Cycles");
        this.BoutonAjoutCycle = new JButton("Ajouter un Cycle");
        Font font = new Font("Arial", Font.BOLD, 12);
        this.scrollPane = new JScrollPane(table);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        DefaultTableModel model = new DefaultTableModel();
        this.table = new JTable(model);

        this.setLayout(new BorderLayout());

        titrepanel.setForeground(Color.BLACK);
        titrepanel.setFont(new Font("Arial", Font.BOLD, 30));

        model.addColumn("Nom");
        model.addColumn("Prénom");
        model.addColumn("Autres infos...");
        model.addColumn("Visualiser");
        model.addColumn("Modifier");
        model.addColumn("Supprimer");

        // Action voir
        table.getColumnModel().getColumn(3).setPreferredWidth(20);
        ButtonColumn ColonneBoutonVisu = new ButtonColumn(table, new Action() {
          controller.voirCycle(Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString()));
        }, 3);
        // Action Modif
        table.getColumnModel().getColumn(4).setPreferredWidth(20);
        ButtonColumn ColonneBoutonModif = new ButtonColumn(table, new Action() {
          controller.modifCycle(Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString()));
        }, 4);
        //Action Supp
        table.getColumnModel().getColumn(5).setPreferredWidth(20);
        ButtonColumn ColonneBoutonSup = new ButtonColumn(table, new Action() {
          controller.suppCycle(Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString()));
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
        this.BoutonAjoutCycle.setPreferredSize(new Dimension(600, 30));
        this.BoutonAjoutCycle.addActionListener(new AbstractAction() {
             @Override
             public void actionPerformed(ActionEvent e) {
                  controller.ajoutCycle();
             }
        });

        titrepanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 100, 1100));
        this.add(titrepanel, BorderLayout.NORTH);
        panelTable.add(scrollPane);
        panelTable.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.add(panelTable, BorderLayout.CENTER);
        panelBouton.setBorder(BorderFactory.createEmptyBorder(10, 0, 100, 0));
        panelBouton.add(BoutonAjoutCycle);
        this.add(panelBouton, BorderLayout.SOUTH);

    }
     @Override
     public void Charg() {
          super.onDisplayed();

          // Effacer la tableau
          DefaultTableModel model = (DefaultTableModel) this.table.getModel();

          model.setRowCount(0);

          // Charger les courses
          controller.refreshCycle();
     }
    public void ajoutCycle(String id, String nom, String NR, String DateMS) {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();

        model.addRow(new Object[]{id, nom, NR, DateMS, "\uF06E", "\uF044", "\uF1F8"});
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

