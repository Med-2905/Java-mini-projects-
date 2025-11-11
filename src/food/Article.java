package food;

public class Article {
	private String id;
	private String libelle;
	
	private int prixCentimes;
	private int stock;
	public Article(String  id, String libelle, int prixCentimes, int stock) {
		if(id == null || id.trim().isBlank()) throw new IllegalArgumentException("Id est vide");
		if(libelle == null || libelle.trim().isBlank()) throw new IllegalArgumentException("Libelle est vide");
		if(prixCentimes <0) throw new IllegalArgumentException("le prix peut pas etre negatif");
		if(stock < 0 ) throw new IllegalArgumentException("le stock ne peut pas etre negatif");
		this.id = id;
		this.libelle = libelle;
		this.prixCentimes = prixCentimes;
		this.stock = stock;
	}
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getId() {
		return id;
	}
	public String getLibelle() {
		return libelle;
	}
	public int getPrixCentimes() {
		return prixCentimes;
	}
	
	
	
	@Override
	public String toString() {
	    return String.format("%s | %s | %d cts | stock=%d", id, libelle, prixCentimes, stock);
	}
	
	
	
	
	
	
	
	
}
