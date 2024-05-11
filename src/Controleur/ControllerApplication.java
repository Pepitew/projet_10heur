package Controleur;

import java.io.IOException;

import com.jfoenix.controls.JFXSlider;

import Main.App;
import Modele.Hierarchie;
import Modele.Musique;
import Vue.VueMusique;
import Vue.VueOptionsFiltrage;
import Vue.VueResultatsRecherche;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class ControllerApplication {
	// initialisation des variables
	@FXML
	GridPane root;
	@FXML
	ImageView logo;
	@FXML
	ImageView imageViewMusiqueEnCours;
	@FXML
	SVGPath play, previousArrow, nextArrow,	like, pause;
	@FXML
	Button btnAddMusic;
	@FXML 
	FlowPane flowPaneLogo;
	@FXML
	JFXSlider lecteur;
	@FXML
	Label timeMaxLabel, timeCurrentLabel, labelNomMusiqueEnCours ,labelAuteurMusiqueEnCours,labelGenreMusiqueEnCours;
	@FXML
	HBox recommandationContainer;
	@FXML
	VBox vboxRecherche;
	@FXML
	ScrollPane scrollPaneRecommandations;
	@FXML
	public TextField textFieldRechercher;
	@FXML
	AnchorPane placeHolderOptionsFiltrage;


	
	Timeline timelineBtnAddMusic;

	
	// initialize() est appelée dès le chargement du fichier fxml
	@FXML
	public void initialize() {
		// Cette méthode attend que la scène soit entièrement chargée avant d'effectué la fonction de callback
		 Platform.runLater(() -> {
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
	    	            		new KeyValue(btnAddMusic.prefWidthProperty(), btnAddMusic.getPrefWidth())), new KeyFrame(Duration.seconds(0.4), 
	    	            		new KeyValue(btnAddMusic.prefWidthProperty(), 200))
	    	        );
	            
	            //masque l'info-bulle au dessus du slider (lecteur de musique) 
	            lecteur.getChildrenUnmodifiable().get(3).setOpacity(0);
	            
	            
	            
	            //appel de méthode nécessaire pour un affichage correct au lancement
        		this.afficherMusiqueRecommandee();
        		this.afficherMusiqueEnCours();
        		this.afficherRechercheMusique();	           
        		
    		   // mise en place de la vue options filtrage
    		   root.getChildren().remove(placeHolderOptionsFiltrage);
    		   root.add(App.vof, 0, 7);
    		   GridPane.setRowSpan(App.vof, 4);
    		   GridPane.setColumnSpan(App.vof, 2);
    		   
    		   /** TEST **/
    		   //ajout d'un évènement sur la hbox recommandationContainer pour défiler au scroll de la souris
    		   recommandationContainer.setOnScroll(event -> {
    	            scrollPaneRecommandations.setHvalue(scrollPaneRecommandations.getHvalue()+event.getDeltaY()/recommandationContainer.getChildren().size()/10);
    		   });
    	        /** TEST **/
	        });
	}
	/** méthode pour faire grossir un élément **/
	public void grow(MouseEvent m) {
		Node target = (Node) m.getTarget();
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), target);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        
        scaleTransition.play();
	}
	/** méthode pour revenir à la taille standard d'un élément **/
	public void shrink(MouseEvent m) {
		Node target = (Node) m.getTarget();
		
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), target);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        
        scaleTransition.play();
	}
	
	/** méthode pour agrandir le bouton qui ajoute une musique **/
	public void growBtnAddMusic(MouseEvent m) {
		timelineBtnAddMusic.setRate(1);
		timelineBtnAddMusic.play();
		btnAddMusic.setText("Ajouter une musique");
		timelineBtnAddMusic.setOnFinished(event -> {
			btnAddMusic.setText("Ajouter une musique");
		});
		}
	/** méthode pour revenir à la taille normal du bouton qui ajoute une musique **/
	public void shrinkBtnAddMusic(MouseEvent m) {
		timelineBtnAddMusic.setRate(-1);
        timelineBtnAddMusic.play();
        timelineBtnAddMusic.setOnFinished(event -> {
			btnAddMusic.setText("+");
		});
	}
	/** méthode pour changer la vue pour celle du formulaire **/
	public void moveToFormulaire(ActionEvent event) throws IOException {
		App.changerDeScene(App.nomScene.Formulaire);
		App.vf.cf.viderChampsSaisie();
		App.vf.cf.addMusique();
		
	}
	/** méthode pour mettre à jour le temps du lecteur de musique **/
	public void lecteurChange() {
		timeCurrentLabel.setText(formatTime(lecteur.getValue()));
	}
	/** Méthode pour formater le temps au format "min:sec"**/
    private String formatTime(double seconds) {
        int minutes = (int) seconds / 60;
        int remainingSeconds = (int) seconds % 60;
        return String.format("%d:%02d", minutes, remainingSeconds);
    }
    
    /** Méthode pour afficher les musiques dans l'onglet recommandation **/
    public void afficherMusiqueRecommandee() {
    	VueMusique v;
    	recommandationContainer.getChildren().clear();
    	for(Musique m : Hierarchie.hierarchie) {			
			v = new VueMusique(m.getImage(), m.getTitre(), m.getAuteur(), m.ID_Musique);
    		recommandationContainer.getChildren().add(v);
    	}
    }
    
    /** Méthode pour afficher la musique en cours **/
    public void afficherMusiqueEnCours() {
    	// en attendant la méthode qui permet de récupérer la musique en cours...
    	if(! Hierarchie.hierarchie.isEmpty()) {
    		if(Musique.musiqueJouée == null) {
    			Musique.musiqueJouée = Hierarchie.hierarchie.first();
    		}
    		imageViewMusiqueEnCours.setImage(new Image("file:"+Musique.musiqueJouée.getImage()));
    		labelNomMusiqueEnCours.setText("Titre - "+Musique.musiqueJouée.getTitre());
    		labelAuteurMusiqueEnCours.setText("Artiste - "+Musique.musiqueJouée.getAuteur());
    		labelGenreMusiqueEnCours.setText("Genre - "+Musique.musiqueJouée.getStyle().toString());
    		// affiche le temps de la musique en cours au niveau du slider
    		lecteur.setMax(Musique.musiqueJouée.getDuree());
    		timeMaxLabel.setText(formatTime(lecteur.getMax()));    	
    		
    		afficherAttributLike();
    	}
    }
    

    
    /** Méthode pour afficher le résultat de la recherche de musique **/
    @FXML
    public void afficherRechercheMusique() {
    	vboxRecherche.getChildren().clear();    		
    	
    	int indice = 0;
    	
    	if (textFieldRechercher.getText() != "") {
	    	for(Musique m : Hierarchie.rechercher(textFieldRechercher.getText())) {
	    		vboxRecherche.getChildren().add(new VueResultatsRecherche(m.getTitre(),m.getAuteur(),indice));
	    		indice++;
	    	}
    	}
    }
    
    /** Méthode pour liker / unliker une musique **/
    @FXML
    public void modifierAttributLike() {
    	Musique.musiqueJouée.isLiked = ! Musique.musiqueJouée.isLiked;
    	afficherAttributLike();
    }
    
    /** Méthode qui colorie le bouton like selon si la musique est likée ou non**/
    public void afficherAttributLike() {
    	if(Musique.musiqueJouée.isLiked) {
    		like.setStyle("-fx-fill: #1db954;\r\n"
    				+ "	-fx-stroke: #1db954;");
    	}
    	else {
    		like.setStyle(null);
    	}
    }
    
    /** Méthode pour changer le logo play / pause**/
    public void afficherLogoLectureOuPause() {
    	if(play.isVisible()) {
    		play.setVisible(false);
    		pause.setVisible(true);
    	}
    	else if(pause.isVisible()) {
    		pause.setVisible(false);
    		play.setVisible(true);
    	}
    }
}
