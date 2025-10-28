package food;

public class LignePanier {
    private Article article;
    private int quantite;
    
    public LignePanier(Article article, int quantite) {
        if (article == null) {
            throw new IllegalArgumentException("L'article ne peut pas être null");
        }
        if (quantite <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }
        this.article = article;
        this.quantite = quantite;
    }
    public Article getArticle() {
        return article;
    }
    
    
    
    public int getQuantite() {
        return quantite;
    }
    
    public void setQuantite(int quantite) {
        if (quantite <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }
        this.quantite = quantite;
    }
    
    public int sousTotal() {
        return article.getPrixCentimes() * quantite;
    }
    
    @Override
    public String toString() {
        return String.format("- %s x %d => %d cts", 
                           article.getId(), quantite, sousTotal());
    }
}