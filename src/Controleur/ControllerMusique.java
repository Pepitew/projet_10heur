package Controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerMusique {
	@FXML ImageView couvertureMusique;
	@FXML Label labelTitre;
	
	// affiche l'image de couverture le titre de la musique
	public void afficherMusique(String imagePath, String titre, String auteur) {
		Image img = new Image("file:"+imagePath);
		couvertureMusique.setImage(img);
		labelTitre.setText(auteur+" - "+titre);
	}
}
