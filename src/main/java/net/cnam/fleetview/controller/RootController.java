package net.cnam.fleetview.controller;

import net.cnam.fleetview.view.AccueilView;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.base.RootPanelView;

import java.util.LinkedList;

public class RootController extends Controller<RootPanelView> {
    private static RootController INSTANCE = null;

    private final LinkedList<View> views = new LinkedList<>();

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
            // On affiche la vue précédente
            this.view.getMainPanel().setContentPanelView(this.views.getLast());
        }
        // Sinon, on affiche accueil
        else {
            this.view.getMainPanel().setContentPanelView(new AccueilView());

            // On cache le bouton de retour
            this.view.getMainPanel().getTopMenuPanel().getTopLeftMenuPanelView().setBackButtonVisible(false);
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
}
