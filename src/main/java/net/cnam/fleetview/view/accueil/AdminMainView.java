package net.cnam.fleetview.view.accueil;

import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;

import javax.swing.*;
import java.awt.*;
public class AdminMainView extends View {

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

    private final IconLabel iconLabel;

    public AdminMainView(){

        this.iconLabel = new IconLabel("\uF013", "Création / Modification d'un Utilisateur");
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel bttnPanel = new JPanel(new GridLayout(2,5,50,50));

        this.dbConnection.setPreferredSize(new Dimension(120,120));
        this.modifyUsers.setPreferredSize(new Dimension(120,120));
        this.viewUsers.setPreferredSize(new Dimension(120,120));
        this.viewCourses.setPreferredSize(new Dimension(120,120));
        this.viewCoursiers.setPreferredSize(new Dimension(120,120));
        this.viewCycles.setPreferredSize(new Dimension(100,100));
        this.viewDocs.setPreferredSize(new Dimension(100,100));
        this.stats.setPreferredSize(new Dimension(100,100));
        this.map.setPreferredSize(new Dimension(100,100));

        bttnPanel.add(this.dbConnection);
        bttnPanel.add(this.modifyUsers);
        bttnPanel.add(this.viewUsers);
        bttnPanel.add(this.viewCourses);
        bttnPanel.add(this.viewCoursiers);
        bttnPanel.add(this.viewCycles);
        bttnPanel.add(this.viewDocs);
        bttnPanel.add(this.stats);
        bttnPanel.add(this.map);

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(100,0));
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(100,0));

        mainPanel.add(this.iconLabel,BorderLayout.NORTH);
        mainPanel.add(leftPanel,BorderLayout.LINE_START);
        mainPanel.add(bttnPanel,BorderLayout.CENTER);
        mainPanel.add(rightPanel,BorderLayout.LINE_END);
        this.add(mainPanel);
    }


}