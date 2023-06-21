package net.cnam.fleetview.view.connexion;

import net.cnam.fleetview.App;
import net.cnam.fleetview.controller.connexion.ConnectionController;
import net.cnam.fleetview.model.TypeHistorique;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.utils.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.text.Normalizer;

public class ConnectionView extends View<ConnectionController> {

/*
    public ConnectionView(){
        ///Création des Panel
        // Création du Panel
        this.mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.panel1 = new JPanel();
        this.PanelBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Création des éléments de l'interface
        this.BoutonConnect = new JButton("<html>Connection</html>");
        this.titre = new IconLabel("\uf007", "Connection");
        this.titre.setForeground(Color.BLACK);
        this.titre.setFont(new Font("Arial", Font.BOLD, 30));
        this.titre.setBorder(BorderFactory.createEmptyBorder(50, 50, 100, 1100));
        this.add(titre, BorderLayout.NORTH);

        this.panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        this.panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.labelID = new JLabel("Identifiant");
        this.textFieldID = new JTextField(10);
        this.textFieldID.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldID.getPreferredSize().height));

        this.panel1.add(labelID);
        this.panel1.add(textFieldID);
        this.panel1.add(Box.createRigidArea(new Dimension(200, 50)));

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
    }*/
    ///Attibuts
    //Panel
    private final JPanel MainPanel;
    private final JPanel TitrePanel;
    private final JPanel LogoPanel;
    private final JPanel FormPanel;
    private final JPanel BouttonPanel;

    //Titre
    private final IconLabel titre;

    //Images
    private final BufferedImage image;

    //Boutton
    private final JButton BoutonValidConnect;
    private final JButton BoutonAnnulConnect;

    //Label
    private final JLabel logo;


    public ConnectionView(){

        //Panel
        this.MainPanel = new JPanel();
        this.MainPanel.setLayout(new BoxLayout(MainPanel,BoxLayout.Y_AXIS));
        this.TitrePanel = new JPanel();
        this.LogoPanel = new JPanel();
        this.FormPanel = new JPanel(new SpringLayout());
        this.FormPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        this.BouttonPanel = new JPanel();

        //Titre
        this.titre = new IconLabel("\uf007", "Connexion");
        this.titre.setForeground(Color.BLACK);
        this.titre.setFont(new Font("Arial", Font.BOLD, 30));
        this.titre.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        this.TitrePanel.add(titre);
        //this.add(TitrePanel, BorderLayout.NORTH);

        //Image
        this.image = App.LOGO_NORMAL;
        this.logo = new JLabel(new ImageIcon(image));
        this.LogoPanel.add(logo);
        this.LogoPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        //this.add(LogoPanel, BorderLayout.SOUTH);

        //Label
        String[] label = {"Username ", "Password "};
        int numPairs = label.length;
        for (int i = 0; i < numPairs; i++){
            JLabel l = new JLabel(label[i], JLabel.TRAILING);
            FormPanel.add(l);
            JTextField textField = new JTextField(20);
            l.setLabelFor(textField);
            FormPanel.add(textField);
        }
        SpringUtilities.makeCompactGrid(FormPanel,
                numPairs, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        //Bouton
        this.BoutonValidConnect = new JButton("<html>Connexion</html>");
        this.BoutonAnnulConnect = new JButton("<html>Annuler</html>");
        this.BoutonValidConnect.setPreferredSize(new Dimension(80, 50));
        this.BoutonAnnulConnect.setPreferredSize(new Dimension(80, 50));
        this.BouttonPanel.add(BoutonValidConnect, BoutonAnnulConnect);

        this.MainPanel.add(TitrePanel);
        this.MainPanel.add(LogoPanel);
        this.MainPanel.add(FormPanel);
        this.MainPanel.add(BouttonPanel);

        this.add(MainPanel);

    }
}
