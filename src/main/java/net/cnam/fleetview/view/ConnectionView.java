package net.cnam.fleetview.view;

import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import java.awt.*;

public class ConnectionView extends View{
    public ConnectionView(){
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        IconLabel titre = new IconLabel("\uf007", "Connection");

        titre.setForeground(Color.BLACK);
        titre.setFont(new Font("Arial", Font.BOLD, 30));

        titre.setBorder(BorderFactory.createEmptyBorder(50, 50, 100, 1100));
        this.add(titre, BorderLayout.NORTH);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel labelID = new JLabel("Identifiant");
        JTextField textFieldID = new JTextField(10);
        textFieldID.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldID.getPreferredSize().height));

        panel1.add(labelID);
        panel1.add(textFieldID);
        panel1.add(Box.createRigidArea(new Dimension(200, 50)));

        JLabel labelPass = new JLabel("Password");
        JTextField textFieldPass = new JTextField(10);
        textFieldPass.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldPass.getPreferredSize().height));

        panel1.add(labelPass);
        panel1.add(textFieldPass);
        panel1.add(Box.createRigidArea(new Dimension(200, 50)));

        mainPanel.add(panel1, BorderLayout.CENTER);

        JPanel PanelBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton BoutonConnect = new JButton("<html>Connection</html>");

        BoutonConnect.setPreferredSize(new Dimension(80, 50));
        PanelBouton.add(BoutonConnect);

        //PanelBouton.setBorder(BorderFactory.createEmptyBorder(200, 1000, 50, 10));
        mainPanel.add(PanelBouton, BorderLayout.SOUTH);

        this.add(mainPanel, BorderLayout.CENTER);

    }
}
