package net.cnam.fleetview;

import net.cnam.fleetview.view.base.RootFrameView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class App {
    public final static String APP_NAME = "FleetView";
    public final static String APP_VERSION = "1.0.0";
    public final static BufferedImage LOGO_NORMAL;
    public final static BufferedImage LOGO_SIMPLIFIED;
    public final static Font TEXT_FONT;
    public final static Font FONTAWESOME_BRANDS_FONT;
    public final static Font FONTAWESOME_REGULAR_FONT;
    public final static Font FONTAWESOME_SOLID_FONT;

    static {
        try {
            LOGO_NORMAL = ImageIO.read(App.class.getResourceAsStream("/assets/img/DistriCycle-Logo normal.png"));
            LOGO_SIMPLIFIED = ImageIO.read(App.class.getResourceAsStream("/assets/img/DistriCycle-Logo simplifié.png"));
            TEXT_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/font/Quicksand-VariableFont_wght.ttf"));
            FONTAWESOME_BRANDS_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/fontawesome/otfs/Font Awesome 6 Brands-Regular-400.otf"));
            FONTAWESOME_REGULAR_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/fontawesome/otfs/Font Awesome 6 Free-Regular-400.otf"));
            FONTAWESOME_SOLID_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/fontawesome/otfs/Font Awesome 6 Free-Solid-900.otf"));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static RootFrameView INSTANCE;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // UI du système d'exploitation
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (INSTANCE == null) {
                    INSTANCE = new RootFrameView();
                }

                INSTANCE.setVisible(true);
            }
        });
    }
}
