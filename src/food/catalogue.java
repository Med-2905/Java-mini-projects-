package food;
import java.io.*;
import java.util.Scanner;


public class catalogue {
	 private Article[] catalogue;
	    private int taillecatalogue;
	    private int capacite;
	    private int[][] statistiques;
	    
	    
	    public catalogue() {
	        this.capacite = 10;
	        this.catalogue = new Article[capacite];
	        this.taillecatalogue = 0;
	        this.statistiques = new int[capacite][2];
	    }
	    
	    private void agrandirCatalogue() {
	        
	        capacite += 10;
	        Article[] nouveauCatalogue = new Article[capacite];
	        int[][] nouvellesStats = new int[capacite][2];
	        
	        System.arraycopy(catalogue, 0, nouveauCatalogue, 0, catalogue.length);
	        System.arraycopy(statistiques, 0, nouvellesStats, 0, statistiques.length);
	        
	        catalogue = nouveauCatalogue;
	        statistiques = nouvellesStats;
	        
	        System.out.println("📦 Catalogue agrandi à " + capacite + " places");
	    }
	    
	    /*
	    public void ajouterArticle(Article article) {
			if (article == null) {
				System.out.println("Article null , impossible d'ajouter");
				return ;
			}
			
			if(taillecatalogue >= catalogue.length ) {
				System.out.println("catalogue");
				return ;
			}
			
			catalogue[taillecatalogue] = article;
			taillecatalogue++;
			
			System.out.println("✓ Article ajouté : " + article.getId());
		}*/
	    public void ajouterArticle(Article article) {
	        if (article == null) {
	            System.out.println("❌ Article null, impossible d'ajouter");
	            return;
	        }
	        
	        // ✅ Vérifie si l'article existe déjà
	        if (trouveParIndex(article.getId()) != -1) {
	            System.out.println("❌ Article '" + article.getId() + "' existe déjà");
	            return;
	        }
	        
	        // ✅ APPELLE agrandirCatalogue() si nécessaire
	        if (taillecatalogue >= catalogue.length) {
	            agrandirCatalogue();
	        }
	        
	        catalogue[taillecatalogue] = article;
	        taillecatalogue++;
	        
	        System.out.println("✓ Article ajouté : " + article.getId());
	    }
	    
	    public int trouveParIndex(String id) {
			if(id == null) {
				return -1;
			}
			
			for(int i=0 ; i< taillecatalogue ; i++) {
				if(catalogue[i].getId().equals(id)) {
					return i;
				}
			}
			
			return -1;
		}
		
		public void afficherCatalogue() {
			StringBuilder sb = new StringBuilder();
			System.out.println("\n ------CATALOGUE------\n");
			if(taillecatalogue ==0) {
				System.out.println("catalogue est vide ");
			}else {
				for(int i=0 ; i<taillecatalogue ; i++) {
					Article a = catalogue[i];
					
					sb.append("- ")
		              .append(a.getId()).append(" | ")
		              .append(a.getLibelle()).append(" | ")
		              .append(a.getPrixCentimes()).append(" cts | ")
		              .append("stock=").append(a.getStock())
		              .append("\n");
				}
			}
			sb.append("------------------------------------------------------\n");
			
			System.out.println(sb.toString());
		}
		
		public int getTailleCatalogue() {
	        return taillecatalogue;
	    }

		public Article[] getArticles() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public int chargerDepuisFichier(String chemin) throws KendiFoodException {
	        Scanner scanner = null;
	        int lignesChargees = 0;
	        
	        try {
	            File file = new File(chemin);
	            scanner = new Scanner(file);
	            
	            while (scanner.hasNextLine()) {
	                String ligne = scanner.nextLine().trim();
	                if (ligne.isEmpty() || ligne.startsWith("#")) {
	                    continue;
	                }
	                
	                String[] parties = ligne.split("\\|");
	                if (parties.length != 4) {
	                    System.err.println("Ligne mal formée ignorée: " + ligne);
	                    continue;
	                }
	                
	                try {
	                    String id = parties[0].trim();
	                    String libelle = parties[1].trim();
	                    int prix = Integer.parseInt(parties[2].trim());
	                    int stock = Integer.parseInt(parties[3].trim());
	                    
	                    Article article = new Article(id, libelle, prix, stock);
	                    ajouterArticle(article);
	                    lignesChargees++;
	                    
	                } catch (NumberFormatException e) {
	                    System.err.println("Erreur de format numérique dans la ligne: " + ligne);
	                } catch (IllegalArgumentException e) {
	                    System.err.println("Erreur de validation: " + e.getMessage());
	                }
	            }
	            
	            System.out.println(lignesChargees + " articles chargés depuis " + chemin);
	            return lignesChargees;
	            
	        } catch (FileNotFoundException e) {
	            throw new KendiFoodException("Chemin introuvable : " + chemin);
	        } finally {
	            if (scanner != null) {
	                scanner.close();
	            }
	        }
	    }
	    
	    // ✅ PHASE 5 - Sauvegarder vers fichier
	    public int sauvegarderVersFichier(String chemin) throws KendiFoodException {
	        PrintWriter writer = null;
	        
	        try {
	            writer = new PrintWriter(new FileWriter(chemin));
	            
	            for (int i = 0; i < taillecatalogue; i++) {
	                Article a = catalogue[i];
	                writer.printf("%s|%s|%d|%d%n", 
	                    a.getId(), a.getLibelle(), a.getPrixCentimes(), a.getStock());
	            }
	            
	            System.out.println("Catalogue sauvegardé dans " + chemin + " (" + taillecatalogue + " articles)");
	            return taillecatalogue;
	            
	        } catch (IOException e) {
	            throw new KendiFoodException("Erreur d'écriture dans le fichier: " + chemin);
	        } finally {
	            if (writer != null) {
	                writer.close();
	            }
	        }
	    }
	}
		

