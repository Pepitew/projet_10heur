package Modele;

// exemple
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Ecriture {

	
	public static void main(String[] args) {
		Musique uneMusique = new Musique("Salut", "je suis l'auteur", 60);
		
		String infoMusique = Musique.encoder(uneMusique);
		
		System.out.println(infoMusique);
	}

	public static void enregistrement(String info) {
		
		
		System.out.println();
		/*
		try {
			// Creates a FileWriter
			FileWriter file = new FileWriter("output.txt");

			// Creates a BufferedWriter
			BufferedWriter output = new BufferedWriter(file);

			// Writes the string to the file
			output.write(data);

			// Closes the writer
			output.close();
		}

		catch (Exception e) {
			e.getStackTrace();
		}
		 */
	}
	
}