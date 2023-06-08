package net.cnam.fleetview.view.paramettre;

import net.cnam.fleetview.view.TitrePanel;

import javax.swing.*;
import java.awt.*;

public class ParametrageBddView extends JPanel {

    private final TitrePanel titrePanel;

    public ParametrageBddView(){
        super();
        this.setLayout(new BorderLayout());


        // Création des éléments de l'interface
        this.titrePanel = new TitrePanel("\uF013", "BASE DE DONNEE");
        JPanel jpPrincipale = new JPanel(new BorderLayout());


        // Ajout des éléments de l'interface
        jpPrincipale.add(this.titrePanel , BorderLayout.NORTH);
        this.add(jpPrincipale);
    }


}
