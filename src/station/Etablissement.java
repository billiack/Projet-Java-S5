package station;

public class Etablissement {
    private String nom;
    private Client[] clients;
    private int nbClients;
    private RendezVous[][] rendezVous;

    public Etablissement(String nom) {
        this.nom = nom;
        this.clients = new Client[100];
        this.nbClients = 0;
        this.rendezVous = new RendezVous[10][7]; // ligne : créneaux, colonne : jours
    }

    private void trierClients() {
        // Tri à bulle des clients par ordre lexicographique
        for (int i = 0; i < nbClients - 1; i++) {
            for (int j = 0; j < nbClients - i - 1; j++) {
                if (clients[j].placerApres(clients[j + 1])) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                }
            }
        }
    }

    public Client rechercher(String nom, String numTel) {
        for (int i = 0; i < nbClients; i++) {
            if (clients[i].getNom().equals(nom) && clients[i].getNumTel().equals(numTel)) {
                return clients[i];
            }
        }
        return null;
    }

    public Client ajouter(String nom, String numTel) {
        if (nbClients >= clients.length) {
            System.out.println("Capacité maximale de clients atteinte.");
            return null;
        }
        if (rechercher(nom, numTel) != null) {
            System.out.println("Client déjà existant.");
            return null;
        }
        Client nouveauClient = new Client(nbClients + 1, nom, numTel);
        clients[nbClients] = nouveauClient;
        nbClients++;
        trierClients();
        return nouveauClient;
    }

    public Client ajouter(String nom, String numTel, String mail) {
        if (nbClients >= clients.length) {
            System.out.println("Capacité maximale de clients atteinte.");
            return null;
        }
        if (rechercher(nom, numTel) != null) {
            System.out.println("Client déjà existant.");
            return null;
        }
        Client nouveauClient = new Client(nbClients + 1, nom, numTel, mail);
        clients[nbClients] = nouveauClient;
        nbClients++;
        trierClients();
        return nouveauClient;
    }
}
