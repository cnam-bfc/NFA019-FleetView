package net.cnam.fleetview.controller.colis;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.Connector;
import net.cnam.fleetview.database.CustomConnectorGenerator;
import net.cnam.fleetview.model.adresse.Adresse;
import net.cnam.fleetview.model.adresse.AdresseDAO;
import net.cnam.fleetview.model.colis.Colis;
import net.cnam.fleetview.model.colis.ColisDAO;
import net.cnam.fleetview.model.colisdestinataire.ColisDestinataire;
import net.cnam.fleetview.model.colisdestinataire.ColisDestinataireDAO;
import net.cnam.fleetview.model.nominatim.NominatimAddress;
import net.cnam.fleetview.model.nominatim.NominatimAddressDAO;
import net.cnam.fleetview.model.secteur.Secteur;
import net.cnam.fleetview.model.secteur.SecteurDAO;
import net.cnam.fleetview.model.secteurdelimitation.SecteurDelimitation;
import net.cnam.fleetview.model.secteurdelimitation.SecteurDelimitationDAO;
import net.cnam.fleetview.model.secteurdelimitation.SecteurDelimitationOrdreComparator;
import net.cnam.fleetview.model.secteurpoint.SecteurPoint;
import net.cnam.fleetview.model.secteurpoint.SecteurPointDAO;
import net.cnam.fleetview.utils.Debouncer;
import net.cnam.fleetview.view.colis.edit.ColisView;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;

