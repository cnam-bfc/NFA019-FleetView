package net.cnam.fleetview.controller;

import net.cnam.fleetview.model.basededonnees.BaseDeDonnees;
import net.cnam.fleetview.view.ParametrageBddView;
import net.cnam.fleetview.database.DBConnectionTest;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class ParametrageBddController extends Controller <ParametrageBddView> {


    public ParametrageBddController(ParametrageBddView view) {
        super(view);
        loadDbConfig();
    }

    public void loadDbConfig() {
        BaseDeDonnees db = getDatabase();
        view.fill(db.getAdresseIP(), db.getPort(), db.getNomBase(), db.getIdentifiant(), db.getMotDePasse());
    }

    public boolean setDatabase() {

        HashMap data = view.getInformation();

        if (testConnectionView()) {
            try (OutputStream output = new FileOutputStream("src/main/resources/project.properties")) {
                Properties prop = new Properties();
                prop.setProperty("bdd_ip",(String) data.get("ip") );
                prop.setProperty("bdd_port", (String) data.get("port"));
                prop.setProperty("bdd_name", (String) data.get("nom"));
                prop.setProperty("bdd_username", (String) data.get("id"));
                prop.setProperty("bdd_password", (String) data.get("mdp"));
                prop.store(output, null);
            } catch (IOException io) {
                io.printStackTrace();
                return false;
            }
            return true;
        }

        return false;
    }

    public static BaseDeDonnees getDatabase() {

        String line;

        String bdd_ip = null;
        String bdd_port = null;
        String bdd_name = null;
        String bdd_username = null;
        String bdd_password = null;

        // Chargement des propriétés
        Properties properties = new Properties();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("src/main/resources/project.properties")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            properties.load(reader);
            bdd_ip = properties.getProperty("bdd_ip");
            bdd_port = properties.getProperty("bdd_port");
            bdd_name = properties.getProperty("bdd_name");
            bdd_username = properties.getProperty("bdd_username");
            bdd_password = properties.getProperty("bdd_password");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return (new BaseDeDonnees(bdd_ip, bdd_port, bdd_name, bdd_username, bdd_password));
    }

    public BaseDeDonnees getViewData() {
        HashMap data = view.getInformation();
        BaseDeDonnees bd = new BaseDeDonnees((String) data.get("ip"), (String) data.get("port"), (String) data.get("nom"), (String) data.get("id"), (String) data.get("mdp"));
        return bd;
    }

    public boolean testConnectionView() {
        BaseDeDonnees db = getViewData();
        return DBConnectionTest.getTest(db);
    }

    public boolean testConnectionServeur() {
        BaseDeDonnees db = getDatabase();
        return DBConnectionTest.getTest(db);
    }
}



