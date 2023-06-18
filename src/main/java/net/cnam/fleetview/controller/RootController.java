package net.cnam.fleetview.controller;

import net.cnam.fleetview.view.AccueilView;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.base.RootFrameView;

import java.util.LinkedList;

public class RootController extends Controller {
    private static RootController INSTANCE = null;

    // Vue
    private final RootFrameView view;

    private final LinkedList<View> views = new LinkedList<>();

    public RootController(RootFrameView view) {
        INSTANCE = this;

        this.view = view;
    }

    @Override
    public void onViewLoaded() {

    }

    public void openView(View view) {
        // Si une vue est déjà ouverte, on vérifie que l'on peut en ouvrir une autre
        if (!this.views.isEmpty() && !this.views.getLast().canOpenNewView()) {
            return;
        }

        // Ok, on peut ouvrir la vue
        this.views.add(view);

        // On affiche la vue
        this.view.getPanel().getMainPanel().setContentPanelView(view);

        // On affiche le bouton de retour
        this.view.getPanel().getMainPanel().getTopMenuPanel().getTopLeftMenuPanelView().setBackButtonVisible(true);
    }

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
            this.view.getPanel().getMainPanel().setContentPanelView(this.views.getLast());
        }
        // Sinon, on affiche accueil
        else {
            this.view.getPanel().getMainPanel().setContentPanelView(new AccueilView());

            // On cache le bouton de retour
            this.view.getPanel().getMainPanel().getTopMenuPanel().getTopLeftMenuPanelView().setBackButtonVisible(false);
        }

        return true;
    }

    public boolean closeLastView() {
        if (!this.views.isEmpty()) {
            return this.closeView(this.views.getLast());
        }
        return false;
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
}
