package COLL;



/**
 * Classe représentant un client fidèle, héritant de la classe Client.
 * Un client fidèle possède un code de fidélité et un taux de réduction.
 */
public class ClientFidel extends Client {

    // --- Attributs spécifiques à un client fidèle ---
    private String CodeFidelite;   // Code de fidélité unique pour le client
    private float TauxReduction;   // Taux de réduction applicable pour le client fidèle

    /**
     * Constructeur de la classe ClientFidel.
     * Appelle le constructeur de la classe parent Client pour initialiser les informations de base.
     *
     *  codeClient    Code unique du client
     *  nomClient     Nom du client
     *  adrClient     Adresse du client
     *  telClient     Téléphone du client
     *  codeFidelite  Code de fidélité
     *  tauxReduction Taux de réduction applicable
     */
    public ClientFidel(int codeClient, String nomClient, String adrClient, String telClient, String codeFidelite,
                       float tauxReduction) {
        super(codeClient, nomClient, adrClient, telClient); // Appel du constructeur de la classe parent
        CodeFidelite = codeFidelite;
        TauxReduction = tauxReduction;
    }

    /**
     * Redéfinition de la méthode toString pour afficher les informations du client fidèle.
     * Inclut les informations du client de base ainsi que le code de fidélité et le taux de réduction.
     */
    @Override
    public String toString() {
        return super.toString() + 
               " | Code de fidélité : " + CodeFidelite +
               " | Réduction : " + TauxReduction;
    }
}
