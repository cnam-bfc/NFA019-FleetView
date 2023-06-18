package net.cnam.fleetview.view.documents;

import net.cnam.fleetview.view.DebugView;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.button.LabelButton;
import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FicheAccidentView extends DebugView {
    private final IconLabel iconLabel;

    public FicheAccidentView() {
        super();
        this.setLayout(new BorderLayout());
        //*********************************************
        //--- Création des éléments de l'interface ---
        //*********************************************

        //--- Titre ---
        this.iconLabel = new IconLabel("\uF56C", "DOCUMENTS");
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
        JPanel jpBloc1 = new JPanel(new GridLayout(1,2));
                //droite
        JPanel jpBloc1droite = new JPanel(new GridLayout(2,1));
                        // bloc 1.1.1 BORDER
        JPanel jpLivreur = new JPanel(new BorderLayout());
        jpLivreur.setLayout(new BoxLayout(jpLivreur, BoxLayout.PAGE_AXIS));
                            //FLOW
        JPanel jpMatricule = new JPanel(new FlowLayout(FlowLayout.LEFT));
                            //FLOW
        JPanel jpNom = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        // bloc 1.1.2(marge) BORDER
        JPanel jpMargeB1 = new JPanel(new BorderLayout());
        jpMargeB1.setLayout(new BoxLayout(jpMargeB1, BoxLayout.PAGE_AXIS));
                        // bloc 1.1.3 BORDER
        JPanel jpDate = new JPanel(new BorderLayout());
        jpDate.setLayout(new BoxLayout(jpDate, BoxLayout.PAGE_AXIS));
                            //FLOW
        JPanel jpJour = new JPanel(new FlowLayout(FlowLayout.LEFT));
                            //FLOW
        JPanel jpHeure = new JPanel(new FlowLayout(FlowLayout.LEFT));
                //gauche
                    //GRID 1,2
        JPanel jpBloc1Gauche = new JPanel(new GridLayout(2,1));
                        // bloc 1.2.1 BORDER
        JPanel jpCourse = new JPanel(new BorderLayout());
        jpCourse.setLayout(new BoxLayout(jpCourse, BoxLayout.PAGE_AXIS));
                            //FLOW
        JPanel jpCourseId = new JPanel(new FlowLayout(FlowLayout.LEFT));
                            //FLOW
        JPanel jpCycle= new JPanel(new FlowLayout(FlowLayout.LEFT));
                        // bloc 1.2.2 (marge) BORDER
            //bloc 2
        JPanel jpAdresse = new JPanel(new GridLayout(3,1));
                // grid 1,3
                    //FLOW
        JPanel jpRue = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    //FLOW
        JPanel jpCommune = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    //FLOw
        JPanel jpCP = new JPanel(new FlowLayout(FlowLayout.LEFT));


        jpLivreur.setPreferredSize(new Dimension(400, 50));
        jpMargeB1.setPreferredSize(new Dimension(300, 50));
        jpDate.setPreferredSize(new Dimension(400, 50));
        jpCourse.setPreferredSize(new Dimension(400, 50));
        jpAdresse.setPreferredSize(new Dimension(400, 50));

        //Marge
        JPanel jpMargeGauche = new JPanel();
        jpMargeGauche.setPreferredSize(new Dimension(100, 0));
        JPanel jpMargeHaut = new JPanel();
        jpMargeHaut.setPreferredSize(new Dimension(0, 50));

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

        texteMatricule.setPreferredSize(new Dimension(100,15));
        texteNom.setPreferredSize(new Dimension(100,15));
        texteCourse.setPreferredSize(new Dimension(100,15));
        texteCycle.setPreferredSize(new Dimension(100,15));
        texteJour.setPreferredSize(new Dimension(100,15));
        texteHeure.setPreferredSize(new Dimension(100,15));
        texteRue.setPreferredSize(new Dimension(100,15));
        texteCommune.setPreferredSize(new Dimension(100,15));
        texteCP.setPreferredSize(new Dimension(100,15));


        //champ texte
        JTextField champMatricule = new JTextField();
        champMatricule.setPreferredSize(new Dimension(200, 24));

        JTextField champNom = new JTextField();
        champNom.setPreferredSize(new Dimension(200, 24));
        //ligne 2
        JTextField champCourse = new JTextField();
        champCourse.setPreferredSize(new Dimension(200, 24));
        //ligne 3
        JTextField champCycle = new JTextField();
        champCycle.setPreferredSize(new Dimension(200, 24));

        JTextField champJour = new JTextField();
        champJour.setPreferredSize(new Dimension(200, 24));

        JTextField champHeure = new JTextField();
        champHeure.setPreferredSize(new Dimension(200, 24));

        JTextField champRue = new JTextField();
        champRue.setPreferredSize(new Dimension(600, 24));

        JTextField champCommune = new JTextField();
        champCommune.setPreferredSize(new Dimension(600, 24));

        JTextField champCP = new JTextField();
        champCP.setPreferredSize(new Dimension(600, 24));

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
        jpEntete.add(this.iconLabel, BorderLayout.CENTER);
        jpEntete.setBorder(new EmptyBorder(20, 20, 20,20));
        jpPrincipale.add(jpEntete, BorderLayout.NORTH);

        //--- Contenu ---

        //Livreur
        jpMatricule.add(texteMatricule);
        jpMatricule.add(champMatricule);
        jpLivreur.add(jpMatricule);

        jpNom.add(texteNom);
        jpNom.add(champNom);
        jpLivreur.add(jpNom);

        //Date
        jpJour.add(texteJour);
        jpJour.add(champJour);
        jpDate.add(jpJour);

        jpHeure.add(texteHeure);
        jpHeure.add(champHeure);
        jpDate.add(jpHeure);

        //bloc1d
        jpBloc1droite.add(jpLivreur);
        jpBloc1droite.add(jpDate);


        //Course
        jpCourseId.add(texteCourse);
        jpCourseId.add(champCourse);
        jpCourse.add(jpCourseId);

        jpCycle.add(texteCycle);
        jpCycle.add(champCycle);
        jpCourse.add(jpCycle);

        //bloc1g
        jpBloc1Gauche.add(jpCourse);
        jpBloc1Gauche.add(jpMargeB1);

        //Adresse
        jpRue.add(texteRue);
        jpRue.add(champRue);
        jpAdresse.add(jpRue);

        jpCommune.add(texteCommune);
        jpCommune.add(champCommune);
        jpAdresse.add(jpCommune);

        jpCP.add(texteCP);
        jpCP.add(champCP);
        jpAdresse.add(jpCP);


        jpBloc1.add(jpBloc1droite);
        jpBloc1.add(jpBloc1Gauche);

        jpDonneeAccident.add(jpBloc1);
        jpDonneeAccident.add(jpAdresse);


        jpPrincipale.add(jpDonneeAccident, BorderLayout.CENTER);

        //--- Marges ---
        jpDonneeAccident.setBorder(new EmptyBorder(0,100,0,100));
        //--- Bloc Action ---

        LabelButton btnValider = new LabelButton("Valider");
        LabelButton btnQuitter = new LabelButton("Quitter");

        jpActionLine.add(btnValider);
        jpActionLine.add(btnQuitter);

        jpActionHolder.add(jpActionLine, BorderLayout.CENTER);
        jpActionHolder.setBorder(new EmptyBorder(25,100,25,100));
        jpPrincipale.add(jpActionHolder, BorderLayout.SOUTH);


        this.add(jpPrincipale);


    }
}
