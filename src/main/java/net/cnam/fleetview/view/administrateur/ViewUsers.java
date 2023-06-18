package net.cnam.fleetview.view.administrateur;

import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewUsers extends View {
    private final IconLabel iconLabel;
    public ViewUsers(){

        this.iconLabel = new IconLabel("\uF013", "Visualisation des utilisateurs");
        JPanel mainPanel = new JPanel(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

    }
}
