package food;

public class panier {
    private LignePanier[] lignesPanier;
    private int taillePanier;
    private int capacite;
    private CodeReduction[] codesReduction;
    private int nombreCodes;
    
    public panier() {
        this.capacite = 5;
        this.lignesPanier = new LignePanier[capacite];
        this.taillePanier = 0;
        
        this.codesReduction = new CodeReduction[10];
        this.nombreCodes = 0;
        initialiserCodesReduction();
    }
 // ========== MÉTHODES MANQUANTES POUR PHASE 2 ==========

    public void ajouterAuPanier(String id, int quantite, catalogue catalogue) {
        if (id == null || id.trim().isEmpty()) {
            System.out.println("❌ ID ne peut pas être vide");
            return;
        }
        
        if (quantite <= 0) {
            System.out.println("❌ La quantité doit être positive");
            return;
        }
        
        int indexArticle = catalogue.trouveParIndex(id);
        if (indexArticle == -1) {
            System.out.println("❌ Article non trouvé : " + id);
            return;
        }
        
        Article article = catalogue.getArticles()[indexArticle];
        
        if (quantite > article.getStock()) {
            System.out.println("❌ Stock insuffisant pour " + id + 
                             " (disponible: " + article.getStock() + ", demandé: " + quantite + ")");
            return;
        }
        
        int indexLigne = trouverIndexDansPanier(id);
        if (indexLigne != -1) {
            LignePanier ligneExistante = lignesPanier[indexLigne];
            int nouvelleQuantite = ligneExistante.getQuantite() + quantite;
            
            ligneExistante.setQuantite(nouvelleQuantite);
            article.setStock(article.getStock() - quantite);
            System.out.println("✓ Quantité mise à jour pour " + id + " : " + nouvelleQuantite);
            
        } else {
            agrandirPanierSiNecessaire();
            
            lignesPanier[taillePanier] = new LignePanier(article, quantite);
            article.setStock(article.getStock() - quantite);
            taillePanier++;
            
            System.out.println("✓ Article ajouté au panier : " + id + " x " + quantite);
        }
    }

    public boolean supprimerDuPanier(String id) {
        if (id == null) {
            System.out.println("❌ ID ne peut pas être null");
            return false;
        }
        
        int index = trouverIndexDansPanier(id);
        if (index == -1) {
            System.out.println("❌ Article non trouvé dans le panier : " + id);
            return false;
        }
        
        LignePanier ligne = lignesPanier[index];
        Article article = ligne.getArticle();
        article.setStock(article.getStock() + ligne.getQuantite());
        
        for (int i = index; i < taillePanier - 1; i++) {
            lignesPanier[i] = lignesPanier[i + 1];
        }
        
        lignesPanier[taillePanier - 1] = null;
        taillePanier--;
        
        System.out.println("✓ Article supprimé du panier : " + id);
        return true;
    }

    public void afficherPanier() {
        System.out.println("\n====== PANIER ======");
        
        if (taillePanier == 0) {
            System.out.println("(panier vide)");
            return;
        }
        
        int maxIdLength = 0;
        for (int i = 0; i < taillePanier; i++) {
            int length = lignesPanier[i].getArticle().getId().length();
            if (length > maxIdLength) {
                maxIdLength = length;
            }
        }
        
        for (int i = 0; i < taillePanier; i++) {
            LignePanier ligne = lignesPanier[i];
            String id = ligne.getArticle().getId();
            int sousTotal = ligne.sousTotal();
            
            System.out.printf("- %-" + (maxIdLength + 2) + "s x %d => %d cts%n", 
                            id, ligne.getQuantite(), sousTotal);
        }
        
        System.out.println("Total brut : " + totalBrut() + " cts");
        System.out.println("====================");
    }

