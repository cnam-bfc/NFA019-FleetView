package net.cnam.fleetview;

import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.view.base.RootFrameView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

public class App {
    // Textes
    public final static String APP_NAME;
    public final static String APP_VERSION;

    // Images
    public final static BufferedImage LOGO_NORMAL;
    public final static BufferedImage LOGO_SIMPLIFIED;

    // Couleurs
    public final static Color PRIMARY_COLOR = new Color(191, 143, 0);

    // Polices
    // Polices personnalisées
    public final static Font TEXT_FONT;
    public final static Font TITLE_FONT;
    public final static Font LOGO_FONT;
    // Polices brutes
    public final static Font FONTAWESOME_BRANDS_FONT;
    public final static Font FONTAWESOME_REGULAR_FONT;
    public final static Font FONTAWESOME_SOLID_FONT;
    public final static Font ALEF_BOLD_FONT;
    public final static Font ALEF_REGULAR_FONT;
    public final static Font QUICKSAND_BOLD_FONT;
    public final static Font QUICKSAND_LIGHT_FONT;
    public final static Font QUICKSAND_MEDIUM_FONT;
    public final static Font QUICKSAND_REGULAR_FONT;
    public final static Font QUICKSAND_SEMIBOLD_FONT;

    static {
        try {
            // Chargement des propriétés
            Properties properties = new Properties();
            properties.load(App.class.getClassLoader().getResourceAsStream("project.properties"));

            APP_NAME = properties.getProperty("name");
            APP_VERSION = properties.getProperty("version");

            // Chargement des images
            LOGO_NORMAL = ImageIO.read(App.class.getResourceAsStream("/assets/img/logo/DistriCycle-Logo normal.png"));
            LOGO_SIMPLIFIED = ImageIO.read(App.class.getResourceAsStream("/assets/img/logo/DistriCycle-Logo simplifié.png"));

            // Chargement des polices
            FONTAWESOME_BRANDS_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/fontawesome/otfs/Font Awesome 6 Brands-Regular-400.otf"));
            FONTAWESOME_REGULAR_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/fontawesome/otfs/Font Awesome 6 Free-Regular-400.otf"));
            FONTAWESOME_SOLID_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/fontawesome/otfs/Font Awesome 6 Free-Solid-900.otf"));

            ALEF_BOLD_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/font/Alef/Alef-Bold.ttf"));
            ALEF_REGULAR_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/font/Alef/Alef-Regular.ttf"));

            QUICKSAND_BOLD_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/font/Quicksand/Quicksand-Bold.ttf"));
            QUICKSAND_LIGHT_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/font/Quicksand/Quicksand-Light.ttf"));
            QUICKSAND_MEDIUM_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/font/Quicksand/Quicksand-Medium.ttf"));
            QUICKSAND_REGULAR_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/font/Quicksand/Quicksand-Regular.ttf"));
            QUICKSAND_SEMIBOLD_FONT = Font.createFont(Font.TRUETYPE_FONT, App.class.getResourceAsStream("/assets/font/Quicksand/Quicksand-SemiBold.ttf"));

            TEXT_FONT = QUICKSAND_REGULAR_FONT;
            TITLE_FONT = QUICKSAND_BOLD_FONT;

            // Polices personnalisées
            // Police du logo : ALEF_BOLD_FONT, italique
            LOGO_FONT = ALEF_BOLD_FONT.deriveFont(Font.ITALIC, 24f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // UI du système d'exploitation
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                new RootFrameView().setVisible(true);

                RootController.start();
            }
        });
    }
}