import java.awt.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ColisController extends Controller<ColisView> {
    // DAO
    private final ColisDAO colisDAO;
    private final AdresseDAO adresseDAO;
    private final SecteurDAO secteurDAO;
    private final SecteurDelimitationDAO secteurDelimitationDAO;
    private final SecteurPointDAO secteurPointDAO;
    private final ColisDestinataireDAO colisDestinataireDAO;
    private final NominatimAddressDAO nominatimAddressDAO;

    // Debouncer
    private final Debouncer searchAddresseDebouncer;

    // Colis
    private Colis colis;
    // Adresse
    private Adresse adresse;
    private NominatimAddress nominatimAddress;
    // Secteur
    private Secteur secteur;
    // Destinataire
    private ColisDestinataire colisDestinataire;

    public ColisController(ColisView view) {
        super(view);

        // Initialisation des DAO
        CustomConnectorGenerator generator = new CustomConnectorGenerator();
        Connector connector = generator.getConnector();
        Connection connection = BDDConnection.getInstance(connector);

        this.colisDAO = new ColisDAO(connection);
        this.adresseDAO = new AdresseDAO(connection);
        this.secteurDAO = new SecteurDAO(connection);
        this.secteurDelimitationDAO = new SecteurDelimitationDAO(connection);
        this.secteurPointDAO = new SecteurPointDAO(connection);
        this.colisDestinataireDAO = new ColisDestinataireDAO(connection);
        this.nominatimAddressDAO = new NominatimAddressDAO();

        // Initialisation du debouncer
        this.searchAddresseDebouncer = new Debouncer(500);
    }

    /**
     * Chargement par défaut de la vue
     */
    private void loadDefault() {
        // Récupération des secteurs
        List<Secteur> secteurs = secteurDAO.getAll();

        // Ajout des secteurs dans la vue
        for (Secteur secteur : secteurs) {
            view.addSecteur(secteur.getIdSecteur(), secteur.getNom());
        }
    }

    /**
     * Chargement par défaut d'un colis
     *
     * @param id
     */
    private void loadColis(int id) {
        // Récupération du colis
        this.colis = colisDAO.getById(id);
        // Récupération de l'adresse
        this.adresse = adresseDAO.getById(colis.getIdAdresse());
        // Récupération du secteur
        this.secteur = secteurDAO.getById(adresse.getIdSecteur());
        // Récupération du destinataire
        this.colisDestinataire = colisDestinataireDAO.getById(colis.getIdColis());

        // Chargement des données dans la vue
        view.fill(String.valueOf(colis.getIdColis()), colis.getNumero(), colis.getPoids());
        view.fillAdresse(adresse.getOsmType(), adresse.getOsmId(), adresse.getPays(), adresse.getCodePostal(), adresse.getCommune(), adresse.getRue(), adresse.getNumeroRue(), adresse.getComplement());
        view.fillSecteur(secteur.getIdSecteur());
        view.fillDestinataire(colisDestinataire.getNom(), colisDestinataire.getPrenom());
    }

    /**
     * Charger un colis vide dans la vue
     */
    public void loadEmptyColis() {
        // Chargement des données par défaut dans la vue
        this.loadDefault();

        // Rendre les champs modifiables
        view.setFieldsEditable(true);
    }

    /**
     * Charger un colis affichable dans la vue
     *
     * @param id
     */
    public void loadViewableColis(int id) {
        // Chargement des données par défaut dans la vue
        this.loadDefault();

        // Chargement du colis
        this.loadColis(id);

        // Rendre les champs non modifiables
        view.setFieldsEditable(false);
    }

    /**
     * Charger un colis modifiable dans la vue
     *
     * @param id
     */
    public void loadEditableColis(int id) {
        // Chargement des données par défaut dans la vue
        this.loadDefault();

        // Chargement du colis
        this.loadColis(id);

        // Rendre les champs modifiables
        view.setFieldsEditable(true);
    }

    /**
     * Sauvegarder un colis
     *
     * @param numero      Numéro du colis
     * @param poids       Poids du colis
     * @param idSecteur   Identifiant du secteur
     * @param osmType     Type de l'adresse
     * @param osmId       Identifiant de l'adresse
     * @param pays        Pays de l'adresse
     * @param codePostal  Code postal de l'adresse
     * @param commune     Commune de l'adresse
     * @param rue         Rue de l'adresse
     * @param numeroDeRue Numéro de rue de l'adresse
     * @param complement  Complément d'adresse
     * @param nom         Nom du destinataire
     * @param prenom      Prénom du destinataire
     * @return Si la sauvegarde a réussi
     */
    public boolean saveColis(String numero, double poids, int idSecteur, String osmType, long osmId, String pays, String codePostal, String commune, String rue, String numeroDeRue, String complement, String nom, String prenom) {
        // Si on est en mode ajout d'un colis
        if (colis == null) {
            this.colis = new Colis();
        }

        // Sauvegarde des données dans le colis
        colis.setNumero(numero);
        colis.setPoids(poids);

        // Adresse
        if (adresse == null) {
            this.adresse = new Adresse();
        }

        // Sauvegarde des données dans l'adresse
        adresse.setOsmType(osmType);
        adresse.setOsmId(osmId);
        adresse.setPays(pays);
        adresse.setCodePostal(codePostal);
        adresse.setCommune(commune);
        adresse.setRue(rue);
        adresse.setNumeroRue(numeroDeRue);
        adresse.setComplement(complement);

        // Secteur
        if (secteur == null) {
            this.secteur = secteurDAO.getById(idSecteur);
            if (secteur == null) {
                return false;
            }
            adresse.setIdSecteur(secteur.getIdSecteur());
        }

        // Sauvegarde de l'adresse
        boolean success;
        if (adresse.getIdAdresse() == null) {
            success = adresseDAO.create(adresse, RootController.getConnectedUser());
        } else {
            success = adresseDAO.update(adresse, RootController.getConnectedUser());
        }
        if (!success) {
            return false;
        }

        this.colis.setIdAdresse(adresse.getIdAdresse());

        // Destinataire
        if (colisDestinataire == null) {
            this.colisDestinataire = new ColisDestinataire();
        }

        // Sauvegarde des données dans le destinataire
        colisDestinataire.setNom(nom);
        colisDestinataire.setPrenom(prenom);

        // Sauvegarde du destinataire
        if (colisDestinataire.getIdColisDestinataire() == null) {
            success = colisDestinataireDAO.create(colisDestinataire, RootController.getConnectedUser());
        } else {
            success = colisDestinataireDAO.update(colisDestinataire, RootController.getConnectedUser());
        }
        if (!success) {
            return false;
        }

        this.colis.setIdColisDestinataire(colisDestinataire.getIdColisDestinataire());

        // Sauvegarde du colis
        if (colis.getIdColis() == null) {
            success = colisDAO.create(colis, RootController.getConnectedUser());
        } else {
            success = colisDAO.update(colis, RootController.getConnectedUser());
        }

        return success;
    }

    /**
     * Charger les suggestions d'adresse auprès de l'API de Nominatim
     *
     * @param query
     */
    public void onSearchAddress(String query) {
        searchAddresseDebouncer.debounce(() -> {
            // Effacer les suggestions
            view.clearSuggestionsAdresse();

            // Récupérer les suggestions
            List<NominatimAddress> suggestions = nominatimAddressDAO.search(query);

            // Ajouter les suggestions
            for (NominatimAddress suggestion : suggestions) {
                // Vérifier si l'adresse possède au moins une rue
                if (suggestion.getRue() == null) {
                    continue;
                }
                view.addSuggestionAdresse(suggestion.getNominatimId().getOsmType(), suggestion.getNominatimId().getOsmId(), suggestion.getPays(), suggestion.getCodePostal(), suggestion.getCommune(), suggestion.getRue(), suggestion.getNumeroDeRue());
            }
        });
    }

    /**
     * Charger les informations d'une adresse
     *
     * @param osmType
     * @param osmId
     */
    public void onSelectAddress(String osmType, long osmId) {
        // Récupérer l'adresse
        this.nominatimAddress = nominatimAddressDAO.lookup(osmType, osmId);

        // Charger l'adresse dans la vue
        view.fillAdresse(nominatimAddress.getNominatimId().getOsmType(), nominatimAddress.getNominatimId().getOsmId(), nominatimAddress.getPays(), nominatimAddress.getCodePostal(), nominatimAddress.getCommune(), nominatimAddress.getRue(), nominatimAddress.getNumeroDeRue(), null);

        // Récupération des secteurs
        List<Secteur> secteurs = secteurDAO.getAll();

        Map<Secteur, Polygon> secteurDelimitationMap = new HashMap<>();
        // Récupération des delimitations
        for (Secteur secteur : secteurs) {
            Map<SecteurDelimitation, SecteurPoint> secteurDelimitations = new HashMap<>();
            for (SecteurDelimitation secteurDelimitation : secteurDelimitationDAO.getAllByIdSecteur(secteur.getIdSecteur())) {
                // Récupération du point
                SecteurPoint secteurPoint = secteurPointDAO.getById(secteurDelimitation.getIdSecteurPoint());
                secteurDelimitations.put(secteurDelimitation, secteurPoint);
            }
            // Trier la hashmap par clé sur l'ordre
            List<SecteurDelimitation> secteurDelimitationsList = new LinkedList<>(secteurDelimitations.keySet());
            secteurDelimitationsList.sort(new SecteurDelimitationOrdreComparator());

            // Ajouter les points à la liste
            List<ICoordinate> coordinates = new LinkedList<>();
            for (SecteurDelimitation secteurDelimitation : secteurDelimitationsList) {
                // Récupération du point
                SecteurPoint secteurPoint = secteurDelimitations.get(secteurDelimitation);
                coordinates.add(new Coordinate(secteurPoint.getLatitude(), secteurPoint.getLongitude()));
            }

            // Création du polygone
            Polygon polygon = new Polygon();
            for (ICoordinate coordinate : coordinates) {
                Point point1 = view.getPointFromLatLng((Coordinate) coordinate);
                if (point1 == null) {
                    continue;
                }
                polygon.addPoint(point1.x, point1.y);
            }
            secteurDelimitationMap.put(secteur, polygon);
        }

        // Convertir les coordonnées de l'adresse
        Point point = view.getPointFromLatLng(new Coordinate(nominatimAddress.getLat(), nominatimAddress.getLon()));
        if (point != null) {
            // Vérifier si l'adresse est dans un secteur
            Secteur secteur = null;
            for (Map.Entry<Secteur, Polygon> entry : secteurDelimitationMap.entrySet()) {
                if (entry.getValue().contains(point)) {
                    secteur = entry.getKey();
                    break;
                }
            }

            // Si un secteur est trouvé, on le sélectionne
            if (secteur != null) {
                view.fillSecteur(secteur.getIdSecteur());
            }
        }
    }
}
