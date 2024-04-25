package Vue;

import java.io.IOException;

import Controleur.ControllerMusique;
import Modele.Hierarchie;
import Modele.Musique;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class VueMusique extends Scene{
	
	public ControllerMusique cm;
	public VueMusique(String imagePath, String titre) throws IOException {
		super(new AnchorPane());
		// Chargement du fichier FXML et création du root node
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Musique.fxml"));
        GridPane root = loader.load();
        
        // Affectation du noeud racine (root node) à la Scene
        this.setRoot(root);
     
        // Récupération du controleur
        this.cm = loader.getController();
        
        for(Musique m : Hierarchie.hierarchie) {
        	cm.afficherMusique( imagePath, titre);        	
        }
        
	}
	
}
