package Vue;

import Main.App;
import Modele.Hierarchie;
import Modele.MP3NewThread;
import Modele.Musique;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class VueResultatsRecherche extends Label {
	private int indiceList;
	private Musique musique;
	private String nomMusique;
	
	public VueResultatsRecherche(Musique m ,int indiceList) {
		super();
		this.indiceList = indiceList;
		this.musique = m;
		this.nomMusique = m.getTitre();
		
		// définir la couleur du fond en fonction de l'indice
		if(indiceList % 2 == 0) {
			this.setStyle("-fx-background-color : #f2f2f2");
		}
		else {
			this.setStyle("-fx-background-color : #e6e6e6");
		}
		// mise en forme
		this.setPadding(new Insets(0,0,0,10));
		this.setPrefSize(300, 35);
		this.setText(nomMusique+" - "+m.getAuteur());
		
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
		if(indiceList % 2 == 0) {
			this.setStyle("-fx-background-color : #dadada");
		}
		else {
			this.setStyle("-fx-background-color : #cfcfcf");
		}
	}
	
	private void eclaircirBackground() {
		if(indiceList % 2 == 0) {
			this.setStyle("-fx-background-color : #f2f2f2");
		}
		else {
			this.setStyle("-fx-background-color : #e6e6e6");
		}
	}
	
	private void lireMusique() {
		if (musique != Musique.musiqueJouée) {
			if (MP3NewThread.playerThread != null) {
    			MP3NewThread.kill();        			
    		}
			new MP3NewThread(musique.getMusicPath(),0); 			
		}
		Musique.musiqueJouée = musique;
		App.vmec.afficherMusiqueEnCours();
		App.va.viderRechercheTextField();
	}
}
