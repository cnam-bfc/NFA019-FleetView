package net.cnam.fleetview.controller.coursier;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.coliscourse.ColisCourseDAO;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.course.CourseDAO;
import net.cnam.fleetview.model.courseaccident.CourseAccidentDAO;
import net.cnam.fleetview.model.coursierutilisateur.CoursierUtilisateurDAO;
import net.cnam.fleetview.view.coursier.show.CoursierView;

import java.sql.Connection;
import java.time.LocalDate;

public class CoursierController extends Controller<CoursierView> {
    // variables
    private int idCoursier;

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
    public void loadViewableCoursier(String id, String matricule, String nom, String prenom) {

        // On remplace le matricule et le titre
        view.setMatricule(matricule);
        // On met le nom en majuscule et le prénom en minuscule avec la première lettre en majuscule
        nom = nom.toUpperCase();
        prenom = prenom.toLowerCase();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        view.setTitre(nom + " " + prenom);

        this.idCoursier = Integer.parseInt(id);

        // On récupère les éléments de la catégorie "COURSE" et on remplie
        // Si possible mettre des boutons cliquable pour re diriger vers les pages
        Course courseEnCours = courseDAO.getCourseEnCours(idCoursier);
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
        view.setPaquetLivre("" + colisCourseDAO.getNbColisLivreCoursier(this.idCoursier, dateDebut, dateFin));

        // On récupère le nombre de Poids livrés
        view.setPoidLivre("" + colisCourseDAO.getPoidsLivreCoursier(this.idCoursier, dateDebut, dateFin));

        // On récupère le poids moyens
        view.setPoidMoyen("" + colisCourseDAO.getPoidsMoyenCoursier(this.idCoursier, dateDebut, dateFin));

        // On récupère le nombre de course
        view.setNombreCourse("" + courseDAO.getNbCourseCoursier(this.idCoursier, dateDebut, dateFin));

        // On récupère la distance parcourue
        view.setDistanceParcourue("" + courseDAO.getDistanceParcourueCoursier(this.idCoursier, dateDebut, dateFin));

        // On récupère le nombre d'accidents
        view.setNombreAccident("" + courseAccidentDAO.getNbAccidentCoursier(this.idCoursier, dateDebut, dateFin));
    }

    /**
     * Méthode permettant de rediriger vers la vue des statistiques d'un coursier
     *
     * @param id l'identifiant du coursier
     */
    public void onShowCoursierStats(int id) {

    }
}
