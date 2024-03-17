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

}
