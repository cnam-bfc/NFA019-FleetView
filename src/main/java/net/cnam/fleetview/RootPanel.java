package net.cnam.fleetview;

import javax.swing.*;
import java.awt.*;

public class RootPanel extends JPanel {
    private final LeftMenuPanel leftMenuPanel = new LeftMenuPanel();
    private final MainPanel mainPanel = new MainPanel();

    public RootPanel() {
        super();

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.add(leftMenuPanel, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    public LeftMenuPanel getRightMenuPanel() {
        return leftMenuPanel;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }
}
