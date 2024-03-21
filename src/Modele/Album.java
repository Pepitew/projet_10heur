package Modele;

import java.util.ArrayList;

public class Album {
	//TODO liste de musique (On peut changer la SD)
	// Les musiques qui n'ont pas d'album seront dans un album fictif 'Pas d'album'
	
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
		
		//TODO
		
		return phrase.toString();
	}

}
