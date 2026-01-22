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

    protected String categorie;
    // Catégorie du véhicule (A, B ou C)

    public Prestation(String categorie) {
        // Initialisation de la catégorie commune aux prestations
        this.categorie = categorie;
    }

    public String getCategorie() {
        // Permet de récupérer la catégorie du véhicule
        return categorie;
    }

    // Méthodes à implémenter dans les classes filles (Express / Sale / TrèsSale)
    public abstract double lavage();
    public abstract double sechage();
    public abstract double prelavage();
    public abstract double nettoyage();
}
