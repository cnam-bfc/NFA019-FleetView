package net.cnam.fleetview.model;

import net.cnam.fleetview.model.utilisateur.Utilisateur;

/**
 * Interface Archivable.
 * Cette interface permet de définir une méthode archive.
 *
 * @param <T> Représente la classe des objets Java à manipuler
 */
public interface Archivable<T> {
    /**
     * Méthode d'archivage d'un enregistrement
     *
     * @param object Un objet à archiver dans la base
     */
    boolean archive(T object, Utilisateur user);
}
