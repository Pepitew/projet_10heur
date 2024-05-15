package Controleur;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeSet;

import com.jfoenix.controls.JFXSlider;

import Main.App;
import Modele.Hierarchie;
import Modele.MP3NewThread;
import Modele.Musique;
import Modele.Playlist;
import Vue.VueListeDeMusique;
import Vue.VueResultatsRecherche;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.util.Duration;

public class ControllerApplication {
	// initialisation des variables
	@FXML
	public GridPane root;
	@FXML
	ImageView logo;
	@FXML
	ImageView imageViewMusiqueEnCours;
	@FXML
	public SVGPath play, previousArrow, nextArrow,	like, pause;
	@FXML
	public Button btnAddMusic;
	@FXML 
	FlowPane flowPaneLogo;
	@FXML
	public JFXSlider lecteur;
	@FXML
	public Label labelPlaylist, timeMaxLabel, timeCurrentLabel, labelNomMusiqueEnCours ,labelAuteurMusiqueEnCours,labelGenreMusiqueEnCours;
	@FXML
	HBox recommandationContainer;
	@FXML
	public VBox vboxRecherche, vboxVosPlaylists;
	@FXML
	public ScrollPane scrollPaneRecommandations, placeHolderMusiqueEnCours, scrollPaneResultatsRecherche;
	@FXML
	public TextField textFieldRechercher;
	@FXML
	AnchorPane placeHolderOptionsFiltrage, placeholderAnchorResultatFiltrage, anchorPaneVosPlaylists;


	Timeline timelineBtnAddMusic;
	public VueListeDeMusique resultatsFiltrage;
	public VueListeDeMusique playlist;
	
	// initialize() est appelée dès le chargement du fichier fxml
	@FXML
	public void initialize() {
		// Cette méthode qui attends le prochain rafraichissement de l'app
		 Platform.runLater(() -> {
	            // écouteur sur la largeur de flowPaneLogo pour définir la largeur du logo en fonction de la taille de la fenêtre
	            this.flowPaneLogo.widthProperty().addListener((obs, oldValue, newValue)->{
	            	this.logo.setFitWidth(this.flowPaneLogo.getWidth()*0.4);
	            });
	            
	            //écouteur sur la hauteur de flowPaneLogo pour définir la hauteur du logo en fonction de la taille de la fenêtre
	            this.flowPaneLogo.heightProperty().addListener((obs, oldValue, newValue)->{
	            	this.logo.setFitHeight(this.flowPaneLogo.getHeight()*0.9);
	            });
	               
	            //masque l'info-bulle au dessus du slider (lecteur de musique) 
	            lecteur.getChildrenUnmodifiable().get(3).setOpacity(0);

		 		});
		 
		 		//écouteur sur l'anchor pane de la session vos playlist pour ajuster la taille de la vbox à celle de l'anchor Pane
		 		anchorPaneVosPlaylists.widthProperty().addListener((obs, odlValue, newValue)->{
		 			this.vboxVosPlaylists.setPrefWidth((double) newValue);
		 			for (Node n : this.vboxVosPlaylists.getChildren()) {
		 				if(n instanceof Label) {
		 					((Label) n).setPrefWidth((double) newValue);
		 				}
		 			}
		 		});
		 
    		   // mise en place de la vue options filtrage
    		   root.getChildren().remove(placeHolderOptionsFiltrage);
    		   root.add(App.vof, 0, 7);
    		   App.vof.toBack();
    		   
    		   // mise en place de la vue musique en cours
    		   root.getChildren().remove(placeHolderMusiqueEnCours);
    		   root.add(App.vmec, 9, 2);
    		    
    		   // mise en place de la vue logo
    		   root.getChildren().remove(flowPaneLogo);
    		   root.add(App.vl, 0, 0);
    		   
    		   /** TEST **/
    		   // mise en place de la vue liste de Musique
    		   root.getChildren().remove(scrollPaneRecommandations);
    		   playlist = new VueListeDeMusique(new TreeSet<Musique>());
    		   playlist.currentPlaylist = "Recommandations";
    		   root.add(playlist, 2, 3);
    		   playlist.toBack();
    		   // mise en place de la vue resultat filtre
    		   root.getChildren().remove(placeholderAnchorResultatFiltrage);
    		   root.add(this.resultatsFiltrage = new VueListeDeMusique(new TreeSet<Musique>()), 2, 7);
    		   resultatsFiltrage.setPadding(new Insets(30,10,0,10));
    		   resultatsFiltrage.toBack();
    		   /** TEST **/
    		   
    		   Platform.runLater(() -> {
	    		   //appel de méthode nécessaire pour un affichage correct au lancement
	    		   App.vmec.afficherMusiqueEnCours();
	    		   this.afficherRechercheMusique();	
	    		   this.chargerLabelMesPlaylists();
    		   });
	}
	
