package Modele;

import java.util.ArrayList;

public class Album {
	
	public ArrayList<Musique> liste = new ArrayList<Musique>(); //Liste des musiques
	private String titre;
	private int nbMusique = 0;
	
	public Album(String titre) {
		this.titre = titre;
	}
	
	
	public void ajouterMusique(Musique m) {
		this.liste.add(m);
		this.nbMusique++;
	}
	
	//TODO Album.decoder / albul.encoder
	
	//Encode toutes les musiques de Album dans inf_music
	public static String encoderMusique(Album a) {
		StringBuilder phrase = new StringBuilder();
		
		for (Musique m : a.liste) {
			phrase.append(Musique.encoder(m));
		}
		
		return phrase.toString();
	}
	
	//Encode les informations de l'album dans inf_album
	public static String encoderAlbum(Album a) {
		StringBuilder phrase = new StringBuilder();
		String[] chaines = {a.titre, String.valueOf(a.nbMusique)};
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
	
	public static Album decoderAlbum(String a) {
		String mot = "";
		ArrayList<String> arguments = new ArrayList<String>();
	    for (int i = 0; i < a.length(); i = i + 2) {
	    	String hexChar = a.substring(i, i + 2);
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
		
	    return new Album(arguments.get(0));
	}
	
	public String toString() {
		StringBuilder phrase = new StringBuilder();
		
		phrase.append("Titre de l'album : " + this.titre + "\nNombre de musiques : " + this.nbMusique);
		return phrase.toString();
	}

}
