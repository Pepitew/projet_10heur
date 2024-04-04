package Modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Lecture {
    
    private static String path = "data/info_music/";

    public static void lire(String fileName) {
        try {
            // Ouverture du fichier
            File file = new File(path + fileName);
            FileReader reader = new FileReader(file);
            BufferedReader fichier = new BufferedReader(reader);

            String ligne;
            // Lecture et traitement de chaque ligne du fichier
            while ((ligne = fichier.readLine()) != null) {
                // Décodage de la musique à partir de la ligne
                Musique musique = Musique.decoder(ligne);
                // Affichage de la musique (ou traitement supplémentaire si nécessaire)
                //System.out.println(musique.toString());
            }
            // Fermeture des flux
            fichier.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
