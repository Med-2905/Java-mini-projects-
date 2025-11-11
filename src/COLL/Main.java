package COLL;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Classe principale pour tester la gestion des clients et commandes.
 * Elle crée des clients, des commandes, les trie, les enregistre dans un fichier
 * et affiche leur contenu.
 */
public class Main {

    public static void main(String[] args) {
        try {
            // Création d'un format de date pour parser les dates des commandes
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // --- Création de quelques commandes ---
            Command c1 = new Command(101, sdf.parse("10/02/2024"), "Mr Amine");
            Command c2 = new Command(102, sdf.parse("05/01/2024"), "Mr Mehdi");
            Command c3 = new Command(103, sdf.parse("25/03/2024"), "Mr Mohamed");
            Command c4 = new Command(104, sdf.parse("15/02/2024"), "Mme Karima");

            // --- Création de clients ---
            Client client1 = new Client(1, "Ali Ben", "Casablanca", "0600000000");
            ClientFidel client2 = new ClientFidel(2, "Sara M.", "Rabat", "0700000000", "FID123", 10.5f);

            // --- Enregistrement des commandes pour chaque client ---
            client1.EnregistrerCommande(c1);
            client1.EnregistrerCommande(c2);
            client2.EnregistrerCommande(c3);
            client2.EnregistrerCommande(c4);

            // --- Création d'une liste de clients ---
            List<Client> clients = new ArrayList<>();
            clients.add(client1);
            clients.add(client2);

            // --- Tri des commandes de client1 par date ---
            Collections.sort(client1.getListeCommandes());

            // --- Création d'un ensemble (Set) pour éviter les doublons de commandes ---
            Set<Command> setCommands = new HashSet<>(client1.getListeCommandes());
            setCommands.add(c1); // tentative d'ajout d'une commande déjà existante (sera ignorée)

            // --- Création d'une map associant une représentation texte du client à l'objet client ---
            Map<String, Client> mapClients = new HashMap<>();
            for (Client c : clients) {
                mapClients.put(c.toString(), c);
            }

            // --- Écriture des clients dans un fichier texte ---
            FileWriter fw = new FileWriter("clients.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            for (Client c : mapClients.values()) {
                bw.write(c.toString());
                bw.write("\n----------------------\n");
            }
            bw.close(); // fermeture du BufferedWriter

            // --- Lecture et affichage du contenu du fichier ---
            System.out.println("=== Contenu du fichier clients.txt ===");
            BufferedReader br = new BufferedReader(new FileReader("clients.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close(); // fermeture du BufferedReader

        } catch (IOException e) {
            System.err.println("Erreur de fichier : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}
