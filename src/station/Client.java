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
    private int id;
    private String nom;
    private String numTel;
    private String mail;

    public Client(int id, String nom, String numTel) {
        this.id = id;
        this.nom = nom;
        this.numTel = numTel;
    }

    public Client(int id, String nom, String numTel, String mail) {
        this.id = id;
        this.nom = nom;
        this.numTel = numTel;
        this.mail = mail;
    }
    // Getters et setters
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

    // Fonction pour comparer lexicographiquement deux noms de clients.
    public boolean placerApres(Client c) {
        return this.nom.compareTo(c.getNom()) > 0;
    }

}
