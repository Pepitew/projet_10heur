package Modele;

import java.awt.Image;

public class Musique {
	static enum STYLE {POP,ROCK,RAP,ELECTRO,SLAM};


	public STYLE[] styles; //Styles de la musique (peut en avoir plusieurs)
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
	
	/**
	 * Transforme une chanson en chaine de caractère compatible
	 * avec la sauvegarde
	 * DELIMITER : ';' entre chaque mots
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
	 * Décode une chaîne de caractères encodée pour représenter une chanson.
	 * Cette méthode prend en entrée une chaîne de caractères encodée et la décode
	 * DELIMITER : ';' entre chaque mots
	 * DELIMITER : '|' entre chaque musiques.
	 * 
	 * @param chaineEncodee La chaîne de caractères encodée représentant une chanson
	 * @return La chanson décodée
	 */
	public static String decoder(String musiqueEncodee) {
		StringBuilder phrase = new StringBuilder();
	    for (int i = 0; i < musiqueEncodee.length(); i = i + 2) {
	    	String hexChar = musiqueEncodee.substring(i, i + 2);
	    	char caractere = (char) Integer.parseInt(hexChar, 16);
	    	if (caractere == ';') {	    		
	    		phrase.append(" ");
	    	} else {
	    		phrase.append(caractere);
	    	}
	    }
		
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
		Musique m1 = new Musique("Salut", "les nullos", 140);
		String chaine = Musique.encoder(m1);
		System.out.println(chaine);
		String chaine2 = Musique.decoder(chaine);
		System.out.println(chaine2);
		
	}


}
