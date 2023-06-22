package net.cnam.fleetview.controller;

import net.cnam.fleetview.controller.connexion.ConnectionController;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.Connector;
import net.cnam.fleetview.database.CustomConnectorGenerator;
import net.cnam.fleetview.model.basededonnees.BaseDeDonnees;
import net.cnam.fleetview.model.utilisateur.Utilisateur;
import net.cnam.fleetview.view.ParametrageBddView;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.accueil.AccueilView;
import net.cnam.fleetview.view.base.RootPanelView;
import net.cnam.fleetview.view.connexion.ConnectionView;

import java.util.LinkedList;

public class RootController extends Controller<RootPanelView> {
    private static RootController INSTANCE = null;

    private final LinkedList<View> views = new LinkedList<>();

    private boolean initialized = false;
    private Utilisateur connectedUser = null;

    public RootController(RootPanelView view) {
        super(view);

        INSTANCE = this;
    }

    /**
     * Méthode permettant d'ouvrir une vue
     *
     * @param view Vue à ouvrir
     */
    public void openView(View view) {
        // Si une vue est déjà ouverte, on vérifie que l'on peut en ouvrir une autre
        if (!this.views.isEmpty() && !this.views.getLast().canOpenNewView()) {
            return;
        }

        // Ok, on peut ouvrir la vue
        this.views.add(view);

        // On affiche la vue
        this.view.getMainPanel().setContentPanelView(view);

        // On affiche le bouton de retour
        this.view.getMainPanel().getTopMenuPanel().getTopLeftMenuPanelView().setBackButtonVisible(true);

        // On notifie la vue qu'elle a été affichée
        view.onDisplayed();
    }

    /**
     * Méthode permettant de fermer une vue
     *
     * @param view Vue à fermer
     * @return true si la vue a été fermée, false sinon
     */
    public boolean closeView(View view) {
        // On vérifie que l'on peut fermer la vue
        if (!view.canCloseCurrentView()) {
            return false;
        }

        // Ok, on peut fermer la vue
        this.views.remove(view);

        // On affiche la vue précédente
        if (!this.views.isEmpty()) {
            // On récupère la vue précédente
            View previousView = this.views.getLast();

            // On affiche la vue précédente
            this.view.getMainPanel().setContentPanelView(previousView);

            // On notifie la vue qu'elle a été réaffichée
            previousView.onDisplayed();
        }
        // Sinon, on affiche accueil
        else {
            // On crée la vue accueil
            AccueilView accueilView = new AccueilView();
            // On crée le controlleur associé
            AccueilController accueilController = new AccueilController(accueilView);
            accueilView.setController(accueilController);

            // On affiche la vue accueil
            this.view.getMainPanel().setContentPanelView(accueilView);

            // On cache le bouton de retour
            this.view.getMainPanel().getTopMenuPanel().getTopLeftMenuPanelView().setBackButtonVisible(false);

            // On notifie la vue qu'elle a été affichée
            accueilView.onDisplayed();
        }

        return true;
    }

    /**
     * Méthode permettant de fermer la dernière vue
     *
     * @return true si la vue a été fermée, false sinon
     */
    public boolean closeLastView() {
        if (!this.views.isEmpty()) {
            return this.closeView(this.views.getLast());
        }
        return false;
    }

    /**
     * Méthode permettant de fermer toutes les vues
     *
     * @return true si les vues ont été fermées, false sinon
     */
    public boolean closeAllViews() {
        boolean result = true;
        while (!this.views.isEmpty()) {
            result = result && this.closeLastView();
        }
        return result;
    }

    /**
     * Méthode permettant de démarrer l'application
     */
    public static void start() {
        boolean dbOK = true;
        // On récupère les identifiants de connexion à la base de données
        BaseDeDonnees db = ParametrageBddController.getDatabase();
        if (db == null) {
            dbOK = false;
        }
        if (!dbOK) {
            // On ouvre la vue de paramétrage de la base de données
            // Création de la vue
            ParametrageBddView parametrageBddView = new ParametrageBddView();
            // Création du contrôleur
            ParametrageBddController parametrageBddController = new ParametrageBddController(parametrageBddView);
            parametrageBddView.setController(parametrageBddController);
            // Ouverture de la vue
            getRootPanelView().getMainPanel().setContentPanelView(parametrageBddView);
            return;
        }

        // Reconnection à la base de données
        CustomConnectorGenerator generator = new CustomConnectorGenerator();
        Connector connector = generator.getConnector();
        BDDConnection.killInstance(connector);

        if (INSTANCE.connectedUser == null) {
            // On affiche la vue de connexion
            // Création de la vue de connexion
            ConnectionView connectionView = new ConnectionView();
            // Création du contrôleur de la vue
            ConnectionController connectionController = new ConnectionController(connectionView);
            connectionView.setController(connectionController);
            // Création de la vue de connexion
            getRootPanelView().getMainPanel().setContentPanelView(connectionView);
            return;
        }

        // Mis à jour du menu de gauche
        if (INSTANCE.connectedUser.getIdRole() == 1) {
            getRootPanelView().getRightMenuPanel().getFootPanel().showAdmin();
            getRootPanelView().getRightMenuPanel().getContentPanel().showAdmin();
        }
        if (INSTANCE.connectedUser.getIdRole() == 2) {
            getRootPanelView().getRightMenuPanel().getFootPanel().showGestionnaireFlotte();
            getRootPanelView().getRightMenuPanel().getContentPanel().showGestionnaireFlotte();
        }
        if (INSTANCE.connectedUser.getIdRole() == 3) {
            getRootPanelView().getRightMenuPanel().getFootPanel().showCoursier();
            getRootPanelView().getRightMenuPanel().getContentPanel().showCoursier();
        }

        // On crée la vue accueil
        AccueilView accueilView = new AccueilView();
        // On crée le controlleur associé
        AccueilController accueilController = new AccueilController(accueilView);
        accueilView.setController(accueilController);
        getRootPanelView().getMainPanel().setContentPanelView(accueilView);
    }

    /**
     * Méthode permettant de savoir si l'application est initialisée
     *
     * @return true si l'application est initialisée, false sinon
     */
    public static boolean isInitialized() {
        return INSTANCE.initialized;
    }

    /**
     * Méthode permettant de définir que l'application est initialisée
     *
     * @param initialized true si l'application est initialisée, false sinon
     */
    public static void setInitialized(boolean initialized) {
        INSTANCE.initialized = initialized;
    }

    public static void open(View view) {
        INSTANCE.openView(view);
    }

    public static void close(View view) {
        INSTANCE.closeView(view);
    }

    public static void closeLast() {
        INSTANCE.closeLastView();
    }

    public static void closeAll() {
        INSTANCE.closeAllViews();
    }

    public static Utilisateur getConnectedUser() {
        return INSTANCE.connectedUser;
    }

    public static void setConnectedUser(Utilisateur utilisateur) {
        INSTANCE.connectedUser = utilisateur;
    }

    public static RootPanelView getRootPanelView() {
        return INSTANCE.view;
    }
}
