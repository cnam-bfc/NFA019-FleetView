package net.cnam.fleetview.controller.carte;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.controller.ParametrageBddController;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.CustomConnectorGenerator;
import net.cnam.fleetview.model.secteur.Secteur;
import net.cnam.fleetview.model.secteur.SecteurDAO;
import net.cnam.fleetview.model.secteurdelimitation.SecteurDelimitation;
import net.cnam.fleetview.model.secteurdelimitation.SecteurDelimitationDAO;
import net.cnam.fleetview.model.secteurpoint.SecteurPoint;
import net.cnam.fleetview.model.secteurpoint.SecteurPointDAO;
import net.cnam.fleetview.view.carte.CarteView;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class CarteController extends Controller<CarteView> {
    // Model
    // DAO
    private final SecteurDAO secteurDAO;
    private final SecteurDelimitationDAO secteurDelimitationDAO;
    private final SecteurPointDAO secteurPointDAO;

    public CarteController(CarteView view) {
        super(view);

        // Initialisation des DAO
        CustomConnectorGenerator dbGenerator = new CustomConnectorGenerator(ParametrageBddController.getDatabase());
        Connection connection = BDDConnection.getInstance(dbGenerator.getConnector());
        this.secteurDAO = new SecteurDAO(connection);
        this.secteurDelimitationDAO = new SecteurDelimitationDAO(connection);
        this.secteurPointDAO = new SecteurPointDAO(connection);
    }

    public void onRefreshSecteurs() {
        // Suppression des secteurs de la vue
        view.clearSecteurs();

        // Récupération des secteurs en base de données
        List<Secteur> secteurs = secteurDAO.getAllNotArchived();
        for (Secteur secteur : secteurs) {
            List<ICoordinate> points = new LinkedList<>();

            // Récupération des délimitations du secteur
            List<SecteurDelimitation> secteurDelimitations = secteurDelimitationDAO.getAllByIdSecteur(secteur.getIdSecteur());
            for (SecteurDelimitation secteurDelimitation : secteurDelimitations) {
                // Récupération des points de la délimitation
                SecteurPoint secteurPoint = secteurPointDAO.getById(secteurDelimitation.getIdSecteurPoint());

                Coordinate coordinate = new Coordinate(secteurPoint.getLatitude(), secteurPoint.getLongitude());

                points.add(coordinate);
            }

            view.addSecteur(secteur.getIdSecteur(), secteur.getNom(), points);
        }
    }

    public void enregistrerSecteur(Integer id, String nom, List<ICoordinate> points) {
        // Création du secteur
        Secteur secteur;
        if (id == null) {
            secteur = new Secteur();
            secteur.setNom(nom);
            secteurDAO.create(secteur, RootController.getCurrentUser());
        } else {
            view.removeSecteur(id);
            secteur = secteurDAO.getById(id);
            secteur.setNom(nom);
            secteurDAO.update(secteur, RootController.getCurrentUser());
        }

        // Suppression des délimitations du secteur
        List<SecteurDelimitation> secteurDelimitations = secteurDelimitationDAO.getAllByIdSecteur(secteur.getIdSecteur());
        for (SecteurDelimitation secteurDelimitation : secteurDelimitations) {
            // Suppression du point de la délimitation
            SecteurPoint secteurPoint = secteurPointDAO.getById(secteurDelimitation.getIdSecteurPoint());
            secteurDelimitationDAO.delete(secteurDelimitation, RootController.getCurrentUser());
            secteurPointDAO.delete(secteurPoint, RootController.getCurrentUser());
        }

        // Création des points
        List<SecteurPoint> secteurPoints = new LinkedList<>();
        for (ICoordinate point : points) {
            SecteurPoint secteurPoint = new SecteurPoint();
            secteurPoint.setLatitude(point.getLat());
            secteurPoint.setLongitude(point.getLon());
            secteurPointDAO.create(secteurPoint, RootController.getCurrentUser());
            secteurPoints.add(secteurPoint);
        }

        // Création des délimitations
        int i = 0;
        for (SecteurPoint secteurPoint : secteurPoints) {
            SecteurDelimitation secteurDelimitation = new SecteurDelimitation();
            secteurDelimitation.setIdSecteur(secteur.getIdSecteur());
            secteurDelimitation.setIdSecteurPoint(secteurPoint.getIdSecteurPoint());
            secteurDelimitation.setOrdre(++i);
            secteurDelimitationDAO.create(secteurDelimitation, RootController.getCurrentUser());
        }

        view.addSecteur(secteur.getIdSecteur(), secteur.getNom(), points);
    }
}
