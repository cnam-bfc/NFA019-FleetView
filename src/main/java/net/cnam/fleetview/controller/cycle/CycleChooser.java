package net.cnam.fleetview.controller.cycle;

import net.cnam.fleetview.model.cycle.Cycle;

import java.util.List;

public interface CycleChooser {
    void chooseCycle(Cycle cycle);

    List<Integer> getBlacklist();
}
