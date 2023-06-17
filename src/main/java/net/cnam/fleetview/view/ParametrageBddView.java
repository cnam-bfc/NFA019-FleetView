package net.cnam.fleetview.view.paramettre;

import net.cnam.fleetview.view.TitrePanel;

import javax.swing.*;
import java.awt.*;

/*
A faire :
    - Agmenter taille titre
    - Ajouter couleur au boutons
    - arrondir les bords des champs de saisie

 */

public class ParametrageBddView extends JPanel {

    private final TitrePanel titrePanel;

    public ParametrageBddView() {
        super();
        this.setLayout(new BorderLayout());
        //*********************************************
        //--- Création des éléments de l'interface ---
        //*********************************************

        //--- Titre ---
        this.titrePanel = new TitrePanel("\uF013", "BASE DE DONNEE");
        JPanel jpEntete = new JPanel(new BorderLayout());

        JPanel jpEnteteMargeLaterale = new JPanel();
        jpEnteteMargeLaterale.setPreferredSize(new Dimension(20, 0));

        JPanel jpEnteteMargeHorizontale = new JPanel();
        jpEnteteMargeHorizontale.setPreferredSize(new Dimension(0, 20));

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

            //Marge
        JPanel jpMargeGauche = new JPanel();
        jpMargeGauche.setPreferredSize(new Dimension(200, 0));
        JPanel jpMargeDroite = new JPanel();

        //Label
        JLabel texteIP = new JLabel("Adresse IP");
        JLabel textePort = new JLabel("Port");
        JLabel texteNomBD = new JLabel("Nom base de donées");
        JLabel texteNomUtilisateur = new JLabel("Nom d'utilisateur");
        JLabel texteMotDePasse = new JLabel("Mot de passe");

        //champ texte
            //ligne 1
        JTextField champIP = new JTextField();
        champIP.setPreferredSize( new Dimension( 200, 24 ) );

        JTextField champPort = new JTextField();
        champPort.setPreferredSize( new Dimension( 200, 24 ) );
            //ligne 2
        JTextField champNomBD = new JTextField();
        champNomBD.setPreferredSize( new Dimension( 500, 24 ) );
            //ligne 3
        JTextField champNomUtilisateur = new JTextField();
        champNomUtilisateur.setPreferredSize( new Dimension( 200, 24 ) );

        JTextField champMotDePasse = new JTextField();
        champMotDePasse.setPreferredSize( new Dimension( 200, 24 ) );

        //--- BasDePage ---
        //JPannel
        JPanel jpActionHolder = new JPanel(new BorderLayout());
        JPanel jpActionLine = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            //marge
        JPanel jpMargeBasDePageMargeVerticale = new JPanel();
        jpMargeBasDePageMargeVerticale.setPreferredSize(new Dimension(20, 0));
        JPanel jpMargeBasDePageMargeHorizontale = new JPanel();
        jpMargeBasDePageMargeHorizontale.setPreferredSize(new Dimension(0, 20));


        //********************************
        //--- Affectation des éléments ---
        //********************************

        //--- Entete ---
        jpEntete.add(this.titrePanel, BorderLayout.CENTER);
        jpEntete.add(jpEnteteMargeLaterale, BorderLayout.LINE_START);
        jpEntete.add(jpEnteteMargeHorizontale, BorderLayout.NORTH);
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
        jpPrincipale.add(jpMargeGauche, BorderLayout.LINE_START);
        jpPrincipale.add(jpMargeDroite, BorderLayout.LINE_END);

        //--- Bloc Action ---

        JButton btnValider = new JButton("Valider");
        JButton btnQuitter = new JButton("Quitter");

        jpActionLine.add(btnValider);
        jpActionLine.add(btnQuitter);

        jpActionHolder.add(jpActionLine, BorderLayout.CENTER);
        jpActionHolder.add(jpMargeBasDePageMargeVerticale, BorderLayout.LINE_END);
        jpActionHolder.add(jpMargeBasDePageMargeHorizontale, BorderLayout.SOUTH);
        jpPrincipale.add(jpActionHolder, BorderLayout.SOUTH);



        this.add(jpPrincipale);




    }
}
