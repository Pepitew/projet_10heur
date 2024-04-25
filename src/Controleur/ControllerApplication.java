package Controleur;

import java.io.IOException;

import com.jfoenix.controls.JFXSlider;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerApplication {
	// initialisation des variables
	@FXML
	GridPane root;
	@FXML
	ImageView logo;
	@FXML
	SVGPath play, previousArrow, nextArrow,	like;
	@FXML
	Button btnAddMusic;
	@FXML 
	FlowPane flowPaneLogo;
	@FXML
	JFXSlider lecteur;
	@FXML
	Label timeMaxLabel, timeCurrentLabel;
	@FXML
	HBox recommandationContainer;
	
	Timeline timelineBtnAddMusic;
	Stage stage;
	
	// initialize() est appelée dès le chargement du fichier fxml
	@FXML
	public void initialize() {
		// Cette méthode attend que la scène soit entièrement chargée avant d'effectué la fonction de callback
		 Platform.runLater(() -> {
	            this.stage = (Stage) this.root.getScene().getWindow();
	            // écouteur sur la largeur de flowPaneLogo pour définir la largeur du logo en fonction de la taille de la fenêtre
	            this.flowPaneLogo.widthProperty().addListener((obs, oldValue, newValue)->{
	            	this.logo.setFitWidth(this.flowPaneLogo.getWidth()*0.4);
	            });
	            
	            //écouteur sur la hauteur de flowPaneLogo pour définir la hauteur du logo en fonction de la taille de la fenêtre
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
	            
	            // affiche le temps de la musique
	            timeMaxLabel.setText(formatTime(lecteur.getMax()));
	        
	            //masque l'info-bulle au dessus du slider (lecteur de musique) 
	            lecteur.getChildrenUnmodifiable().get(3).setOpacity(0);
	        });
	}
	// fonction pour faire grossir un élément
	public void grow(MouseEvent m) {
		Node target = (Node) m.getTarget();
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), target);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        
        scaleTransition.play();
	}
	// fonction pour revenir à la taille standard d'un élément
	public void shrink(MouseEvent m) {
		Node target = (Node) m.getTarget();
		
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), target);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        
        scaleTransition.play();
	}
	
	// méthode pour agrandir le bouton qui ajoute une musique
	public void growBtnAddMusic(MouseEvent m) {
		timelineBtnAddMusic.setRate(1);
		timelineBtnAddMusic.play();
		btnAddMusic.setText("Ajouter une musique");
		timelineBtnAddMusic.setOnFinished(event -> {
			btnAddMusic.setText("Ajouter une musique");
		});
		}
	// méthode pour revenir à la taille normal du bouton qui ajoute une musique
	public void shrinkBtnAddMusic(MouseEvent m) {
		timelineBtnAddMusic.setRate(-1);
        timelineBtnAddMusic.play();
        timelineBtnAddMusic.setOnFinished(event -> {
			btnAddMusic.setText("+");
		});
	}
	// méthode pour changer la vue pour celle du formulaire
	public void moveToFormulaire(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/Vue/Formulaire.fxml"));
		
		Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
		stage.setScene(scene);
		stage.show();
	}
	// méthode pour mettre à jour le temps du lecteur de musique
	public void lecteurChange() {
		timeCurrentLabel.setText(formatTime(lecteur.getValue()));
	}
	  // Méthode pour formater le temps au format "min:sec"
    private String formatTime(double seconds) {
        int minutes = (int) seconds / 60;
        int remainingSeconds = (int) seconds % 60;
        return String.format("%d:%02d", minutes, remainingSeconds);
    }
    
    //Méthode pour afficher les musiques dans l'onglet recommandation
    private void afficherMusiqueRecommandee() {
    }
}
