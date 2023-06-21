package net.cnam.fleetview.view.carte;

import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;

import java.util.List;

public class CarteSecteur {
    private final Integer idSecteur;
    private final String nom;
    private final List<ICoordinate> points;

    private final MapPolygonImpl polygon;

    public CarteSecteur(Integer idSecteur, String nom, List<ICoordinate> points, MapPolygonImpl polygon) {
        this.idSecteur = idSecteur;
        this.nom = nom;
        this.points = points;
        this.polygon = polygon;
    }

    public Integer getIdSecteur() {
        return idSecteur;
    }

    public String getNom() {
        return nom;
    }

    public List<ICoordinate> getPoints() {
        return points;
    }

    public MapPolygonImpl getPolygon() {
        return polygon;
    }
}
