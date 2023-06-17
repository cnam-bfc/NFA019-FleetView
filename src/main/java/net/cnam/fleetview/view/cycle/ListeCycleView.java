package net.cnam.fleetview.view.cycle;

import net.cnam.fleetview.view.TitrePanel;
import net.cnam.fleetview.view.utils.ButtonColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class ListeCycleView extends JPanel {
   /* public ListeCycleView() {

        //JLabel LabelTitrePage = new JLabel("Cycles");
        JPanel panelTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelBouton = new JPanel();
        TitrePanel titrepanel = new TitrePanel("\uF84A", "Cycles");
        JButton BoutonAjoutCycle = new JButton("Ajouter un Cycle");
        DefaultTableModel model = new DefaultTableModel();
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
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }, 3);
        ButtonColumn ColonneBoutonModif = new ButtonColumn(table, new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }, 4);
        ButtonColumn ColonneBoutonSup = new ButtonColumn(table, new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }, 5);


        model.addRow(new Object[]{"John Doe", 28, "USA", "\uF06E", "\uF304", "\uF1F8"});
        model.addRow(new Object[]{"Jane Smith", 32, "UK", "\uF06E", "\uF304", "\uF1F8"});
        model.addRow(new Object[]{"Bob Johnson", 45, "Canada", "\uF06E", "\uF304", "\uF1F8"});
        model.addRow(new Object[]{"Alice Williams", 19, "Australia", "\uF06E", "\uF304", "\uF1F8"});

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

        titrepanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 100, 1100));
        this.add(titrepanel, BorderLayout.NORTH);
        panelTable.add(scrollPane);
        panelTable.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.add(panelTable, BorderLayout.CENTER);
        panelBouton.setBorder(BorderFactory.createEmptyBorder(10, 0, 100, 0));
        panelBouton.add(BoutonAjoutCycle);
        this.add(panelBouton, BorderLayout.SOUTH);

    }*/
}

