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

    private int typeSalissure;
    // Type de salissure (1 à 4)

    public PrestationTresSale(String categorie, int typeSalissure) {
        // Appel du constructeur de la classe mère
        super(categorie);
        this.typeSalissure = typeSalissure;
    }

    public int getTypeSalissure() {
        // Retourne le type de salissure
        return typeSalissure;
    }

    // Ajoute un surcoût pour les taches
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
        // Séchage identique aux autres prestations
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
        // Prélavage avec ajout du surcoût
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
        // Calcul du prix total pour un véhicule très sale
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
