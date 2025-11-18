package gst;

import java.io.Serializable;
import java.util.Date;

public class Enseignant extends Employe implements Serializable {
	private String discipline;
    private int nbHeure;
    
    
    
	public Enseignant(String nomComplet, String email, Date dateNaissance, Date dateRecrutement, int ppr,
			String discipline, int nbHeure) {
		super(nomComplet, email, dateNaissance, dateRecrutement, ppr);
		this.discipline = discipline;
		this.nbHeure = nbHeure;
	}
    
	
	public Enseignant(String discipline, int nbHeure, Date dateRecrutement, int ppr, 
            String nomComplet, String email, Date dateNaissance) {
		super(dateRecrutement, ppr, nomComplet, email, dateNaissance);
		this.discipline = discipline;
		this.nbHeure = nbHeure;
}
	
	public boolean isOfTheSameClassAs(Object o) {
		if(this == o ) return true;
		if(o == null) return false;
		
		return this.getClass() == o.getClass();
	}
	@Override
    public String toString() {
        return super.toString() + ", Discipline: " + discipline + ", Nb Heures: " + nbHeure;
    }
	
	public String afficher() {
	    StringBuilder sb = new StringBuilder();

	    sb.append("=== Coordonnées de l'Enseignant ===\n");
	    sb.append("ID: ").append(getIdUser()).append("\n");
	    sb.append("Nom Complet: ").append(getNomComplet()).append("\n");
	    sb.append("Email: ").append(getEmail()).append("\n");
	    sb.append("Date de Naissance: ").append(getDateNaissance()).append("\n");
	    sb.append("Date de Recrutement: ").append(getDateRecrutement()).append("\n");
	    sb.append("PPR: ").append(getPpr()).append("\n");
	    sb.append("Discipline: ").append(discipline).append("\n");
	    sb.append("Nombre d'Heures: ").append(nbHeure).append("\n");
	    sb.append("===================================\n");

	    return sb.toString();
	}

	 
	
	public String getDiscipline() {
        return discipline;
    }

    public int getNbHeure() {
        return nbHeure;
    }
	
    
}
