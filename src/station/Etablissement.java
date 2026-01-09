/*
 * Projet Java
 *
 * Créé par :
 * PRAK Billy
 * PERNON Neil
 * SANTOS Emmanuel
 */

package station;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Etablissement {
    private String nom;
    private Client[] clients;
    private int nbClients;
    private RendezVous[][] rendezVous;
    private LocalDate debutSemaine;
    private LocalTime[] heures;

    public Etablissement(String nom) {
        this.nom = nom;
        this.clients = new Client[100];
        this.nbClients = 0;
        this.rendezVous = new RendezVous[10][7]; // ligne : créneaux, colonne : jours
        this.debutSemaine = LocalDate.now().plusDays(1);
        this.heures = new LocalTime[10];
        for (int i = 0; i < heures.length; i++) {
            heures[i] = LocalTime.of(8 + i, 0);
        }
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

    // Vérifie si un client existe déjà
    public Client rechercher(String nom, String numTel) {
        for (int i = 0; i < nbClients; i++) {
            if (clients[i].getNom().equals(nom) && clients[i].getNumTel().equals(numTel)) {
                return clients[i];
            }
        }
        return null;
    }

    // Ajoute un client dans l'établissement
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
    
    // Rechercher un créneau pour un jour donné
    public LocalDateTime rechercher(LocalDate jour) {
        // Calculer l'index du jour dans la semaine (0-6)
        int indexJour = -1;
        for (int j = 0; j < 7; j++) {
            if (debutSemaine.plusDays(j).equals(jour)) {
                indexJour = j;
                break;
            }
        }
        if (indexJour == -1) {
            System.out.println("Le jour " + jour + " n'est pas dans la semaine planifiée.");
            return null;
        }
        System.out.println("Heures disponibles pour le " + jour + " :");
        int compteur = 0;
        for (int i = 0; i < rendezVous.length; i++) {
            if (rendezVous[i][indexJour] == null) {
                compteur++;
                System.out.println(compteur + ". " + heures[i]);
            }
        }
        if (compteur == 0) {
            System.out.println("Aucune heure disponible pour ce jour.");
            return null;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choisissez le numéro de l'heure souhaitée : ");
        int choix = scanner.nextInt();
        
        if (choix < 1 || choix > compteur) {
            System.out.println("Choix invalide.");
            return null;
        }
        int compteurTemp = 0;
        for (int i = 0; i < rendezVous.length; i++) {
            if (rendezVous[i][indexJour] == null) {
                compteurTemp++;
                if (compteurTemp == choix) {
                    return LocalDateTime.of(jour, heures[i]);
                }
            }
        }
        
        return null;
    }
    
    // Rechercher un créneau pour une heure donnée
    public LocalDateTime rechercher(LocalTime heure) {
        int indexHeure = -1;
        for (int i = 0; i < heures.length; i++) {
            if (heures[i].equals(heure)) {
                indexHeure = i;
                break;
            }
        }
        
        if (indexHeure == -1) {
            System.out.println("L'heure " + heure + " n'est pas un créneau proposé.");
            return null;
        }
        System.out.println("Jours disponibles à " + heure + " :");
        int compteur = 0;
        for (int j = 0; j < 7; j++) {
            if (rendezVous[indexHeure][j] == null) {
                compteur++;
                LocalDate jour = debutSemaine.plusDays(j);
                System.out.println(compteur + ". " + jour);
            }
        }
        if (compteur == 0) {
            System.out.println("Aucun jour disponible pour cette heure.");
            return null;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choisissez le numéro du jour souhaité : ");
        int choix = scanner.nextInt();
        
        if (choix < 1 || choix > compteur) {
            System.out.println("Choix invalide.");
            return null;
        }
        int compteurTemp = 0;
        for (int j = 0; j < 7; j++) {
            if (rendezVous[indexHeure][j] == null) {
                compteurTemp++;
                if (compteurTemp == choix) {
                    LocalDate jour = debutSemaine.plusDays(j);
                    return LocalDateTime.of(jour, heure);
                }
            }
        }
        
        return null;
    }
    
    // Ajouter un rendez-vous pour une prestation express
    public RendezVous ajouter(Client client, LocalDateTime creneau, String categorie, boolean nettoyageInterieur) {
        int indexHeure = -1;
        int indexJour = -1;
        
        for (int i = 0; i < heures.length; i++) {
            if (heures[i].equals(creneau.toLocalTime())) {
                indexHeure = i;
                break;
            }
        }
        for (int j = 0; j < 7; j++) {
            if (debutSemaine.plusDays(j).equals(creneau.toLocalDate())) {
                indexJour = j;
                break;
            }
        }
        if (indexHeure == -1 || indexJour == -1) {
            System.out.println("Le créneau spécifié n'est pas valide.");
            return null;
        }
        if (rendezVous[indexHeure][indexJour] != null) {
            System.out.println("Ce créneau est déjà réservé.");
            return null;
        }
        PrestationExpress prestation = new PrestationExpress(categorie, nettoyageInterieur);
        RendezVous rdv = new RendezVous(client, prestation, creneau);
        rendezVous[indexHeure][indexJour] = rdv;
        
        return rdv;
    }
    
    // Ajouter un rendez-vous pour un véhicule sale
    public RendezVous ajouter(Client client, LocalDateTime creneau, String categorie) {
        int indexHeure = -1;
        int indexJour = -1;
        for (int i = 0; i < heures.length; i++) {
            if (heures[i].equals(creneau.toLocalTime())) {
                indexHeure = i;
                break;
            }
        }
        for (int j = 0; j < 7; j++) {
            if (debutSemaine.plusDays(j).equals(creneau.toLocalDate())) {
                indexJour = j;
                break;
            }
        }
        if (indexHeure == -1 || indexJour == -1) {
            System.out.println("Le créneau spécifié n'est pas valide.");
            return null;
        }
        if (rendezVous[indexHeure][indexJour] != null) {
            System.out.println("Ce créneau est déjà réservé.");
            return null;
        }
        PrestationSale prestation = new PrestationSale(categorie);
        RendezVous rdv = new RendezVous(client, prestation, creneau);
        rendezVous[indexHeure][indexJour] = rdv;
        
        return rdv;
    }
    
    // Ajouter un rendez-vous pour un véhicule très sale
    public RendezVous ajouter(Client client, LocalDateTime creneau, String categorie, int typeSalissure) {
        int indexHeure = -1;
        int indexJour = -1;
        for (int i = 0; i < heures.length; i++) {
            if (heures[i].equals(creneau.toLocalTime())) {
                indexHeure = i;
                break;
            }
        }
        for (int j = 0; j < 7; j++) {
            if (debutSemaine.plusDays(j).equals(creneau.toLocalDate())) {
                indexJour = j;
                break;
            }
        }
        if (indexHeure == -1 || indexJour == -1) {
            System.out.println("Le créneau spécifié n'est pas valide.");
            return null;
        }
        if (rendezVous[indexHeure][indexJour] != null) {
            System.out.println("Ce créneau est déjà réservé.");
            return null;
        }
        PrestationTresSale prestation = new PrestationTresSale(categorie, typeSalissure);
        RendezVous rdv = new RendezVous(client, prestation, creneau);
        rendezVous[indexHeure][indexJour] = rdv;
        return rdv;
    }

    // Demande à l'utilisateur de planifier un rendez-vous
    public void planifier() {
        System.out.println("La semaine de rendez-vous a été planifiée à partir du " + debutSemaine + " au " + debutSemaine.plusDays(6) + ".");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du client : ");
        String nom = scanner.nextLine();
        System.out.println("Entrez le numéro de téléphone du client : ");
        String numTel = scanner.nextLine();
        Client client = rechercher(nom, numTel);
        if (client == null) {
            client = ajouter(nom, numTel);
            if (client == null) {
                System.out.println("Impossible d'ajouter le client.");
                return;
            }
        }

        System.out.println("Choisissez un jour pour le rendez-vous.");
        for (int j = 0; j < 7; j++) {
            System.out.println((j + 1) + ". " + debutSemaine.plusDays(j));
        }
        int choixJour = scanner.nextInt();
        if (choixJour < 1 || choixJour > 7) {
            System.out.println("Choix de jour invalide.");
            return;
        }
        LocalDateTime creneau = rechercher(debutSemaine.plusDays(choixJour - 1));
        
        System.out.println("Choisissez le type de prestation :\n 1. Prestation Express\n 2. Prestation Sale\n 3. Prestation Très Sale");
        int choixPrestation = scanner.nextInt();
        System.out.println("Entrez la catégorie (A, B, C) : ");
        String categorie = scanner.next();
        if (categorie.equals("A") == false && categorie.equals("B") == false && categorie.equals("C") == false) {
            System.out.println("Catégorie invalide.");
            return;
        }
        if (choixPrestation == 1) {
            System.out.println("Nettoyage intérieur ? (true/false) : ");
            boolean nettoyageInterieur = scanner.nextBoolean();
            RendezVous rdv = ajouter(client, creneau, categorie, nettoyageInterieur);
            if (rdv != null) {
                System.out.println("Rendez-vous planifié : " + rdv);
            }
        } else if (choixPrestation == 2) {
            RendezVous rdv = ajouter(client, creneau, categorie);
            if (rdv != null) {
                System.out.println("Rendez-vous planifié : " + rdv);
            }
        } else if (choixPrestation == 3) {
            System.out.println("Entrez le type de salissure (1-4) : ");
            int typeSalissure = scanner.nextInt();
            if (typeSalissure < 1 || typeSalissure > 4) {
                System.out.println("Type de salissure invalide.");
                return;
            }
            RendezVous rdv = ajouter(client, creneau, categorie, typeSalissure);
            if (rdv != null) {
                System.out.println("Rendez-vous planifié : " + rdv);
            }
        } else {
            System.out.println("Choix de prestation invalide.");
        }
    }
    
    public void afficher() {
        System.out.println("Etablissement : " + nom);
        System.out.println("Clients :");
        for (int i = 0; i < nbClients; i++) {
            System.out.println(clients[i]);
        }
        System.out.println("Rendez-vous de la semaine du " + debutSemaine + " au " + debutSemaine.plusDays(6) + " :");
        for (int j = 0; j < 7; j++) {
            LocalDate jour = debutSemaine.plusDays(j);
            // Affiche les rendez-vous pour ce jour s'il y en a
            boolean rdv = false;
            for (int i = 0; i < rendezVous.length; i++) {
                if (rendezVous[i][j] != null) {
                    if (!rdv) {
                        System.out.println("Jour : " + jour);
                        rdv = true;
                    }
                    System.out.println("  " + heures[i] + " - " + rendezVous[i][j]);
                }
            }
        }
    }

    public void afficher(LocalDate jour) {
        System.out.println("Rendez-vous pour le " + jour + " :");
        int indexJour = -1;
        for (int j = 0; j < 7; j++) {
            if (debutSemaine.plusDays(j).equals(jour)) {
                indexJour = j;
                break;
            }
        }
        if (indexJour == -1) {
            System.out.println("Le jour " + jour + " n'est pas dans la semaine planifiée.");
            return;
        }
        boolean rdv = false;
        for (int i = 0; i < rendezVous.length; i++) {
            if (rendezVous[i][indexJour] != null) {
                if (!rdv) {
                    rdv = true;
                }
                System.out.println("  " + heures[i] + " - " + rendezVous[i][indexJour]);
            }
        }
        if (!rdv) {
            System.out.println("Aucun rendez-vous pour ce jour.");
        }
    }

    // Afficher le(s) client(s) de l'établissement (identifiant : nom ou numéro de téléphone)
    public void afficher(String identifiant) {
        System.out.println("Clients correspondant à l'identifiant \"" + identifiant + "\" :");
        boolean trouve = false;
        for (int i = 0; i < nbClients; i++) {
            if (clients[i].getNom().equals(identifiant) || clients[i].getNumTel().equals(identifiant)) {
                System.out.println(clients[i]);
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Aucun client ne correspond à cet identifiant.");
        }
    }

    // Affiche les rendez-vous d'un client donné par son ID
    public void afficher(int idClient) {
        System.out.println("Rendez-vous pour le client ID " + idClient + " :");
        boolean trouve = false;
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < rendezVous.length; i++) {
                if (rendezVous[i][j] != null && rendezVous[i][j].getClient().getId() == idClient) {
                    if (!trouve) {
                        trouve = true;
                    }
                    LocalDate jour = debutSemaine.plusDays(j);
                    System.out.println("  " + jour + " " + heures[i] + " - " + rendezVous[i][j]);
                }
            }
        }
        if (!trouve) {
            System.out.println("Aucun rendez-vous pour ce client.");
        }
    }
}
