package gst;

import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GestionUtilisateurs {
	private List<Utilisateur> utilisateurs;
	
	
	public GestionUtilisateurs(){
		this.utilisateurs = new ArrayList<>();
	}
	
	
	 public void ajouterUtilisateur(Utilisateur utilisateur) {
		 if(utilisateur != null) {
			 utilisateurs.add(utilisateur);
			 System.out.println("utilisateur ajoute avec succes");
		 }else {
			 System.out.println("utilisateur est null en paut pas ajoute");
		 }
	 }
	 
	 
	 
	 public void trierUtilisateurs () {
		 Collections.sort(utilisateurs);
	 }
	 
	 
	 public void afficherUtilisateurs() {
	        if (utilisateurs.isEmpty()) {
	            System.out.println("Aucun utilisateur à afficher.");
	        } else {
	            System.out.println("\n=== Liste des Utilisateurs ===");
	            for (Utilisateur u : utilisateurs) {
	                System.out.println(u.toString());
	            }
	            System.out.println("================================\n");
	        }
	    }
	 
	 
	 
	 
	 
	 public void sauvegarderDansFichier(String nomFichier) {
		try( ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(nomFichier))){
			os.writeObject(utilisateurs);
			
		}catch (IOException e) {
			System.out.println("ERREUR :" + e.getMessage());
		}
	 }
	 
	 
	 @SuppressWarnings("unchecked")
	 public void  chargerDepuisFichier(String nomFichier) {
		 try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))){
			 ArrayList<Utilisateur> liste_utlis = (ArrayList<Utilisateur>) ois.readObject();
			 for(Utilisateur u : liste_utlis) {
				 System.out.println(u);
			 }
		 }catch(IOException  e) {
			 System.out.println("Erreur :" + e.getMessage());
		 }catch(ClassNotFoundException e) {
			 System.out.println("Erreur :"+ e.getMessage());
		 }
	 }
	 
	 
}
