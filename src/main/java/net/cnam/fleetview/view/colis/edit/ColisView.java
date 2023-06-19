package net.cnam.fleetview.view.colis.edit;

import net.cnam.fleetview.controller.ColisController;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.button.IconLabelButton;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.utils.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ColisView extends View<ColisController> {
    // ÉLÉMENTS DE L'INTERFACE
    // Titre
    private final IconLabel titre;
    // Panel du contenu
    private final JPanel contenu;
    // Panel des champs
    private final JPanel champs;
    // Label de l'id
    private final JLabel idLabel;
    // Champ de saisie de l'id
    private final JTextField idField;
    // Label du numéro
    private final JLabel numeroLabel;
    // Champ de saisie du numéro
    private final JTextField numeroField;
    // Label du poids
    private final JLabel poidsLabel;
    // Champ de saisie du poids
    private final JTextField poidsField;
    // Bouton de sauvegarde
    private final IconLabelButton saveButton;

    public ColisView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Bordures
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.titre = new IconLabel("\uF466", "Colis");
        this.contenu = new JPanel();
        this.champs = new JPanel();
        this.idLabel = new JLabel("ID :", JLabel.TRAILING);
        this.idField = new JTextField();
        this.numeroLabel = new JLabel("Numéro :", JLabel.TRAILING);
        this.numeroField = new JTextField(20);
        this.poidsLabel = new JLabel("Poids :", JLabel.TRAILING);
        this.poidsField = new JTextField();
        this.saveButton = new IconLabelButton("\uF0C7", "Sauvegarder");


        // Configuration des éléments de l'interface
        // Panel du contenu
        BoxLayout contenuLayout = new BoxLayout(this.contenu, BoxLayout.Y_AXIS);
        this.contenu.setLayout(contenuLayout);

        // Panel des champs
        SpringLayout champsLayout = new SpringLayout();
        this.champs.setLayout(champsLayout);
        this.champs.setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));
        // Pas d'agrandissement des champs sur la hauteur
        this.champs.setMaximumSize(new Dimension(Short.MAX_VALUE, 0));

        // Label de l'id
        this.idLabel.setLabelFor(this.idField);

        // Champ de saisie de l'id
        this.idField.setEditable(false);

        // Label du numéro
        this.numeroLabel.setLabelFor(this.numeroField);

        // Champ de saisie du numéro

        // Label du poids
        this.poidsLabel.setLabelFor(this.poidsField);

        // Champ de saisie du poids

        // Bouton de sauvegarde
        JPanel saveButtonPanel = new JPanel();
        this.saveButton.setVisible(false);
        this.saveButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vérification des champs
                // Numéro
                if (numeroField.getText().isEmpty()) {
                    afficherMessageErreur("Le numéro du colis est obligatoire");
                    return;
                }
                // Poids
                if (poidsField.getText().isEmpty()) {
                    afficherMessageErreur("Le poids du colis est obligatoire");
                    return;
                }
                double poids;
                try {
                    poids = Double.parseDouble(poidsField.getText());
                    if (poids <= 0) {
                        afficherMessageErreur("Le poids du colis doit être supérieur à 0");
                        return;
                    }
                } catch (NumberFormatException exception) {
                    afficherMessageErreur("Le poids du colis doit être un nombre");
                    return;
                }

                boolean success = controller.saveColis(numeroField.getText(), poids);
                if (!success) {
                    afficherMessageErreur("Impossible de sauvegarder le colis");
                    return;
                }

                RootController.close(ColisView.this);
            }
        });
        saveButtonPanel.add(this.saveButton);

        // Ajout des éléments de l'interface
        this.add(this.titre, BorderLayout.NORTH);
        this.champs.add(this.idLabel);
        this.champs.add(this.idField);
        this.champs.add(this.numeroLabel);
        this.champs.add(this.numeroField);
        this.champs.add(this.poidsLabel);
        this.champs.add(this.poidsField);
        // Lay out the panel
        SpringUtilities.makeCompactGrid(this.champs,
                3, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        this.contenu.add(this.champs);
        this.add(this.contenu, BorderLayout.CENTER);
        this.add(saveButtonPanel, BorderLayout.SOUTH);
    }

    /**
     * Rendre les champs modifiables ou non
     *
     * @param editable
     */
    public void setFieldsEditable(boolean editable) {
        this.numeroField.setEditable(editable);
        this.poidsField.setEditable(editable);
        // Rendre le bouton de sauvegarde visible ou non
        this.saveButton.setVisible(editable);
    }

    /**
     * Remplir les champs de la vue
     *
     * @param id
     * @param numero
     * @param poids
     */
    public void fill(String id, String numero, double poids) {
        // Titre
        this.titre.getTexteLabel().setText("Colis n°" + numero);

        // Champs
        this.idField.setText(id);
        this.numeroField.setText(numero);
        this.poidsField.setText(String.valueOf(poids));
    }
}
