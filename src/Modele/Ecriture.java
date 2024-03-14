package Modele;

// exemple
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Ecriture {

	
	public static void main(String[] args) {
		Musique uneMusique = new Musique("Salut", "je suis l'auteur", 60);
		enregistrement(uneMusique);
	}

	public static void enregistrement(Musique music) {
		String data;
		data = music.getTitre().hashCode()+";"+music.getAuteur().hashCode();
		System.out.println(data);
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