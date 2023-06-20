package net.cnam.fleetview.view.cycle;

import net.cnam.fleetview.controller.CreationCycleController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import java.awt.*;

public class CreationCycleView extends View<CreationCycleController> {

    public CreationCycleView() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        IconLabel titre = new IconLabel("\uF84A", "Cycles");

        titre.setForeground(Color.BLACK);
        titre.setFont(new Font("Arial", Font.BOLD, 30));

        titre.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 1100));
        this.add(titre, BorderLayout.NORTH);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 100));

        JLabel labelModel = new JLabel("Model");
        JTextField textFieldModel = new JTextField(10);
        textFieldModel.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldModel.getPreferredSize().height));

        JLabel labelNS = new JLabel("Numéro de Série");
        JTextField textFieldNS = new JTextField(10);
        textFieldNS.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldNS.getPreferredSize().height));

        JLabel labelDR = new JLabel("Dernière Révision");
        JTextField textFieldDR = new JTextField(10);
        textFieldDR.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldDR.getPreferredSize().height));

        JLabel labelCM = new JLabel("Charge Maximale");
        JTextField textFieldCM = new JTextField(10);
        textFieldCM.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldCM.getPreferredSize().height));

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

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 5));

        JLabel labelDA = new JLabel("Date d'acquisition");
        JTextField textFieldDA = new JTextField(10);
        textFieldDA.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldDA.getPreferredSize().height));

        JLabel labelMS = new JLabel("Mise en Service");
        JTextField textFieldMS = new JTextField(10);
        textFieldMS.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldMS.getPreferredSize().height));

        JLabel labelMarque = new JLabel("Marque");
        JTextField textFieldMarque = new JTextField(10);
        textFieldMarque.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldMarque.getPreferredSize().height));

        JLabel labelFourn = new JLabel("Fournisseur");
        JTextField textFieldFourn = new JTextField(10);
        textFieldFourn.setMaximumSize(new Dimension(Short.MAX_VALUE, textFieldFourn.getPreferredSize().height));

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

        mainPanel.add(panel1, BorderLayout.WEST);
        mainPanel.add(panel2, BorderLayout.EAST);


        add(mainPanel, BorderLayout.CENTER);


        JPanel PanelBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton BoutonValidCycle = new JButton("<html>Valider</html>");
        JButton BoutonAnnulCycle = new JButton("<html>Annuler</html>");

        BoutonValidCycle.setPreferredSize(new Dimension(80, 50));
        BoutonAnnulCycle.setPreferredSize(new Dimension(80, 50));

        PanelBouton.add(BoutonAnnulCycle);
        PanelBouton.add(BoutonValidCycle);

        PanelBouton.setBorder(BorderFactory.createEmptyBorder(200, 1000, 50, 10));
        this.add(PanelBouton, BorderLayout.SOUTH);

    }
}