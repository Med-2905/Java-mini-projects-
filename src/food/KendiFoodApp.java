package food;

public class KendiFoodApp {
    private catalogue catalogue;
    private panier panier;
    
    public KendiFoodApp() {
        this.catalogue = new catalogue();
        this.panier = new panier();
        chargerDonneesInitiales();
    }
    
    private void chargerDonneesInitiales() {
        // Articles initiaux pour tester
        try {
            catalogue.ajouterArticle(new Article("KIT_BOL1", "Bol végétarien", 5990, 12));
            catalogue.ajouterArticle(new Article("TOMATE3", "Tomates x3", 990, 40));
            catalogue.ajouterArticle(new Article("FROM1", "Fromage bio", 2500, 8));
            catalogue.ajouterArticle(new Article("PAIN1", "Pain complet", 450, 20));
            catalogue.ajouterArticle(new Article("LAIT2", "Lait d'amande", 320, 15));
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur initialisation: " + e.getMessage());
        }
    }
    
    public void executer() {
        System.out.println("=== 🛒 KendiFood - Système de paniers-repas ===");
        
        boolean continuer = true;
        while (continuer) {
            afficherMenuPrincipal();
            int choix = SaisieUtil.saisirEntier("Votre choix: ");
            
            try {
                switch (choix) {
                    case 1:
                        gererCatalogue();
                        break;
                    case 2:
                        gererPanier();
                        break;
                    case 3:
                        gererCodesReduction();
                        break;
                    case 4:
                        gererFichiers();
                        break;
                    case 0:
                        continuer = false;
                        System.out.println("🍕 Merci d'avoir utilisé KendiFood ! À bientôt !");
                        break;
                    default:
                        System.out.println("❌ Choix invalide.");
                }
            } catch (Exception e) {
                System.err.println("💥 Erreur: " + e.getMessage());
            }
            
            System.out.println();
        }
    }
    
