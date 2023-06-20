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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ColisView extends View<ColisController> {
    // ÉLÉMENTS DE L'INTERFACE
    // Titre
    private final IconLabel titre;
    // Panel du contenu
    private final JPanel contenu;

    // Panel des champs du colis
    private final JPanel champsColis;
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

    // Titre de l'adresse
    private final IconLabel adresseTitle;
    // Panel de l'adresse
    private final JPanel champsAdresse;
    // Label de recherche de l'adresse
    private final JLabel searchAdresseLabel;
    // Champ de recherche de l'adresse
    private final JTextField searchAdresseField;
    // Label de la liste des adresses suggérées
    private final JLabel adressesSuggereesLabel;
    // Liste des adresses suggérées
    private final JList<String> adressesSuggereesList;
    // Label du code postal
    private final JLabel codePostalLabel;
    // Champ de saisie du code postal
    private final JTextField codePostalField;
    // Label de la commune
    private final JLabel communeLabel;
    // Champ de saisie de la commune
    private final JTextField communeField;
    // Label du secteur
    private final JLabel secteurLabel;
    // Liste des secteurs
    private final JComboBox<String> secteurList;
    // Label de la rue
    private final JLabel rueLabel;
    // Champ de saisie de la rue
    private final JTextField rueField;
    // Label du numéro de rue
    private final JLabel numeroRueLabel;
    // Champ de saisie du numéro de rue
    private final JTextField numeroRueField;
    // Label du complément d'adresse
    private final JLabel complementLabel;
    // Champ de saisie du complément d'adresse
    private final JTextField complementField;

    // Titre du destinataire
    private final IconLabel destinataireTitle;
    // Panel du destinataire
    private final JPanel champsDestinataire;
    // Label de recherche du destinataire
    private final JLabel searchDestinataireLabel;
    // Champ de recherche du destinataire
    private final JTextField searchDestinataireField;
    // Label de la liste des destinataires suggérés
    private final JLabel destinatairesSuggereesLabel;
    // Liste des destinataires suggérés
    private final JList<String> destinatairesSuggereesList;
    // Label du nom
    private final JLabel nomLabel;
    // Champ de saisie du nom
    private final JTextField nomField;
    // Label du prénom
    private final JLabel prenomLabel;
    // Champ de saisie du prénom
    private final JTextField prenomField;

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
        this.champsColis = new JPanel();
        this.idLabel = new JLabel("ID :", JLabel.TRAILING);
        this.idField = new JTextField();
        this.numeroLabel = new JLabel("Numéro :", JLabel.TRAILING);
        this.numeroField = new JTextField(20);
        this.poidsLabel = new JLabel("Poids :", JLabel.TRAILING);
        this.poidsField = new JTextField();
        this.adresseTitle = new IconLabel("\uF59F", "Adresse");
        this.champsAdresse = new JPanel();
        this.searchAdresseLabel = new JLabel("Rechercher une adresse :", JLabel.TRAILING);
        this.searchAdresseField = new JTextField();
        this.adressesSuggereesLabel = new JLabel("Adresses suggérées :", JLabel.TRAILING);
        this.adressesSuggereesList = new JList<>();
        this.codePostalLabel = new JLabel("Code postal :", JLabel.TRAILING);
        this.codePostalField = new JTextField();
        this.communeLabel = new JLabel("Commune :", JLabel.TRAILING);
        this.communeField = new JTextField();
        this.secteurLabel = new JLabel("Secteur :", JLabel.TRAILING);
        this.secteurList = new JComboBox<>();
        this.rueLabel = new JLabel("Rue :", JLabel.TRAILING);
        this.rueField = new JTextField();
        this.numeroRueLabel = new JLabel("Numéro de rue :", JLabel.TRAILING);
        this.numeroRueField = new JTextField();
        this.complementLabel = new JLabel("Complément d'adresse :", JLabel.TRAILING);
        this.complementField = new JTextField();
        this.destinataireTitle = new IconLabel("\uF007", "Destinataire");
        this.champsDestinataire = new JPanel();
        this.searchDestinataireLabel = new JLabel("Rechercher un destinataire :", JLabel.TRAILING);
        this.searchDestinataireField = new JTextField();
        this.destinatairesSuggereesLabel = new JLabel("Destinataires suggérés :", JLabel.TRAILING);
        this.destinatairesSuggereesList = new JList<>();
        this.nomLabel = new JLabel("Nom :", JLabel.TRAILING);
        this.nomField = new JTextField();
        this.prenomLabel = new JLabel("Prénom :", JLabel.TRAILING);
        this.prenomField = new JTextField();
        this.saveButton = new IconLabelButton("\uF0C7", "Sauvegarder");


        // Configuration des éléments de l'interface
        // Panel du contenu
        JScrollPane contenuScrollPanel = new JScrollPane(this.contenu);
        contenuScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        BoxLayout contenuLayout = new BoxLayout(this.contenu, BoxLayout.Y_AXIS);
        this.contenu.setLayout(contenuLayout);

        // Panel des champs
        SpringLayout champsLayout = new SpringLayout();
        this.champsColis.setLayout(champsLayout);
        this.champsColis.setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));
        // Pas d'agrandissement des champs sur la hauteur
        this.champsColis.setMaximumSize(new Dimension(Short.MAX_VALUE, 0));

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

        // Panel des champs de l'adresse
        SpringLayout champsAdresseLayout = new SpringLayout();
        this.champsAdresse.setLayout(champsAdresseLayout);
        this.champsAdresse.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        // Pas d'agrandissement des champs sur la hauteur
        this.champsAdresse.setMaximumSize(new Dimension(Short.MAX_VALUE, 0));

        // Label de recherche de l'adresse
        this.searchAdresseLabel.setLabelFor(this.searchAdresseField);

        // Champ de recherche de l'adresse
        this.searchAdresseField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                // Recherche des adresses correspondantes
                controller.onSearchAddress(searchAdresseField.getText());
            }
        });

        // Label des adresses suggérées
        this.adressesSuggereesLabel.setLabelFor(this.adressesSuggereesList);

        // Liste des adresses suggérées
        this.adressesSuggereesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Label du code postal
        this.codePostalLabel.setLabelFor(this.codePostalField);

        // Champ de saisie du code postal
        this.codePostalField.setEditable(false);

        // Label de la commune
        this.communeLabel.setLabelFor(this.communeField);

        // Champ de saisie de la commune
        this.communeField.setEditable(false);

        // Label du secteur
        this.secteurLabel.setLabelFor(this.secteurList);

        // Liste des secteurs
        this.secteurList.setEnabled(false);

        // Label de la rue
        this.rueLabel.setLabelFor(this.rueField);

        // Champ de saisie de la rue
        this.rueField.setEditable(false);

        // Label du numéro de rue
        this.numeroRueLabel.setLabelFor(this.numeroRueField);

        // Champ de saisie du numéro de rue
        this.numeroRueField.setEditable(false);

        // Label du complément d'adresse
        this.complementLabel.setLabelFor(this.complementField);

        // Champ de saisie du complément d'adresse
        this.complementField.setEditable(false);

        // Panel des champs du destinataire
        SpringLayout champsDestinataireLayout = new SpringLayout();
        this.champsDestinataire.setLayout(champsDestinataireLayout);
        this.champsDestinataire.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        // Pas d'agrandissement des champs sur la hauteur
        this.champsDestinataire.setMaximumSize(new Dimension(Short.MAX_VALUE, 0));

        // Label de recherche du destinataire
        this.searchDestinataireLabel.setLabelFor(this.searchDestinataireField);

        // Champ de recherche du destinataire
        this.searchDestinataireField.setEditable(false);

        // Label des destinataires suggérés
        this.destinatairesSuggereesLabel.setLabelFor(this.destinatairesSuggereesList);

        // Liste des destinataires suggérés
        this.destinatairesSuggereesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.destinatairesSuggereesList.setEnabled(false);

        // Label du nom
        this.nomLabel.setLabelFor(this.nomField);

        // Champ de saisie du nom
        this.nomField.setEditable(false);

        // Label du prénom
        this.prenomLabel.setLabelFor(this.prenomField);

        // Champ de saisie du prénom
        this.prenomField.setEditable(false);

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
        this.champsColis.add(this.idLabel);
        this.champsColis.add(this.idField);
        this.champsColis.add(this.numeroLabel);
        this.champsColis.add(this.numeroField);
        this.champsColis.add(this.poidsLabel);
        this.champsColis.add(this.poidsField);
        // Lay out the panel
        SpringUtilities.makeCompactGrid(this.champsColis,
                3, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        this.contenu.add(this.champsColis);
        this.contenu.add(this.adresseTitle);
        this.champsAdresse.add(this.searchAdresseLabel);
        this.champsAdresse.add(this.searchAdresseField);
        this.champsAdresse.add(this.adressesSuggereesLabel);
        this.champsAdresse.add(this.adressesSuggereesList);
        this.champsAdresse.add(this.codePostalLabel);
        this.champsAdresse.add(this.codePostalField);
        this.champsAdresse.add(this.communeLabel);
        this.champsAdresse.add(this.communeField);
        this.champsAdresse.add(this.secteurLabel);
        this.champsAdresse.add(this.secteurList);
        this.champsAdresse.add(this.rueLabel);
        this.champsAdresse.add(this.rueField);
        this.champsAdresse.add(this.numeroRueLabel);
        this.champsAdresse.add(this.numeroRueField);
        this.champsAdresse.add(this.complementLabel);
        this.champsAdresse.add(this.complementField);
        // Lay out the panel
        SpringUtilities.makeCompactGrid(this.champsAdresse,
                8, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        this.contenu.add(this.champsAdresse);
        this.contenu.add(this.destinataireTitle);
        this.champsDestinataire.add(this.searchDestinataireLabel);
        this.champsDestinataire.add(this.searchDestinataireField);
        this.champsDestinataire.add(this.destinatairesSuggereesLabel);
        this.champsDestinataire.add(this.destinatairesSuggereesList);
        this.champsDestinataire.add(this.nomLabel);
        this.champsDestinataire.add(this.nomField);
        this.champsDestinataire.add(this.prenomLabel);
        this.champsDestinataire.add(this.prenomField);
        // Lay out the panel
        SpringUtilities.makeCompactGrid(this.champsDestinataire,
                4, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        this.contenu.add(this.champsDestinataire);
        this.contenu.add(Box.createVerticalGlue());
        this.add(contenuScrollPanel, BorderLayout.CENTER);
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

    /**
     * Vide les suggestions d'adresse
     */
    public void clearSuggestionsAdresse() {
        this.adressesSuggereesList.removeAll();
    }

    /**
     * Ajoute une suggestion d'adresse
     *
     * @param codePostal
     * @param commune
     * @param rue
     * @param numero
     * @param complement
     */
    public void addSuggestionAdresse(String codePostal, String commune, String rue, String numero, String complement) {
        this.adressesSuggereesList.add(new JLabel(complement + ", " + numero + " " + rue + ", " + commune + " " + codePostal));
    }
}
