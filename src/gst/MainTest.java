package gst;


import java.text.*;
import java.util.*;


public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			
			
			GestionUtilisateurs gestion = new GestionUtilisateurs();
			Utilisateur user1 = new Utilisateur("Ahmed Ali", "ahmed@bts-kendi.ma", sdf.parse("15/05/1995"));
			Utilisateur user2 = new Utilisateur("Fatima Zahra", "fatima@bts-kendi.ma", sdf.parse("20/08/1998"));
			
			
			
			
			//cree un utilisateur avec un amil invalide
			Utilisateur user3 = new Utilisateur("Hassan Bennani", "hassan@gmail.com", sdf.parse("29/03/2005"));
			
			
			// cree des employes
			Employe emp1 = new Employe(sdf.parse("01/01/2020"), 12345, "Karim Said", "karim@bts-kendi.ma", sdf.parse("10/03/1985"));
            Employe emp2 = new Employe(sdf.parse("15/06/2019"), 67890, "Leila Smith", "leila@bts-kendi.ma", sdf.parse("25/12/1990"));
            
            
            
            
            Enseignant ens1 = new Enseignant("Informatique", 20, sdf.parse("01/09/2018"), 11111, "Prof Informatique", "prof.info@bts-kendi.ma", sdf.parse("12/07/1980"));
            Enseignant ens2 = new Enseignant("Mathématiques", 18, sdf.parse("01/10/2017"), 22222, "Prof Maths", "prof.maths@bts-kendi.ma", sdf.parse("30/01/1975"));
            
            gestion.ajouterUtilisateur(user1);
            gestion.ajouterUtilisateur(user2);
            gestion.ajouterUtilisateur(emp1);
            gestion.ajouterUtilisateur(emp2);
            gestion.ajouterUtilisateur(ens1);
            gestion.ajouterUtilisateur(ens1);
            
            
            
            System.out.println("la liste des utilisateurs avant le tri");
            gestion.afficherUtilisateurs();
            
            
            
            
            System.out.println("la liste des utilisateurs apres le tri");
            gestion.trierUtilisateurs();
            gestion.afficherUtilisateurs();
            
            
            
            
            System.out.println("les details dun un Enseignant");
            ens1.afficher();
            
            
            
            System.out.println("test emp1 est equals a emp 2 " + emp1.equals(emp2));
            
            
            System.out.println("test si ens1 en meme class ens2" + ens1.isOfTheSameClassAs(ens2));
            System.out.println("test si ens1 en meme class amp1" + ens1.isOfTheSameClassAs(emp1));
            
            
            System.out.println("nombre total des utillisateurs : " + Utilisateur.countUsers());
            
            String nomFichier = "utilisateurs.dat";
            gestion.sauvegarderDansFichier(nomFichier);
            
            GestionUtilisateurs gestion2 = new GestionUtilisateurs();
            gestion2.chargerDepuisFichier(nomFichier);
            gestion2.afficherUtilisateurs();
            
            

		}catch(Exception e){
			
		}
	}

}
