package Controleur;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerApplication {
	@FXML
	GridPane root;
	@FXML
	ImageView logo, previousArrow, nextArrow, play, like;
	@FXML
	Button btnAddMusic;
	@FXML 
	FlowPane flowPaneLogo;
	
	Timeline timelineBtnAddMusic;
	Stage stage;
	
	@FXML
	public void initialize() {
		// Cette méthode attend que la scne soit entièrement chargée avant d'effectué la fonction de callback
		 Platform.runLater(() -> {
	            this.stage = (Stage) this.root.getScene().getWindow();
	            // écouteur sur la largeur de flowPaneLogo
	            this.flowPaneLogo.widthProperty().addListener((obs, oldValue, newValue)->{
	            	this.logo.setFitWidth(this.flowPaneLogo.getWidth()*0.4);
	            });
	            
	            //écouteur sur la hauteur de flowPaneLogo
	            this.flowPaneLogo.heightProperty().addListener((obs, oldValue, newValue)->{
	            	this.logo.setFitHeight(this.flowPaneLogo.getHeight()*0.9);
	            });
	            
	            //initialisation de la timeline qui sert à resize le btn qui ajouter une musique
	            timelineBtnAddMusic = new Timeline();
	            
	            timelineBtnAddMusic.getKeyFrames().addAll(
	    	            new KeyFrame(Duration.ZERO, 
	    	            		new KeyValue(btnAddMusic.prefWidthProperty(), btnAddMusic.getPrefWidth())),	    	            new KeyFrame(Duration.seconds(0.4), 
	    	            		new KeyValue(btnAddMusic.prefWidthProperty(), 200))
	    	        );
	            
           
	        });
	}
	
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
	
	
	public void growBtnAddMusic(MouseEvent m) {
		timelineBtnAddMusic.setRate(1);
		timelineBtnAddMusic.play();
		btnAddMusic.setText("Ajouter une musique");
		timelineBtnAddMusic.setOnFinished(event -> {
			btnAddMusic.setText("Ajouter une musique");
		});
		}
	public void shrinkBtnAddMusic(MouseEvent m) {
		timelineBtnAddMusic.setRate(-1);
        timelineBtnAddMusic.play();
        timelineBtnAddMusic.setOnFinished(event -> {
			btnAddMusic.setText("+");
		});
	}
	
	public void moveToFormulaire(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/Vue/Formulaire.fxml"));
		
		Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
		stage.setScene(scene);
		stage.show();
	}
}
