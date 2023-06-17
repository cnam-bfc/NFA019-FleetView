package net.cnam.fleetview.view.accueil;

import javax.swing.*;
import java.awt.*;
public class AdminMainView extends JPanel {

    //private final JFrame test = new JFrame();
    private final JButton viewUsers = new JButton("<html>Visualisation de tous les utilisateurs</html>");
    private final JButton modifyUsers = new JButton("<html>Modification des utilisateurs</html>");

    private final JButton dbConnection = new JButton("<html>Connexion à la base de données</html>");

    private final JButton viewDocs = new JButton("<html>Visualisation des documents</html>");

    private final JButton viewCourses = new JButton("<html>Visualisation de toutes les courses</html>");

    private final JButton viewCoursiers = new JButton("<html>Visualisation de tous les coursiers</html>");

    private final JButton viewCycles = new JButton("<html>Visualisation de tous les cycles</html>");

    private final JButton stats = new JButton("<html> Gestion des statistiques </html>");

    private final JButton map = new JButton("<html>Carte interactive</html>");

    public AdminMainView(){
        //this.setPreferredSize(new Dimension(800,100));
        //this.setUI();
        //this.setBackground(new Color(24, 123, 89));
        //this.add(new JButton("ok"));
        //this.add(new JButton("Visualisation de tous les utilisateurs"));
        //this.viewUsers.setMargin(new Insets(10,10,10,10));

        //this.dbConnection.setBorder(new RoundedBorder(10));

        this.dbConnection.setPreferredSize(new Dimension(100,100));
        this.modifyUsers.setPreferredSize(new Dimension(100,100));
        this.viewUsers.setPreferredSize(new Dimension(100,100));
        this.viewCourses.setPreferredSize(new Dimension(100,100));
        this.viewCoursiers.setPreferredSize(new Dimension(100,100));
        this.viewCycles.setPreferredSize(new Dimension(100,100));
        this.viewDocs.setPreferredSize(new Dimension(100,100));
        this.stats.setPreferredSize(new Dimension(100,100));
        this.map.setPreferredSize(new Dimension(100,100));

        this.add(this.dbConnection);
        this.add(this.modifyUsers);
        this.add(this.viewUsers);
        this.add(this.viewCourses);
        this.add(this.viewCoursiers);
        this.add(this.viewCycles);
        this.add(this.viewDocs);
        this.add(this.stats);
        this.add(this.map);
    }


}