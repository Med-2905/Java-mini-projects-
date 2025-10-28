package COLL; // Déclaration du package

import java.util.ArrayList; // Import pour utiliser ArrayList
import java.util.List;      // Import pour utiliser List

/**
 * Classe représentant un client.
 * Chaque client possède des informations personnelles et une liste de commandes.
 */
public class Client {

    // --- Attributs privés ---
    private int CodeClient;        // Code unique du client
    private String NomClient;      // Nom du client
    private String AdrClient;      // Adresse du client
    private String TelClient;      // Numéro de téléphone du client

    private List<Command> listCommande; // Liste des commandes associées au client

    /**
     * Constructeur de la classe Client.
     *
     *  codeClient Code unique du client
     *  nomClient  Nom du client
     *  adrClient  Adresse du client
     *  telClient  Numéro de téléphone du client
     */
    public Client(int codeClient, String nomClient, String adrClient, String telClient) {
        CodeClient = codeClient;
        NomClient = nomClient;
        AdrClient = adrClient;
        TelClient = telClient;
        this.listCommande = new ArrayList<>(); // Initialisation de la liste des commandes
    }

    /**
     * Enregistre une commande pour ce client.
     *
     *  cmd La commande à ajouter
     * @return true si la commande a été ajoutée avec succès, false sinon
     */
    public boolean EnregistrerCommande(Command cmd) {
        return listCommande.add(cmd);
    }

    /**
     * Supprime une commande du client en fonction de son numéro.
     *
     *  numCommand Numéro de la commande à supprimer
     * @return true si la commande a été trouvée et supprimée, false sinon
     */
    public boolean SupprimerCommande(int numCommand) {
        for (Command c : listCommande) {
            if (c.getNumCommande() == numCommand) {
                listCommande.remove(c); // Supprime la commande correspondante
                return true;
            }
        }
        return false; // Aucune commande trouvée avec ce numéro
    }

    /**
     * Redéfinition de la méthode toString pour afficher les informations du client
     * ainsi que la liste de ses commandes.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" [ Code client : ").append(CodeClient)
          .append(" | Nom du client : ").append(NomClient)
          .append(" | Adresse : ").append(AdrClient)
          .append(" | Téléphone : ").append(TelClient)
          .append(" ]\n");

        if (listCommande.isEmpty()) {
            sb.append("Aucune commande enregistrée.\n");
        } else {
            sb.append("Liste des commandes :\n");
            for (Command cmd : listCommande) {
                sb.append("    - ").append(cmd.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    // --- Getters ---
    public int getCodeClient() {
        return CodeClient;
    }

    public List<Command> getListeCommandes() {
        return listCommande;
    }
}
