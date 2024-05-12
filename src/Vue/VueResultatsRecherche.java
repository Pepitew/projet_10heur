package Vue;

import Main.App;
import Modele.Hierarchie;
import Modele.Musique;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class VueResultatsRecherche extends Label {
	private int indice;
	private String nomMusique;
	
	public VueResultatsRecherche(String nomMusique, String auteurMusique, int indice) {
		super();
		this.indice = indice;
		this.nomMusique = nomMusique;
		
		// définir la couleur du fond en fonction de l'indice
		if(indice % 2 == 0) {
			this.setStyle("-fx-background-color : #f2f2f2");
		}
		else {
			this.setStyle("-fx-background-color : #e6e6e6");
		}
		// mise en forme
		this.setPadding(new Insets(0,0,0,10));
		this.setPrefSize(250, 30);
		this.setText(nomMusique+" - "+auteurMusique);
		
		// assombri le fond du label au survol de la souris
		this.setOnMouseEntered(event ->{
			this.assombrirBackground();			
		});
		this.setOnMouseExited(event ->{
			this.eclaircirBackground();			
		});
		// lire la musique au click de la souris sur le label
		this.setOnMousePressed(event -> {
			this.lireMusique();
		});
	}
	
	private void assombrirBackground() {
		if(indice % 2 == 0) {
			this.setStyle("-fx-background-color : #dadada");
		}
		else {
			this.setStyle("-fx-background-color : #cfcfcf");
		}
	}
	
	private void eclaircirBackground() {
		if(indice % 2 == 0) {
			this.setStyle("-fx-background-color : #f2f2f2");
		}
		else {
			this.setStyle("-fx-background-color : #e6e6e6");
		}
	}
	
	private void lireMusique() {
		Musique musique_a_lire = Hierarchie.rechercher(nomMusique).first();
		Musique.musiqueJouée = musique_a_lire;
		App.vmec.afficherMusiqueEnCours();
		App.va.viderRechercheTextField();
	}
}
