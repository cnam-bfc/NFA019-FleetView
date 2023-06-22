package net.cnam.fleetview.view.administrateur;
import net.cnam.fleetview.controller.utilisateur.CreateModifyUserController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;
import javax.swing.*;
import java.awt.*;

public class CreateModifyUserView extends View<CreateModifyUserController> {
    private final IconLabel iconLabel;
    public CreateModifyUserView(){


        this.iconLabel = new IconLabel("\uF013", "Création / Modification d'un Utilisateur");
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(100,0));
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(100,0));


        JPanel infoPanel = new JPanel(new GridLayout(2,2,40,20));


        JPanel nom = new JPanel(new BorderLayout());
        nom.setLayout(new BoxLayout(nom, BoxLayout.PAGE_AXIS));
        JTextField nomChamp = new JTextField();
        nomChamp.setPreferredSize(new Dimension(400,20));
        JLabel nomLabel = new JLabel("Nom");
        nom.add(nomLabel);
        nom.add(nomChamp);

        JPanel prenom = new JPanel(new BorderLayout());
        prenom.setLayout(new BoxLayout(prenom, BoxLayout.PAGE_AXIS));
        JTextField prenomChamp = new JTextField();
        prenomChamp.setPreferredSize(new Dimension(400,20));
        JLabel prenomLabel = new JLabel("Prénom");
        prenom.add(prenomLabel);
        prenom.add(prenomChamp);

        JPanel dateCreation = new JPanel(new BorderLayout());
        dateCreation.setLayout(new BoxLayout(dateCreation, BoxLayout.PAGE_AXIS));
        JTextField dateCreationChamp = new JTextField();
        dateCreationChamp.setPreferredSize(new Dimension(400,20));
        JLabel dateCreationLabel = new JLabel("Date de création");
        dateCreation.add(dateCreationLabel);
        dateCreation.add(dateCreationChamp);

        JPanel role = new JPanel(new BorderLayout());
        role.setLayout(new BoxLayout(role, BoxLayout.PAGE_AXIS));
        JTextField roleChamp = new JTextField();
        roleChamp.setPreferredSize(new Dimension(400,20));
        JLabel roleLabel = new JLabel("Rôle");
        role.add(roleLabel);
        role.add(roleChamp);


        infoPanel.add(nom);
        infoPanel.add(prenom);
        infoPanel.add(dateCreation);
        infoPanel.add(role);

        JPanel valider = new JPanel(new GridLayout(1,1,500,20));
        JButton validerButton = new JButton("Valider");
        valider.add(validerButton);
        valider.setPreferredSize(new Dimension(80,80));


        mainPanel.add(this.iconLabel, BorderLayout.NORTH);
        mainPanel.add(leftPanel,BorderLayout.LINE_START);
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel,BorderLayout.LINE_END);
        mainPanel.add(valider, BorderLayout.SOUTH);

        this.add(mainPanel);



    }
}
