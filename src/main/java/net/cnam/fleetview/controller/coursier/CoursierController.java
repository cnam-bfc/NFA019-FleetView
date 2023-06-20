package net.cnam.fleetview.controller.coursier;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.coursierutilisateur.CoursierUtilisateurDAO;
import net.cnam.fleetview.view.coursier.show.CoursierView;

public class CoursierController extends Controller<CoursierView> {
    private final CoursierUtilisateurDAO coursierUtilisateurDAO;
    public CoursierController(CoursierView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        this.coursierUtilisateurDAO = new CoursierUtilisateurDAO(BDDConnection.getInstance(connector));
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

        // On récupère les éléments de la catégorie "COURSE" et on remplie

        // On récupère les éléments de la catégorie "COLIS" et on remplie

        // On récupère les éléments de la catégorie "AUTRE" et on remplie
    }

    /**
     * Méthode permettant de rediriger vers la vue des statistiques d'un coursier
     *
     * @param id l'identifiant du coursier
     */
    public void onShowCoursierStats(int id) {

    }
}
