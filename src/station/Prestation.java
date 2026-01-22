/*
 * Projet Java
 *
 * Créé par :
 * PRAK Billy
 * PERNON Neil
 * SANTOS Emmanuel
 */

package station;

public abstract class Prestation {
    // Classe mère des prestations : définit le "contrat" commun à toutes les prestations

    protected String categorie; // A, B ou C

    public Prestation(String categorie) {
        // Initialisation de la catégorie commune aux prestations
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    // méthodes à implémenter dans les sous-classes
    public abstract double lavage();
    public abstract double sechage();
    public abstract double prelavage();
    public abstract double nettoyage();
}