    private int trouverIndexDansPanier(String id) {
        if (id == null) return -1;
        
        for (int i = 0; i < taillePanier; i++) {
            if (lignesPanier[i].getArticle().getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private void agrandirPanierSiNecessaire() {
        if (taillePanier >= capacite) {
            capacite += 5;
            LignePanier[] nouveauPanier = new LignePanier[capacite];
            
            for (int i = 0; i < taillePanier; i++) {
                nouveauPanier[i] = lignesPanier[i];
            }
            
            lignesPanier = nouveauPanier;
            System.out.println("📦 Panier agrandi à " + capacite + " places");
        }
    }

    public int getTaillePanier() {
        return taillePanier;
    }

    public boolean estVide() {
        return taillePanier == 0;
    }

    public void viderPanier() {
        for (int i = 0; i < taillePanier; i++) {
            LignePanier ligne = lignesPanier[i];
            Article article = ligne.getArticle();
            article.setStock(article.getStock() + ligne.getQuantite());
            lignesPanier[i] = null;
        }
        taillePanier = 0;
        System.out.println("✓ Panier vidé");
    }
    
    public int totalBrut() {
        int total = 0;
        for (int i = 0; i < taillePanier; i++) {
            total += lignesPanier[i].sousTotal();
        }
        return total;
    }
    
    // Initialiser quelques codes de réduction
    private void initialiserCodesReduction() {
        try {
            ajouterCodeReduction(new CodeReduction("KENDI10", 10));
            ajouterCodeReduction(new CodeReduction("KENDI20", 20));
            ajouterCodeReduction(new CodeReduction("KENDI15", 15));
            ajouterCodeReduction(new CodeReduction("BIENVENUE", 5));
            ajouterCodeReduction(new CodeReduction("FIDELITE", 25));
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur initialisation codes: " + e.getMessage());
        }
    }
    
    // Ajouter un code de réduction
    public void ajouterCodeReduction(CodeReduction code) {
        if (code == null) {
            throw new IllegalArgumentException("Le code ne peut pas être null");
        }
        
        // Vérifier si le code existe déjà
        if (trouverCodeReduction(code.getCode()) != null) {
            throw new IllegalArgumentException("Le code " + code.getCode() + " existe déjà");
        }
        
        // Redimensionner si nécessaire
        if (nombreCodes >= codesReduction.length) {
            CodeReduction[] nouveauxCodes = new CodeReduction[codesReduction.length + 5];
            System.arraycopy(codesReduction, 0, nouveauxCodes, 0, nombreCodes);
            codesReduction = nouveauxCodes;
        }
        
        codesReduction[nombreCodes++] = code;
    }
    
    // Appliquer un code de réduction
    public int appliquerCode(String code, int totalBrut) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Le code ne peut pas être vide");
        }
        
        CodeReduction codeReduction = trouverCodeReduction(code);
        if (codeReduction == null) {
            throw new IllegalArgumentException("Code promo inconnu : \"" + code + "\"");
        }
        
        return codeReduction.calculerTotalNet(totalBrut);
    }
    
    // Générer un reçu complet
    public String genererRecu(String codeOptionnel) {
        StringBuilder recu = new StringBuilder();
        
        recu.append("===== REÇU KendiFood =====\n");
        
        if (taillePanier == 0) {
            recu.append("(panier vide)\n");
            return recu.toString();
        }
        
        // Trouver la longueur maximale pour l'alignement
        int maxIdLength = 0;
        for (int i = 0; i < taillePanier; i++) {
            int length = lignesPanier[i].getArticle().getId().length();
            if (length > maxIdLength) {
                maxIdLength = length;
            }
        }
        
        // Détail des articles
        for (int i = 0; i < taillePanier; i++) {
            LignePanier ligne = lignesPanier[i];
            String format = String.format("%%-%ds x%%d -> %%d cts%%n", maxIdLength + 2);
            recu.append(String.format(format, 
                ligne.getArticle().getId(), ligne.getQuantite(), ligne.sousTotal()));
        }
        
        int totalBrut = totalBrut();
        recu.append("Total brut : ").append(totalBrut).append(" cts\n");
        
        int totalNet = totalBrut;
        String codeApplique = null;
        int pourcentageApplique = 0;
        
        if (codeOptionnel != null && !codeOptionnel.isEmpty()) {
            try {
                totalNet = appliquerCode(codeOptionnel, totalBrut);
                codeApplique = codeOptionnel;
                pourcentageApplique = trouverPourcentageCode(codeOptionnel);
                recu.append("Code appliqué : ").append(codeApplique)
                    .append(" (-").append(pourcentageApplique).append("%)\n");
            } catch (IllegalArgumentException e) {
                recu.append("⚠️  Code ignoré : ").append(e.getMessage()).append("\n");
            }
        }
        
        recu.append("Total à payer : ").append(totalNet).append(" cts\n");
        recu.append("==========================\n");
        
        return recu.toString();
    }
    
    // Trouver un code de réduction
    private CodeReduction trouverCodeReduction(String code) {
        if (code == null) return null;
        
        for (int i = 0; i < nombreCodes; i++) {
            if (codesReduction[i].getCode().equals(code)) {
                return codesReduction[i];
            }
        }
        return null;
    }
    
    // Trouver le pourcentage d'un code
    private int trouverPourcentageCode(String code) {
        CodeReduction codeReduction = trouverCodeReduction(code);
        if (codeReduction != null) {
            return codeReduction.getPourcentage();
        }
        return 0;
    }
    
    // Afficher tous les codes disponibles
    public void afficherCodesReduction() {
        System.out.println("\n🎫 CODES DE RÉDUCTION DISPONIBLES");
        if (nombreCodes == 0) {
            System.out.println("(aucun code disponible)");
            return;
        }
        
        for (int i = 0; i < nombreCodes; i++) {
            System.out.println("- " + codesReduction[i]);
        }
    }
    
    // Les méthodes existantes de la Phase 2 restent inchangées...
    // ajouterAuPanier(), supprimerDuPanier(), totalBrut(), afficherPanier(), etc.
}