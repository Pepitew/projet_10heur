package Modele;

import java.util.TreeSet;

public class Hierarchie {
	
	public static TreeSet<Musique> hierarchie = new TreeSet<>();
	
	
	public static void ajouterMusique(Musique m) {
		Hierarchie.hierarchie.add(m);
	}
	
	public static void retirerMusique(Musique m) {
		Hierarchie.hierarchie.remove(m);
	}
	
	public static String encoder() {
		StringBuilder phrase = new StringBuilder();
		
		for (Musique m : Hierarchie.hierarchie) {
			phrase.append(Musique.encoder(m));
		}
		
		Hierarchie.hierarchie.clear();
		
		return phrase.toString();
	}
	
	


}
