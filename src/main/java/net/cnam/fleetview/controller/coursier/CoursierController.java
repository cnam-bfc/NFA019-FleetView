package net.cnam.fleetview.controller.coursier;

import net.cnam.fleetview.App;
import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.coliscourse.ColisCourseDAO;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.course.CourseDAO;
import net.cnam.fleetview.model.courseaccident.CourseAccidentDAO;
import net.cnam.fleetview.model.coursierutilisateur.CoursierUtilisateur;
import net.cnam.fleetview.model.coursierutilisateur.CoursierUtilisateurDAO;
import net.cnam.fleetview.view.coursier.show.CoursierView;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

public class CoursierController extends Controller<CoursierView> {
    // variables
    private CoursierUtilisateur coursier;

    // DAO
    private final CoursierUtilisateurDAO coursierUtilisateurDAO;
    private final CourseDAO courseDAO;
    private final ColisCourseDAO colisCourseDAO;
    private final CourseAccidentDAO courseAccidentDAO;

    public CoursierController(CoursierView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        Connection bddConnection = BDDConnection.getInstance(connector);
        this.coursierUtilisateurDAO = new CoursierUtilisateurDAO(bddConnection);
        this.courseDAO = new CourseDAO(bddConnection);
        this.colisCourseDAO = new ColisCourseDAO(bddConnection);
        this.courseAccidentDAO = new CourseAccidentDAO(bddConnection);
    }

    /**
     * Méthode permettant de charger les informations d'un coursier dans la vue
     *
     * @param id l'identifiant du coursier
     */
    public void loadViewableCoursier(String id) {
        // On récupère le coursier
        this.coursier = coursierUtilisateurDAO.getByIdCoursier(Integer.parseInt(id));

        // On remplace le matricule et le titre
        // On met le nom en full caps et le prénom en initiale avec première lettre en majuscule
        view.setTitre(this.coursier.getNom().toUpperCase() + " " + this.coursier.getPrenom().substring(0, 1).toUpperCase() + this.coursier.getPrenom().substring(1).toLowerCase());
        view.setMatricule(this.coursier.getMatricule());

        // On récupère les éléments de la catégorie "COURSE" et on remplie
        // Si possible mettre des boutons cliquable pour re diriger vers les pages
        Course courseEnCours = courseDAO.getCourseEnCours(this.coursier.getIdCoursier());
        if (courseEnCours != null) {
            view.setCourseEnCours("OUI");
            view.setCycleEnCours("OUI");
        } else {
            view.setCourseEnCours("NON");
            view.setCycleEnCours("NON");
        }

        // On actualise les informations en passant en paramètre l'id du cousier, la date du jour et la date d'il y a un mois

        view.setDateDebut(LocalDate.now().minusMonths(1).toString());
        view.setDateFin(LocalDate.now().toString());

        this.actualiserInformations();
    }

    public void actualiserInformations() {
        // On récupère l'Id du cousier à partir de la vue
        String dateDebut = view.getDateDebut();
        String dateFin = view.getDateFin();

        // On récupère le nombre de paquets livrés
        view.setPaquetLivre("" + colisCourseDAO.getNbColisLivreCoursier(this.coursier.getIdCoursier(), dateDebut, dateFin));

        // On récupère le nombre de Poids livrés
        view.setPoidLivre("" + colisCourseDAO.getPoidsLivreCoursier(this.coursier.getIdCoursier(), dateDebut, dateFin));

        // On récupère le poids moyens
        view.setPoidMoyen("" + colisCourseDAO.getPoidsMoyenCoursier(this.coursier.getIdCoursier(), dateDebut, dateFin));

        // On récupère le nombre de course
        view.setNombreCourse("" + courseDAO.getNbCourseCoursier(this.coursier.getIdCoursier(), dateDebut, dateFin));

        // On récupère la distance parcourue
        view.setDistanceParcourue("" + courseDAO.getDistanceParcourueCoursier(this.coursier.getIdCoursier(), dateDebut, dateFin));

        // On récupère le nombre d'accidents
        view.setNombreAccident("" + courseAccidentDAO.getNbAccidentCoursier(this.coursier.getIdCoursier(), dateDebut, dateFin));
    }

