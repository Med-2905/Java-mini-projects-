package food;

public class CodeReduction {
	private String code;
    private int pourcentage;
    
    // Constructeur avec validation
    public CodeReduction(String code, int pourcentage) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Le code ne peut pas être vide");
        }
        if (pourcentage < 1 || pourcentage > 50) {
            throw new IllegalArgumentException("Le pourcentage doit être entre 1 et 50");
        }
        
        this.code = code.trim();
        this.pourcentage = pourcentage;
    }
    
    // Getters
    public String getCode() {
        return code;
    }
    
    public int getPourcentage() {
        return pourcentage;
    }
    
    // Setters avec validation
    public void setCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Le code ne peut pas être vide");
        }
        this.code = code.trim();
    }
    
    public void setPourcentage(int pourcentage) {
        if (pourcentage < 1 || pourcentage > 50) {
            throw new IllegalArgumentException("Le pourcentage doit être entre 1 et 50");
        }
        this.pourcentage = pourcentage;
    }
    
    // Calculer le montant de la réduction
    public int calculerReduction(int totalBrut) {
        return totalBrut * pourcentage / 100;
    }
    
    // Calculer le total net après réduction
    public int calculerTotalNet(int totalBrut) {
        return totalBrut - calculerReduction(totalBrut);
    }
    
    @Override
    public String toString() {
        return String.format("%s (-%d%%)", code, pourcentage);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CodeReduction that = (CodeReduction) obj;
        return code.equals(that.code);
    }
    
    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
