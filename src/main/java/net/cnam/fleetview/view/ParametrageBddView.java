package net.cnam.fleetview.view;

import net.cnam.fleetview.controller.ParametrageBddController;
import net.cnam.fleetview.view.components.button.LabelButton;
import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;


/*
A faire :
    - Agmenter taille titre
    - Ajouter couleur au boutons
    - arrondir les bords des champs de saisie

 */

public class ParametrageBddView extends View <ParametrageBddController> {

    private final IconLabel iconLabel;
    private JTextField champIP;
    private JTextField champPort;
    private JTextField champNomBD;
    private JTextField champNomUtilisateur;
    private JTextField champMotDePasse;
    private LabelButton btnValider;
    private LabelButton btnQuitter;

    public ParametrageBddView() {
        super();
        this.setLayout(new BorderLayout());
        //*********************************************
        //--- Création des éléments de l'interface ---
        //*********************************************

        //--- Titre ---
        this.iconLabel = new IconLabel("\uF013", "BASE DE DONNEE");
        JPanel jpEntete = new JPanel(new BorderLayout());

        JPanel jpPrincipale = new JPanel(new BorderLayout());

        JPanel jpBdInfo = new JPanel();
        jpBdInfo.setLayout(new BoxLayout(jpBdInfo, BoxLayout.PAGE_AXIS));

        //--- Contenu ---

        //JPannel
            //ligne 1
        JPanel jpLigneTop = new JPanel(new GridLayout(1,2));
        JPanel jpLigneTopIp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel jpLigneTopPort = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel jpIP = new JPanel(new BorderLayout());
        jpIP.setLayout(new BoxLayout(jpIP, BoxLayout.PAGE_AXIS));
        JPanel jpPort = new JPanel(new BorderLayout());
        jpPort.setLayout(new BoxLayout(jpPort, BoxLayout.PAGE_AXIS));
            //ligne 2
        JPanel jpLigneMid = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel jpNomDB = new JPanel(new BorderLayout());
        jpNomDB.setLayout(new BoxLayout(jpNomDB, BoxLayout.PAGE_AXIS));
            //ligne 3
        JPanel jpLigneBott = new JPanel(new GridLayout(1,2));
        JPanel jpLigneBottUtilisateur = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel jpLigneBottPasse = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel jpNomUtilisateur = new JPanel(new BorderLayout());
        jpNomUtilisateur.setLayout(new BoxLayout(jpNomUtilisateur, BoxLayout.PAGE_AXIS));
        JPanel jpMotDePasse = new JPanel(new BorderLayout());
        jpMotDePasse.setLayout(new BoxLayout(jpMotDePasse, BoxLayout.PAGE_AXIS));

        //Label
        JLabel texteIP = new JLabel("Adresse IP");
        JLabel textePort = new JLabel("Port");
        JLabel texteNomBD = new JLabel("Nom base de donées");
        JLabel texteNomUtilisateur = new JLabel("Nom d'utilisateur");
        JLabel texteMotDePasse = new JLabel("Mot de passe");

        //champ texte
            //ligne 1
        this.champIP = new JTextField();
        champIP.setPreferredSize(new Dimension(200, 24));
        this.champPort = new JTextField();
        champPort.setPreferredSize(new Dimension(200, 24));

        this.champNomBD = new JTextField();
        champNomBD.setPreferredSize(new Dimension(500, 24));

        this.champNomUtilisateur = new JTextField();
        champNomUtilisateur.setPreferredSize( new Dimension( 200, 24 ) );
        this.champMotDePasse = new JPasswordField();
        champMotDePasse.setPreferredSize( new Dimension( 200, 24 ) );

        //--- BasDePage ---
        //JPannel
        JPanel jpActionHolder = new JPanel(new BorderLayout());
        JPanel jpActionLine = new JPanel(new FlowLayout(FlowLayout.RIGHT));


        //********************************
        //--- Affectation des éléments ---
        //********************************

        //--- Entete ---
        jpEntete.add(this.iconLabel, BorderLayout.CENTER);
        jpEntete.setBorder(new EmptyBorder(20, 20, 20,20));
        jpPrincipale.add(jpEntete, BorderLayout.NORTH);

        //--- Contenu ---
            //ligne 1
        jpIP.add(texteIP);
        jpIP.add(champIP);
        jpLigneTopIp.add(jpIP);
        jpLigneTop.add(jpLigneTopIp);

        jpPort.add(textePort);
        jpPort.add(champPort);
        jpLigneTopPort.add(jpPort);
        jpLigneTop.add(jpLigneTopPort);

            //ligne 2
        jpNomDB.add(texteNomBD);
        jpNomDB.add(champNomBD);
        jpLigneMid.add(jpNomDB);

            //ligne 3
        jpNomUtilisateur.add(texteNomUtilisateur);
        jpNomUtilisateur.add(champNomUtilisateur);
        jpLigneBottUtilisateur.add(jpNomUtilisateur);
        jpLigneBott.add(jpLigneBottUtilisateur);

        jpMotDePasse.add(texteMotDePasse);
        jpMotDePasse.add(champMotDePasse);
        jpLigneBottPasse.add(jpMotDePasse);
        jpLigneBott.add(jpLigneBottPasse);

            // JPanel intermédiaire
        jpBdInfo.add(jpLigneTop);
        jpBdInfo.add(jpLigneMid);
        jpBdInfo.add(jpLigneBott);

        jpPrincipale.add(jpBdInfo, BorderLayout.CENTER);

        //--- Marges ---
        jpBdInfo.setBorder(new EmptyBorder(50,200,0,200));
        //--- Bloc Action ---

        this.btnValider = new LabelButton("Valider");

        jpActionLine.add(this.btnValider);

        jpActionHolder.add(jpActionLine, BorderLayout.CENTER);
        jpActionHolder.setBorder(new EmptyBorder(25,100,25,100));
        jpPrincipale.add(jpActionHolder, BorderLayout.SOUTH);


        this.add(jpPrincipale);

        // Ajout d'une course
        this.btnValider.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setDatabase();
            }
        });

    }

    public HashMap<String,String> getInformation(){
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("ip", this.champIP.getText());
        data.put("port", this.champPort.getText());
        data.put("nom", this.champNomBD.getText());
        data.put("id", this.champNomUtilisateur.getText());
        data.put("mdp", this.champMotDePasse.getText());

        return data;
    }

    public void fill(String ip, String port, String nom, String id, String mdp) {

        // Champs
        this.champIP.setText(ip);
        this.champPort.setText(port);
        this.champNomBD.setText(nom);
        this.champNomUtilisateur.setText(id);
        this.champMotDePasse.setText(mdp);
    }

}
