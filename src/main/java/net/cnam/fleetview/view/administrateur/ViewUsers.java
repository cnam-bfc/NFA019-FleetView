package net.cnam.fleetview.view.administrateur;

import javax.swing.*;
import java.awt.*;
public class ViewUsers extends JPanel {

    private final JButton nom = new JButton("<html></html>");

    private final JButton prenom = new JButton("<html></html>");

    private final JButton dateCreation = new JButton("<html></html>");

    private final JButton role = new JButton("<html></html>");

    private final JButton valider = new JButton("<html></html>");
    public ViewUsers(){
        this.nom.setPreferredSize(new Dimension(400,40));
        this.prenom.setPreferredSize(new Dimension(400,40));
        this.dateCreation.setPreferredSize(new Dimension(400,40));
        this.role.setPreferredSize(new Dimension(400,40));

        this.valider.setPreferredSize(new Dimension(120,80));

        this.add(this.nom);
        this.add(this.prenom);
        this.add(this.dateCreation);
        this.add(this.role);

        this.add(this.valider);

    }
}
