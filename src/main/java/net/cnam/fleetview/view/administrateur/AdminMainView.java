package net.cnam.fleetview.view.administrateur;

import javax.swing.*;
import java.awt.*;
public class AdminMainView extends JPanel {

    //private final JFrame test = new JFrame();
    private final JButton ViewUsers = new JButton();

    private final JButton DBConnection = new JButton();

    public AdminMainView(){
        this.setPreferredSize(new Dimension(800,100));
        //this.setUI();
        this.setBackground(new Color(24, 123, 89));
        //this.add(new JButton("ok"));
        //this.add(new JButton("Visualisation de tous les utilisateurs"));
        //this.ViewUsers
        this.add(this.ViewUsers);
        this.add(this.DBConnection);
    }


}