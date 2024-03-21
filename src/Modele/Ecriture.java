package Modele;

// exemple
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Ecriture {



	public static void main(String[] args) {
		Musique m1 = new Musique("Salut", "je suis l'auteur", 60);
		Musique m2 = new Musique("sd;jkfls<d", "je suis l'auteur", 60);
		Musique m3 = new Musique("hjk:;,hj", "je suis l'auteur", 60);
		Musique m4 = new Musique("djkf", "je suis l'auteur", 60);
		Musique m5 = new Musique("gdflknnnbm", "je suis l'auteur", 60);



		Ecriture.enregistrement(m1);

	}



	public static void enregistrement(Musique music) {

		try {
			// Creates a FileWriter
			File file = new File("data/info_music/test");

			if(file.exists()) {

				FileReader reader = new FileReader(file);

				System.out.println("le fichier existe déjà :");

				BufferedReader fichier = new BufferedReader(reader);
				String line = fichier.readLine();
				while (line != null) {
					System.out.println(line);
					//parsing manuel -> peupler le modèle
					line = fichier.readLine();
				}
			}


		} else {

			System.out.println("Existe pas : data/info_music/inf_music ");				
			FileWriter writer = new FileWriter(file);
			// Creates a BufferedWriter
			BufferedWriter output = new BufferedWriter(writer);

			// Writes the string to the file
			output.write(Musique.encoder(music));

			// Closes the writer
			output.close();

		}
	}

	catch (Exception e) {
		e.getStackTrace();
	}

}

}