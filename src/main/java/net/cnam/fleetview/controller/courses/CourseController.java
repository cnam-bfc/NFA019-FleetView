package net.cnam.fleetview.controller.courses;

import net.cnam.fleetview.controller.Controller;
import net.cnam.fleetview.controller.RootController;
import net.cnam.fleetview.controller.colis.ColisChooser;
import net.cnam.fleetview.controller.colis.ColissController;
import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.DefaultConnector;
import net.cnam.fleetview.model.adresse.Adresse;
import net.cnam.fleetview.model.adresse.AdresseDAO;
import net.cnam.fleetview.model.colis.Colis;
import net.cnam.fleetview.model.coliscourse.ColisCourse;
import net.cnam.fleetview.model.coliscourse.ColisCourseDAO;
import net.cnam.fleetview.model.colisdestinataire.ColisDestinataire;
import net.cnam.fleetview.model.colisdestinataire.ColisDestinataireDAO;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.course.CourseDAO;
import net.cnam.fleetview.view.colis.list.ColissView;
import net.cnam.fleetview.view.course.edit.CourseView;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class CourseController extends Controller<CourseView> implements ColisChooser {
    // DAO
    private final CourseDAO courseDAO;
    private final AdresseDAO adresseDAO;
    private final ColisCourseDAO colisCourseDAO;
    private final ColisDestinataireDAO colisDestinataireDAO;

    // Course
    private Course course;
    // Colis
    private List<ColisCourse> courseColis = new LinkedList<>();

    public CourseController(CourseView view) {
        super(view);

        // Initialisation des DAO
        DefaultConnector connector = new DefaultConnector();
        Connection connection = BDDConnection.getInstance(connector);
        this.courseDAO = new CourseDAO(connection);
        this.adresseDAO = new AdresseDAO(connection);
        this.colisCourseDAO = new ColisCourseDAO(connection);
        this.colisDestinataireDAO = new ColisDestinataireDAO(connection);
    }

    /**
     * Charger une course vide dans la vue
     */
    public void loadEmptyCourse() {
        // Rendre les champs modifiables
        view.setFieldsEditable(true);
    }

    /**
     * Chargement par défaut d'une course
     *
     * @param id
     */
    private void loadCourse(int id) {
        // Récupération de la course
        this.course = courseDAO.getById(id);

        String idForm = String.valueOf(course.getIdCourse());
        String nomForm = course.getNom();
        LocalDate dateForm = course.getDateCourse();

        // Chargement des données dans la vue
        view.fill(idForm, nomForm, dateForm);
    }


    /**
     * Charger une course affichable dans la vue
     *
     * @param id
     */
    public void loadViewableCourse(int id) {
        // Chargement de la course
        loadCourse(id);

        // Rendre les champs non modifiables
        view.setFieldsEditable(false);
    }

    /**
     * Charger une course modifiable dans la vue
     *
     * @param id
     */
    public void loadEditableCourse(int id) {
        // Chargement de la course
        loadCourse(id);

        // Rendre les champs modifiables
        view.setFieldsEditable(true);
    }

    /**
     * Sauvegarder une course
     *
     * @param nom  Nom de la course
     * @param date Date de la course
     * @return Si la sauvegarde a réussi
     */
    public boolean saveCourse(String nom, LocalDate date) {
        // Si on est en mode ajout d'une course
        if (course == null) {
            this.course = new Course();
        }

        // Sauvegarde des données dans la course
        course.setNom(nom);
        course.setDateCourse(date);

        // Sauvegarde de la course
        boolean success;
        if (course.getIdCourse() == null) {
            success = courseDAO.create(course, RootController.getCurrentUser());
        } else {
            success = courseDAO.update(course, RootController.getCurrentUser());
        }
        if (!success) {
            return false;
        }

        // Affectation de l'id de la course aux colis
        for (ColisCourse colisCourse : courseColis) {
            colisCourse.setIdCourse(course.getIdCourse());

            // Sauvegarde du colis
            if (colisCourse.getIdColisCourse() == null) {
                success = colisCourseDAO.create(colisCourse, RootController.getCurrentUser());
            } else {
                success = colisCourseDAO.update(colisCourse, RootController.getCurrentUser());
            }
            if (!success) {
                return false;
            }
        }

        return true;
    }

    public void onAjouterColis() {
        // Affichage du tableau des colis
        ColissView colissView = new ColissView();
        ColissController colissController = new ColissController(colissView, this);
        colissView.setController(colissController);

        RootController.open(colissView);
    }

    private void addColisToView(Colis colis) {
        // Récupération de l'adresse du colis
        Adresse colisAdresse = adresseDAO.getById(colis.getIdAdresse());
        // Récupération du destinataire du colis
        ColisDestinataire colisDestinataire = colisDestinataireDAO.getById(colis.getIdColisDestinataire());

        if (colisAdresse == null || colisDestinataire == null) {
            return;
        }

        String id = String.valueOf(colis.getIdColis());
        String numero = colis.getNumero();
        String poids = String.valueOf(colis.getPoids());

        String adresse;
        if (colisAdresse.getNumeroRue() != null) {
            adresse = colisAdresse.getNumeroRue() + " " + colisAdresse.getRue() + ", " + colisAdresse.getCodePostal() + " " + colisAdresse.getCommune();
        } else {
            adresse = colisAdresse.getRue() + ", " + colisAdresse.getCodePostal() + " " + colisAdresse.getCommune();
        }

        String destinataire = colisDestinataire.getNom() + " " + colisDestinataire.getPrenom();

        // Ajout des colis dans la vue
        view.addColis(id, numero, poids + " kg", adresse, destinataire);
    }


    @Override
    public void chooseColis(Colis colis) {
        // Récupération de la position du colis maximum dans la liste
        int maxPosition = 0;
        for (ColisCourse colisCourse : courseColis) {
            if (colisCourse.getOrdre() > maxPosition) {
                maxPosition = colisCourse.getOrdre();
            }
        }

        // Création du colis course
        ColisCourse colisCourse = new ColisCourse();

        colisCourse.setIdColis(colis.getIdColis());
        colisCourse.setOrdre(maxPosition + 1);

        // Ajout du colis à la liste des colis de la course
        courseColis.add(colisCourse);

        // Ajout du colis à la vue
        addColisToView(colis);
    }

    @Override
    public List<Integer> getBlacklist() {
        List<Integer> blacklist = new LinkedList<>();

        for (ColisCourse colisCourse : courseColis) {
            blacklist.add(colisCourse.getIdColis());
        }

        return blacklist;
    }
}
