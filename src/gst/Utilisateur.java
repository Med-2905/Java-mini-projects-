package gst;

import java.io.*;
import java.util.Date;

public class Utilisateur implements Serializable, Comparable<Utilisateur> {
	private int idUser; 
    private String nomComplet, email ; 
    private Date dateNaissance; 
    private static int nbUsers = 0;
    
    
	public Utilisateur( String nomComplet, String email, Date dateNaissance) {
		
		this.idUser = incrUser();
		this.nomComplet = nomComplet;
		
		this.dateNaissance = dateNaissance;
		
		String emailVerifie = verifEmail(email);
		if(emailVerifie != null) {
			this.email  = emailVerifie;
		}else {
			System.out.println("inccorect , email non ajoute");
		}
	} 
    
	
	public int incrUser() {
		nbUsers++;
		return nbUsers;
	}
	public String verifEmail(String email) {
		if(email != null && email.contains("@bts-kendi.ma")) {
			return email;
		}else {
			System.out.println("email incorrect ");
			return null ;
		}
		
	}
    
	public static int countUsers() {
		return nbUsers;
	} 
    
	
	@Override
	public String toString() {
		return "Nom: " + nomComplet + ", Email: " + email;
	}
	
	@Override 
    public int compareTo(Utilisateur autre) {
		return this.nomComplet.compareToIgnoreCase(autre.nomComplet);
	}


	public String getNomComplet() {
		return nomComplet;
	}


	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}


	public Date getDateNaissance() {
		return dateNaissance;
	}


	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public String getEmail() {
		return email;
	}


	
	
	
	
    
}
