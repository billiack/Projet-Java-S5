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

    private int typeSalissure;

    public PrestationTresSale(String categorie, int typeSalissure) {
        super(categorie);
        this.typeSalissure = typeSalissure;
    }

    public int getTypeSalissure() {
        return typeSalissure;
    }

    // Ajoute un surcoût pour les taches
    private double surcout() {
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
        if (categorie.equals("C")) {
            total += 40;
        } else {
            total += 30;
        }
        return total;
    }

    @Override
    public String toString() {
        return "TresSale [" + categorie + ", type=" + typeSalissure + ", prix=" + nettoyage() + "]";
    }
}

