/*
 * Projet Java - Station de lavage (console)
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

        // Affichage d'accueil
        System.out.println("=== Station de Lavage ===\n");

        // Scanner pour récupérer toutes les saisies clavier de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // Chargement optionnel des données depuis des fichiers
        System.out.println("Voulez-vous charger l'établissement depuis des fichiers ? (y/n)");
        String reponse = scanner.nextLine();

        // Création de l'établissement
        Etablissement station = new Etablissement("Station Lavage Express");

        // Si l'utilisateur choisit "y", on charge clients + RDV depuis les fichiers donnés
        if (reponse.equals("y")) {
            System.out.println("Entrez le nom du fichier clients :");
            String fichierClients = scanner.nextLine();

            System.out.println("Entrez le nom du fichier rendez-vous :");
            String fichierRDV = scanner.nextLine();

            try {
                // Chargement des clients puis des RDV
                station.depuisFichierClients(fichierClients);
                station.depuisFichierRDV(fichierRDV);
                System.out.println("Établissement chargé avec succès depuis les fichiers.");
            } catch (IOException e) {
                // Gestion simple d'erreur : fichier manquant / accès refusé / format incorrect, etc.
                System.out.println("Erreur lors du chargement des fichiers");
            }
        }

        // Menu principal : boucle jusqu'à ce que l'utilisateur choisisse 0
        int option = -1;
        while (option != 0) {

            // Affichage du menu
            System.out.println("\nQue voulez-vous faire ?\n"
                    + "\t1. Ajouter un client\n"
                    + "\t2. Rechercher un client\n"
                    + "\t3. Comparer le nom de deux clients\n"
                    + "\t4. Tester les prestations\n"
                    + "\t5. Gérer les rendez-vous\n"
                    + "\t6. Afficher l'établissement\n"
                    + "\t0. Quitter"
            );

            // Lecture de l'option utilisateur
            option = Integer.parseInt(scanner.nextLine());

            // Traitement selon l'option choisie
            switch (option) {

                case 1: {
                    // (2.1) Ajout d'un client
                    System.out.println("Entrez le nom du client :");
                    String nomClient = scanner.nextLine();

                    System.out.println("Entrez le numéro de téléphone du client :");
                    String telClient = scanner.nextLine();

                    System.out.println("Entrez l'email du client (vide si aucun) :");
                    String mailClient = scanner.nextLine();

                    // Appel de la surcharge adaptée selon présence de l'email
                    if (mailClient.equals("")) {
                        Client nouveauClient = station.ajouter(nomClient, telClient);
                        System.out.println("Client ajouté : " + nouveauClient);
                    } else {
                        Client nouveauClient = station.ajouter(nomClient, telClient, mailClient);
                        System.out.println("Client ajouté : " + nouveauClient);
                    }
                    break;
                }

                case 2: {
                    // Recherche d'un client (nom + téléphone)
                    System.out.println("Entrez le nom du client à rechercher :");
                    String nomClient = scanner.nextLine();

                    System.out.println("Entrez le numéro de téléphone du client à rechercher :");
                    String telClient = scanner.nextLine();

                    Client clientTrouve = station.rechercher(nomClient, telClient);

                    // Affichage du résultat
                    if (clientTrouve != null) {
                        System.out.println("Client trouvé : " + clientTrouve);
                    } else {
                        System.out.println("Client non trouvé");
                    }
                    break;
                }

                case 3: {
                    // Test de comparaison lexicographique
                    System.out.println("Entrez le nom du premier client :");
                    String nomClient1 = scanner.nextLine();

                    System.out.println("Entrez le numéro de téléphone du premier client :");
                    String telClient1 = scanner.nextLine();

                    System.out.println("Entrez le nom du deuxième client :");
                    String nomClient2 = scanner.nextLine();

                    System.out.println("Entrez le numéro de téléphone du deuxième client :");
                    String telClient2 = scanner.nextLine();

                    // Recherche des deux clients avant comparaison
                    Client c1 = station.rechercher(nomClient1, telClient1);
                    Client c2 = station.rechercher(nomClient2, telClient2);

                    if (c1 != null && c2 != null) {
                        // Indique si c1 doit être placé après c2 dans le tri
                        if (c1.placerApres(c2)) {
                            System.out.println(c1.getNom() + " vient après " + c2.getNom());
                        } else {
                            System.out.println(c2.getNom() + " vient après " + c1.getNom());
                        }
                    } else {
                        System.out.println("Un ou les deux clients n'ont pas été trouvés.");
                    }
                    break;
                }

                case 4: {
                    // Tests unitaires simples : calcul du prix selon type de prestation
                    System.out.println("Choisissez une prestation à tester :\n"
                            + "1. Prestation Express\n"
                            + "2. Prestation Sale\n"
                            + "3. Prestation Très Sale"
                    );
                    int optionPrestation = Integer.parseInt(scanner.nextLine());

                    switch (optionPrestation) {

                        case 1: {
                            // PrestationExpress : lavage + séchage + intérieur si demandé
                            System.out.println("Entrez la catégorie du véhicule (A, B ou C) :");
                            String categorie = scanner.nextLine();

                            System.out.println("Nettoyage intérieur ? (y/n) :");
                            boolean nettoyageInterieur = scanner.nextLine().equals("y");

                            PrestationExpress express = new PrestationExpress(categorie, nettoyageInterieur);

                            // Affichage détaillé pour vérifier le calcul du prix
                            System.out.println("Lavage : " + express.lavage() + " €");
                            System.out.println("Séchage : " + express.sechage() + " €");
                            System.out.println("Total : " + express.nettoyage() + " €");
                            break;
                        }

                        case 2: {
                            // PrestationSale : prélavage + lavage + séchage + intérieur inclus selon votre implémentation
                            System.out.println("Entrez la catégorie du véhicule (A, B ou C) :");
                            String categorie = scanner.nextLine();

                            PrestationSale sale = new PrestationSale(categorie);
                            System.out.println("Total : " + sale.nettoyage() + " €");
                            break;
                        }

                        case 3: {
                            // PrestationTresSale : surcoût selon type de salissure
                            System.out.println("Entrez la catégorie du véhicule (A, B ou C) :");
                            String categorie = scanner.nextLine();

                            System.out.println("Entrez le type de salissure (1 à 4) :");
                            int typeSalissure = Integer.parseInt(scanner.nextLine());

                            PrestationTresSale tresSale = new PrestationTresSale(categorie, typeSalissure);
                            System.out.println("Total : " + tresSale.nettoyage() + " €");
                            break;
                        }

                        default:
                            System.out.println("Option invalide.");
                            break;
                    }
                    break;
                }

                case 5: {
                    // Sous-menu RDV : planifier / rechercher créneau / afficher RDV
                    System.out.println("Choisissez une option de gestion des rendez-vous :\n"
                            + "1. Planifier un rendez-vous\n"
                            + "2. Rechercher un créneau\n"
                            + "3. Afficher les rendez-vous\n"
                    );

                    int optionRDV = Integer.parseInt(scanner.nextLine());

                    switch (optionRDV) {

                        case 1:
                            // Planification complète : client + créneau + prestation
                            station.planifier();
                            break;

                        case 2: {
                            // Recherche de créneau disponible selon un jour ou une heure
                            System.out.println("Rechercher par :\n"
                                    + "1. Jour\n"
                                    + "2. Heure"
                            );

                            int optionRecherche = Integer.parseInt(scanner.nextLine());

                            switch (optionRecherche) {

                                case 1: {
                                    System.out.println("Entrez la date (YYYY-MM-DD) :");
                                    LocalDate date = LocalDate.parse(scanner.nextLine());
                                    System.out.println(station.rechercher(date) + " est disponible.");
                                    break;
                                }

                                case 2: {
                                    System.out.println("Entrez l'heure (HH:MM) :");
                                    LocalTime heure = LocalTime.parse(scanner.nextLine());
                                    System.out.println(station.rechercher(heure) + " est disponible.");
                                    break;
                                }

                                default:
                                    System.out.println("Option invalide.");
                                    break;
                            }
                            break;
                        }

                        case 3: {
                            // Affiche le planning des RDV pour une journée donnée
                            System.out.println("Entrez la date (YYYY-MM-DD) :");
                            LocalDate dateAffichage = LocalDate.parse(scanner.nextLine());
                            station.afficher(dateAffichage);
                            break;
                        }

                        default:
                            System.out.println("Option invalide.");
                            break;
                    }
                    break;
                }

                case 6:
                    // Affiche l'état de l'établissement
                    station.afficher();
                    break;

                case 0:
                    // Sortie de la boucle
                    break;

                default:
                    // Gestion d'une option hors menu
                    System.out.println("Option invalide.");
                    break;
            }
        }

        // (3) Sauvegarde optionnelle avant fermeture
        System.out.println("\nVoulez-vous exporter les données de l'établissement avant de quitter ? (y/n)");
        reponse = scanner.nextLine();

        if (reponse.equals("y")) {
            System.out.println("Export des données de l'établissement :");
            try {
                // Les noms sont fixés ici
                station.versFichierClients("clients_export.txt");
                station.versFichierRDV("rendezvous_export.txt");
                System.out.println("Export réussi.");
            } catch (IOException e) {
                System.out.println("Erreur lors de l'export des fichiers.");
            }
        }

        // Fin du programme : fermeture des ressources
        System.out.println("Au revoir !");
        scanner.close();
    }
}
