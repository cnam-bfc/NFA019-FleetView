package net.cnam.fleetview.view.documents;

import net.cnam.fleetview.view.TitrePanel;

import javax.swing.*;
import java.awt.*;

public class FicheAccidentView extends JPanel  {
    private final TitrePanel titrePanel;

    public FicheAccidentView() {
        super();
        this.setLayout(new BorderLayout());
        //*********************************************
        //--- Création des éléments de l'interface ---
        //*********************************************

        //--- Titre ---
        this.titrePanel = new TitrePanel("\uF56C", "DOCUMENTS");
        JPanel jpEntete = new JPanel(new BorderLayout());

        JPanel jpEnteteMargeLaterale = new JPanel();
        jpEnteteMargeLaterale.setPreferredSize(new Dimension(20, 0));

        JPanel jpEnteteMargeHorizontale = new JPanel();
        jpEnteteMargeHorizontale.setPreferredSize(new Dimension(0, 20));

        JPanel jpPrincipale = new JPanel(new BorderLayout());

        //--- Contenu ---

        //JPannel
        JPanel jpDonneeAccident = new JPanel();
        jpDonneeAccident.setLayout(new BoxLayout(jpDonneeAccident, BoxLayout.PAGE_AXIS));

            //bloc 1 GRID 2,1
        JPanel jpBloc1 = new JPanel(new GridLayout(2,1));
                //droite
        JPanel jpBloc1droite = new JPanel(new GridLayout(3,1));
                        // bloc 1.1.1 BORDER
        JPanel jpLivreur = new JPanel(new BorderLayout());
                            //FLOW
        JPanel jpMatricule = new JPanel(new FlowLayout());
                            //FLOW
        JPanel jpNom = new JPanel(new FlowLayout());
                        // bloc 1.1.2(marge) BORDER
        JPanel jpMargeB1 = new JPanel(new BorderLayout());
                        // bloc 1.1.3 BORDER
        JPanel jpDate = new JPanel(new BorderLayout());
                            //FLOW
        JPanel jpJour = new JPanel(new FlowLayout());
                            //FLOW
        JPanel jpHeure = new JPanel(new FlowLayout());
                //gauche
                    //GRID 1,2
        JPanel jpBloc1Gauche = new JPanel(new GridLayout(2,1));
                        // bloc 1.2.1 BORDER
        JPanel jpCourse = new JPanel(new BorderLayout());
                            //FLOW
        JPanel jpCourseId = new JPanel(new FlowLayout());
                            //FLOW
        JPanel jpCycle= new JPanel(new FlowLayout());
                        // bloc 1.2.2 (marge) BORDER
            //bloc 2
        JPanel jpAdresse = new JPanel(new GridLayout(3,1));
                // grid 1,3
                    //FLOW
        JPanel jpRue = new JPanel(new FlowLayout());
                    //FLOW
        JPanel jpCommune = new JPanel(new FlowLayout());
                    //FLOw
        JPanel jpCP = new JPanel(new FlowLayout());


        //Marge
        JPanel jpMargeGauche = new JPanel();
        jpMargeGauche.setPreferredSize(new Dimension(200, 0));
        JPanel jpMargeDroite = new JPanel();

        //Label
        JLabel texteMatricule = new JLabel("Matricule");
        JLabel texteNom = new JLabel("Nom");
        JLabel texteCourse = new JLabel("Course");
        JLabel texteCycle = new JLabel("Cycle");
        JLabel texteJour = new JLabel("Jour");
        JLabel texteHeure = new JLabel("Heure");
        JLabel texteRue = new JLabel("Rue");
        JLabel texteCommune = new JLabel("Commune");
        JLabel texteCP = new JLabel("Code postal");

        //champ texte
        JTextField champMatricule = new JTextField();
        champMatricule.setPreferredSize(new Dimension(200, 24));

        JTextField champNom = new JTextField();
        champNom.setPreferredSize(new Dimension(200, 24));
        //ligne 2
        JTextField champCourse = new JTextField();
        champCourse.setPreferredSize(new Dimension(500, 24));
        //ligne 3
        JTextField champCycle = new JTextField();
        champCycle.setPreferredSize(new Dimension(200, 24));

        JTextField champJour = new JTextField();
        champJour.setPreferredSize(new Dimension(200, 24));

        JTextField champHeure = new JTextField();
        champHeure.setPreferredSize(new Dimension(200, 24));

        JTextField champRue = new JTextField();
        champRue.setPreferredSize(new Dimension(200, 24));

        JTextField champCommune = new JTextField();
        champCommune.setPreferredSize(new Dimension(200, 24));

        JTextField champCP = new JTextField();
        champCP.setPreferredSize(new Dimension(200, 24));

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

        jpPrincipale.add(jpDonneeAccident, BorderLayout.CENTER);

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
