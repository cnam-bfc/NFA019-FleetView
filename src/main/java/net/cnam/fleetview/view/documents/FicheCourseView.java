package net.cnam.fleetview.view.documents;

import net.cnam.fleetview.view.DebugView;
import net.cnam.fleetview.view.components.button.LabelButton;
import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FicheCourseView extends DebugView {
    private final IconLabel iconLabel;

    public FicheCourseView() {
        super();
        this.setLayout(new BorderLayout());
        //*********************************************
        //--- Création des éléments de l'interface ---
        //*********************************************

        //--- Titre ---
        this.iconLabel = new IconLabel("\uF56C", "DOCUMENTS");
        JPanel jpEntete = new JPanel(new BorderLayout());
        JPanel jpPrincipale = new JPanel(new BorderLayout());

        //--- Contenu ---

        //JPannel
        JPanel jpDonneeCourse = new JPanel();
        jpDonneeCourse.setLayout(new BoxLayout(jpDonneeCourse, BoxLayout.PAGE_AXIS));

            //bloc 1 GRID 2,1
        JPanel jpBloc1 = new JPanel(new GridLayout( 1,2));

        JPanel jpLivreur = new JPanel(new BorderLayout());
        jpLivreur.setLayout(new BoxLayout(jpLivreur, BoxLayout.PAGE_AXIS));
                            //FLOW
        JPanel jpMatricule = new JPanel(new FlowLayout(FlowLayout.LEFT));
                            //FLOW
        JPanel jpNom = new JPanel(new FlowLayout(FlowLayout.LEFT));
                //bloc Gauche
        JPanel jpCourse = new JPanel(new BorderLayout());
        jpCourse.setLayout(new BoxLayout(jpCourse, BoxLayout.PAGE_AXIS));

        JPanel jpCourseId = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel jpCycle = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //bloc 2 flow
        JPanel jpDistanceHolder = new JPanel(new BorderLayout());
        JPanel jpDistance = new JPanel(new FlowLayout(FlowLayout.LEFT));
            //marge
        JPanel jpMargeB2top = new JPanel();
        jpMargeB2top.setPreferredSize(new Dimension(100, 5));
        JPanel jpMargeB2Bot = new JPanel();
        jpMargeB2Bot.setPreferredSize(new Dimension(100, 5));


        //bloc 3
        JPanel jpTableHolder = new JPanel(new BorderLayout());

        Object[][] donnees = {
                {"Johnathan", "4 place jeacque Onpagne", 2},
                {"Nicolas", "5b allée des ebbrumes", 1},
                {"Damien", "Maison du grands schtumf", 8},
                {"Sivester Stalone", "Jungle vietnamiène",5},

        };

        String[] entetes = {"Destinataire", "Adresse", "Quantité colis"};

        JTable tableau = new JTable(donnees, entetes);


        //taille
        jpBloc1.setPreferredSize( new Dimension(400, 70));
        jpLivreur.setPreferredSize(new Dimension(400, 50));
        jpCourse.setPreferredSize(new Dimension(400, 50));

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
        JLabel texteDistance = new JLabel("Distance");


        texteMatricule.setPreferredSize(new Dimension(100,15));
        texteNom.setPreferredSize(new Dimension(100,15));
        texteCourse.setPreferredSize(new Dimension(100,15));
        texteCycle.setPreferredSize(new Dimension(100,15));
        texteDistance.setPreferredSize(new Dimension(100,15));


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

        JTextField champDistance = new JTextField();
        champDistance.setPreferredSize(new Dimension(200, 24));


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

        //Course
        jpCourseId.add(texteCourse);
        jpCourseId.add(champCourse);
        jpCourse.add(jpCourseId);

        jpCycle.add(texteCycle);
        jpCycle.add(champCycle);
        jpCourse.add(jpCycle);


        //bloc1d
        jpBloc1.add(jpLivreur);
        jpBloc1.add(jpCourse);



        //Distance
        jpDistance.add(texteDistance);
        jpDistance.add(champDistance);
        jpDistanceHolder.add(jpDistance, BorderLayout.CENTER);
        jpDistanceHolder.add(jpMargeB2top, BorderLayout.NORTH);
        jpDistanceHolder.add(jpMargeB2Bot, BorderLayout.SOUTH);


        //Tableau
        jpTableHolder.add(tableau.getTableHeader(), BorderLayout.NORTH);
        jpTableHolder.add(tableau, BorderLayout.CENTER);
        //Data
        jpDonneeCourse.add(jpBloc1);
        jpDonneeCourse.add(jpDistanceHolder);
        jpDonneeCourse.add(jpTableHolder);

        jpPrincipale.add(jpDonneeCourse, BorderLayout.CENTER);

        //--- Marges ---
        jpDonneeCourse.setBorder(new EmptyBorder(0,100,0,100));
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
