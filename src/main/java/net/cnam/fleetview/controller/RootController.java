package net.cnam.fleetview.controller;

import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.base.RootFrameView;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class RootController {
    private static RootController INSTANCE = null;

    // Vue
    private final RootFrameView view;

    private final List<View> views = new LinkedList<>();

    public RootController(RootFrameView view) {
        INSTANCE = this;

        this.view = view;
    }

    public void openView(View view) {
        // Si une vue est déjà ouverte, on vérifie que l'on peut en ouvrir une autre
        if (!this.views.isEmpty() && !this.views.get(this.views.size() - 1).canOpenNewView()) {
            return;
        }

        // Ok, on peut ouvrir la vue
        this.views.add(view);

        // On affiche la vue
        this.view.getPanel().getMainPanel().setContentPanelView(view);
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
            this.view.getPanel().getMainPanel().setContentPanelView(this.views.get(this.views.size() - 1));
        }
        // Sinon, on affiche rien
        else {
            this.view.getPanel().getMainPanel().setContentPanelView(new JPanel());
        }

        return true;
    }

    public boolean closeLastView() {
        if (!this.views.isEmpty()) {
            return this.closeView(this.views.get(this.views.size() - 1));
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
