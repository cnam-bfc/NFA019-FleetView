package net.cnam.fleetview.database;

import net.cnam.fleetview.model.basededonnees.BaseDeDonnees;

import java.sql.*;

public class DBConnectionTest {
    public static boolean getTest(BaseDeDonnees db){

        String requete = "SELECT count(id_droits) as nb FROM fleetview_droits";

        CustomConnectorGenerator dbGenerator = new CustomConnectorGenerator(db);
        Connector connector = dbGenerator.getConnector();

        try (Connection connection = BDDConnection.getInstance(connector);
             PreparedStatement stmt = connection.prepareStatement(requete);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int nb = rs.getInt("nb");
                System.out.println("nb : " + nb);
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
