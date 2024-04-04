package Modele;

// exemple
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat.Style;
import java.util.ArrayList;

import Modele.Musique.STYLE;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Ecriture {

	private static String path = "data/info_music/test.txt";

	public static void main(String[] args) {
		Musique m1 = new Musique("Salut", "je suis l'auteur", 60, false, STYLE.ELECTRO);
		Musique m2 = new Musique("sdjkfls<d", "je suis l'auteur", 60, false, STYLE.ELECTRO);
		Musique m3 = new Musique("hjkhj", "je suis l'auteur", 60, false, STYLE.ELECTRO);
		Musique m4 = new Musique("djkf", "je suis l'auteur", 60, false, STYLE.ELECTRO);
		Musique m5 = new Musique("gdflknnnbm", "je suis l'auteur", 60, false, STYLE.ELECTRO);



		Ecriture.enregistrement(m1);

	}



	public static void enregistrement(Musique music) {

		try {
			// Creates a FileWriter
			File file = new File(path);
			System.out.println(path);

			//Création buffered et file writer / reader
			FileWriter writer = new FileWriter(file);
			BufferedWriter output = new BufferedWriter(writer);

			FileReader reader = new FileReader(file);
			BufferedReader fichier = new BufferedReader(reader);

			// récupération de toutes les données existantes
			ArrayList<String> data = new ArrayList<>();

			String line = fichier.readLine(); // vaut null si le fichier est vide de base

			while(line != null) {
				System.out.println("lecture de la base de donnée ...");
				data.add(line);
			}
			
			System.out.println("fin de lecture");
			data.add(Musique.encoder(music));
			for(String ligne : data) {
				System.out.println("ecriture ..");
				System.out.println(ligne);
				output.write(ligne);
				output.newLine();
			}
			System.out.println("fin d'enregistrement");
			
			output.close();
			writer.close();
			reader.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}