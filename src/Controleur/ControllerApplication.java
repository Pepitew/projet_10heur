package Controleur;

import java.io.IOException;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerApplication {
	@FXML
	ImageView logo, previousArrow, nextArrow, play, like;
	@FXML
	Button btnAddMusic;
	
	public void imageGrowth(MouseEvent m) {
		ImageView target = (ImageView) m.getTarget();
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), target);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        
        scaleTransition.play();
	}
	public void imageShrinkage(MouseEvent m) {
		ImageView target = (ImageView) m.getTarget();
		
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), target);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        
        scaleTransition.play();
	}
	
	public void moveToFormulaire(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/Vue/Formulaire.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	
		Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
		stage.setScene(scene);
		stage.show();
	}
}
