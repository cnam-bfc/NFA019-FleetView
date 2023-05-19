package net.cnam.fleetview;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class App {
    public final static String APP_NAME = "FleetView";
    public final static String APP_VERSION = "1.0.0";
    public final static BufferedImage APP_LOGO;
    public final static Font TEXT_FONT;
    public final static Font FONTAWESOME_BRANDS_FONT;
    public final static Font FONTAWESOME_REGULAR_FONT;
    public final static Font FONTAWESOME_SOLID_FONT;

    static {
        try {
            APP_LOGO = ImageIO.read(App.class.getResourceAsStream("/assets/img/logo.png"));
            TEXT_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/font/Quicksand-VariableFont_wght.ttf"));
            FONTAWESOME_BRANDS_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/fontawesome/otfs/Font Awesome 6 Brands-Regular-400.otf"));
            FONTAWESOME_REGULAR_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/fontawesome/otfs/Font Awesome 6 Free-Regular-400.otf"));
            FONTAWESOME_SOLID_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/fontawesome/otfs/Font Awesome 6 Free-Solid-900.otf"));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static RootFrame INSTANCE;

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
                    INSTANCE = new RootFrame();
                }

                INSTANCE.setVisible(true);
            }
        });
    }
}
