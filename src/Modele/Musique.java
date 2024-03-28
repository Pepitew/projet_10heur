package Modele;

import java.awt.Image;
import java.util.ArrayList;

public class Musique {
	static enum STYLE {POP,ROCK,RAP,ELECTRO,SLAM};


	public ArrayList<STYLE> styles; //Styles de la musique (peut en avoir plusieurs)
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
	}
	public Musique(String titre, String auteur, int duree, Image couverture) {
		this.titre = titre;
		this.auteur = auteur;
		this.duree = duree;
		this.couverture = couverture;
		this.isLiked = false;
		
	}

	public void ajouterImage(Image img) {
		this.couverture = img;
	}
	
	public void ajouterStyle(STYLE style) {
		this.styles.add(style);
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
		String[] chaines = {m.titre, m.auteur, String.valueOf(m.duree)};
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
		
	    return new Musique(arguments.get(0), arguments.get(1), Integer.parseInt(arguments.get(2)));
	}
	
	public String toString() {
		StringBuilder phrase = new StringBuilder();
		phrase.append("Titre : " + this.titre + "\nAuteur : " + this.auteur + "\ndurée (minutes) :" + this.duree + "\n");
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
	
	public static void main(String[] args) {
		Musique m1 = new Musique("Apix", "collander", 140); // créer 4 nouvelle musique
		Musique m2 = new Musique("Apex", "collander", 136);
		Musique m3 = new Musique("Apax", "collander", 141);
		Musique m4 = new Musique("Apox", "collander", 145);
		Album a = new Album("Apex le roi");
		
		a.ajouterMusique(m1);
		a.ajouterMusique(m2);
		a.ajouterMusique(m3);
		a.ajouterMusique(m4);
		
		String musiquesEncodees = Album.encoderMusique(a);
		String albumEncode = Album.encoderAlbum(a);
		
		System.out.println(musiquesEncodees);
		System.out.println(albumEncode);
		
		
		Album a2 = Album.decoderAlbum(albumEncode);
		
		System.out.println(a2);
		
		String chaine = "";
		chaine = Musique.encoder(m1) + Musique.encoder(m2) + Musique.encoder(m3) + Musique.encoder(m4); //Sauvegarde les musique dans une chaine de caractere
		
		String[] musiques = chaine.split("7c"); // 7c correspond a | en ASCII
		ArrayList<Musique> nouvellesMusiques = new ArrayList<Musique>(); //liste dans laquelle on contiendra toutes les nouvelles Musiques
		for (String str : musiques) {
			nouvellesMusiques.add(Musique.decoder(str));
		}
		System.out.println(nouvellesMusiques);
		
		
		
		
	}


}
