package station;

import java.time.LocalDateTime;

public class RendezVous {
    // Représente un rendez-vous entre un client et une prestation à un créneau donné

    private Client client;
    private Prestation prestation;
    private LocalDateTime creneau;
    private double prix;
    // Le prix est calculé automatiquement à partir de la prestation

    public RendezVous(Client client, Prestation prestation, LocalDateTime creneau) {
        // Création d'un rendez-vous avec calcul immédiat du prix
        this.client = client;
        this.prestation = prestation;
        this.creneau = creneau;
        this.prix = prestation.nettoyage();
    }

    @Override
    public String toString() {
        // Affichage complet d'un rendez-vous
        return "RendezVous{" +
                "creneau=" + creneau +
                ", client=" + client +
                ", prestation=" + prestation +
                ", prix=" + prix +
                '}';
    }

    // Getters utilisés par l'établissement et l'export
    public Client getClient() { return client; }
    public Prestation getPrestation() { return prestation; }
    public LocalDateTime getCreneau() { return creneau; }
    public double getPrix() { return prix; }

    public String versFichier() {
        // Prépare le rendez-vous pour l'écriture dans un fichier texte
        String out = creneau.toString() + "\n" + client.getId() + "\n";

        // Le format dépend du type réel de la prestation
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
