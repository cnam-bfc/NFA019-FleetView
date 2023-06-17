package net.cnam.fleetview.view.paramettre;


//Usually you will require both swing and awt packages
// even if you are working with just swings.
import net.cnam.fleetview.view.documents.FicheAccidentView;

import javax.swing.*;
import java.awt.*;
class gui {
    public static void main(String args[]) {

        //Creation de la fenetre
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);


        //ajout du paneau a tester
        JPanel panel = new FicheAccidentView();

        //ajout du JPanel a la fenetre
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);
    }
}
