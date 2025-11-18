package food;

import java.io.File;
import java.util.Scanner;

public class SaisieUtil {
    private static Scanner scanner = new Scanner(System.in);
    
    public static int saisirEntier(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Quantité invalide (non numérique). Réessayez.");
            }
        }
    }
    
    public static String saisirString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
    
    public static String saisirCheminFichier(String message) {
        while (true) {
            String chemin = saisirString(message);
            File file = new File(chemin);
            if (file.exists() && file.isFile()) {
                return chemin;
            } else {
                System.out.println("❌ Chemin introuvable : " + chemin + " --- saisissez un autre chemin.");
            }
        }
    }
    
    public static double saisirDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Valeur invalide (non numérique). Réessayez.");
            }
        }
    }
}