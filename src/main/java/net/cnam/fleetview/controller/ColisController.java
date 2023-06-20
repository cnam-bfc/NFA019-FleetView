package net.cnam.fleetview.controller;

import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.colis.Colis;
import net.cnam.fleetview.model.colis.ColisDAO;
import net.cnam.fleetview.model.nominatim.NominatimAddress;
import net.cnam.fleetview.model.nominatim.NominatimAddressDAO;
import net.cnam.fleetview.view.colis.edit.ColisView;

import java.sql.Connection;
import java.util.List;

public class ColisController extends Controller<ColisView> {
    // DAO
    private final ColisDAO colisDAO;
    private final NominatimAddressDAO nominatimAddressDAO;

    // Colis
    private Colis colis;

    public ColisController(ColisView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        Connection connection = BDDConnection.getInstance(connector);
        this.colisDAO = new ColisDAO(connection);
        this.nominatimAddressDAO = new NominatimAddressDAO();
    }

    /**
     * Charger un colis vide dans la vue
     */
    public void loadEmptyColis() {
        // Rendre les champs modifiables
        view.setFieldsEditable(true);
    }

    /**
     * Charger un colis affichable dans la vue
     *
     * @param id
     */
    public void loadViewableColis(int id) {
        // Récupération du colis
        this.colis = colisDAO.getById(id);

        String idForm = String.valueOf(colis.getIdColis());
        String numeroForm = colis.getNumero();
        double poidsForm = colis.getPoids();

        // Chargement des données dans la vue
        view.fill(idForm, numeroForm, poidsForm);

        // Rendre les champs non modifiables
        view.setFieldsEditable(false);
    }

    /**
     * Charger un colis modifiable dans la vue
     *
     * @param id
     */
    public void loadEditableColis(int id) {
        // Récupération du colis
        this.colis = colisDAO.getById(id);

        String idForm = String.valueOf(colis.getIdColis());
        String numeroForm = colis.getNumero();
        double poidsForm = colis.getPoids();

        // Chargement des données dans la vue
        view.fill(idForm, numeroForm, poidsForm);

        // Rendre les champs modifiables
        view.setFieldsEditable(true);
    }

    /**
     * Sauvegarder un colis
     *
     * @param numero Numéro du colis
     * @param poids  Poids du colis
     * @return Si la sauvegarde a réussi
     */
    public boolean saveColis(String numero, double poids) {
        // Si on est en mode ajout d'un colis
        if (colis == null) {
            this.colis = new Colis();
        }

        // Sauvegarde des données dans le colis
        colis.setNumero(numero);
        colis.setPoids(poids);

        // Sauvegarde du colis
        boolean success;
        if (colis.getIdColis() == null) {
            success = colisDAO.create(colis, RootController.getCurrentUser());
        } else {
            success = colisDAO.update(colis, RootController.getCurrentUser());
        }

        return success;
    }

    /**
     * Charger les suggestions d'adresse auprès de l'API de Nominatim
     *
     * @param query
     */
    public void onSearchAddress(String query) {
        // Effacer les suggestions
        view.clearSuggestionsAdresse();

        // Récupérer les suggestions
        List<NominatimAddress> suggestions = nominatimAddressDAO.search(query);

        // Ajouter les suggestions
        for (NominatimAddress suggestion : suggestions) {
            view.addSuggestionAdresse(suggestion.getCodePostal(), suggestion.getCommune(), suggestion.getRue(), suggestion.getNumeroDeRue(), suggestion.getComplement());
        }
    }
}