    private void afficherMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. 📦 Gérer le catalogue");
        System.out.println("2. 🛒 Gérer le panier");
        System.out.println("3. 🎫 Gérer les codes réduction");
        System.out.println("4. 📁 Gestion fichiers");
        System.out.println("0. 🚪 Quitter");
    }
    
    private void gererCatalogue() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n=== GESTION CATALOGUE ===");
            System.out.println("1. 📋 Afficher le catalogue");
            System.out.println("2. ➕ Ajouter un article");
            System.out.println("3. 🔍 Rechercher un article");
            System.out.println("4. ↩️ Retour au menu principal");
            
            int choix = SaisieUtil.saisirEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    catalogue.afficherCatalogue();
                    break;
                case 2:
                    ajouterArticleInteractif();
                    break;
                case 3:
                    rechercherArticleInteractif();
                    break;
                case 4:
                    retour = true;
                    break;
                default:
                    System.out.println("❌ Choix invalide.");
            }
        }
    }
    
    private void ajouterArticleInteractif() {
        try {
            String id = SaisieUtil.saisirString("ID de l'article: ");
            String libelle = SaisieUtil.saisirString("Libellé: ");
            int prix = SaisieUtil.saisirEntier("Prix (en centimes): ");
            int stock = SaisieUtil.saisirEntier("Stock initial: ");
            
            Article article = new Article(id, libelle, prix, stock);
            catalogue.ajouterArticle(article);
            System.out.println("✅ Article ajouté avec succès !");
            
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Erreur: " + e.getMessage());
        }
    }
    
    private void rechercherArticleInteractif() {
        String id = SaisieUtil.saisirString("ID de l'article à rechercher: ");
        int index = catalogue.trouveParIndex(id);
        
        if (index != -1) {
            System.out.println("✅ Article trouvé: " + catalogue.getArticles()[index]);
        } else {
            System.out.println("❌ Article non trouvé.");
        }
    }
    
    private void gererPanier() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n=== GESTION PANIER ===");
            System.out.println("1. 📋 Afficher le panier");
            System.out.println("2. ➕ Ajouter au panier");
            System.out.println("3. 🗑️ Supprimer du panier");
            System.out.println("4. 🧾 Générer un reçu");
            System.out.println("5. 🗂️ Vider le panier");
            System.out.println("6. ↩️ Retour au menu principal");
            
            int choix = SaisieUtil.saisirEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    panier.afficherPanier();
                    break;
                case 2:
                    ajouterAuPanierInteractif();
                    break;
                case 3:
                    supprimerDuPanierInteractif();
                    break;
                case 4:
                    genererRecuInteractif();
                    break;
                case 5:
                    panier.viderPanier();
                    break;
                case 6:
                    retour = true;
                    break;
                default:
                    System.out.println("❌ Choix invalide.");
            }
        }
    }
    
    private void ajouterAuPanierInteractif() {
        try {
            String id = SaisieUtil.saisirString("ID de l'article: ");
            int quantite = SaisieUtil.saisirEntier("Quantité: ");
            
            panier.ajouterAuPanier(id, quantite, catalogue);
            
        } catch (Exception e) {
            System.err.println("❌ Erreur: " + e.getMessage());
        }
    }
    
    private void supprimerDuPanierInteractif() {
        String id = SaisieUtil.saisirString("ID de l'article à supprimer: ");
        if (!panier.supprimerDuPanier(id)) {
            System.out.println("❌ Article non trouvé dans le panier.");
        }
    }
    
    private void genererRecuInteractif() {
        String code = SaisieUtil.saisirString("Code de réduction (laisser vide si aucun): ");
        if (code.isEmpty()) {
            code = null;
        }
        
        String recu = panier.genererRecu(code);
        System.out.println(recu);
    }
    
    private void gererCodesReduction() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n=== CODES RÉDUCTION ===");
            System.out.println("1. 📋 Afficher les codes");
            System.out.println("2. ➕ Ajouter un code");
            System.out.println("3. ↩️ Retour au menu principal");
            
            int choix = SaisieUtil.saisirEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    panier.afficherCodesReduction();
                    break;
                case 2:
                    ajouterCodeReductionInteractif();
                    break;
                case 3:
                    retour = true;
                    break;
                default:
                    System.out.println("❌ Choix invalide.");
            }
        }
    }
    
    private void ajouterCodeReductionInteractif() {
        try {
            String code = SaisieUtil.saisirString("Code: ");
            int pourcentage = SaisieUtil.saisirEntier("Pourcentage (1-50): ");
            
            CodeReduction nouveauCode = new CodeReduction(code, pourcentage);
            panier.ajouterCodeReduction(nouveauCode);
            System.out.println("✅ Code ajouté avec succès !");
            
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Erreur: " + e.getMessage());
        }
    }
    
    private void gererFichiers() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n=== GESTION FICHIERS ===");
            System.out.println("1. 📥 Charger catalogue depuis fichier");
            System.out.println("2. 💾 Sauvegarder catalogue vers fichier");
            System.out.println("3. ↩️ Retour au menu principal");
            
            int choix = SaisieUtil.saisirEntier("Votre choix: ");
            
            switch (choix) {
                case 1:
                    chargerCatalogueFichier();
                    break;
                case 2:
                    sauvegarderCatalogueFichier();
                    break;
                case 3:
                    retour = true;
                    break;
                default:
                    System.out.println("❌ Choix invalide.");
            }
        }
    }
    
    private void chargerCatalogueFichier() {
        try {
            String chemin = SaisieUtil.saisirCheminFichier("Chemin du fichier: ");
            int nbArticles = catalogue.chargerDepuisFichier(chemin);
            System.out.println("✅ " + nbArticles + " articles chargés avec succès !");
            
        } catch (KendiFoodException e) {
            System.err.println("❌ Erreur: " + e.getMessage());
        }
    }
    
    private void sauvegarderCatalogueFichier() {
        try {
            String chemin = SaisieUtil.saisirString("Chemin de sauvegarde: ");
            int nbArticles = catalogue.sauvegarderVersFichier(chemin);
            System.out.println("✅ " + nbArticles + " articles sauvegardés avec succès !");
            
        } catch (KendiFoodException e) {
            System.err.println("❌ Erreur: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        KendiFoodApp app = new KendiFoodApp();
        app.executer();
    }
}