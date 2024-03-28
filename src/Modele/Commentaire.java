package Modele;

public class Commentaire {
	
	
	public static int ID = 0;
	public int ID_Commentaire;
	public String nom;
	public String texte;
	
	public Commentaire(String nom, String texte) {
		this.nom = nom;
		this.texte = texte;
		this.ID_Commentaire = Commentaire.ID;
		Commentaire.ID++;
	}

}
