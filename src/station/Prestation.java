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
    protected String categorie;

    public Prestation(String categorie) {
        this.categorie = categorie;
    }

    public abstract double lavage();
    public abstract double sechage();
    public abstract double prelavage();
    public abstract double nettoyage();
}

