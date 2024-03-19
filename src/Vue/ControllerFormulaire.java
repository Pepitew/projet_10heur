// Contrôleur associé à la vue VueFormulaire 
package Vue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import javafx.animation.ScaleTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class ControllerFormulaire {
	
	// Variable associée au fichier FXML
	@FXML
	private Label labelTitre, labelAuteur, labelDuree;
	@FXML
	private TextField fieldTitre, fieldAuteur, fieldDuree;
	@FXML
	private Button btnAjouter;
	@FXML
	private Label labelAddMusic;
	
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
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), labelAddMusic);
        scaleTransition.setToX(1.1); // Mise à l'échelle en direction X
        scaleTransition.setToY(1.1); // Mise à l'échelle en direction Y
        scaleTransition.play();
	}
	// retour à la taille normal
	public void shrinkLabel(MouseEvent m) {
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), labelAddMusic);
        scaleTransition.setToX(1); // Mise à l'échelle en direction X
        scaleTransition.setToY(1); // Mise à l'échelle en direction Y
        scaleTransition.play();
	}
	
	// Permet d'enregistrer des images dans le dossier Images
	public void addImage() throws IOException {
		// Création d'une instance de FileChooser
        FileChooser fileChooser = new FileChooser();
        // Paramétrage du fileChooser
        fileChooser.setTitle("Choisissez une image");
        fileChooser.setInitialDirectory(new File("C:\\Users\\thiba\\Pictures"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All images", "*.jpeg", "*.png", "*.jpg"));
        // Ouverture de la boite de dialogue
        File selectedFile = fileChooser.showOpenDialog(null);
        // Si une image est selectionnée...
        if (selectedFile != null) {
        	String imageName = selectedFile.getName();
        	Image image = new Image(selectedFile.toURI().toString());	
        	// ... on l'enregistre
        	ImageIO.write(SwingFXUtils.fromFXImage(image, null),"png", new File("Image/"+imageName));
        	System.out.println("Image enregistrer avec succès");
        }
	}
}
