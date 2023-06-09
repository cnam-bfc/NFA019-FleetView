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
        table.setMaximumSize(new Dimension(10, 10));
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.gray);
        header.setForeground(Color.white);
        header.setFont(font);
        BoutonAjoutCycle.setMaximumSize(new Dimension(10, 5));


        this.add(titrepanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(BoutonAjoutCycle, BorderLayout.SOUTH);

        // Création du modèle de tableau avec des données vides
        /*DefaultTableModel model = new DefaultTableModel();

        // Ajout des colonnes
        model.addColumn("Nom");
        model.addColumn("Âge");
        model.addColumn("Pays");

        // Ajout des données
        model.addRow(new Object[]{"John Doe", 28, "USA"});
        model.addRow(new Object[]{"Jane Smith", 32, "UK"});
        model.addRow(new Object[]{"Bob Johnson", 45, "Canada"});
        model.addRow(new Object[]{"Alice Williams", 19, "Australia"});

        // Création du tableau avec le modèle personnalisé
        JTable table = new JTable(model);

        // Définition du style de la police
        Font font = new Font("Arial", Font.BOLD, 12);
        table.setFont(font);

        // Définition du style des cellules
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        cellRenderer.setBackground(Color.lightGray);
        cellRenderer.setForeground(Color.black);
        table.setDefaultRenderer(Object.class, cellRenderer);

        // Définition du style de l'en-tête du tableau
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.gray);
        header.setForeground(Color.white);
        header.setFont(font);

        // Création d'un JScrollPane pour afficher le tableau avec une barre de défilement
        JScrollPane scrollPane = new JScrollPane(table);

        this.add(scrollPane);*/
    }
}

