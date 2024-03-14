package Modele;

import java.awt.Image;

public class Musique {
	static enum STYLE {POP,ROCK,RAP,ELECTRO,SLAM};
	
	
	public STYLE[] styles; //Styles de la musique (peut en avoir plusieurs)
	public String titre;
	public String auteur;
	public int duree; //En minutes
	public Image couverture; //on peut changer l'Image
	
	public Musique(String titre, String auteur, int duree) {
		this.titre = titre;
		this.auteur = auteur;
		this.duree = duree;
	}
	public Musique(String titre, String auteur, int duree, Image couverture) {
		this.titre = titre;
		this.auteur = auteur;
		this.duree = duree;
		this.couverture = couverture;
	}
	
	public void ajouterImage(Image img) {
		this.couverture = img;
	}

	
}
