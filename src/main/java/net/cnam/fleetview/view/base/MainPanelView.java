package net.cnam.fleetview.view.base;

import javax.swing.*;
import java.awt.*;

import net.cnam.fleetview.view.documents.FicheAccidentView;
import net.cnam.fleetview.view.paramettre.ParametrageBddView;

public class MainPanelView extends JPanel {
    private final TopMenuPanelView topMenuPanelView = new TopMenuPanelView();
    private final ContentPanelView contentPanelView = new ContentPanelView();

    public MainPanelView() {
        super();

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.add(topMenuPanelView, BorderLayout.NORTH);
        this.add(contentPanelView, BorderLayout.CENTER);
        //this.add(new ParametrageBddView() , BorderLayout.CENTER);
        this.add(new ParametrageBddView() , BorderLayout.CENTER);
    }

    public TopMenuPanelView getTopMenuPanel() {
        return topMenuPanelView;
    }

    public ContentPanelView getContentPanel() {
        return contentPanelView;
    }
}
