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
	
	public static void encoder() {
		//StringBuilder phrase = new StringBuilder();
		
		for (Musique m : Hierarchie.hierarchie) {
			//phrase.append(Musique.encoder(m));
			Ecriture.enregistrement(Musique.encoder(m), "database");
		}
		
		Hierarchie.hierarchie.clear();
		
		//return phrase.toString();
	}
	
	public String toString() {
		return Hierarchie.hierarchie.toString();
		
	}
	
	


}
