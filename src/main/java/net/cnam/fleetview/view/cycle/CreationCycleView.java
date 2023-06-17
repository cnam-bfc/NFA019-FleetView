package net.cnam.fleetview.view.cycle;

import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import java.awt.*;

public class CreationCycleView extends JPanel {

    public CreationCycleView() {
        //JPanel PanelFlow1 = new JPanel(new)
        JPanel Panel0 = new JPanel(new BorderLayout());
        JPanel Panel1 = new JPanel(new GridLayout(4, 1));
        JPanel Panel2 = new JPanel(new GridLayout(4, 1));
        JPanel PanelBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        IconLabel titre = new IconLabel("\uF84A", "Cycles");

        this.setLayout(new BorderLayout());

        titre.setForeground(Color.BLACK);
        titre.setFont(new Font("Arial", Font.BOLD, 30));

        titre.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 1100));
        this.add(titre, BorderLayout.NORTH);

        JLabel LabelModel = new JLabel("Model");
        JLabel LabelNS = new JLabel("Numéro de Série");
        JLabel LabelDR = new JLabel("Dernière Révision");
        JLabel LabelCM = new JLabel("Charge Maximale");
        JLabel LabelDA = new JLabel("Date d'acquisition");
        JLabel LabelMS = new JLabel("Mise en Service");
        JLabel LabelMarque = new JLabel("Marque");
        JLabel LabelFourn = new JLabel("Fournisseur");

        JTextField textFieldModel = new JTextField(10);
        textFieldModel.setMaximumSize(new Dimension(500, 200));
        JTextField textFieldNS = new JTextField(10);
        JTextField textFieldDR = new JTextField(10);
        JTextField textFieldCM = new JTextField(10);
        JTextField textFieldDA = new JTextField(10);
        JTextField textFieldMS = new JTextField(10);
        JTextField textFieldMarque = new JTextField(10);
        JTextField textFieldFourn = new JTextField(10);

        JButton BoutonValidCycle = new JButton("<html>Valider</html>");
        JButton BoutonAnnulCycle = new JButton("<html>Annuler</html>");

        BoutonValidCycle.setPreferredSize(new Dimension(60, 30));
        BoutonAnnulCycle.setPreferredSize(new Dimension(60, 30));

        JPanel JPModel = new JPanel(new BorderLayout());
        JPModel.add(LabelModel);
        JPModel.add(textFieldModel);
        Panel1.add(JPModel);

        JPanel JPNUmS = new JPanel(new BorderLayout());
        JPNUmS.add(LabelNS);
        JPNUmS.add(textFieldNS);
        Panel1.add(JPNUmS);

        JPanel JPDerR = new JPanel(new BorderLayout());
        JPDerR.add(LabelDR);
        JPDerR.add(textFieldDR);
        Panel1.add(JPDerR);

        JPanel JPChargeM = new JPanel(new BorderLayout());
        JPChargeM.add(LabelCM);
        JPChargeM.add(textFieldCM);
        Panel1.add(JPChargeM);

        JPanel JPDateAc = new JPanel(new BorderLayout());
        JPDateAc.add(LabelDA);
        JPDateAc.add(textFieldDA);
        Panel2.add(JPDateAc);

        JPanel JPMiseS = new JPanel(new BorderLayout());
        JPMiseS.add(LabelMS);
        JPMiseS.add(textFieldMS);
        Panel2.add(JPMiseS);

        JPanel JPMarque = new JPanel(new BorderLayout());
        JPMarque.add(LabelMarque);
        JPMarque.add(textFieldMarque);
        Panel2.add(JPMarque);

        JPanel JPF = new JPanel(new BorderLayout());
        JPF.add(LabelFourn);
        JPF.add(textFieldFourn);
        Panel2.add(JPF);

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.X_AXIS));
        panelCenter.add(Panel1);
        panelCenter.add(Panel2);


        PanelBouton.add(BoutonAnnulCycle);
        PanelBouton.add(BoutonValidCycle);

        PanelBouton.setBorder(BorderFactory.createEmptyBorder(50, 1000, 50, 10));
        this.add(PanelBouton, BorderLayout.SOUTH);

        Panel0.add(panelCenter, BorderLayout.CENTER);

        this.add(Panel0, BorderLayout.CENTER);
        this.add(PanelBouton, BorderLayout.SOUTH);
    }
}