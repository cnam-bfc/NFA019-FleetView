package net.cnam.fleetview.view.connexion;

import net.cnam.fleetview.App;
import net.cnam.fleetview.controller.connexion.ConnectionController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.utils.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class ConnectionView extends View<ConnectionController> {


    ///Attibuts
    //Panel
    private final JPanel MainPanel;
    private final JPanel TitrePanel;
    private final JPanel LogoPanel;
    private final JPanel FormPanel;
    private final JPanel BouttonPanel;

    //Titre
    private final IconLabel titre;

    //Images
    private final BufferedImage image;

    //Boutton
    private final JButton BoutonValidConnect;

    //TextField

    private final JTextField username = new JTextField();
    private final JPasswordField password = new JPasswordField();

    //Label
    private final JLabel logo;

    private final JLabel labelUsername = new JLabel("Nom d'utilisateur :");
    private final JLabel labelPassword = new JLabel("Mot de passe :");


    public ConnectionView() {
        //Panel
        this.MainPanel = new JPanel();
        this.MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        this.TitrePanel = new JPanel();
        this.LogoPanel = new JPanel();
        this.FormPanel = new JPanel(new SpringLayout());
        this.FormPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        this.BouttonPanel = new JPanel();

        //Titre
        this.titre = new IconLabel("\uf007", "Connexion");
        this.titre.setForeground(Color.BLACK);
        this.titre.setFont(new Font("Arial", Font.BOLD, 30));
        this.titre.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        this.TitrePanel.add(titre);


        //Image
        this.image = App.LOGO_NORMAL;
        this.logo = new JLabel(new ImageIcon(image));
        this.LogoPanel.add(logo);
        this.LogoPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));


        //Label
        labelUsername.setLabelFor(username);
        labelPassword.setLabelFor(password);
        FormPanel.add(labelUsername);
        FormPanel.add(username);
        FormPanel.add(labelPassword);
        FormPanel.add(password);
        SpringUtilities.makeCompactGrid(FormPanel,
                2, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        //Bouton
        this.BoutonValidConnect = new JButton("<html>Connexion</html>");
        this.BoutonValidConnect.setPreferredSize(new Dimension(80, 50));
        this.BoutonValidConnect.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username.getText().isEmpty()) {
                    ConnectionView.this.afficherMessageErreur("Veuillez entrer un nom d'utilisateur");
                }
                if (password.getText().isEmpty()) {
                    ConnectionView.this.afficherMessageErreur("Veuillez entrer un mot de passe");
                }

                controller.onConnection(username.getText(), password.getText());
            }
        });
        this.BouttonPanel.add(BoutonValidConnect);

        this.MainPanel.add(TitrePanel);
        this.MainPanel.add(LogoPanel);
        this.MainPanel.add(FormPanel);
        this.MainPanel.add(BouttonPanel);

        this.add(MainPanel);

    }

    public String getIdent() {
        return this.username.getText();
    }

    public String getPass() {
        return this.password.getText();
    }
}
