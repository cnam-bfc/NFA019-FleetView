package net.cnam.fleetview.controller.colis;

import net.cnam.fleetview.model.colis.Colis;

import java.util.List;

public interface ColisChooser {
    void chooseColis(Colis colis);

    List<Integer> getBlacklist();
}
