package Controleur;

import java.io.IOException;

import Main.App;
import Modele.Hierarchie;
import Modele.Musique;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ControllerMusique {
	@FXML ImageView couvertureMusique;
	@FXML Label labelTitre;
	
	// affiche l'image de couverture le titre de la musique
	public void afficherMusique(String imagePath, String titre, String auteur) {
		Image img = new Image("file:"+imagePath);
		couvertureMusique.setImage(img);
		labelTitre.setText(titre+" - "+auteur);
	}
	
    //Méthode pour lire une musique
    @FXML
    private void lireMusique(MouseEvent m) {
    	Node target = (Node) m.getTarget();
    	if (target instanceof ImageView || target instanceof Label) {
    		target = target.getParent();
    	}
    	AnchorPane musiqueChoisi = (AnchorPane) target;
    	String nomMusique = ((Label) musiqueChoisi.getChildren().get(0)).getText().split(" - ")[0];
    	Musique musique_a_lire = Hierarchie.rechercher(nomMusique).first();
    	
    	App.va.ca.afficherMusiqueEnCours(musique_a_lire);
    }
}
