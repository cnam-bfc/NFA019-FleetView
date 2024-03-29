package net.cnam.fleetview.view.course.edit;

import com.github.lgooddatepicker.components.DatePicker;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.controller.courses.CourseController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.button.IconLabelButton;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.utils.ButtonColumn;
import net.cnam.fleetview.view.utils.SpringUtilities;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class CourseView extends View<CourseController> {
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
    // Tableau des colis
    private final JTable colisTable;
    // Bouton d'ajout d'un colis
    private final IconLabelButton ajouterColis;
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
        this.colisTable = new JTable();
        this.ajouterColis = new IconLabelButton("\uF067", "Ajouter");
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

        // Tableau des colis
        DefaultTableModel colisTableModel = new DefaultTableModel();
        DefaultTableCellRenderer colisTableCellRenderer = new DefaultTableCellRenderer();
        JScrollPane colisTableScrollPane = new JScrollPane(colisTable);

        colisTableModel.addColumn("ID");
        colisTableModel.addColumn("Numero");
        colisTableModel.addColumn("Poids");
        colisTableModel.addColumn("Adresse");
        colisTableModel.addColumn("Destinataire");
        colisTableModel.addColumn("Voir");
        colisTableModel.addColumn("Monter");
        colisTableModel.addColumn("Descendre");
        colisTableModel.addColumn("Supprimer");

        colisTable.setModel(colisTableModel);

        colisTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Id
        TableColumn idColumn = colisTable.getColumnModel().getColumn(0);
        idColumn.setMinWidth(60);
        idColumn.setMaxWidth(100);

        // Numéro
        TableColumn numeroColumn = colisTable.getColumnModel().getColumn(1);
        numeroColumn.setMinWidth(60);
        numeroColumn.setMaxWidth(100);

        // Poids
        TableColumn poidsColumn = colisTable.getColumnModel().getColumn(2);
        poidsColumn.setMinWidth(60);
        poidsColumn.setMaxWidth(120);

        // Destinataire
        TableColumn destinataireColumn = colisTable.getColumnModel().getColumn(4);
        destinataireColumn.setMinWidth(150);
        destinataireColumn.setMaxWidth(300);

        // Action voir
        TableColumn voirColumn = colisTable.getColumnModel().getColumn(5);
        voirColumn.setMinWidth(60);
        voirColumn.setMaxWidth(60);
        ButtonColumn voirButtonColumn = new ButtonColumn(colisTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onVoirColis(Integer.parseInt(colisTableModel.getValueAt(colisTable.getSelectedRow(), 0).toString()));
            }
        }, 5);

        // Action monter
        TableColumn monterColumn = colisTable.getColumnModel().getColumn(6);
        monterColumn.setMinWidth(60);
        monterColumn.setMaxWidth(60);
        ButtonColumn monterButtonColumn = new ButtonColumn(colisTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onMonterColis(Integer.parseInt(colisTableModel.getValueAt(colisTable.getSelectedRow(), 0).toString()));
            }
        }, 6);

        // Action descendre
        TableColumn descendreColumn = colisTable.getColumnModel().getColumn(7);
        descendreColumn.setMinWidth(60);
        descendreColumn.setMaxWidth(60);
        ButtonColumn descendreButtonColumn = new ButtonColumn(colisTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onDescendreColis(Integer.parseInt(colisTableModel.getValueAt(colisTable.getSelectedRow(), 0).toString()));
            }
        }, 7);

        // Action supprimer
        TableColumn supprimerColumn = colisTable.getColumnModel().getColumn(8);
        supprimerColumn.setMinWidth(60);
        supprimerColumn.setMaxWidth(60);
        ButtonColumn supprimerButtonColumn = new ButtonColumn(colisTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSupprimerColis(Integer.parseInt(colisTableModel.getValueAt(colisTable.getSelectedRow(), 0).toString()));
            }
        }, 8);

        colisTable.setDefaultRenderer(Object.class, colisTableCellRenderer);
        colisTable.setRowHeight(30);

        // Tableau non editable
        colisTable.setDefaultEditor(Object.class, null);

        // Désactiver le dragging des colonnes
        colisTable.getTableHeader().setReorderingAllowed(false);

        // Bouton d'ajout de colis
        this.ajouterColis.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.ajouterColis.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onAjouterColis();
            }
        });

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
                // Colis
                if (colisTableModel.getRowCount() == 0) {
                    afficherMessageErreur("La course doit contenir au moins un colis");
                    return;
                }

                boolean success = controller.saveCourse(nomField.getText(), dateField.getDate());
                if (!success) {
                    afficherMessageErreur("Impossible de sauvegarder la course");
                    return;
                }

                RootController.close(CourseView.this);
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
        this.contenu.add(colisTableScrollPane);
        this.contenu.add(this.ajouterColis);
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
        // Désactiver les boutons du tableau des colis
        ButtonColumn monterButtonColumn = (ButtonColumn) this.colisTable.getColumnModel().getColumn(6).getCellEditor();
        monterButtonColumn.setButtonEnabled(editable);
        ButtonColumn descendreButtonColumn = (ButtonColumn) this.colisTable.getColumnModel().getColumn(7).getCellEditor();
        descendreButtonColumn.setButtonEnabled(editable);
        ButtonColumn supprimerButtonColumn = (ButtonColumn) this.colisTable.getColumnModel().getColumn(8).getCellEditor();
        supprimerButtonColumn.setButtonEnabled(editable);
        // Rendre le bouton d'ajout de colis visible ou non
        this.ajouterColis.setVisible(editable);
        // Rendre le bouton de sauvegarde visible ou non
        this.saveButton.setVisible(editable);
    }

    /**
     * Remplir les champs de la vue
     *
     * @param id
     * @param nom
     * @param date
     */
    public void fill(String id, String nom, LocalDate date) {
        // Titre
        this.titre.getTexteLabel().setText("Course n°" + id + " - " + nom);

        // Champs
        this.idField.setText(id);
        this.nomField.setText(nom);
        this.dateField.setDate(date);
    }

    /**
     * Ajouter un colis dans le tableau
     *
     * @param id
     * @param numero
     * @param poids
     * @param adresse
     * @param destinataire
     */
    public void addColis(String id, String numero, String poids, String adresse, String destinataire) {
        DefaultTableModel colisTableModel = (DefaultTableModel) this.colisTable.getModel();

        colisTableModel.addRow(new Object[]{id, numero, poids, adresse, destinataire, "\uF06E", "\uF062", "\uF063", "\uF1F8"});
    }

    /**
     * Supprimer un colis du tableau
     *
     * @param id
     */
    public void removeColis(String id) {
        DefaultTableModel colisTableModel = (DefaultTableModel) this.colisTable.getModel();

        for (int i = 0; i < colisTableModel.getRowCount(); i++) {
            if (colisTableModel.getValueAt(i, 0).equals(id)) {
                colisTableModel.removeRow(i);
                break;
            }
        }
    }

    /**
     * Monter un colis dans le tableau
     *
     * @param id
     */
    public void monterColis(String id) {
        DefaultTableModel colisTableModel = (DefaultTableModel) this.colisTable.getModel();

        for (int i = 0; i < colisTableModel.getRowCount(); i++) {
            if (colisTableModel.getValueAt(i, 0).equals(id)) {
                if (i > 0) {
                    Object[] row = new Object[colisTableModel.getColumnCount()];
                    for (int j = 0; j < colisTableModel.getColumnCount(); j++) {
                        row[j] = colisTableModel.getValueAt(i, j);
                    }
                    colisTableModel.removeRow(i);
                    colisTableModel.insertRow(i - 1, row);
                    break;
                }
            }
        }
    }

    /**
     * Descendre un colis dans le tableau
     *
     * @param id
     */
    public void descendreColis(String id) {
        DefaultTableModel colisTableModel = (DefaultTableModel) this.colisTable.getModel();

        for (int i = 0; i < colisTableModel.getRowCount(); i++) {
            if (colisTableModel.getValueAt(i, 0).equals(id)) {
                if (i < colisTableModel.getRowCount() - 1) {
                    Object[] row = new Object[colisTableModel.getColumnCount()];
                    for (int j = 0; j < colisTableModel.getColumnCount(); j++) {
                        row[j] = colisTableModel.getValueAt(i, j);
                    }
                    colisTableModel.removeRow(i);
                    colisTableModel.insertRow(i + 1, row);
                    break;
                }
            }
        }
    }

    /**
     * Supprimer un colis du tableau
     *
     * @param id
     */
    public void supprimerColis(String id) {
        DefaultTableModel colisTableModel = (DefaultTableModel) this.colisTable.getModel();

        for (int i = 0; i < colisTableModel.getRowCount(); i++) {
            if (colisTableModel.getValueAt(i, 0).equals(id)) {
                colisTableModel.removeRow(i);
                break;
            }
        }
    }
}
