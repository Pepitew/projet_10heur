package Vue;

import Modele.Hierarchie;
import Modele.Musique;
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
import javafx.scene.text.Font;

public class VueOptionsFiltrage extends AnchorPane{
	double vGap = 20.0;
	double widthChoiceBox = 150.0;
	
	ChoiceBox<String> choiceBoxGenre = new ChoiceBox<>();
    ChoiceBox<String> choiceBoxAuteur = new ChoiceBox<>();
    ChoiceBox<String> choiceBoxAlbum = new ChoiceBox<>();
    Label labelOptions = new Label("Options de filtrage");
    FlowPane flowPane = new FlowPane();
    
	public VueOptionsFiltrage() {
		super();
		this.setStyle("-fx-border-width: 1 1 0 0; -fx-border-color: black; -fx-background-color: #e6e6e6;");
	        
		 	// configuration Label
	        labelOptions.setStyle("-fx-background-color: #ffffff0b;");
	        labelOptions.setAlignment(Pos.CENTER);
	        labelOptions.setFont(Font.font("Verdana Italic", 18));
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
	        
	        this.getChildren().addAll(labelOptions, flowPane);
	        
	        chargerOptions();
	}
	
	public void chargerOptions() {
	
        
        
        // définit le texte par défaut de choiceBoxGenre + remplit les valeurs
        ObservableList<String> listeGenre = FXCollections.observableArrayList("Trier par genre");
        for(Musique.STYLE genre : Musique.STYLE.values()) {
        	listeGenre.add(genre.name());
        }
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
				}
				else {
					choiceBoxAlbum.setVisible(false);
				}
				
			}	            	
        });
        choiceBoxAuteur.setItems(listeAuteur);
        choiceBoxAuteur.getSelectionModel().selectFirst();
        // définit le texte par défaut de choiceBoxAlbum
        ObservableList<String> listeAlbum = FXCollections.observableArrayList("Trier par album");
        choiceBoxAlbum.setItems(listeAlbum);
        choiceBoxAlbum.getSelectionModel().selectFirst();
	}
}
