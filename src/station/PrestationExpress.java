/*
 * Projet Java
 *
 * Créé par :
 * PRAK Billy
 * PERNON Neil
 * SANTOS Emmanuel
 */

package station;

public class PrestationExpress extends Prestation {

    private boolean nettoyageInterieur;

    public PrestationExpress(String categorie, boolean nettoyageInterieur) {
        super(categorie);
        this.nettoyageInterieur = nettoyageInterieur;
    }

    public boolean getNettoyageInterieur() {
        return nettoyageInterieur;
    }

    @Override
    public double lavage() {
        if (categorie.equals("A")) {
            return 20;
        } else if (categorie.equals("B")) {
            return 30;
        } else {
            return 35;
        }
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

    // Pas de prélavage
    @Override
    public double prelavage() {
        return 0;
    }

    @Override
    public double nettoyage() {
        double total = lavage() + sechage();

        if (nettoyageInterieur) {
            if (categorie.equals("C")) {
                total += 40;
            } else {
                total += 30;
            }
        }

        return total;
    }

    @Override
    public String toString() {
        return "Express [" + categorie + ", interieur=" + nettoyageInterieur + ", prix=" + nettoyage() + "]";
    }
}
