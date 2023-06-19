package net.cnam.fleetview.controller.coursier;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.coliscourse.ColisCourseDAO;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.course.CourseDAO;
import net.cnam.fleetview.model.courseaccident.CourseAccidentDAO;
import net.cnam.fleetview.model.coursierutilisateur.CoursierUtilisateurDAO;
import net.cnam.fleetview.utils.Periode;
import net.cnam.fleetview.view.coursier.show.CoursierView;

import java.sql.Connection;

public class CoursierController extends Controller<CoursierView> {
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

        int idCoursier = Integer.parseInt(id);

        // On récupère les éléments de la catégorie "COURSE" et on remplie
        view.setCoursesSemaine(String.valueOf(courseDAO.getNbCourseCoursier(idCoursier, Periode.SEMAINE)));
        view.setCoursesMois(String.valueOf(courseDAO.getNbCourseCoursier(idCoursier, Periode.MOIS)));
        view.setCoursesAnnee(String.valueOf(courseDAO.getNbCourseCoursier(idCoursier, Periode.ANNEE)));

        // On récupère les éléments de la catégorie "COLIS" et on remplie
        view.setColisSemaine(String.valueOf(colisCourseDAO.getNbColisCoursier(idCoursier, Periode.SEMAINE)));
        view.setColisMois(String.valueOf(colisCourseDAO.getNbColisCoursier(idCoursier, Periode.MOIS)));
        view.setColisAnnee(String.valueOf(colisCourseDAO.getNbColisCoursier(idCoursier, Periode.ANNEE)));

        // On récupère les éléments de la catégorie "AUTRE" et on remplie
        view.setAccidents(String.valueOf(courseAccidentDAO.getNbAccidentForCoursier(idCoursier)));
        // Si possible mettre des boutons cliquable pour re diriger vers les pages
        Course courseEnCours = courseDAO.getCourseEnCours(idCoursier);
        if (courseEnCours != null) {
            view.setCourseEnCours("OUI");
            view.setCycleEnCours("OUI");
        } else {
            view.setCourseEnCours("NON");
            view.setCycleEnCours("NON");
        }
    }

    /**
     * Méthode permettant de rediriger vers la vue des statistiques d'un coursier
     *
     * @param id l'identifiant du coursier
     */
    public void onShowCoursierStats(int id) {

    }
}