	/** méthode pour agrandir le bouton qui ajoute une musique **/
	public void growBtnAddMusic(MouseEvent m) {
		//initialisation de la timeline qui sert à resize le btn qui ajouter une musique
        timelineBtnAddMusic = new Timeline();
        
        timelineBtnAddMusic.getKeyFrames().addAll(
	            new KeyFrame(Duration.ZERO, 
	            		new KeyValue(btnAddMusic.prefWidthProperty(), btnAddMusic.getPrefWidth())), new KeyFrame(Duration.seconds(0.4), 
	            		new KeyValue(btnAddMusic.prefWidthProperty(), 200))
	        );
		timelineBtnAddMusic.setRate(1);
		timelineBtnAddMusic.play();
		btnAddMusic.setText("Ajouter une musique");
		timelineBtnAddMusic.setOnFinished(event -> {
			btnAddMusic.setText("Ajouter une musique");
		});
		}
	/** méthode pour revenir à la taille normal du bouton qui ajoute une musique **/
	public void shrinkBtnAddMusic(MouseEvent m) {
	    
		//initialisation de la timeline qui sert à resize le btn qui ajouter une musique
        timelineBtnAddMusic = new Timeline();
        
        timelineBtnAddMusic.getKeyFrames().addAll(
	            new KeyFrame(Duration.ZERO, 
	            		new KeyValue(btnAddMusic.prefWidthProperty(), btnAddMusic.getPrefWidth())), new KeyFrame(Duration.seconds(0.4), 
	            		new KeyValue(btnAddMusic.prefWidthProperty(), btnAddMusic.getPrefHeight()))
	        );
		
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

	/** méthode pour reprendre la lecture lorsque l'on relâche le slider**/
	public void lecteurChange() {
		if(MP3NewThread.playerThread != null) {
			MP3NewThread.kill();
		}
		if(pause.isVisible()) {
			new MP3NewThread(Musique.musiqueJouée.getMusicPath(), (int)lecteur.getValue());			
		}
		timeCurrentLabel.setText(formatTime(lecteur.getValue()));
	}
	/** méthode pour un affichage correct lorsque l'on déplace le slider**/
	public void bougerLecteur() {
		if(MP3NewThread.positionThread != null) {
			MP3NewThread.positionThread.suspend();			
		}
		timeCurrentLabel.setText(formatTime(lecteur.getValue()));
	}
	
	/** Méthode pour formater le temps au format "min:sec"**/
    public String formatTime(double seconds) {
        int minutes = (int) seconds / 60;
        int remainingSeconds = (int) seconds % 60;
        return String.format("%d:%02d", minutes, remainingSeconds);
    }
    
    
    /** Méthode pour afficher le résultat de la recherche de musique **/
    @FXML
    public void afficherRechercheMusique() {
    	vboxRecherche.getChildren().clear();    		
    	
    	int indice = 0;
    	
    	if (textFieldRechercher.getText() != "") {
	    	for(Musique m : Hierarchie.rechercher(textFieldRechercher.getText())) {
	    		vboxRecherche.getChildren().add(new VueResultatsRecherche(m,indice));
	    		indice++;
	    	}
    	}
    }
    
    /** Méthode pour liker / unliker une musique **/
    @FXML
    public void modifierAttributLike() {
    	Musique.musiqueJouée.isLiked = ! Musique.musiqueJouée.isLiked;
    	afficherAttributLike();
    	if (Musique.musiqueJouée.isLiked) {
    		Playlist.mesPlaylist.get("Musiques likées").add(Musique.musiqueJouée);
    		Playlist.mesPlaylist.get("Recommandations").clear();
    		Playlist.mesPlaylist.get("Recommandations").addAll(Hierarchie.recommandation());
    	}
    	else {
    		Playlist.mesPlaylist.get("Musiques likées").remove(Musique.musiqueJouée);
    		Playlist.mesPlaylist.get("Recommandations").clear();
    		Playlist.mesPlaylist.get("Recommandations").addAll(Hierarchie.recommandation());
    	}
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
    		if (MP3NewThread.playerThread == null) {
    			new MP3NewThread(Musique.musiqueJouée.getMusicPath(),(int)lecteur.getValue()); 
    		}
    		else if(MP3NewThread.enPause == true) {
    			MP3NewThread.kill();
    			new MP3NewThread(Musique.musiqueJouée.getMusicPath(), (int)lecteur.getValue());	
    		}
    		else {
    			MP3NewThread.resume();
    		}
    	}
    	else if(pause.isVisible()) {
    		pause.setVisible(false);
    		play.setVisible(true);
    		MP3NewThread.pause();
    	}
    }
    
    /** Méthode pour charger les labels des playlist, dans l'onglet mes playlists **/
    public void chargerLabelMesPlaylists() {
    	this.vboxVosPlaylists.getChildren().clear();
    	for(Entry<String, Playlist> p : Playlist.mesPlaylist.entrySet()) {
    		Label l = new Label(p.getKey());
    		l.setFont(Font.font("System",14));
    		l.setPadding(new Insets(10, 0, 10, 0));
    		l.getStyleClass().add("labelPlaylist");
    		l.setPrefWidth(this.anchorPaneVosPlaylists.getWidth());
    		
    		l.setOnMousePressed(event->{
    			this.labelPlaylist.setText(p.getValue().getName());
    			this.playlist.currentPlaylist = p.getValue().getName();
    			this.playlist.miseAJourAffichagePlaylist(p.getValue().getName());
    		});
    		
    		this.vboxVosPlaylists.getChildren().add(l);
    	}
    }
}	
