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
	
	public static void main (String[] args) {
		Musique m1 = new Musique("une iezjfhjhdsbg", "leTitre", 658621, false, STYLE.ELECTRO);
		Musique m2 = new Musique("une zpoiaueriury", "leTitre", 658621, false, STYLE.ROCK);
		Musique m3 = new Musique("une cvbxcvnxvcnbvxc", "leTitre", 658621, false, STYLE.ELECTRO);
		Musique m4 = new Musique("ouai c'est micheeeeeel tu donne pas de nouvelle", "leTitre", 658621, false, STYLE.ELECTRO);
		
		Ecriture.enregistrement(Musique.encoder(m1));
		Ecriture.enregistrement(Musique.encoder(m2));
		Ecriture.enregistrement(Musique.encoder(m3));
		Ecriture.enregistrement(Musique.encoder(m4));
	}

	private static String path = "data/info_music/database";

	public static void enregistrement(String unString) {

		try {
			// Création fileWriter
			File file = new File(path);
			System.out.println(path);

			//Création buffered et file writer / reader
			if(!file.exists()){
				file.createNewFile();
			}
			FileReader reader = new FileReader(file);
			BufferedReader fichier = new BufferedReader(reader);

			FileWriter writer = new FileWriter(file);
			BufferedWriter output = new BufferedWriter(writer);
			
			
			System.out.println(file.exists());
			
			// récupération de toutes les données existantes
			
			ArrayList<String> data = new ArrayList<>();

			String line = fichier.readLine(); // vaut null si le fichier est vide de base sinon première ligne
			
			System.out.println(line);
			while(true) {
				System.out.println("lecture de la base de donnée ...");
				if(line == null) {
					break;
				}
				data.add(line);
				
				line = fichier.readLine();
				
				System.out.println(line);
			}
			
			//System.out.println("fin de lecture " + data.size() +" nombre de data distincte");
			data.add(unString); // ajoute la nouvelle musique à la liste.

			
			
			
			
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