    /**
     * Méthode permettant de générer un rapport d'activité en PDF.
     * Utilisation de la librairie Apache PDFBox
     *
     * Note : la Biblio est complexe voir si on peut utiliser iText (problème, toutes les versions trouvées ont des licences ou alors des vulnérabilités)
     * Note 2 : Se serait bien de créer un objet destiner à gérer les PDF si on a le temps (Facilement placer titre/sous titre, infos, image, etc...)
     */
    public void exporterRapportActivite(String selectedFilePath, String selectedFileName, String nbPaquet, String nbPoidsLivre, String poidsMoyen, String nbCourse, String distanceParcourue, String nbAccident) {
        // On récupère l'Id du cousier à partir de la vue
        String dateDebut = view.getDateDebut();
        String dateFin = view.getDateFin();

        try (PDDocument document = new PDDocument()) {
            // Création d'une nouvelle page
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 24);
            contentStream.beginText();
            // Centrer le texte au milieu de la page
            float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("Rapport d'activité") / 1000 * 24;
            float titleStartX = (page.getMediaBox().getWidth() - titleWidth) / 2;
            float titleStartY = page.getMediaBox().getHeight() - 50;
            contentStream.newLineAtOffset(titleStartX, titleStartY);
            contentStream.showText("Rapport d'activité");
            contentStream.endText();

            float subtitleWidth = PDType1Font.HELVETICA_BOLD_OBLIQUE.getStringWidth(this.coursier.getNom().toUpperCase() + " " + this.coursier.getPrenom().substring(0, 1).toUpperCase() + this.coursier.getPrenom().substring(1).toLowerCase()) / 1000 * 20;
            float subtitleStartX = (page.getMediaBox().getWidth() - subtitleWidth) / 2;
            float subtitleStartY = titleStartY - 20;
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD_OBLIQUE, 20);
            contentStream.newLineAtOffset(((page.getMediaBox().getWidth() - subtitleWidth) / 2), titleStartY - 25);
            contentStream.showText(this.coursier.getNom().toUpperCase() + " " + this.coursier.getPrenom().substring(0, 1).toUpperCase() + this.coursier.getPrenom().substring(1).toLowerCase());
            contentStream.endText();

            float textStartX = -page.getMediaBox().getWidth() / 2 + 50;
            float textStartY = subtitleStartY - 50;
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(textStartX - textStartX + 50, textStartY);
            contentStream.showText("Matricule : " + coursier.getMatricule());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Date du rapport : " + LocalDate.now());
            contentStream.endText();

            // Ajout d'une image
            BufferedImage image = App.LOGO_NORMAL;
            PDImageXObject pdImage = LosslessFactory.createFromImage(document, image);
            // Mettre l'image au milieu de la page en gros
            float scale = 0.5f;
            float x = (page.getMediaBox().getWidth() - (pdImage.getWidth() * scale)) / 2;
            float y = (page.getMediaBox().getHeight() - (pdImage.getHeight() * scale)) / 2;
            contentStream.drawImage(pdImage, x, y, pdImage.getWidth() * scale, pdImage.getHeight() * scale);

            contentStream.close();

            // Ajout d'une nouvelle page pour les informations suivantes
            PDPage page2 = new PDPage(PDRectangle.A4);
            document.addPage(page2);

            PDPageContentStream contentStream2 = new PDPageContentStream(document, page2);
            contentStream2.setFont(PDType1Font.HELVETICA, 12);
            contentStream2.beginText();
            contentStream2.newLineAtOffset(50, page2.getMediaBox().getHeight() - 50);
            contentStream2.showText("Date début : " + dateDebut);
            contentStream2.newLineAtOffset(0, -15);
            contentStream2.showText("Date fin : " + dateFin);
            contentStream2.newLineAtOffset(0, -15);
            contentStream2.showText("Nombre de paquets livrés : " + nbPaquet);
            contentStream2.newLineAtOffset(0, -15);
            contentStream2.showText("Nombre de poids livrés : " + nbPoidsLivre);
            contentStream2.newLineAtOffset(0, -15);
            contentStream2.showText("Poids moyen : " + poidsMoyen);
            contentStream2.newLineAtOffset(0, -15);
            contentStream2.showText("Nombre de course : " + nbCourse);
            contentStream2.newLineAtOffset(0, -15);
            contentStream2.showText("Distance parcourue : " + distanceParcourue);
            contentStream2.newLineAtOffset(0, -15);
            contentStream2.showText("Nombre d'accidents : " + nbAccident);
            contentStream2.newLineAtOffset(0, -15);

            // Ajouter les autres informations
            contentStream2.endText();
            contentStream2.close();

            // Si le document existe déjà, on l'écrase
            document.save(selectedFilePath + File.separator + selectedFileName + ".pdf");
            System.out.println("Rapport d'activité généré avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
