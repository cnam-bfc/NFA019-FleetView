package net.cnam.fleetview.database;

import javax.swing.*;

public class DefaultConnector extends Connector {
    public DefaultConnector() {
        super("jdbc:mysql://fleetview.cnam.farmvivi.fr:3310/fleetview", "fleetview", "fleetview", "fleetview");

        // Chargement du driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erreur de chargement du driver : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            System.err.println("Erreur de chargement du driver : " + e.getMessage());
        }
    }
}
