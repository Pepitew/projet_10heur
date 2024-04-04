package Modele;

import java.awt.Image;
import java.util.ArrayList;

public class Musique implements Comparable<Musique>{
	
	
	static enum STYLE {POP,ROCK,RAP,ELECTRO};
	public static int ID = 0;

	
	public int ID_Musique;
	public STYLE style;
	public boolean isLiked;
	private String titre;
	private String auteur;
	private int duree; //En minutes
	private Image couverture; //on peut changer l'Image
	

	public Musique(String titre, String auteur, int duree) {
		this.titre = titre;
		this.auteur = auteur;
		this.duree = duree;
		this.isLiked = false;
		Hierarchie.ajouterMusique(this);
		
	}
	public Musique(String titre, String auteur, int duree, boolean isLiked, STYLE style) {
		this.titre = titre;
		this.auteur = auteur;
		this.duree = duree;
		this.isLiked = isLiked;
		this.style = style;
		Hierarchie.ajouterMusique(this);
		
	}
	

	public void ajouterImage(Image img) {
		this.couverture = img;
	}
	

	
	/**
	 * Transforme une chanson en chaine de caractère compatible
	 * avec la sauvegarde
	 * DELIMITER : ';' entre chaque arguments (titre, auteur...)
	 * DELIMITER : '|' entre chaque musiques
	 * 
	 * 
	 * @returns String
	 * @return
	 */
	public static String encoder(Musique m) {
		//TODO Il faut ajouter les avis/commentaires
		StringBuilder phrase = new StringBuilder();
		String[] chaines = {m.titre, m.auteur, String.valueOf(m.duree), String.valueOf(m.isLiked), m.style.toString()};
		for (String element : chaines) {
			for (int i = 0; i < element.length(); i++) {
	            char caractere = element.charAt(i);
	            int asciiValue = (int) caractere;
	            String hexValue = Integer.toHexString(asciiValue);
	            phrase.append(hexValue);
	        }
			phrase.append(Integer.toHexString((int) ';'));
		}
		phrase.append(Integer.toHexString((int) '|'));
		return phrase.toString();
	}
	
	/**
	 * Décode une chaîne de caractères encodée pour renvoyer une musique.
	 * Cette méthode prend en entrée une chaîne de caractères encodée et la décode
	 * DELIMITER : ';' entre chaque arguments (titre, auteur...)
	 * DELIMITER : '|' entre chaque musiques.
	 * 
	 * @param chaineEncodee La chaîne de caractères encodée représentant une chanson
	 * @return La chanson décodée
	 */
	public static Musique decoder(String musiqueEncodee) {
		String mot = "";
		ArrayList<String> arguments = new ArrayList<String>();
	    for (int i = 0; i < musiqueEncodee.length(); i = i + 2) {
	    	String hexChar = musiqueEncodee.substring(i, i + 2);
	    	char caractere = (char) Integer.parseInt(hexChar, 16);
	    	if (caractere != '|' || caractere != ' ') {
	    		if (caractere == ';') {	    		
	    			arguments.add(mot);
	    			mot = "";
	    		} else {
	    			mot += caractere;
	    		}
	    	}
	    }
		Musique m = new Musique(arguments.get(0), arguments.get(1), Integer.parseInt(arguments.get(2)), Boolean.parseBoolean(arguments.get(3)), STYLE.valueOf(arguments.get(4)));
		Hierarchie.ajouterMusique(m);
		return m;
	}
	
	public String toString() {
		StringBuilder phrase = new StringBuilder();
		phrase.append("Titre : " + this.titre + "\nAuteur : " + this.auteur + "\ndurée (minutes) :" + this.duree + "\nLiké ? : " + this.isLiked + "\nStyle : " + this.style.toString() + "\n");
		return phrase.toString();
	}

	

	public String getTitre() {
		return this.titre;
	}
	public String getAuteur() {
		return this.auteur;
	}
	public int getDuree() {
		return this.duree;
	}
	public Image getImage() {
		return this.couverture;
	}
	@Override
	public int compareTo(Musique m) {
		return this.titre.compareTo(m.titre);
	}
	
	public static void main(String[] args) {
		Hierarchie h = new Hierarchie();
		Musique m1 = new Musique("Apix", "collander", 140, true, STYLE.POP); // créer 4 nouvelle musique
		Musique m2 = new Musique("Apex", "collander", 136, false, STYLE.ELECTRO);
		Musique m3 = new Musique("Apax", "collander", 141, false, STYLE.RAP);
		Musique m4 = new Musique("Apox", "collander", 145, true, STYLE.ELECTRO);
		

		System.out.println("hierarchie 1 : " + Hierarchie.hierarchie);

		String chaine = Hierarchie.encoder();
		
		
		System.out.println("Hierarchie 2 : " + Hierarchie.hierarchie);
		
		System.out.println();
 
		String[] musiques = chaine.split("7c"); // 7c correspond a | en ASCII
		for (String str : musiques) {
			Musique.decoder(str);
		}
		
		System.out.println("Hierarchie 3 : " + Hierarchie.hierarchie);
		
		
		
		
	}


}
