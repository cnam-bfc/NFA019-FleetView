package net.cnam.fleetview.view.cycle;

import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.controller.cycle.CycleController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.course.edit.CourseView;
import net.cnam.fleetview.view.utils.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CycleView extends View<CycleController> {
    ///Attributs
    //Panel
    private final JPanel mainPanel;
    private final JPanel panel1;
    private final JPanel PanelBouton;

    //Titre
    private final IconLabel titre;

    //Label
    private final JLabel labelId = new JLabel("Id");
    private final JLabel labelModel = new JLabel("Model");
    private final JLabel labelNS = new JLabel("Numéro de Série");
    private final JLabel labelDR = new JLabel("Dernière Révision");
    private final JLabel labelCM = new JLabel("Charge Maximale");
    private final JLabel labelDA = new JLabel("Date d'acquisition");
    private final JLabel labelMS = new JLabel("Mise en Service");
    private final JLabel labelMarque = new JLabel("Marque");
    private final JLabel labelFourn = new JLabel("Fournisseur");

    //TextField
    private final JTextField textFieldId = new JTextField(10);
    private final JTextField textFieldModel = new JTextField(10);
    private final JTextField textFieldNS = new JTextField(10);
    private final JTextField textFieldDR = new JTextField(10);
    private final JTextField textFieldCM = new JTextField(10);
    private final JTextField textFieldDA = new JTextField(10);
    private final JTextField textFieldMS = new JTextField(10);
    private final JTextField textFieldMarque = new JTextField(10);
    private final JTextField textFieldFourn = new JTextField(10);

    //Button
    private final JButton BoutonValidCycle;
    private final JButton BoutonAnnulCycle;
    public CycleView() {
        //Panel
        this.mainPanel = new JPanel();

        this.panel1 = new JPanel();
        this.panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.PanelBouton = new JPanel();

        //Titre
        this.titre = new IconLabel("\uF84A", "Cycles");
        this.titre.setForeground(Color.BLACK);
        this.titre.setFont(new Font("Arial", Font.BOLD, 30));
        this.titre.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 1100));

        //Label
        labelId.setLabelFor(textFieldId);
        labelModel.setLabelFor(textFieldModel);
        labelNS.setLabelFor(textFieldNS);
        labelDR.setLabelFor(textFieldDR);
        labelCM.setLabelFor(textFieldCM);
        labelDA.setLabelFor(textFieldDA);
        labelMS.setLabelFor(textFieldMS);
        labelMarque.setLabelFor(textFieldMarque);
        labelFourn.setLabelFor(textFieldFourn);

        panel1.add(labelId);
        panel1.add(textFieldId);
        panel1.add(labelModel);
        panel1.add(textFieldModel);
        panel1.add(labelNS);
        panel1.add(textFieldNS);
        panel1.add(labelDR);
        panel1.add(textFieldDR);
        panel1.add(labelCM);
        panel1.add(textFieldCM);
        panel1.add(labelDA);
        panel1.add(textFieldDA);
        panel1.add(labelMS);
        panel1.add(textFieldMS);
        panel1.add(labelMarque);
        panel1.add(textFieldMarque);
        panel1.add(labelFourn);
        panel1.add(textFieldFourn);

        SpringUtilities.makeCompactGrid(panel1,
                2, 9, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        //Add Titre
        this.mainPanel.add(titre, BorderLayout.NORTH);

        //Add Panel Princip
        this.mainPanel.add(panel1, BorderLayout.CENTER);

        //Add Boutton
        this.BoutonValidCycle = new JButton("<html>Valider</html>");
        this.BoutonAnnulCycle = new JButton("<html>Annuler</html>");

        this.BoutonValidCycle.setPreferredSize(new Dimension(80, 50));
        this.BoutonAnnulCycle.setPreferredSize(new Dimension(80, 50));

        this.BoutonValidCycle.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vérification des champs
                // Nom
                if (textFieldModel.getText().isEmpty()) {
                    afficherMessageErreur("Le nom de la course est obligatoire");
                    return;
                }

                boolean success = controller.saveCycle(nomField.getText(), dateField.getDate());
                if (!success) {
                    afficherMessageErreur("Impossible de sauvegarder la course");
                    return;
                }

                RootController.close(CycleView.this);
            }
        });

        this.BoutonAnnulCycle.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onConnection(identi, pass);
            }
        });

        this.PanelBouton.add(BoutonAnnulCycle);
        this.PanelBouton.add(BoutonValidCycle);

        this.PanelBouton.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        this.mainPanel.add(PanelBouton, BorderLayout.SOUTH);

        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        this.add(mainPanel);

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