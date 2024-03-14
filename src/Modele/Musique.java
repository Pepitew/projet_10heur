package Modele;

import java.awt.Image;

public class Musique {
	static enum STYLE {POP,ROCK,RAP,ELECTRO,SLAM};


	public STYLE[] styles; //Styles de la musique (peut en avoir plusieurs)
	private String titre;
	private String auteur;
	private int duree; //En minutes
	private Image couverture; //on peut changer l'Image

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


}
