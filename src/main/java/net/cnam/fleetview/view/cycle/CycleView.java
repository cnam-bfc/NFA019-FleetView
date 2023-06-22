package net.cnam.fleetview.view.cycle;

import com.github.lgooddatepicker.components.DatePicker;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.controller.cycle.CycleController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.button.IconLabelButton;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.course.edit.CourseView;
import net.cnam.fleetview.view.utils.ButtonColumn;
import net.cnam.fleetview.view.utils.SpringUtilities;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class CycleView extends View<CycleController> {
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
    // Label du nom
    private final JLabel nomLabel;
    // Champ de saisie du nom
    private final JTextField nomField;
    // Label de la date
    private final JLabel dateLabel;
    // Champ de saisie de la date
    private final DatePicker dateField;
    // Titre des colis
    private final IconLabel colisTitre;
    // Bouton de sauvegarde
    private final IconLabelButton saveButton;

    public CourseView() {
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Bordures
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.titre = new IconLabel("\uF0D1", "Course");
        this.contenu = new JPanel();
        this.champs = new JPanel();
        this.idLabel = new JLabel("ID :", JLabel.TRAILING);
        this.idField = new JTextField();
        this.nomLabel = new JLabel("Nom :", JLabel.TRAILING);
        this.nomField = new JTextField();
        this.dateLabel = new JLabel("Date :", JLabel.TRAILING);
        this.dateField = new DatePicker();
        this.colisTitre = new IconLabel("\uF466", "Colis");
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

        // Label du nom
        this.nomLabel.setLabelFor(this.nomField);

        // Champ de saisie du nom

        // Label de la date
        this.dateLabel.setLabelFor(this.dateField);

        // Champ de saisie de la date

        // Titre des colis
        this.colisTitre.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Bouton de sauvegarde
        JPanel saveButtonPanel = new JPanel();
        this.saveButton.setVisible(false);
        this.saveButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vérification des champs
                // Nom
                if (nomField.getText().isEmpty()) {
                    afficherMessageErreur("Le nom de la course est obligatoire");
                    return;
                }
                // Date
                if (dateField.getDate() == null) {
                    afficherMessageErreur("La date de la course est obligatoire");
                    return;
                }

                RootController.close(CycleView.this);
            }
        });
        saveButtonPanel.add(this.saveButton);

        // Ajout des éléments de l'interface
        this.add(this.titre, BorderLayout.NORTH);
        this.champs.add(this.idLabel);
        this.champs.add(this.idField);
        this.champs.add(this.nomLabel);
        this.champs.add(this.nomField);
        this.champs.add(this.dateLabel);
        this.champs.add(this.dateField);
        // Lay out the panel
        SpringUtilities.makeCompactGrid(this.champs,
                3, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        this.contenu.add(this.champs);
        this.contenu.add(this.colisTitre);
        this.add(this.contenu, BorderLayout.CENTER);
        this.add(saveButtonPanel, BorderLayout.SOUTH);
    }

    /**
     * Rendre les champs modifiables ou non
     *
     * @param editable
     */
    public void setFieldsEditable(boolean editable) {
        this.nomField.setEditable(editable);
        this.dateField.setEnabled(editable);
        // Rendre le bouton de sauvegarde visible ou non
        this.saveButton.setVisible(editable);
    }

    /**
     * Remplir les champs de la vue
     *
     * @param idCycle
     * @param nom
     * @param chargeMax
     * @param dateAchat
     * @param miseEnService
     * @param marque
     * @param fournisseur
     */
    public void fill(String idCycle,String nom,String numSerie,String chargeMax,LocalDate dateAchat,String miseEnService,String marque,String fournisseur) {
        // Titre
        this.titre.getTexteLabel().setText("Cycle : " + nom);

        // Champs
        this.idField.setText(id);
        this.nomField.setText(nom);
        this.dateField.setDate(date);
    }
}