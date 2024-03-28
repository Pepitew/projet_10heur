// Contrôleur associé à la vue VueFormulaire 
package Controleur;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import javafx.animation.ScaleTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerFormulaire {
	// Variable de la class ControllerFormulaire
	String imageName;
	// Variable associée au fichier FXML
	@FXML
	private GridPane root;
	@FXML
	private Label labelTitre, labelAuteur, labelDuree;
	@FXML
	private TextField fieldTitre, fieldAuteur, fieldDuree;
	@FXML
	private Button btnAjouter;
	@FXML
	private AnchorPane anchorPaneImageMusic;
	@FXML
	private Label labelImageMusic;
	@FXML 
	private ImageView imageMusic;
	
	 // Création d'un dictionnaire (Map) associant les champs de texte aux labels
    private Map<TextField, Label> labelFieldMap = new HashMap<>();
	
    // action effectué au chargement du fichier FXML
	@FXML
	public void initialize() {
	    labelFieldMap.put(fieldTitre,labelTitre);
        labelFieldMap.put(fieldAuteur,labelAuteur);
        labelFieldMap.put(fieldDuree,labelDuree);
	}
	
	// mets le texte associer à la zone de saisie en noir lorsqu'elle est sélectionnée
	public void setLabelColor(MouseEvent m) {
		TextField target;
		if (m.getTarget() instanceof TextField) {
			target = (TextField) m.getTarget();	
		}
		else {
			target = (TextField)((Pane)m.getTarget()).getParent();
		}
		for (TextField textField : labelFieldMap.keySet()) {
			if (target == textField) {
				labelFieldMap.get(target).setTextFill(Paint.valueOf("black"));
			}
			else {
				labelFieldMap.get(textField).setTextFill(Paint.valueOf("#000000b2"));
			}
		}
	}
	
	// effet de grossissement du texte au survol de la souris
	public void growLabel(MouseEvent m) {
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), labelImageMusic);
        scaleTransition.setToX(1.1); // Mise à l'échelle en direction X
        scaleTransition.setToY(1.1); // Mise à l'échelle en direction Y
        scaleTransition.play();
	}
	// retour à la taille normal
	public void shrinkLabel(MouseEvent m) {
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), labelImageMusic);
        scaleTransition.setToX(1); // Mise à l'échelle en direction X
        scaleTransition.setToY(1); // Mise à l'échelle en direction Y
        scaleTransition.play();
	}
	
	// Permet de selectionner une image et de l'afficher
	public void addImage(){
		// Création d'une instance de FileChooser
        FileChooser fileChooser = new FileChooser();
        // Paramétrage du fileChooser
        fileChooser.setTitle("Choisissez une image");
        
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All images", "*.jpeg", "*.png", "*.jpg"));
        // Ouverture de la boite de dialogue
        File selectedFile = fileChooser.showOpenDialog(null);
        // Si une image est selectionnée...
        if (selectedFile != null) {
        	imageName = selectedFile.getName();
        	Image image = new Image(selectedFile.toURI().toString());	
        	// ... on l'affiche
        	imageMusic.setImage(image);
        	
        	// modification de l'affichage
        	labelImageMusic.setTranslateX(-40);
        	labelImageMusic.setTranslateY(100);
        	labelImageMusic.setText("Cliquer sur l'image pour la changer");
        	anchorPaneImageMusic.setOnMouseEntered(null);
        	anchorPaneImageMusic.setOnMouseExited(null);
        	anchorPaneImageMusic.setStyle("-fx-border-width:0;");
        	root.getChildren().remove(btnAjouter);
        	root.getChildren().add(btnAjouter);
      
        }
	}

	// Permet d'enregistrer des images dans le dossier Images
	public void saveImage(ActionEvent event) throws IOException{
		Image image = imageMusic.getImage();
		if (image != null) {
			if (! Files.exists(Paths.get("Image"))) {
				Files.createDirectory(Paths.get("Image"));
			}
			ImageIO.write(SwingFXUtils.fromFXImage(image, null),"png", new File("Image/"+imageName));			
			
			Parent root = FXMLLoader.load(getClass().getResource("/Vue/Application.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

			Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
			stage.setScene(scene);
			
			stage.show();	

			System.out.println("Image ajouter avec succès");
		}
	}
}
