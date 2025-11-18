package gst;

import java.io.Serializable;
import java.util.Date;

public class Employe extends Utilisateur implements Serializable{
	
	
	private Date dateRecrutement; 
    private int ppr;
	public Employe(String nomComplet, String email, Date dateNaissance, Date dateRecrutement, int ppr) {
		super(nomComplet, email, dateNaissance);
		this.dateRecrutement = dateRecrutement;
		this.ppr = ppr;
	} 
	
	
	public Employe(Date dateRecrutement, int ppr, String nomComplet, String email, Date dateNaissance) {
	        super(nomComplet, email, dateNaissance);
	        this.dateRecrutement = dateRecrutement;
	        this.ppr = ppr;
	   }
    
    
	 @Override 
	    public int getIdUser(){ 
		 return super.getIdUser();
	 } 
	
	public boolean equals(Employe e) {
		if(this == e ) return true;
		if(e == null) return false;
		
		
		Employe emp = (Employe) e ;
		return this.getIdUser() == emp.getIdUser();
	}
	
	
	public String toString() {
		return super.toString() + ", Date Recrutement: " + dateRecrutement + ", PPR: " + ppr;
	}
	
	
	public Date getDateRecrutement() {
        return dateRecrutement;
    }

    public int getPpr() {
        return ppr;
    }
    
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
