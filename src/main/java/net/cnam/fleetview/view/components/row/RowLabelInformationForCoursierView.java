package net.cnam.fleetview.view.components.row;

import net.cnam.fleetview.view.components.label.LabelInformation;

import javax.swing.*;
import java.awt.*;

public class RowLabelInformationForCoursierView extends JPanel {
    private final JLabel title;
    private final LabelInformation firstLabel;
    private final LabelInformation secondLabel;
    private final LabelInformation thirdLabel;

    public RowLabelInformationForCoursierView(JLabel title, LabelInformation firstLabel, LabelInformation secondLabel, LabelInformation thirdLabel) {
        super();

        // Initialisation des attributs
        this.title = title;
        this.firstLabel = firstLabel;
        this.secondLabel = secondLabel;
        this.thirdLabel = thirdLabel;

        // Layout
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        // Partie haute contenant le titre
        JPanel firstJPanel = new JPanel();
        FlowLayout flowLayoutTitre = new FlowLayout();
        flowLayoutTitre.setAlignment(FlowLayout.LEFT); // Centrer le label
        flowLayoutTitre.setHgap(10); // Ajouter un espace de 10px entre les éléments
        firstJPanel.setLayout(flowLayoutTitre); // Utiliser un BorderLayout pour centrer le label
        firstJPanel.add(this.title); // Ajouter le label au centre du panel

        // Partie basse contenant les informations
        JPanel secondJPanel = new JPanel();
        secondJPanel.setLayout(new GridLayout(1, 3)); // Utiliser un GridLayout avec 1 ligne et 3 colonnes
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.CENTER); // Centrer le label

        // Ajout du premier label
        JPanel firstLabelPanel = new JPanel(flowLayout); // Utiliser un BorderLayout pour centrer le label
        firstLabelPanel.add(firstLabel); // Ajouter le label au centre du panel
        secondJPanel.add(firstLabelPanel); // Ajouter le panel contenant le label à la vue principale

        // Ajout du deuxième label
        JPanel secondLabelPanel = new JPanel(flowLayout);
        secondLabelPanel.add(secondLabel);
        secondJPanel.add(secondLabelPanel);

        // Ajout du troisième label
        JPanel thirdLabelPanel = new JPanel(flowLayout);
        thirdLabelPanel.add(thirdLabel);
        secondJPanel.add(thirdLabelPanel);

        // Ajout des panels à la vue principale
        this.add(firstJPanel);
        this.add(secondJPanel);
    }

    public JLabel getTitle() {
        return title;
    }

    public LabelInformation getFirstLabel() {
        return firstLabel;
    }

    public LabelInformation getSecondLabel() {
        return secondLabel;
    }

    public LabelInformation getThirdLabel() {
        return thirdLabel;
    }
}