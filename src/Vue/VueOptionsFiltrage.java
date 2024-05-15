package Vue;

import Main.App;
import Modele.Hierarchie;
import Modele.Musique;
import Modele.Musique.STYLE;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class VueOptionsFiltrage extends AnchorPane{
	double vGap = 20.0;
	double widthChoiceBox = 150.0;
	
	ChoiceBox<String> choiceBoxGenre = new ChoiceBox<>();
    ChoiceBox<String> choiceBoxAuteur = new ChoiceBox<>();
    ChoiceBox<String> choiceBoxAlbum = new ChoiceBox<>();
    static STYLE choixGenre;
    static String choixAuteur;
    static String choixAlbum;
    
    private ChangeListener<String> boxGenreListener;
    private ChangeListener<String> boxAuteurListener;
    private ChangeListener<String> boxAlbumListener;
    
    Label labelOptions = new Label("Options de filtrage");
    FlowPane flowPane = new FlowPane();
    
	public VueOptionsFiltrage() {
		super();
		this.setStyle("-fx-border-width: 0 1 0 0; -fx-border-color: black; -fx-background-color: #e6e6e6;");
       
	 	// configuration Label
        labelOptions.setStyle("-fx-background-color: #ffffff0b;");
        labelOptions.setAlignment(Pos.CENTER);
        labelOptions.setFont(Font.font("Verdana",FontPosture.ITALIC, 18));
        labelOptions.setPadding(new Insets(10, 0, 10, 0));
        labelOptions.setPrefHeight(30.0);
        AnchorPane.setLeftAnchor(labelOptions, 0.0);
        AnchorPane.setRightAnchor(labelOptions, 0.0);
        AnchorPane.setTopAnchor(labelOptions, 0.0);
        
    	// configuration choiceBox
        choiceBoxGenre.setPrefWidth(widthChoiceBox);
        choiceBoxAuteur.setPrefWidth(widthChoiceBox);
        choiceBoxAlbum.setPrefWidth(widthChoiceBox);
        
        // configuration flowPane
        flowPane.setPrefWrapLength(widthChoiceBox);
        flowPane.setVgap(vGap);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.getChildren().addAll(choiceBoxGenre, choiceBoxAuteur, choiceBoxAlbum);
        AnchorPane.setTopAnchor(flowPane, labelOptions.getPrefHeight() + vGap);
        AnchorPane.setLeftAnchor(flowPane, 0.0);
        AnchorPane.setRightAnchor(flowPane, 0.0);
        
        // ajouts des enfants
        this.getChildren().addAll(labelOptions, flowPane);
        
        // place dans la grille
        GridPane.setRowSpan(this, 4);
		GridPane.setColumnSpan(this, 2);
		
		// Initialise les listener
		boxGenreListener = new ChangeListener<String>() {
  			@Override
  			public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
  				if(newValue != "Trier par genre") {
  					VueOptionsFiltrage.choixGenre = STYLE.valueOf(newValue);
  				}
  				else {
  					VueOptionsFiltrage.choixGenre = null;
  				}
				changeChoiceBoxAuteur();
  			}};
  			
  		boxAuteurListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
				if(newValue != "Trier par auteur") {
					choiceBoxAlbum.setVisible(true);
					VueOptionsFiltrage.choixAuteur = newValue;
				}
				else {
					choiceBoxAlbum.setVisible(false);
					VueOptionsFiltrage.choixAuteur = null;
				}
				changeChoiceBoxAlbum();
				
			}	            	
        };
        
        boxAlbumListener = new ChangeListener<String>() {
 			@Override
 			public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
 				if(newValue != "Trier par album") {
 					VueOptionsFiltrage.choixAlbum = newValue;
 				}
 				else {
 					VueOptionsFiltrage.choixAlbum = null;
 				}
 				Platform.runLater(() -> {
 					App.va.ca.resultatsFiltrage.miseAJourAffichageOptionsFiltrage(Hierarchie.rechercher(VueOptionsFiltrage.choixGenre, VueOptionsFiltrage.choixAuteur ,VueOptionsFiltrage.choixAlbum ));					
   				});
 			}};
        
        chargerOptions();
	}
	/** Permet de modifier les options dans les choices box genre et Auteur**/
	public void chargerOptions() {
        
        // définit le texte par défaut de choiceBoxGenre + remplit les valeurs + ajouter des listener sur les choiceBox
        ObservableList<String> listeGenre = FXCollections.observableArrayList("Trier par genre");
        for(Musique.STYLE genre : Musique.STYLE.values()) {
        	listeGenre.add(genre.name());
        }
        choiceBoxGenre.valueProperty().addListener(boxGenreListener);
        choiceBoxGenre.setItems(listeGenre);
        choiceBoxGenre.getSelectionModel().selectFirst();
       
        choiceBoxAuteur.valueProperty().addListener(boxAuteurListener);
        changeChoiceBoxAuteur();
        
        choiceBoxAlbum.valueProperty().addListener(boxAlbumListener);
	}
	
	private void changeChoiceBoxAuteur() {
		 // définit le texte par défaut de choiceBoxAuteur + remplit les valeurs
        ObservableList<String> listeAuteur = FXCollections.observableArrayList("Trier par auteur");
        String copieChoixAuteur = choixAuteur;
        for(Musique m : Hierarchie.hierarchie) {
        	if(! listeAuteur.contains(m.getAuteur())) {
        		if (choiceBoxGenre.getValue() == "Trier par genre" || m.getStyle() == choixGenre) {
        			listeAuteur.add(m.getAuteur());
        		}
        	}
        	
        }
        choiceBoxAuteur.setItems(listeAuteur);
        if(copieChoixAuteur != null) {
        	choiceBoxAuteur.getSelectionModel().select(copieChoixAuteur);
        	if(Hierarchie.rechercher(choixGenre, choixAuteur, choixAlbum).size() == 0) {
        		choiceBoxAuteur.getSelectionModel().selectFirst(); 
        	}
        }
        else {
        	choiceBoxAuteur.getSelectionModel().selectFirst();        	
        }
	}
	
	/** Permet d'afficher et de modifier les options dans la choices box album**/
	private void changeChoiceBoxAlbum() {
		  // définit le texte par défaut de choiceBoxAlbum
        ObservableList<String> listeAlbum = FXCollections.observableArrayList("Trier par album");
        for(Musique m : Hierarchie.hierarchie) {
        	if((! listeAlbum.contains(m.getAlbum()) && (m.getAuteur().equals(choiceBoxAuteur.getValue())))) {
        		listeAlbum.add(m.getAlbum());
        	}
        }
        choiceBoxAlbum.setItems(listeAlbum);
        choiceBoxAlbum.getSelectionModel().selectFirst();
        
	}
	
	
	
}
