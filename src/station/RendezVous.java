package station;

public class RendezVous {
    private Client client;
    private Prestation prestation;
    private double prix;

    public RendezVous(Client client, Prestation prestation) {
        this.client = client;
        this.prestation = prestation;
        this.prix = prestation.nettoyage();
    }

    @Override
    public String toString() {
        return "RendezVous{" +
               "client=" + client +
               ", prestation=" + prestation +
               ", prix=" + prix +
               '}';
    }

    public Client getClient() {
        return client;
    }

    public Prestation getPrestation() {
        return prestation;
    }

    public double getPrix() {
        return prix;
    }
}
