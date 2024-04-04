package Modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Lecture {

	private static String path = "data/info_music/database";
	
	public Lecture() {
		
	}
	
	public void lire() {
		Hierarchie h = new Hierarchie();
		
		try {
			FileReader fichier = new FileReader(path);
			BufferedReader reader = new BufferedReader(fichier);
			
			ArrayList<String> data = new ArrayList<>();
			
			while(reader.readLine() != null) {
				data.add()
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
