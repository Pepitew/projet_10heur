package Modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Record {
	
	private static String path = "data/info_music/";
	
	/* 
	 * Record.write()
	 * 
	 * Méthode d'écriture dans la base de donnée.
	 * La méthode prend en charge l'existance du fichier ou nom.
	 * 
	 * unString : Chaine à écrire dans la base de donnée
	 * fileName : Nom du fichier dans lequel la chaine sera écrite
	 */
	
	public static void write(String[] desString, String fileName) {
		// Création fileWriter
		try { 
			File file = new File(path+fileName);
			FileWriter writer = new FileWriter(file); 
			writer.write("");
			writer.close();
			writer = new FileWriter(file, true); 
			BufferedWriter output = new BufferedWriter(writer);
		
			// Création buffered et file writer / reader
			if(!file.exists()){
				file.createNewFile();
			}
		
			for (String unString : desString) {     
	           // Ajout de la nouvelle musique
	            output.write(unString);
	            output.newLine();
			}
	            // Fermeture des flux
	            output.close();
	            writer.close();
	
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    }
	
	/* 
	 * Record.read()
	 * 
	 * Méthode de lecture dans la base de donnée.
	 * 
	 * fileName : Nom du fichier dans lequel la chaine sera écrite
	 */
	
	public static void read(String fileName) {
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
            System.out.println("Lecture du fichier impossible");
            e.printStackTrace();
        }
    }
    /*
     * ecrire une méthode qui permet de supprimer la data base
     */


     /*
      * ecrire une méthode qui permet de supprimer une musique de la data base
      */

      
}
