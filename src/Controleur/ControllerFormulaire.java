// Contrôleur associé à la vue VueFormulaire 
package Controleur;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

import Main.App;
import Modele.Hierarchie;
import Modele.Musique;
import Modele.Record;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
	String nomImage;
	String nomMusique;
	AudioFile audioFile;
	// Variable associée au fichier FXML
	@FXML
	private GridPane root;
	@FXML
	private Label labelMusique,labelTitre, labelGenre, labelAuteur, labelDuree;
	@FXML
	private TextField fieldTitre, fieldGenre, fieldAuteur, fieldDuree;
	@FXML
	private Button btnAjouter;
	@FXML
	private AnchorPane anchorPaneImageMusic;
	@FXML
	private Label labelImageMusic;
	@FXML 
	private ImageView imageMusic, logoMusic;

	
	 // Création d'un dictionnaire (Map) associant les champs de texte aux labels
    private Map<TextField, Label> labelFieldMap = new HashMap<>();
	
    // action effectué au chargement du fichier FXML
	@FXML
	public void initialize() {
		//labelFieldMap.put(fieldMusique,labelMusique);
		// Remplir le dictionnaire labelFieldMap
		labelFieldMap.put(fieldTitre,labelTitre);
		labelFieldMap.put(fieldGenre,labelGenre);
        labelFieldMap.put(fieldAuteur,labelAuteur);
        labelFieldMap.put(fieldDuree,labelDuree);
        
     // Cette méthode attend que la scène soit entièrement chargée avant d'effectué la fonction de callback
        Platform.runLater(() -> {
        	// écouteur appelé à chaque fois que la taille change
        	anchorPaneImageMusic.widthProperty().addListener((obs, oldValue, newValue) -> {
        		this.imageMusic.setFitWidth(this.anchorPaneImageMusic.getWidth());
        		this.imageMusic.setFitHeight(this.anchorPaneImageMusic.getHeight()-40);
        	});
        	anchorPaneImageMusic.heightProperty().addListener((obs, oldValue, newValue) -> {
        		this.imageMusic.setFitWidth(this.anchorPaneImageMusic.getWidth());
        		this.imageMusic.setFitHeight(this.anchorPaneImageMusic.getHeight()-40);        	
        	});
        });
	}
	
	/** mets le texte associer à la zone de saisie en noir lorsqu'elle est sélectionnée **/
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
	
	/** effet de grossissement du texte au survol de la souris **/
	public void grow(MouseEvent m) {
		Node target = (Node) m.getTarget();
		if (target instanceof AnchorPane) {
			target = labelImageMusic;
		}
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), target);
        scaleTransition.setToX(1.1); // Mise à l'échelle en direction X
        scaleTransition.setToY(1.1); // Mise à l'échelle en direction Y
        scaleTransition.play();
	}
	/** retour à la taille normal **/
	public void shrink(MouseEvent m) {
		Node target = (Node) m.getTarget();
		if (target instanceof AnchorPane) {
			target = labelImageMusic;
		}
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), target);
        scaleTransition.setToX(1); // Mise à l'échelle en direction X
        scaleTransition.setToY(1); // Mise à l'échelle en direction Y
        scaleTransition.play();
	}
	
	/** Permet de selectionner une image et de l'afficher**/
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
        	nomImage = selectedFile.getName();
        	Image image = new Image(selectedFile.toURI().toString());	
        	// ... on l'affiche
        	imageMusic.setImage(image);
        	
        	this.imageLoaded();
      
        }
	}
	/**Méthode pour ajuster l'affichage après avoir chargé une image**/
	private void imageLoaded() {
		// modification de l'affichage
    	anchorPaneImageMusic.setTopAnchor(labelImageMusic, null);
    	anchorPaneImageMusic.setBottomAnchor(labelImageMusic,0.0);
    	labelImageMusic.setText("Cliquer sur l'image pour la changer");
    	labelImageMusic.setAlignment(Pos.CENTER_LEFT);
    	
    	anchorPaneImageMusic.setOnMouseEntered(null);
    	anchorPaneImageMusic.setOnMouseExited(null);
    	anchorPaneImageMusic.setOnMouseClicked(null);
    	anchorPaneImageMusic.setStyle("-fx-border-width:0;-fx-cursor:default;");
    	root.getChildren().remove(btnAjouter);
    	root.getChildren().add(btnAjouter);
	}
	
	/** Permet de selectionner un fichier mp3 et d'extraire ces méta données pour auto compléter les champs de saisies**/
	public void addMusique() {
		// Création d'une instance de FileChooser
        FileChooser fileChooser = new FileChooser();
        // Paramétrage du fileChooser
        fileChooser.setTitle("Choisissez une musique");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers audio (*.mp3, *.wav)", "*.mp3", "*.wav"));
        // Ouverture de la boite de dialogue
        File selectedFile = fileChooser.showOpenDialog(null);
        
        // Si une musique est selectionnée...
        if (selectedFile != null) {	
        	// ... on l'enregistre dans une variable 
            File mp3File = new File(selectedFile.getAbsolutePath());
            try {
            	// on récupère les métadonnées
            	audioFile = AudioFileIO.read(mp3File); 			
            	Tag tag = audioFile.getTag(); 
            	nomMusique = tag.getFirst(FieldKey.TITLE); 
            	Artwork artwork = tag.getFirstArtwork(); 
            	String genre = tag.getFirst(FieldKey.GENRE); 
            	String artist = tag.getFirst(FieldKey.ARTIST); 
            	int durationSeconds = audioFile.getAudioHeader().getTrackLength(); 
            	
            	// on les assignes au champ de saisi et au lecteur d'image
            	this.fieldTitre.setText(nomMusique);
            	this.fieldAuteur.setText(artist);
            	this.fieldGenre.setText(genre);
            	this.fieldDuree.setText(String.valueOf(durationSeconds));
            	
            	if (artwork != null) {
            	    // Convertir l'artwork en tableau de bytes
            	    byte[] imageData = artwork.getBinaryData();

            	    // Écrire le tableau de bytes dans un fichier temporaire
            	    File tempFile = File.createTempFile("artwork", ".jpg");
            	    try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            	        fos.write(imageData);
            	    }

            	    // Obtenir le chemin absolu du fichier temporaire
            	    String artworkPath = tempFile.getAbsolutePath();

            	    // Afficher l'image dans une ImageView
            	    Image image = new Image("file:" + artworkPath);
            	    
            	    this.imageMusic.setImage(image);
            	    this.imageLoaded();
            	}
			} catch (Exception e) {
				e.printStackTrace();
			}	
          	nomImage = nomMusique+".jpg"; 
        }
	}
	
	/** Permet d'enregister la musique dans la Hiérarchie 
	 * @throws CannotWriteException **/
	public void saveMusique(ActionEvent event) throws IOException, CannotWriteException{
		// enregister l'image de la musique en local
		Image image = imageMusic.getImage();
		if (image != null) {
			if (! Files.exists(Paths.get("data/Image"))) {
				Files.createDirectory(Paths.get("data/Image"));
			}
			ImageIO.write(SwingFXUtils.fromFXImage(image, null),"png", new File("data/Image/"+nomImage));			
		}
		// enregister la musique en local
		if (audioFile != null) {
			if (! Files.exists(Paths.get("data/Musique"))) {
				Files.createDirectory(Paths.get("data/Musique"));
			}
			AudioFileIO.writeAs(audioFile, "data/Musique/"+nomMusique);
		}
		// créer une nouvelle musique
		String titre = fieldTitre.getText();
		String auteur = fieldAuteur.getText();
		int duree = Integer.valueOf(fieldDuree.getText());
		String couverture = "data/Image/"+nomImage;
		Musique.STYLE style = Musique.STYLE.valueOf(fieldGenre.getText()); 
				
		Musique m = new Musique(titre,auteur,duree,false,style,couverture);
		Hierarchie.hierarchie.add(m);
		Hierarchie.encoder();
		Record.read("database");
		
		// changer de scene
		App.changerDeScene(App.nomScene.Application);
		
	}
}
