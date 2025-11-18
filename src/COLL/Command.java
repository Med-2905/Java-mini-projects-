package COLL; // Déclaration du package

import java.text.SimpleDateFormat; // Import pour le formatage des dates
import java.util.Date; // Import de la classe Date

/**
 * Classe représentant une commande passée à un fournisseur.
 * Elle implémente l'interface Comparable afin de permettre la comparaison des commandes par date.
 */
public class Command implements Comparable<Command> {

    // --- Attributs privés ---
    private int NumCommande;          // Numéro unique de la commande
    private Date DateCommande;        // Date à laquelle la commande a été passée
    private String NomFournisseur;    // Nom du fournisseur associé à la commande

    /**
     * Constructeur de la classe Command.
     * 
     *  numCommande    numéro unique de la commande
     *  dateCommande   date de la commande
     *  nomFournisseur nom du fournisseur
     */
    public Command(int numCommande, Date dateCommande, String nomFournisseur) {
        NumCommande = numCommande;
        DateCommande = dateCommande;
        NomFournisseur = nomFournisseur;
    }

    // --- Getters et Setters ---
    public int getNumCommande() {
        return NumCommande;
    }

    public void setNumCommande(int numCommande) {
        NumCommande = numCommande;
    }

    public Date getDateCommande() {
        return DateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        DateCommande = dateCommande;
    }

    public String getNomFournisseur() {
        return NomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        NomFournisseur = nomFournisseur;
    }

    /**
     * Redéfinition de la méthode toString pour afficher les informations de la commande
     * de manière lisible.
     */
    @Override 
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Format de la date
        return "Numéro de commande : " + NumCommande +
               " | Date de commande : " + sdf.format(DateCommande) + 
               " | Nom du fournisseur : " + NomFournisseur;
    }

    /**
     * Redéfinition de la méthode equals pour comparer deux objets Command.
     * Deux commandes sont considérées égales si elles ont le même numéro.
     */
    @Override 
    public boolean equals(Object obj) {
        if (this == obj) return true;               // Même référence en mémoire
        if (!(obj instanceof Command)) return false; // Vérifie le type de l'objet
        Command c = (Command) obj;
        return this.NumCommande == c.NumCommande;    // Compare les numéros de commande
    }

    /**
     * Implémentation de la méthode compareTo de l'interface Comparable.
     * Permet de trier les commandes par date (ordre chronologique).
     */
    @Override
    public int compareTo(Command o) {
        return this.DateCommande.compareTo(o.DateCommande);
    }
}
