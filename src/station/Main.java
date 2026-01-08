/*
 * Projet Java
 *
 * Créé par :
 * PRAK Billy
 * PERNON Neil
 * SANTOS Emmanuel
 */

package station;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Test de la Station de Lavage ===\n");

        // Création de l'établissement
        Etablissement station = new Etablissement("Station Lavage Express");
        System.out.println("Établissement créé : Station Lavage Express\n");

        // Ajout de clients
        System.out.println("=== Ajout de clients ===");
        Client client1 = station.ajouter("Dupont", "0612345678");
        System.out.println(client1);

        Client client2 = station.ajouter("Martin", "0623456789");
        System.out.println(client2);

        Client client3 = station.ajouter("Bernard", "0634567890");
        System.out.println(client3);

        Client client4 = station.ajouter("Durand", "0645678901", "durand@email.com");
        System.out.println(client4);

        // Tentative d'ajout d'un client existant
        System.out.println("\n=== Tentative d'ajout d'un client existant ===");
        station.ajouter("Dupont", "0612345678");

        // Recherche de clients
        System.out.println("\n=== Recherche de clients ===");
        Client trouve1 = station.rechercher("Martin", "0623456789");
        if (trouve1 != null) {
            System.out.println("Client trouvé : " + trouve1);
        } else {
            System.out.println("Client non trouvé");
        }

        Client trouve2 = station.rechercher("Inconnu", "0000000000");
        if (trouve2 != null) {
            System.out.println("Client trouvé : " + trouve2);
        } else {
            System.out.println("Client non trouvé");
        }

        // Test de la méthode placerApres
        System.out.println("\n=== Comparaison lexicographique ===");
        Client clientA = new Client(100, "Alice", "0600000001");
        Client clientZ = new Client(101, "Zoe", "0600000002");
        System.out.println("Alice > Zoe ? " + clientA.placerApres(clientZ));
        System.out.println("Zoe > Alice ? " + clientZ.placerApres(clientA));

        // Prestations Express
        System.out.println("\n=== Prestations Express ===");
        PrestationExpress expressA = new PrestationExpress("A", false);
        System.out.println(expressA);
        System.out.println("  Lavage : " + expressA.lavage() + " €");
        System.out.println("  Séchage : " + expressA.sechage() + " €");
        System.out.println("  Prélavage : " + expressA.prelavage() + " €");
        System.out.println("  Total : " + expressA.nettoyage() + " €");

        PrestationExpress expressC = new PrestationExpress("C", true);
        System.out.println("\n" + expressC);
        System.out.println("  Lavage : " + expressC.lavage() + " €");
        System.out.println("  Séchage : " + expressC.sechage() + " €");
        System.out.println("  Total avec nettoyage intérieur : " + expressC.nettoyage() + " €");

        // Prestations Sale
        System.out.println("\n=== Prestations Sale ===");
        PrestationSale saleA = new PrestationSale("A");
        System.out.println(saleA);
        System.out.println("  Prélavage : " + saleA.prelavage() + " €");
        System.out.println("  Lavage : " + saleA.lavage() + " €");
        System.out.println("  Séchage : " + saleA.sechage() + " €");
        System.out.println("  Total : " + saleA.nettoyage() + " €");

        PrestationSale saleB = new PrestationSale("B");
        System.out.println("\n" + saleB);
        System.out.println("  Total : " + saleB.nettoyage() + " €");

        PrestationSale saleC = new PrestationSale("C");
        System.out.println("\n" + saleC);
        System.out.println("  Total : " + saleC.nettoyage() + " €");

        // Prestations Très Sale
        System.out.println("\n=== Prestations Très Sale ===");
        PrestationTresSale tresSale1 = new PrestationTresSale("A", 1);
        System.out.println(tresSale1);
        System.out.println("  Prélavage : " + tresSale1.prelavage() + " €");
        System.out.println("  Lavage : " + tresSale1.lavage() + " €");
        System.out.println("  Séchage : " + tresSale1.sechage() + " €");
        System.out.println("  Total : " + tresSale1.nettoyage() + " €");

        PrestationTresSale tresSale2 = new PrestationTresSale("B", 2);
        System.out.println("\n" + tresSale2);
        System.out.println("  Total : " + tresSale2.nettoyage() + " €");

        PrestationTresSale tresSale4 = new PrestationTresSale("C", 4);
        System.out.println("\n" + tresSale4);
        System.out.println("  Total : " + tresSale4.nettoyage() + " €");
    }
}
