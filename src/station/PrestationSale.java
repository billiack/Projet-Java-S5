/*
 * Projet Java
 *
 * Créé par :
 * PRAK Billy
 * PERNON Neil
 * SANTOS Emmanuel
 */

package station;

public class PrestationSale extends Prestation {
    // Prestation pour véhicule sale : prélavage + lavage + séchage

    public PrestationSale(String categorie) {
        // Appel du constructeur de la classe mère
        super(categorie);
    }

    @Override
    public double lavage() {
        // Prix du lavage selon la catégorie
        if (categorie.equals("A")) return 20;
        if (categorie.equals("B")) return 30;
        return 35;
    }

    @Override
    public double sechage() {
        // Prix du séchage selon la catégorie
        if (categorie.equals("A")) return 10;
        if (categorie.equals("B")) return 10.5;
        return 11;
    }

    @Override
    public double prelavage() {
        // Prix du prélavage selon la catégorie
        if (categorie.equals("A")) return 5;
        if (categorie.equals("B")) return 7.5;
        return 8.75;
    }

    @Override
    public double nettoyage() {
        // Calcul du prix total : prélavage + lavage + séchage
        double total = prelavage() + lavage() + sechage();

        // Supplément lié à la catégorie du véhicule
        if (categorie.equals("C")) total += 40;
        else total += 30;

        return total;
    }

    @Override
    public String toString() {
        // Affichage lisible de la prestation
        return "Sale [" + categorie + ", prix=" + nettoyage() + "]";
    }
}
