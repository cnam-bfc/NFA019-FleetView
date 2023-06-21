package net.cnam.fleetview.view.connexion;

import net.cnam.fleetview.controller.connexion.ConnectionController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConnectionView extends View<ConnectionController> {
    /// ÉLÉMENTS DE L'INTERFACE
    //Boutton
    private final JButton BoutonConnect;
    private final IconLabel titre;
    private final JLabel labelID;
    private final JLabel labelPass;
    private final JTextField textFieldID;
    private final JTextField textFieldPass;

    public ConnectionView(){
        ///Création des Panel
        // Création du Panel principal
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Création du PanelBoutton
        JPanel PanelBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Création des éléments de l'interface
        this.BoutonConnect = new JButton("<html>Connection</html>");
        this.titre = new IconLabel("\uf007", "Connection");

        this.titre.setForeground(Color.BLACK);
        this.titre.setFont(new Font("Arial", Font.BOLD, 30));

        this.titre.setBorder(BorderFactory.createEmptyBorder(50, 50, 100, 1100));
        this.add(titre, BorderLayout.NORTH);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.labelID = new JLabel("Identifiant");
        this.textFieldID = new JTextField(10);
        this.textFieldID.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldID.getPreferredSize().height));

        panel1.add(labelID);
        panel1.add(textFieldID);
        panel1.add(Box.createRigidArea(new Dimension(200, 50)));

        this.labelPass = new JLabel("Password");
        this.textFieldPass = new JTextField(10);
        this.textFieldPass.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldPass.getPreferredSize().height));

        panel1.add(labelPass);
        panel1.add(textFieldPass);
        panel1.add(Box.createRigidArea(new Dimension(200, 50)));

        mainPanel.add(panel1, BorderLayout.CENTER);

        this.BoutonConnect.setPreferredSize(new Dimension(80, 50));

        String identi = this.getIdent();
        String pass = this.getPass();

        this.BoutonConnect.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onConnection(identi, pass);
            }
        });

        PanelBouton.add(BoutonConnect);

        PanelBouton.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        mainPanel.add(PanelBouton, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);

    }

    public String getIdent(){
        return this.textFieldID.getText();
    }

    public  String getPass(){
        return this.textFieldPass.getText();
    }
}
