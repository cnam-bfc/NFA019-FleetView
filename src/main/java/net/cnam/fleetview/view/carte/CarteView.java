package net.cnam.fleetview.view.carte;

import net.cnam.fleetview.controller.carte.CarteController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.button.IconButton;
import net.cnam.fleetview.view.components.button.IconLabelButton;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.utils.SpringUtilities;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class CarteView extends View<CarteController> {
    // ÉLÉMENTS DE L'INTERFACE
    // Titre
    private final IconLabel titre;
    // Carte
    private final JMapViewer carte;
    // Listener de la carte
    private final CarteListener carteListener;
    // Panel des boutons
    private final JPanel boutonsPanel;
    // Dessin sur la carte
    private final IconLabelButton dessinerButton;
    // Arrêter de dessiner sur la carte
    private final IconLabelButton stopDessinerButton;
    // Menu
    private final JPanel menuPanel;
    // Menu - Titre
    private final IconLabel menuTitre;
    // Menu - Boutons
    private final IconButton closeMenu;
    // Champs
    private final JPanel champsPanel;
    // Label du champ idSecteur
    private final JLabel idSecteurLabel;
    // Champ idSecteur
    private final JTextField idSecteur;
    // Label du champ nomSecteur
    private final JLabel nomSecteurLabel;
    // Champ nomSecteur
    private final JTextField nomSecteur;
    // Boutons
    private final JPanel menuButtonsPanel;
    private final IconLabelButton enregistrerSecteur;

    // Secteurs
    private final List<CarteSecteur> secteurs = new LinkedList<>();
    private final LinkedList<Color> colors = new LinkedList<>();

    private Color ancienneCouleurSecteurSelectionne;
    private CarteSecteur secteurSelectionne;

    public CarteView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Bordures
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.titre = new IconLabel("\uf279", "Carte");
        this.carte = new JMapViewer();
        this.carteListener = new CarteListener(this, this.carte, this.secteurs);
        this.boutonsPanel = new JPanel();
        this.dessinerButton = new IconLabelButton("\uF1FC", "Dessiner un secteur");
        this.stopDessinerButton = new IconLabelButton("\uF04D", "Arrêter de dessiner");
        this.menuPanel = new JPanel();
        this.menuTitre = new IconLabel("\uF5EE", "Menu");
        this.closeMenu = new IconButton("\uF00D");
        this.menuButtonsPanel = new JPanel();
        this.champsPanel = new JPanel();
        this.idSecteurLabel = new JLabel("ID du secteur :");
        this.idSecteur = new JTextField();
        this.nomSecteurLabel = new JLabel("Nom du secteur :");
        this.nomSecteur = new JTextField(25);
        this.enregistrerSecteur = new IconLabelButton("\uF0C7", "Enregistrer le secteur");


        // Configuration des éléments de l'interface
        // Carte
        this.carte.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.carte.addMouseListener(carteListener);
        this.carte.addMouseMotionListener(carteListener);
        this.carte.setDisplayPosition(new Coordinate(46.783, 4.8519), 13);

        // Dessiner
        this.dessinerButton.addActionListener(e -> {
            carteListener.setMode(CarteMode.CREATION);
            dessinerButton.setVisible(false);
            stopDessinerButton.setVisible(true);
        });
        this.stopDessinerButton.addActionListener(e -> {
            // Récupération des coordonnées du secteur
            List<ICoordinate> coordonnees = new LinkedList<>(carteListener.getPoints());
            // Création du secteur
            CarteSecteur secteur;
            if (this.secteurSelectionne == null) {
                secteur = addSecteur(null, "Nouveau secteur", coordonnees);
            } else {
                CarteSecteur ancienSecteur = this.secteurSelectionne;
                unselectSecteur(true);
                secteur = addSecteur(ancienSecteur.getIdSecteur(), ancienSecteur.getNom(), coordonnees);
            }
            selectSecteur(secteur);
            carteListener.setMode(CarteMode.SELECTION);
            dessinerButton.setVisible(true);
            stopDessinerButton.setVisible(false);
        });
        this.stopDessinerButton.setVisible(false);

        // Menu
        this.menuPanel.setLayout(new BorderLayout());
        this.menuPanel.setBorder(BorderFactory.createEmptyBorder(this.titre.getHeight(), 10, 0, 0));
        this.menuPanel.setVisible(false);

        // Menu - Titre
        JPanel menuTitrePanel = new JPanel();
        menuTitrePanel.setLayout(new BorderLayout());
        menuTitrePanel.add(this.menuTitre, BorderLayout.CENTER);
        menuTitrePanel.add(this.closeMenu, BorderLayout.EAST);

        // Fermer le menu
        closeMenu.addActionListener(e -> {
            unselectSecteur();

            controller.onRefreshSecteurs();
        });

        // Enregistrer le secteur
        this.enregistrerSecteur.addActionListener(e -> {
            if (this.secteurSelectionne != null) {
                controller.enregistrerSecteur(this.secteurSelectionne.getIdSecteur(), this.nomSecteur.getText(), this.secteurSelectionne.getPoints());

                unselectSecteur();

                controller.onRefreshSecteurs();
            }
        });

        // Champs
        JPanel menuContentPanel = new JPanel();
        BoxLayout menuContentLayout = new BoxLayout(menuContentPanel, BoxLayout.Y_AXIS);
        menuContentPanel.setLayout(menuContentLayout);
        this.champsPanel.setLayout(new SpringLayout());
        // Pas d'agrandissement des champs sur la hauteur
        this.champsPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 0));

        // Id du secteur
        this.idSecteur.setEditable(false);

        // Couleurs
        // Ajout de couleurs
        this.colors.add(new Color(255, 177, 193));
        this.colors.add(new Color(255, 209, 177));
        this.colors.add(new Color(255, 241, 177));
        this.colors.add(new Color(209, 255, 177));
        this.colors.add(new Color(177, 255, 193));
        this.colors.add(new Color(177, 255, 209));
        this.colors.add(new Color(177, 255, 241));
        this.colors.add(new Color(177, 209, 255));
        this.colors.add(new Color(177, 177, 255));
        this.colors.add(new Color(209, 177, 255));
        this.colors.add(new Color(255, 177, 255));
        this.colors.add(new Color(255, 177, 209));


        // Ajout des éléments de l'interface
        this.add(this.titre, BorderLayout.NORTH);
        this.menuPanel.add(menuTitrePanel, BorderLayout.NORTH);
        this.champsPanel.add(this.idSecteurLabel);
        this.champsPanel.add(this.idSecteur);
        this.champsPanel.add(this.nomSecteurLabel);
        this.champsPanel.add(this.nomSecteur);
        SpringUtilities.makeCompactGrid(this.champsPanel, 2, 2, 6, 6, 6, 6);
        menuContentPanel.add(this.champsPanel);
        menuContentPanel.add(Box.createVerticalGlue());
        this.menuPanel.add(menuContentPanel, BorderLayout.CENTER);
        this.menuPanel.add(this.menuButtonsPanel, BorderLayout.SOUTH);
        this.menuButtonsPanel.add(this.enregistrerSecteur);
        this.add(this.menuPanel, BorderLayout.EAST);
        this.add(this.carte, BorderLayout.CENTER);
        this.boutonsPanel.add(this.dessinerButton);
        this.boutonsPanel.add(this.stopDessinerButton);
        this.add(this.boutonsPanel, BorderLayout.SOUTH);
    }

    @Override
    public void onDisplayed() {
        super.onDisplayed();

        // Actualisation des secteurs
        this.controller.onRefreshSecteurs();
    }

    /**
     * Méthode permettant de retirer tous les secteurs de la carte
     */
    public void clearSecteurs() {
        for (CarteSecteur secteur : this.secteurs) {
            this.carte.removeMapPolygon(secteur.getPolygon());
        }
        this.secteurs.clear();
    }

    /**
     * Méthode permettant d'ajouter un secteur à la carte
     *
     * @param id
     * @param nom
     * @param points
     */
    public CarteSecteur addSecteur(Integer id, String nom, List<ICoordinate> points) {
        // Création du polygone
        MapPolygonImpl polygon = new MapPolygonImpl(nom, points);
        Color color = this.colors.removeFirst();
        this.colors.addLast(color);
        polygon.setColor(color);
        polygon.setBackColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));

        CarteSecteur secteur = new CarteSecteur(id, nom, points, polygon);
        this.secteurs.add(secteur);

        // Ajout du polygone à la carte
        this.carte.addMapPolygon(polygon);

        return secteur;
    }

    /**
     * Méthode permettant de supprimer un secteur de la carte
     *
     * @param id
     */
    public void removeSecteur(Integer id) {
        CarteSecteur secteur = null;
        for (CarteSecteur s : this.secteurs) {
            if (s.getIdSecteur().equals(id)) {
                secteur = s;
                break;
            }
        }
        if (secteur != null) {
            this.carte.removeMapPolygon(secteur.getPolygon());
            this.secteurs.remove(secteur);
        }
    }

    /**
     * Méthode permettant de sélectionner un secteur
     *
     * @param secteur
     */
    public void selectSecteur(CarteSecteur secteur) {
        if (this.secteurSelectionne != null) {
            unselectSecteur();
        }
        this.ancienneCouleurSecteurSelectionne = secteur.getPolygon().getColor();
        secteur.getPolygon().setColor(new Color(252, 73, 73));
        secteur.getPolygon().setBackColor(new Color(252, 73, 73, 100));
        this.secteurSelectionne = secteur;
        if (secteur.getIdSecteur() != null) {
            this.idSecteur.setText(secteur.getIdSecteur().toString());
        }
        this.nomSecteur.setText(secteur.getNom());
        this.menuPanel.setVisible(true);
    }

    /**
     * Méthode permettant de désélectionner un secteur
     */
    public void unselectSecteur() {
        unselectSecteur(false);
    }

    /**
     * Méthode permettant de désélectionner un secteur
     *
     * @param remove Forcer la suppression du secteur de la carte
     */
    public void unselectSecteur(boolean remove) {
        this.secteurSelectionne.getPolygon().setColor(this.ancienneCouleurSecteurSelectionne);
        this.secteurSelectionne.getPolygon().setBackColor(new Color(this.ancienneCouleurSecteurSelectionne.getRed(), this.ancienneCouleurSecteurSelectionne.getGreen(), this.ancienneCouleurSecteurSelectionne.getBlue(), 100));
        if (this.secteurSelectionne.getIdSecteur() == null || remove) {
            this.secteurs.remove(this.secteurSelectionne);
            this.carte.removeMapPolygon(this.secteurSelectionne.getPolygon());
        }
        this.secteurSelectionne = null;
        this.idSecteur.setText("");
        this.nomSecteur.setText("");
        this.menuPanel.setVisible(false);
    }

    /**
     * Méthode permettant de récupérer les coordonnées x, y d'un point latitude, longitude sur la carte
     *
     * @param coordinate Coordonnées latitude, longitude
     * @return Coordonnées x, y
     */
    public Point getPointFromLatLng(Coordinate coordinate) {
        return this.carte.getMapPosition(coordinate);
    }
}
