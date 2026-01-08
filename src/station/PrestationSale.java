package station;

public class PrestationSale extends Prestation {

    public PrestationSale(String categorie) {
        super(categorie);
    }

    @Override
    public double lavage() {
        if (categorie.equals("A")) return 20;
        if (categorie.equals("B")) return 30;
        return 35;
    }

    @Override
    public double sechage() {
        if (categorie.equals("A")) return 10;
        if (categorie.equals("B")) return 10.5;
        return 11;
    }

    @Override
    public double prelavage() {
        if (categorie.equals("A")) return 5;
        if (categorie.equals("B")) return 7.5;
        return 8.75;
    }

    @Override
    public double nettoyage() {
        double total = prelavage() + lavage() + sechage();
        if (categorie.equals("C")) total += 40;
        else total += 30;
        return total;
    }

    @Override
    public String toString() {
        return "Sale [" + categorie + ", prix=" + nettoyage() + "]";
    }
}
