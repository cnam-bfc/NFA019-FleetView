package net.cnam.fleetview.view.cycle;

import net.cnam.fleetview.controller.cycle.CycleController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import java.awt.*;

public class CycleView extends View<CycleController> {
    ///Attributs
    //Panel
    private final JPanel mainPanel;
    private final JPanel panel1;
    private final JPanel panel2;
    private final JPanel PanelBouton;

    //Titre
    private final IconLabel titre;

    //Label
    private final JLabel labelId;
    private final JLabel labelModel;
    private final JLabel labelNS;
    private final JLabel labelDR;
    private final JLabel labelCM;
    private final JLabel labelDA;
    private final JLabel labelMS;
    private final JLabel labelMarque;
    private final JLabel labelFourn;

    //TextField
    private final JTextField textFieldId;
    private final JTextField textFieldModel;
    private final JTextField textFieldNS;
    private final JTextField textFieldDR;
    private final JTextField textFieldCM;
    private final JTextField textFieldDA;
    private final JTextField textFieldMS;
    private final JTextField textFieldMarque;
    private final JTextField textFieldFourn;

    //Button
    private final JButton BoutonValidCycle;
    private final JButton BoutonAnnulCycle;
    public CycleView() {
        //Panel
        this.mainPanel = new JPanel(new BorderLayout());

        this.panel1 = new JPanel();
        this.panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        this.panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 100));

        this.panel2 = new JPanel();
        this.panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        this.panel2.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 5));

        this.PanelBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Titre
        this.titre = new IconLabel("\uF84A", "Cycles");
        this.titre.setForeground(Color.BLACK);
        this.titre.setFont(new Font("Arial", Font.BOLD, 30));
        this.titre.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 1100));

        //Label

        this.labelId = new JLabel("Id");
        this.labelModel = new JLabel("Model");
        this.labelNS = new JLabel("Numéro de Série");
        this.labelDR = new JLabel("Dernière Révision");
        this.labelCM = new JLabel("Charge Maximale");
        this.labelDA = new JLabel("Date d'acquisition");
        this.labelMS = new JLabel("Mise en Service");
        this.labelMarque = new JLabel("Marque");
        this.labelFourn = new JLabel("Fournisseur");

        //TextField
        this.textFieldId = new JTextField(10);
        this.textFieldModel = new JTextField(10);
        this.textFieldNS = new JTextField(10);
        this.textFieldDR = new JTextField(10);
        this.textFieldCM = new JTextField(10);
        this.textFieldDA = new JTextField(10);
        this.textFieldMS = new JTextField(10);
        this.textFieldMarque = new JTextField(10);
        this.textFieldFourn = new JTextField(10);

        this.textFieldModel.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldModel.getPreferredSize().height));
        this.textFieldNS.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldNS.getPreferredSize().height));
        this.textFieldDR.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldDR.getPreferredSize().height));
        this.textFieldCM.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldCM.getPreferredSize().height));
        this.textFieldDA.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldDA.getPreferredSize().height));
        this.textFieldMS.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldMS.getPreferredSize().height));
        this.textFieldMarque.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldMarque.getPreferredSize().height));
        this.textFieldFourn.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldFourn.getPreferredSize().height));

        //Add Titre
        this.add(titre, BorderLayout.NORTH);

        //Add Panel1
        panel1.add(labelId, BorderLayout.EAST);
        panel1.add(textFieldId);
        panel1.add(labelModel, BorderLayout.EAST);
        panel1.add(textFieldModel);
        panel1.add(Box.createRigidArea(new Dimension(200, 50)));
        panel1.add(labelNS, BorderLayout.EAST);
        panel1.add(textFieldNS);
        panel1.add(Box.createRigidArea(new Dimension(200, 50)));
        panel1.add(labelDR, BorderLayout.WEST);
        panel1.add(textFieldDR);
        panel1.add(Box.createRigidArea(new Dimension(200, 50)));
        panel1.add(labelCM);
        panel1.add(textFieldCM);

        //Panel2
        panel2.add(labelDA);
        panel2.add(textFieldDA);
        panel2.add(Box.createRigidArea(new Dimension(200, 50)));
        panel2.add(labelMS);
        panel2.add(textFieldMS);
        panel2.add(Box.createRigidArea(new Dimension(200, 50)));
        panel2.add(labelMarque);
        panel2.add(textFieldMarque);
        panel2.add(Box.createRigidArea(new Dimension(200, 50)));
        panel2.add(labelFourn);
        panel2.add(textFieldFourn);

        //Add Panel Princip
        mainPanel.add(panel1, BorderLayout.WEST);
        mainPanel.add(panel2, BorderLayout.EAST);


        this.add(mainPanel, BorderLayout.CENTER);

        //Add Boutton
        this.BoutonValidCycle = new JButton("<html>Valider</html>");
        this.BoutonAnnulCycle = new JButton("<html>Annuler</html>");

        this.BoutonValidCycle.setPreferredSize(new Dimension(80, 50));
        this.BoutonAnnulCycle.setPreferredSize(new Dimension(80, 50));

        this.PanelBouton.add(BoutonAnnulCycle);
        this.PanelBouton.add(BoutonValidCycle);

        this.PanelBouton.setBorder(BorderFactory.createEmptyBorder(200, 1000, 50, 10));
        this.add(PanelBouton, BorderLayout.SOUTH);

    }

    public void editField(boolean edit){
        // Champs
        this.textFieldId.setEditable(edit);
        this.textFieldModel.setEditable(edit);
        this.textFieldNS.setEditable(edit);
        this.textFieldCM.setEditable(edit);
        this.textFieldDA.setEditable(edit);
        this.textFieldMS.setEditable(edit);
        this.textFieldMarque.setEditable(edit);
        this.textFieldFourn.setEditable(edit);
    }


    public void fill(String idCycle,  String nom, String numSerie, String chargeMax, String dateAchat, String dateMiseService, String marque, String fournisseur){
        // Titre
        this.titre.getTexteLabel().setText("Cycle n°" + idCycle + " - " + nom);

        // Champs
        this.textFieldId.setText(idCycle);
        this.textFieldModel.setText(nom);
        this.textFieldNS.setText(numSerie);
        this.textFieldCM.setText(chargeMax);
        this.textFieldDA.setText(dateAchat);
        this.textFieldMS.setText(dateMiseService);
        this.textFieldMarque.setText(marque);
        this.textFieldFourn.setText(fournisseur);
    }
}