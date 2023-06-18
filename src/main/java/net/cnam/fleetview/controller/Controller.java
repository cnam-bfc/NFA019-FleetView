package net.cnam.fleetview.controller;

import net.cnam.fleetview.view.View;

public abstract class Controller<V extends View> {
    protected final V view;

    public Controller(V view) {
        this.view = view;
    }
}
