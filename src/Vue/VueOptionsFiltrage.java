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
        
        chargerOptions();
	}
	/** Permet de modifier les options dans les choices box genre et auteur**/
	public void chargerOptions() {
        
        // définit le texte par défaut de choiceBoxGenre + remplit les valeurs
        ObservableList<String> listeGenre = FXCollections.observableArrayList("Trier par genre");
        for(Musique.STYLE genre : Musique.STYLE.values()) {
        	listeGenre.add(genre.name());
        }
        choiceBoxGenre.valueProperty().addListener(new ChangeListener<String>() {
  			@Override
  			public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
  				if(newValue != "Trier par genre") {
  					VueOptionsFiltrage.choixGenre = STYLE.valueOf(newValue);
  					App.va.ca.resultatsFiltrage.miseAJourAffichageOptionsFiltrage(Hierarchie.rechercher(VueOptionsFiltrage.choixGenre, VueOptionsFiltrage.choixAuteur ,VueOptionsFiltrage.choixAlbum ));					
  				}
  				else {
  					VueOptionsFiltrage.choixGenre = null;
  				}
  			}});
        choiceBoxGenre.setItems(listeGenre);
        choiceBoxGenre.getSelectionModel().selectFirst();
        // définit le texte par défaut de choiceBoxAuteur + ajouter un observateur + remplit les valeurs
        ObservableList<String> listeAuteur = FXCollections.observableArrayList("Trier par auteur");
        for(Musique m : Hierarchie.hierarchie) {
        	if(! listeAuteur.contains(m.getAuteur())) {
        		listeAuteur.add(m.getAuteur());
        	}
        	
        }
        choiceBoxAuteur.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
				if(newValue != "Trier par auteur") {
					choiceBoxAlbum.setVisible(true);
					afficherChoiceListAlbum();
					VueOptionsFiltrage.choixAuteur = newValue;
					App.va.ca.resultatsFiltrage.miseAJourAffichageOptionsFiltrage(Hierarchie.rechercher(VueOptionsFiltrage.choixGenre, VueOptionsFiltrage.choixAuteur ,VueOptionsFiltrage.choixAlbum ));					
				}
				else {
					choiceBoxAlbum.setVisible(false);
					VueOptionsFiltrage.choixAuteur = null;
				}
				
			}	            	
        });
        choiceBoxAuteur.setItems(listeAuteur);
        choiceBoxAuteur.getSelectionModel().selectFirst();
      
	}
	
	/** Permet d'afficher et de modifier les options dans la choices box album**/
	public void afficherChoiceListAlbum() {
		  // définit le texte par défaut de choiceBoxAlbum
        ObservableList<String> listeAlbum = FXCollections.observableArrayList("Trier par album");
        for(Musique m : Hierarchie.hierarchie) {
        	if((! listeAlbum.contains(m.getAlbum()) && (m.getAuteur() == choiceBoxAuteur.getValue()))) {
        		listeAlbum.add(m.getAlbum());
        	}
        }
        choiceBoxAlbum.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
				if(newValue != "Trier par album") {
					VueOptionsFiltrage.choixAlbum = newValue;
					App.va.ca.resultatsFiltrage.miseAJourAffichageOptionsFiltrage(Hierarchie.rechercher(VueOptionsFiltrage.choixGenre, VueOptionsFiltrage.choixAuteur ,VueOptionsFiltrage.choixAlbum ));					
				}
				else {
					VueOptionsFiltrage.choixAlbum = null;
				}
			}});
        choiceBoxAlbum.setItems(listeAlbum);
        choiceBoxAlbum.getSelectionModel().selectFirst();
	}
}
