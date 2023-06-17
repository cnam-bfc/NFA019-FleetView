package net.cnam.fleetview.view.base;

import net.cnam.fleetview.view.cycle.CreationCycleView;
import net.cnam.fleetview.view.cycle.ListeCycleView;

import javax.swing.*;
import java.awt.*;

public class MainPanelView extends JPanel {
    private final TopMenuPanelView topMenuPanelView = new TopMenuPanelView();
    private final CreationCycleView contentPanelView = new CreationCycleView();

    public MainPanelView() {
        super();

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.add(topMenuPanelView, BorderLayout.NORTH);
        this.add(contentPanelView, BorderLayout.CENTER);
    }

    public TopMenuPanelView getTopMenuPanel() {
        return topMenuPanelView;
    }

    public CreationCycleView getContentPanel() {
        return contentPanelView;
    }
}
