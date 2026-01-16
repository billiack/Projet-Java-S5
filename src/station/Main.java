/*
 * Projet Java
 *
 * Créé par :
 * PRAK Billy
 * PERNON Neil
 * SANTOS Emmanuel
 */

package station;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Station de Lavage ===\n");
        
        System.out.println("Vouslez-vous charger l'établissement depuis des fichiers ? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String reponse = scanner.nextLine();

        // Création de l'établissement
        Etablissement station = new Etablissement("Station Lavage Express");
        if (reponse.equals("y")) {
            System.out.println("Entrez le nom du fichier clients :");
            String fichierClients = scanner.nextLine();
            System.out.println("Entrez le nom du fichier rendez-vous :");
            String fichierRDV = scanner.nextLine();
            try {
                station.depuisFichierClients(fichierClients);
                station.depuisFichierRDV(fichierRDV);
                System.out.println("Établissement chargé avec succès depuis les fichiers.");
            } catch (IOException e) {
                System.out.println("Erreur lors du chargement des fichiers");
            }
        }
        // Menu de test des fonctionnalités
        int option = -1;
        while (option != 0) {
        System.out.println("\nQue voulez-vous faire ?\n"
                + "\t1. Ajouter un client\n"
                + "\t2. Rechercher un client\n"
                + "\t3. Comparer le nom de deux clients\n"
                + "\t4. Tester les prestations\n"
                + "\t5. Gérer les rendez-vous\n"
                + "\t6. Afficher l'établissement\n"
                + "\t0. Quitter"
        );
        option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1: // Ajouter un client
                System.out.println("Entrez le nom du client :");
                String nomClient = scanner.nextLine();
                System.out.println("Entrez le numéro de téléphone du client :");
                String telClient = scanner.nextLine();
                System.out.println("Entrez l'email du client (vide si aucun) :");
                String mailClient = scanner.nextLine();
                if (mailClient.equals("")) {
                    Client nouveauClient = station.ajouter(nomClient, telClient);
                    System.out.println("Client ajouté : " + nouveauClient);
                } else {
                    Client nouveauClient = station.ajouter(nomClient, telClient, mailClient);
                    System.out.println("Client ajouté : " + nouveauClient);
                }
                break;
            case 2: // Rechercher un client
                System.out.println("Entrez le nom du client à rechercher :");
                nomClient = scanner.nextLine();
                System.out.println("Entrez le numéro de téléphone du client à rechercher :");
                telClient = scanner.nextLine();
                Client clientTrouve = station.rechercher(nomClient, telClient);
                if (clientTrouve != null) {
                    System.out.println("Client trouvé : " + clientTrouve);
                } else {
                    System.out.println("Client non trouvé");
                }
                break;
            case 3: // Comparer le nom de deux clients
                System.out.println("Entrez le nom du premier client :");
                String nomClient1 = scanner.nextLine();
                System.out.println("Entrez le numéro de téléphone du premier client :");
                String telClient1 = scanner.nextLine();
                System.out.println("Entrez le nom du deuxième client :");
                String nomClient2 = scanner.nextLine();
                System.out.println("Entrez le numéro de téléphone du deuxième client :");
                String telClient2 = scanner.nextLine();
                Client c1 = station.rechercher(nomClient1, telClient1);
                Client c2 = station.rechercher(nomClient2, telClient2);
                if (c1 != null && c2 != null) {
                    if (c1.placerApres(c2)) {
                        System.out.println(c1.getNom() + " vient après " + c2.getNom());
                    } else {
                        System.out.println(c2.getNom() + " vient après " + c1.getNom());
                    }
                } else {
                    System.out.println("Un ou les deux clients n'ont pas été trouvés.");
                }

                break;
            case 4: // Tester les prestations
                System.out.println("Choississez une prestation à tester :\n"
                        + "1. Prestation Express\n"
                        + "2. Prestation Sale\n"
                        + "3. Prestation Très Sale"
                );
                int optionPrestation = Integer.parseInt(scanner.nextLine());
                switch (optionPrestation) {
                    case 1: // Prestation Express
                        System.out.println("Entrez la catégorie du véhicule (A, B ou C) :");
                        String categorie = scanner.nextLine();
                        System.out.println("Nettoyage intérieur ? (y/n) :");
                        boolean nettoyageInterieur = scanner.nextLine().equals("y");

                        PrestationExpress express = new PrestationExpress(categorie, nettoyageInterieur);
                        System.out.println("Lavage : " + express.lavage() + " €");
                        System.out.println("Séchage : " + express.sechage() + " €");
                        System.out.println("Total : " + express.nettoyage() + " €");
                        break;
                    case 2: // Prestation Sale
                        System.out.println("Entrez la catégorie du véhicule (A, B ou C) :");
                        categorie = scanner.nextLine();
                        PrestationSale sale = new PrestationSale(categorie);
                        System.out.println("Total : " + sale.nettoyage() + " €");
                        break;
                    case 3: // Prestation Très Sale
                        System.out.println("Entrez la catégorie du véhicule (A, B ou C) :");
                        categorie = scanner.nextLine();
                        System.out.println("Entrez le type de salissure (1 à 4) :");
                        int typeSalissure = Integer.parseInt(scanner.nextLine());

                        PrestationTresSale tresSale = new PrestationTresSale(categorie, typeSalissure);
                        System.out.println("Total : " + tresSale.nettoyage() + " €");
                        break;
                    default:
                        System.out.println("Option invalide.");
                        break;
                }
                break;
            case 5: // Gérer les rendez-vous
                System.out.println("Choississez une option de gestion des rendez-vous :\n"
                        + "1. Planifier un rendez-vous\n"
                        + "2. Rechercher un créneau\n"
                        + "3. Afficher les rendez-vous\n"
                );
                int optionRDV = Integer.parseInt(scanner.nextLine());
                switch (optionRDV) {
                    case 1: // Planifier un rendez-vous
                        station.planifier();
                        break;
                    case 2: // Rechercher un créneau
                        System.out.println("Rechercher par :\n"
                                + "1. Jour\n"
                                + "2. Heure"
                        );
                        int optionRecherche = Integer.parseInt(scanner.nextLine());
                        switch (optionRecherche) {
                            case 1: // Jour
                                System.out.println("Entrez la date (YYYY-MM-DD) :");
                                LocalDate date = LocalDate.parse(scanner.nextLine());
                                System.out.println(station.rechercher(date) + " est disponible.");
                                break;
                            case 2: // Heure
                                System.out.println("Entrez l'heure (HH:MM) :");
                                LocalTime heure = LocalTime.parse(scanner.nextLine());
                                System.out.println(station.rechercher(heure) + " est disponible.");
                                break;
                            default:
                                System.out.println("Option invalide.");
                                break;
                        }
                        break;
                    case 3: // Afficher les rendez-vous
                        System.out.println("Entrez la date (YYYY-MM-DD) :");
                        LocalDate dateAffichage = LocalDate.parse(scanner.nextLine());
                        station.afficher(dateAffichage);
                        break;
                    default:
                        System.out.println("Option invalide.");
                        break;
                }
                break;
            case 6: // Afficher l'établissement
                station.afficher();
                break;
            case 0: // Quitter
                break;
            default:
                System.out.println("Option invalide.");
                break;
            }
        }
        System.out.println("\nVoulez-vous exporter les données de l'établissement avant de quitter ? (y/n)");
        reponse = scanner.nextLine();
        if (reponse.equals("y")) {
            System.out.println("Export des données de l'établissement :");
            try {
                station.versFichierClients("clients_export.txt");
                station.versFichierRDV("rendezvous_export.txt");
                System.out.println("Export réussi.");
            } catch (IOException e) {
                System.out.println("Erreur lors de l'export des fichiers.");
            }
        }
        System.out.println("Au revoir !");
        scanner.close();
    }
}
        
