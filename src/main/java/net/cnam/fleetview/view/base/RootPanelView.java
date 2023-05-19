package net.cnam.fleetview.view.base;

import javax.swing.*;
import java.awt.*;

public class RootPanelView extends JPanel {
    private final LeftMenuPanelView leftMenuPanelView = new LeftMenuPanelView();
    private final MainPanelView mainPanelView = new MainPanelView();

    public RootPanelView() {
        super();

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.add(leftMenuPanelView, BorderLayout.WEST);
        this.add(mainPanelView, BorderLayout.CENTER);
    }

    public LeftMenuPanelView getRightMenuPanel() {
        return leftMenuPanelView;
    }

    public MainPanelView getMainPanel() {
        return mainPanelView;
    }
}
