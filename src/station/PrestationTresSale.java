/*
 * Projet Java
 *
 * Créé par :
 * PRAK Billy
 * PERNON Neil
 * SANTOS Emmanuel
 */

package station;

public class PrestationTresSale extends Prestation {
    // Prestation la plus complète : surcoût selon le type de salissure

    private int typeSalissure; // 1 à 4

    public PrestationTresSale(String categorie, int typeSalissure) {
        // Appel du constructeur de la classe mère
        super(categorie);
        this.typeSalissure = typeSalissure;
    }

    public int getTypeSalissure() {
        return typeSalissure;
    }

    private double surcout() {
        // Surcoût dépendant du type de salissure
        if (typeSalissure == 1) {
            return 5;
        } else if (typeSalissure == 2) {
            return 7;
        } else if (typeSalissure == 3) {
            return 4;
        } else if (typeSalissure == 4) {
            return 8;
        } else {
            return 0;
        }
    }

    @Override
    public double lavage() {
        // Prix du lavage avec ajout du surcoût
        double base;
        if (categorie.equals("A")) {
            base = 20;
        } else if (categorie.equals("B")) {
            base = 30;
        } else {
            base = 35;
        }
        return base + surcout();
    }

    @Override
    public double sechage() {
        // Prix du séchage selon la catégorie
        if (categorie.equals("A")) {
            return 10;
        } else if (categorie.equals("B")) {
            return 10.5;
        } else {
            return 11;
        }
    }

    @Override
    public double prelavage() {
        // Prix du prélavage avec ajout du surcoût
        double base;
        if (categorie.equals("A")) {
            base = 5;
        } else if (categorie.equals("B")) {
            base = 7.5;
        } else {
            base = 8.75;
        }
        return base + surcout();
    }

    @Override
    public double nettoyage() {
        double total = prelavage() + lavage() + sechage();

        // Supplément lié à la catégorie du véhicule
        if (categorie.equals("C")) {
            total += 40;
        } else {
            total += 30;
        }
        return total;
    }

    @Override
    public String toString() {
        // Affichage lisible de la prestation
        return "TresSale [" + categorie + ", type=" + typeSalissure + ", prix=" + nettoyage() + "]";
    }
}
