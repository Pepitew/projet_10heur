package Controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerMusique {
	@FXML ImageView couvertureMusique;
	@FXML Label labelTitre;
	
	
	
	public void afficherMusique(String imagePath, String titre) {
		Image img = new Image(imagePath);
		couvertureMusique.setImage(img);
		
		labelTitre.setText(titre);
	}
}
