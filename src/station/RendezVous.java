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

    public String versFichier() {
        String out = creneau.toString() + "\n" + client.getId() + "\n";
        if (prestation instanceof PrestationExpress) {
            PrestationExpress pe = (PrestationExpress) prestation;
            out += pe.getCategorie() + ":" + pe.getNettoyageInterieur() + ":" + prix + "\n";
        } else if (prestation instanceof PrestationSale) {
            PrestationSale ps = (PrestationSale) prestation;
            out += ps.getCategorie() + ":" + prix + "\n";
        } else if (prestation instanceof PrestationTresSale) {
            PrestationTresSale pts = (PrestationTresSale) prestation;
            out += pts.getCategorie() + ":" + pts.getTypeSalissure() + ":" + prix + "\n";
        }
        return out;
    }
}
