package net.cnam.fleetview.controller;

import net.cnam.fleetview.database.BDDConnection;
import net.cnam.fleetview.database.Connector;
import net.cnam.fleetview.database.CustomConnectorGenerator;
import net.cnam.fleetview.model.adresse.Adresse;
import net.cnam.fleetview.model.adresse.AdresseDAO;
import net.cnam.fleetview.model.course.Course;
import net.cnam.fleetview.model.course.CourseDAO;
import net.cnam.fleetview.model.courseaccident.CourseAccident;
import net.cnam.fleetview.model.courseaccident.CourseAccidentDAO;
import net.cnam.fleetview.model.coursier.Coursier;
import net.cnam.fleetview.model.coursier.CoursierDAO;
import net.cnam.fleetview.model.coursiertravail.CoursierTravail;
import net.cnam.fleetview.model.coursiertravail.CoursierTravailDAO;
import net.cnam.fleetview.model.cycle.Cycle;
import net.cnam.fleetview.model.cycle.CycleDAO;
import net.cnam.fleetview.model.utilisateur.Utilisateur;
import net.cnam.fleetview.model.utilisateur.UtilisateurDAO;
import net.cnam.fleetview.view.documents.FicheAccidentView;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FicheAccidentController extends Controller<FicheAccidentView> {

    // Modèle
    // DAO
    private final CoursierTravailDAO coursierTravailDAO;
    private final CourseDAO courseDAO;
    private final CourseAccidentDAO courseAccidentDAO;
    private final CoursierDAO coursierDAO;
    private final UtilisateurDAO utilisateurDAO;
    private final AdresseDAO adresseDAO;
    private final CycleDAO cycleDAO;


    //Classe
    private CoursierTravail travail;
    private Course course;
    private CourseAccident accident;
    private Coursier coursier;
    private Utilisateur utilisateur;
    private Adresse adresse;
    private Cycle cycle;

    public FicheAccidentController(FicheAccidentView view) {
        super(view);

        // Initialisation des DAO
        CustomConnectorGenerator generator = new CustomConnectorGenerator();
        Connector connector = generator.getConnector();
        Connection connection = BDDConnection.getInstance(connector);

        this.coursierTravailDAO = new CoursierTravailDAO(connection);
        this.courseDAO = new CourseDAO(connection);
        this.courseAccidentDAO = new CourseAccidentDAO(connection);
        this.coursierDAO = new CoursierDAO(connection);
        this.utilisateurDAO = new UtilisateurDAO(connection);
        this.adresseDAO = new AdresseDAO(connection);
        this.cycleDAO = new CycleDAO(connection);
    }

    public void seeView(int id) {

        this.accident = courseAccidentDAO.getById(id);
        this.course = courseDAO.getById(id);
        this.travail = coursierTravailDAO.getById(course.getIdCoursierTravail());
        this.coursier = coursierDAO.getById(travail.getIdCoursier());
        this.utilisateur = utilisateurDAO.getById(coursier.getIdUtilisateur());
        this.cycle = cycleDAO.getById(course.getIdCycle());
        this.adresse = adresseDAO.getById(accident.getIdAdresse());

        String matricule = coursier.getMatricule();
        String nom = utilisateur.getNom();
        String idCourse = String.valueOf(course.getIdCourse());
        String[] idCourses = {idCourse};
        String nomCycle = cycle.getIdentifiant();
        LocalDateTime dateAccident = accident.getDateAccident();
        String jour = dateAccident.format(DateTimeFormatter.ofPattern("DD/MM/YYYY"));
        String heure = dateAccident.format(DateTimeFormatter.ofPattern("HH:MM"));
        String rue = adresse.getRue();
        String commune = adresse.getCommune();
        String cp = adresse.getCodePostal();

        view.fill(matricule, nom, idCourses, nomCycle, jour, heure, rue, commune, cp);
    }

    public String[] getCourselist() {
        List<Course> courses = courseDAO.getAllNotArchived();
        ArrayList<String> liste = new ArrayList<>();
        for (Course course : courses) {
            String id = String.valueOf(course.getIdCourse());
            liste.add(id);
        }

        return liste.toArray(new String[0]);
    }

    public void setChampsSelonIdCourse(String idCourse) {

        this.course = courseDAO.getById(Integer.parseInt(idCourse));
        this.travail = coursierTravailDAO.getById(course.getIdCoursierTravail());
        this.coursier = coursierDAO.getById(travail.getIdCoursier());
        this.utilisateur = utilisateurDAO.getById(coursier.getIdUtilisateur());
        this.cycle = cycleDAO.getById(course.getIdCycle());
        this.adresse = adresseDAO.getById(accident.getIdAdresse());

        String matricule = coursier.getMatricule();
        String nom = utilisateur.getNom();
        String[] idCourses = {idCourse};
        String nomCycle = cycle.getIdentifiant();
        LocalDateTime dateAccident = accident.getDateAccident();
        String jour = dateAccident.format(DateTimeFormatter.ofPattern("DD/MM/YYYY"));
        String heure = dateAccident.format(DateTimeFormatter.ofPattern("HH:MM"));

        view.fill(matricule, nom, idCourses, nomCycle, jour, heure);
    }

    public void createView() {
        getCourselist();
        view.setEditableCreate(false);
    }

}
