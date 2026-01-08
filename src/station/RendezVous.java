package station;

import java.time.LocalDateTime;

public class RendezVous {
    private Client client;
    private Prestation prestation;
    private LocalDateTime creneau;
    private double prix;

    public RendezVous(Client client, Prestation prestation, LocalDateTime creneau) {
        this.client = client;
        this.prestation = prestation;
        this.creneau = creneau;
        this.prix = prestation.nettoyage();
    }

    @Override
    public String toString() {
        return "RendezVous{" +
                "creneau=" + creneau +
                ", client=" + client +
                ", prestation=" + prestation +
                ", prix=" + prix +
                '}';
    }

    public Client getClient() { return client; }
    public Prestation getPrestation() { return prestation; }
    public LocalDateTime getCreneau() { return creneau; }
    public double getPrix() { return prix; }
}
