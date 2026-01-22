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
    // Prestation simple : lavage + séchage, avec option nettoyage intérieur

    private boolean nettoyageInterieur;

    public PrestationExpress(String categorie, boolean nettoyageInterieur) {
        // Appel du constructeur de la classe mère
        super(categorie);
        this.nettoyageInterieur = nettoyageInterieur;
    }

    public boolean getNettoyageInterieur() {
        return nettoyageInterieur;
    }

    @Override
    public double lavage() {
        // Prix du lavage selon la catégorie du véhicule
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
        // Prix du séchage selon la catégorie du véhicule
        if (categorie.equals("A")) {
            return 10;
        } else if (categorie.equals("B")) {
            return 10.5;
        } else {
            return 11;
        }
    }

    // Pas de prélavage pour la prestation express
    @Override
    public double prelavage() {
        return 0;
    }

    @Override
    public double nettoyage() {
        // Calcul du prix total de la prestation express
        double total = lavage() + sechage();

        // Ajout du nettoyage intérieur si demandé
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
        // Affichage lisible de la prestation
        return "Express [" + categorie + ", interieur=" + nettoyageInterieur + ", prix=" + nettoyage() + "]";
    }
}
