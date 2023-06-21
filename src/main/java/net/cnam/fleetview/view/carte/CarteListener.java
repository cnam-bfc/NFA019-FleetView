package net.cnam.fleetview.view.carte;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CarteListener implements MouseListener, MouseMotionListener {
    private final CarteView carteView;
    private final JMapViewer carte;

    private final List<CarteSecteur> secteurs;
    private final List<ICoordinate> points = new LinkedList<>();
    private CarteMode mode = CarteMode.SELECTION;
    private MapPolygonImpl polygon;

    public CarteListener(CarteView carteView, JMapViewer carte, List<CarteSecteur> secteurs) {
        this.carteView = carteView;
        this.carte = carte;
        this.secteurs = secteurs;
    }

    public List<ICoordinate> getPoints() {
        return points;
    }

    public CarteMode getMode() {
        return mode;
    }

    public void setMode(CarteMode mode) {
        this.mode = mode;
        // Reset
        if (this.polygon != null) {
            this.carte.removeMapPolygon(this.polygon);
            this.polygon = null;
        }
        this.points.clear();
    }

    private void refreshPolygon(ICoordinate tempPoint) {
        if (this.points.size() + (tempPoint != null ? 1 : 0) < 3) {
            return;
        }
        this.carte.removeMapPolygon(this.polygon);
        List<ICoordinate> tempPoints = new LinkedList<>(this.points);
        if (tempPoint != null) {
            tempPoints.add(tempPoint);
        }
        this.polygon = new MapPolygonImpl(tempPoints);
        Color polygonColor = new Color(252, 73, 73);
        this.polygon.setColor(polygonColor);
        this.polygon.setBackColor(new Color(polygonColor.getRed(), polygonColor.getGreen(), polygonColor.getBlue(), 100));
        this.carte.addMapPolygon(this.polygon);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Si clic gauche, ajout d'un point
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (mode == CarteMode.CREATION) {
                // Ajout du point
                ICoordinate point = this.carte.getPosition(e.getX(), e.getY());
                this.points.add(point);

                // Rafraîchissement du polygone
                this.refreshPolygon(null);
            } else if (mode == CarteMode.SELECTION) {
                // Récupération du secteur sélectionné par l'utilisateur
                ICoordinate point = this.carte.getPosition(e.getX(), e.getY());
                Map<CarteSecteur, Polygon> polygons = new HashMap<>();
                // Création des polygones
                for (CarteSecteur secteur : this.secteurs) {
                    Polygon polygon = new Polygon();
                    for (ICoordinate coordinate : secteur.getPoints()) {
                        Point point1 = this.carte.getMapPosition((Coordinate) coordinate);
                        polygon.addPoint(point1.x, point1.y);
                    }
                    polygons.put(secteur, polygon);
                }
                // Vérification de la sélection
                for (Map.Entry<CarteSecteur, Polygon> entry : polygons.entrySet()) {
                    if (entry.getValue().contains(e.getX(), e.getY())) {
                        this.carteView.selectSecteur(entry.getKey());
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Lorsque la souris bouge, on rafraîchit le polygone
        ICoordinate tempPoint = this.carte.getPosition(e.getX(), e.getY());
        this.refreshPolygon(tempPoint);
    }
}
