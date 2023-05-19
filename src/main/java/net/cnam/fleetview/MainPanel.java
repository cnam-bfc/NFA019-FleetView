package net.cnam.fleetview;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final TopMenuPanel topMenuPanel = new TopMenuPanel();
    private final ContentPanel contentPanel = new ContentPanel();

    public MainPanel() {
        super();

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.add(topMenuPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
    }

    public TopMenuPanel getTopMenuPanel() {
        return topMenuPanel;
    }

    public ContentPanel getContentPanel() {
        return contentPanel;
    }
}
