package net.cnam.fleetview.view.documents;

import net.cnam.fleetview.controller.DocumentsController;
import net.cnam.fleetview.view.View;
import net.cnam.fleetview.view.components.button.IconLabelButton;
import net.cnam.fleetview.view.components.field.IconTextField;
import net.cnam.fleetview.view.components.label.IconLabel;
import net.cnam.fleetview.view.utils.ButtonColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DocumentsView extends View<DocumentsController>{
    // ÉLÉMENTS DE L'INTERFACE
    // Titre
    private final IconLabel titre;
    // Panel du contenu
    private final JPanel contenu;

    // filtre déroulant type de document
    private final JComboBox filtreDocument;

    //filtre date
    private final JFormattedTextField filtreDate;

    //filtre déroulant coursier

    private final JComboBox filtreCoursier;

    //filtre déroulant cycle
    private final JComboBox filtreCycle;

    //porte filtre
    private JPanel filterHolder;
    // Tableau
    private final JTable DocumentsTable;
    // Ajout d'une Document
    private final IconLabelButton ajouterRapport;
    private final IconLabelButton ajouterFicheAccident;

    public DocumentsView(){
        super();

        // Layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Bordures
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des éléments de l'interface
        this.titre = new IconLabel("\uF0D1", "Document");
        this.contenu = new JPanel();
        this.filterHolder = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.filtreDocument = initDocType();
        this.filtreDate = initDateFilter();
        this.filtreCoursier = initCoursierFilter();
        this.filtreCycle = initCycleFilter();
        this.DocumentsTable = new JTable();
        this.ajouterFicheAccident = new IconLabelButton("\uF067", "Ajouter une fiche accident");
        this.ajouterRapport = new IconLabelButton("\uF067", "Ajouter un rapport quotidient");

        // Configuration des éléments de l'interface
        // Panel du contenu
        BorderLayout contenuLayout = new BorderLayout();
        this.contenu.setLayout(contenuLayout);

        // Barre de recherche
        /*this.barreDeRecherche.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.barreDeRecherche.getTextField().setPlaceholder("Rechercher un Document");
        */;
        this.filterHolder.add(this.filtreDocument);
        this.filterHolder.add(this.filtreDate);

        // Tableau
        DefaultTableModel DocumentsTableModel = new DefaultTableModel();
        DefaultTableCellRenderer DocumentsTableCellRenderer = new DefaultTableCellRenderer();
        TableRowSorter<DefaultTableModel> DocumentsTableRowSorter = new TableRowSorter<>(DocumentsTableModel);
        JScrollPane DocumentsTableScrollPane = new JScrollPane(DocumentsTable);

        DocumentsTableModel.addColumn("Document");
        DocumentsTableModel.addColumn("Date");
        DocumentsTableModel.addColumn("Cycle");
        DocumentsTableModel.addColumn("Livreur");
        DocumentsTableModel.addColumn("Voir");
        DocumentsTableModel.addColumn("Modifier");
        DocumentsTableModel.addColumn("Supprimer");

        DocumentsTable.setModel(DocumentsTableModel);

        DocumentsTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Action voir
        DocumentsTable.getColumnModel().getColumn(5).setPreferredWidth(30);


        DocumentsTable.setDefaultRenderer(Object.class, DocumentsTableCellRenderer);
        DocumentsTable.setRowHeight(30);

        // Tableau non editable
        DocumentsTable.setDefaultEditor(Object.class, null);

        // Ajout d'une course
        JPanel ajouterDocumentPanel = new JPanel();
        this.ajouterFicheAccident.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onAjouterFicheAccident();
            }
        });
        ajouterDocumentPanel.add(this.ajouterFicheAccident);

        this.ajouterRapport.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onAjouterRapport();
            }
        });
        ajouterDocumentPanel.add(this.ajouterRapport);

        // Ajout des éléments de l'interface
        this.add(this.titre, BorderLayout.NORTH);
        this.contenu.add(this.filterHolder, BorderLayout.NORTH);
        this.contenu.add(DocumentsTableScrollPane, BorderLayout.CENTER);
        this.contenu.add(ajouterDocumentPanel, BorderLayout.SOUTH);
        this.add(this.contenu, BorderLayout.CENTER);
    }

    private JComboBox initCycleFilter() {
        //get liste coursier sous un tableau de nom
        String[] listeCycles = controller.listCycles();

        //retourner le filtre avec la liste
        return new JComboBox(listeCycles);

    }

    private JFormattedTextField initDateFilter() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        return new JFormattedTextField(dateFormat);
    }

    private JComboBox initCoursierFilter() {
        //get liste coursier sous un tableau de nom
        String[] listCoursiers;
        listCoursiers = controller.listCoursiers();

        //retourner le filtre avec la liste
        JComboBox coursier = new JComboBox(listCoursiers);
        return coursier;

    }

    private JComboBox initDocType() {
        String[] docTypeFilterContent = {"type", "fiche accident", "rapport activité"};
        return new JComboBox<>(docTypeFilterContent);
    }
}