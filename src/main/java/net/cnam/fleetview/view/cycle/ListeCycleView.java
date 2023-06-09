package net.cnam.fleetview.view.cycle;

import net.cnam.fleetview.view.MenuButton;
import net.cnam.fleetview.view.TitrePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ListeCycleView extends JPanel {
    public ListeCycleView() {

        //JLabel LabelTitrePage = new JLabel("Cycles");
        JPanel panelTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelBouton =  new JPanel();
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
        model.addColumn("Âge");
        model.addColumn("Pays");
        model.addRow(new Object[]{"John Doe", 28, "USA"});
        model.addRow(new Object[]{"Jane Smith", 32, "UK"});
        model.addRow(new Object[]{"Bob Johnson", 45, "Canada"});
        model.addRow(new Object[]{"Alice Williams", 19, "Australia"});

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
        BoutonAjoutCycle.setPreferredSize(new Dimension(10, 5));


        this.add(titrepanel, BorderLayout.NORTH);
        panelTable.add(scrollPane);
        this.add(panelTable, BorderLayout.CENTER);
        panelBouton.add(BoutonAjoutCycle);
        this.add(panelBouton, BorderLayout.SOUTH);

    }
}

