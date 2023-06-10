package net.cnam.fleetview.view.administrateur;

import javax.swing.*;
import java.awt.*;
public class AdminMainView extends JPanel {

    //private final JFrame test = new JFrame();
    private final JButton viewUsers = new JButton();

    private final JButton dbConnection = new JButton();

    public AdminMainView(){
        this.setPreferredSize(new Dimension(800,100));
        //this.setUI();
        this.setBackground(new Color(24, 123, 89));
        //this.add(new JButton("ok"));
        //this.add(new JButton("Visualisation de tous les utilisateurs"));
        //this.viewUsers.setMargin(new Insets(10,10,10,10));
        this.add(this.viewUsers);
        this.add(this.dbConnection);
    }


}