/*
 * Projet Java
 *
 * Créé par :
 * PRAK Billy
 * PERNON Neil
 * SANTOS Emmanuel
 */

package station;

public class Client {
    // Classe représentant un client de la station
    private int id;
    private String nom;
    private String numTel;
    private String mail; // optionnel

    public Client(int id, String nom, String numTel) {
        // Constructeur pour un client sans adresse mail
        this.id = id;
        this.nom = nom;
        this.numTel = numTel;
    }

    public Client(int id, String nom, String numTel, String mail) {
        // Constructeur pour un client avec adresse mail
        this.id = id;
        this.nom = nom;
        this.numTel = numTel;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getNumTel() {
        return numTel;
    }

    public String getMail() {
        return mail;
    }

    // Compare deux clients selon l'ordre alphabétique de leur nom
    public boolean placerApres(Client c) {
        return this.nom.compareTo(c.getNom()) > 0;
    }

    @Override
    public String toString() {
        // Affichage du client avec ou sans adresse mail
        if (mail != null)
            return id + " : " + nom + " : " + numTel + " : " + mail;
        else
            return id + " : " + nom + " : " + numTel;
    }

    public String versFichier() {
        // Format utilisé pour la sauvegarde dans un fichier texte
        if (mail != null)
            return id + ":" + nom + ":" + numTel + ":" + mail;
        else
            return id + ":" + nom + ":" + numTel;
    }
}
