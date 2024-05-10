package Vue;

import java.io.IOException;

import Main.App;
import Modele.Hierarchie;
import Modele.Musique;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class VueMusique extends AnchorPane{
	public int id;
	public String titre;
	public AnchorPane root;
	
	private Label labelTitre;
    private ImageView couvertureMusique;
	
	public VueMusique(String imagePath, String titre, String auteur, int id) {
		super(new Pane());
		
		this.id = id;
		this.titre = titre;

		// configuration du AnchorPanel
		this.setPrefSize(190, 190);
		this.setStyle("-fx-background-color : #e6e6e6");
		
		// configuration du label
		labelTitre = new Label();
        labelTitre.setPrefHeight(30.0);
        labelTitre.setPrefWidth(150.0);
        labelTitre.setText(titre+" - "+auteur);
        AnchorPane.setBottomAnchor(labelTitre, 0.0);
        AnchorPane.setLeftAnchor(labelTitre, 20.0);
        AnchorPane.setRightAnchor(labelTitre, 20.0);
        
        // configuration de l'ImageView
        couvertureMusique = new ImageView();
        couvertureMusique.setImage(new Image("file:"+imagePath));
        couvertureMusique.setFitHeight(150.0);
        couvertureMusique.setFitWidth(150.0);
        couvertureMusique.setPickOnBounds(true);
        couvertureMusique.setPreserveRatio(true);
        AnchorPane.setLeftAnchor(couvertureMusique, 20.0);
        AnchorPane.setRightAnchor(couvertureMusique, 20.0);
        AnchorPane.setTopAnchor(couvertureMusique, 10.0);

        // Ajout des éléments au conteneur AnchorPane
        getChildren().addAll(labelTitre, couvertureMusique);

        // Définition du curseur
        setCursor(Cursor.HAND);
  
        // ajout d'un écouteur d'évènement
        this.setOnMousePressed(event -> {
        	Musique musique_a_lire = Hierarchie.rechercher(titre).first();
        	Musique.musiqueJouée = musique_a_lire;
        	App.va.ca.afficherMusiqueEnCours();
        });
        }
}