package net.cnam.fleetview.view.course.edit;

import net.cnam.fleetview.controller.CourseController;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.button.LabelButton;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.utils.ButtonColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CourseView extends View<CourseController> {
    // ÉLÉMENTS DE L'INTERFACE
    // Titre
    private final IconLabel titre;
    // Panel du contenu
    private final JPanel contenu;
    // Champ de saisie de l'id
    private final JTextField idField;
    // Champ de saisie du nom
    private final JTextField nomField;
    // Champ de saisie de la date
    private final JTextField dateField;
    // Tableau des colis
    private final JTable colisTable;
    // Bouton de sauvegarde
    private final LabelButton saveButton;

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
        this.idField = new JTextField();
        this.nomField = new JTextField();
        this.dateField = new JTextField();
        this.colisTable = new JTable();
        this.saveButton = new LabelButton("Sauvegarder");


        // Configuration des éléments de l'interface
        // Panel du contenu
        BoxLayout contenuLayout = new BoxLayout(this.contenu, BoxLayout.Y_AXIS);
        this.contenu.setLayout(contenuLayout);

        this.contenu.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Champ de saisie de l'id
        this.idField.setEditable(false);

        // Champ de saisie du nom

        // Champ de saisie de la date

        // Tableau des colis
        DefaultTableModel colisTableModel = new DefaultTableModel();
        DefaultTableCellRenderer colisTableCellRenderer = new DefaultTableCellRenderer();
        JScrollPane colisTableScrollPane = new JScrollPane(colisTable);

        colisTableModel.addColumn("ID");
        colisTableModel.addColumn("Numero");
        colisTableModel.addColumn("Poids");
        colisTableModel.addColumn("Adresse");
        colisTableModel.addColumn("Destinataire");
        colisTableModel.addColumn("Statut");
        colisTableModel.addColumn("Voir");
        colisTableModel.addColumn("Monter");
        colisTableModel.addColumn("Descendre");
        colisTableModel.addColumn("Supprimer");

        colisTable.setModel(colisTableModel);

        colisTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Action voir
        colisTable.getColumnModel().getColumn(6).setPreferredWidth(30);
        ButtonColumn voirButtonColumn = new ButtonColumn(colisTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controller.onVoirColis(Integer.parseInt(colisTableModel.getValueAt(colisTable.getSelectedRow(), 0).toString()));
                afficherMessageInformation("Voir");
            }
        }, 6);

        // Action monter
        colisTable.getColumnModel().getColumn(7).setPreferredWidth(30);
        ButtonColumn monterButtonColumn = new ButtonColumn(colisTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controller.onMonterColis(Integer.parseInt(colisTableModel.getValueAt(colisTable.getSelectedRow(), 0).toString()));
                afficherMessageInformation("Monter");
            }
        }, 7);

        // Action descendre
        colisTable.getColumnModel().getColumn(8).setPreferredWidth(30);
        ButtonColumn descendreButtonColumn = new ButtonColumn(colisTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controller.onDescendreColis(Integer.parseInt(colisTableModel.getValueAt(colisTable.getSelectedRow(), 0).toString()));
                afficherMessageInformation("Descendre");
            }
        }, 8);

        // Action supprimer
        colisTable.getColumnModel().getColumn(9).setPreferredWidth(30);
        ButtonColumn supprimerButtonColumn = new ButtonColumn(colisTable, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controller.onSupprimerColis(Integer.parseInt(colisTableModel.getValueAt(colisTable.getSelectedRow(), 0).toString()));
                afficherMessageInformation("Supprimer");
            }
        }, 9);

        colisTable.setDefaultRenderer(Object.class, colisTableCellRenderer);
        colisTable.setRowHeight(30);

        // Tableau non editable
        colisTable.setDefaultEditor(Object.class, null);

        // Désactiver le dragging des colonnes
        colisTable.getTableHeader().setReorderingAllowed(false);

        // Bouton de sauvegarde
        this.saveButton.setVisible(false);
        this.saveButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = controller.saveCourse(nomField.getText(), dateField.getText());
                if (!success) {
                    afficherMessageErreur("Impossible de sauvegarder la course");
                    return;
                }

                RootController.close(CourseView.this);
            }
        });

        // Ajout des éléments de l'interface
        this.add(this.titre, BorderLayout.NORTH);
        this.contenu.add(this.idField);
        this.contenu.add(this.nomField);
        this.contenu.add(this.dateField);
        this.contenu.add(colisTableScrollPane);
        this.add(this.contenu, BorderLayout.CENTER);
        this.add(this.saveButton, BorderLayout.SOUTH);
    }

    /**
     * Rendre les champs modifiables ou non
     *
     * @param editable
     */
    public void setFieldsEditable(boolean editable) {
        this.nomField.setEditable(editable);
        this.dateField.setEditable(editable);
        // Désactiver les boutons du tableau des colis
        ButtonColumn monterButtonColumn = (ButtonColumn) this.colisTable.getColumnModel().getColumn(7).getCellEditor();
        monterButtonColumn.setButtonEnabled(editable);
        ButtonColumn descendreButtonColumn = (ButtonColumn) this.colisTable.getColumnModel().getColumn(8).getCellEditor();
        descendreButtonColumn.setButtonEnabled(editable);
        ButtonColumn supprimerButtonColumn = (ButtonColumn) this.colisTable.getColumnModel().getColumn(9).getCellEditor();
        supprimerButtonColumn.setButtonEnabled(editable);
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
    public void fill(String id, String nom, String date) {
        // Titre
        this.titre.getTexteLabel().setText("Course n°" + id + " - " + nom);

        // Champs
        this.idField.setText(id);
        this.nomField.setText(nom);
        this.dateField.setText(date);
    }

    /**
     * Ajouter un colis dans le tableau
     *
     * @param id
     * @param numero
     * @param poids
     * @param adresse
     * @param destinataire
     * @param statut
     */
    public void addColis(String id, String numero, String poids, String adresse, String destinataire, String statut) {
        DefaultTableModel colisTableModel = (DefaultTableModel) this.colisTable.getModel();

        colisTableModel.addRow(new Object[]{id, numero, poids, adresse, destinataire, statut, "\uF06E", "\uF1F8"});
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
}
