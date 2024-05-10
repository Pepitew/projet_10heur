package Modele;

import java.awt.Image;
import java.util.ArrayList;

public class Musique implements Comparable<Musique>{
	
	
	public static enum STYLE {POP,ROCK,RAP,ELECTRO,METAL};
	public static int ID = 0;
	public static Musique musiqueJouée;

	
	public int ID_Musique;
	public STYLE style;
	public boolean isLiked;
	private String titre;
	private String auteur;
	private int duree; //En secondes
	private String couverture; //chemin de l'image
	

	public Musique(String titre, String auteur, int duree) {
		this.titre = titre;
		this.auteur = auteur;
		this.duree = duree;
		this.isLiked = false;
		Hierarchie.ajouterMusique(this);
		
	}
	public Musique(String titre, String auteur, int duree, boolean isLiked, STYLE style, String couverture) {
		this.titre = titre;
		this.auteur = auteur;
		this.duree = duree;
		this.isLiked = isLiked;
		this.style = style;
		this.couverture = couverture;
		Hierarchie.ajouterMusique(this);
		
	}
	

	public void ajouterImage(String img) {
		this.couverture = img; //CHemin de l'image
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
		String[] chaines = {m.titre, m.auteur, String.valueOf(m.duree), String.valueOf(m.isLiked), m.style.toString(), m.couverture};
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
		Musique m = new Musique(arguments.get(0), arguments.get(1), Integer.parseInt(arguments.get(2)), Boolean.parseBoolean(arguments.get(3)), STYLE.valueOf(arguments.get(4)), arguments.get(5));
		Hierarchie.ajouterMusique(m);
		return m;
	}
	public String toString() {
		StringBuilder phrase = new StringBuilder();
		phrase.append("Titre : " + this.titre + "\nAuteur : " + this.auteur + "\ndurée (secondes) :" + this.duree + "\nLiké ? : " + this.isLiked + "\nStyle : " + this.style.toString() + "\nChemin de l'image : " + this.couverture);
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
	public String getImage() {
		return this.couverture;
	}
	public STYLE getStyle() {
		return this.style;
	}
	@Override
	public int compareTo(Musique m) {
		return this.titre.compareTo(m.titre);
	}
	
	public static void main(String[] args) {
		Hierarchie h = new Hierarchie();
		Musique m1 = new Musique("Apix", "collander", 140, true, STYLE.POP, "/tata/quebec"); // créer 4 nouvelle musique
		Musique m2 = new Musique("Apex", "collander", 136, false, STYLE.ELECTRO, "/tata/quebec/calisse");
		Musique m3 = new Musique("Apax", "collander", 141, false, STYLE.RAP, "/tata/quebec/pétoche");
		Musique m4 = new Musique("Apox", "collander", 145, true, STYLE.ELECTRO, "");
		

		Hierarchie.encoder();
		
		Record.read("database");
		
		System.out.println(Hierarchie.rechercher(""));
		
		
	}


}
