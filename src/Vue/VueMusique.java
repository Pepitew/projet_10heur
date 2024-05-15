package Vue;

import Main.App;
import Modele.MP3NewThread;
import Modele.Musique;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class VueMusique extends AnchorPane{
	private int id;
	private String titre;
	private Musique musique;
	public AnchorPane root;
	
	private Label labelTitre;
    private ImageView couvertureMusique;
	
	public VueMusique(Musique m) {
		super();
		this.musique = m;
		this.id = m.ID_Musique;
		this.titre = m.getTitre();

		// configuration du AnchorPanel
		this.setPrefSize(190, 190);
		this.setStyle("-fx-background-color : #e6e6e6");
		
		// configuration du label
		labelTitre = new Label();
        labelTitre.setPrefHeight(30.0);
        labelTitre.setPrefWidth(150.0);
        labelTitre.setText(titre+" - "+m.getAuteur());
        AnchorPane.setBottomAnchor(labelTitre, 0.0);
        AnchorPane.setLeftAnchor(labelTitre, 20.0);
        AnchorPane.setRightAnchor(labelTitre, 20.0);
        
        // configuration de l'ImageView
        couvertureMusique = new ImageView();
        couvertureMusique.setImage(new Image("file:"+m.getImage()));
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
        	App.lireMusique(musique);
        });
        }
}