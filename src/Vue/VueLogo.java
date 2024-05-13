package Vue;

import java.io.File;

import Main.App;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;

public class VueLogo extends FlowPane {
    
    private ImageView logo;

    public VueLogo() {
        super();
        
        // configuration flowPane (VueLogo)
        this.setStyle("-fx-border-width: 0 1 0 0; -fx-border-color: black;-fx-background-color: #e6e6e6;");
        this.setAlignment(Pos.CENTER);

        // Création de l'ImageView pour le logo
        logo = new ImageView();
        logo.setFitHeight(70.0);
        
        logo.setPickOnBounds(true);
        logo.setPreserveRatio(true);
        logo.getStyleClass().add("interactif");
        
        // Chargement de l'image
        Image image = new Image("file:Logo/logo_10Heur.png");
        logo.setImage(image);

        // Ajout de l'ImageView au FlowPane
        this.getChildren().add(logo);

        // Gestion des événements
        logo.setOnMouseEntered(event -> grow());
        logo.setOnMouseExited(event -> shrink());
        
        // Gestion des écouteurs
        this.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> obs, Number oldValue, Number newValue) {
				logo.setFitWidth(((double)newValue)/1.5);
				
			}
		});
        this.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> obs, Number oldValue, Number newValue) {
				logo.setFitHeight(((double)newValue)/1.5);
				// agit sur le placement des composants en haut de l'app 
				AnchorPane.setTopAnchor(App.va.ca.textFieldRechercher, ((double)newValue - App.va.ca.textFieldRechercher.getHeight())/2);
				AnchorPane.setTopAnchor(App.va.ca.scrollPaneResultatsRecherche, (((double)newValue + App.va.ca.textFieldRechercher.getHeight())/2)-1 );
				
				App.va.ca.textFieldRechercher.setPrefHeight(logo.getFitHeight()/1.8);
				App.va.ca.textFieldRechercher.setPrefWidth(App.va.ca.textFieldRechercher.getPrefHeight()*6.25);
				App.va.ca.btnAddMusic.setPrefSize(logo.getFitHeight()/1.5, logo.getFitHeight()/1.5);
			}
		});
    }

    /** Méthode pour agrandir l'ImageView lorsque la souris entre**/
    private void grow() {
    	ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), this.logo);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    }

    /** Méthode pour réduire l'ImageView lorsque la souris sort **/
    private void shrink() {
    	ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.4), this.logo);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }
}