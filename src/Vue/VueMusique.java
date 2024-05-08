package Vue;

import java.io.IOException;

import Controleur.ControllerMusique;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class VueMusique extends Scene{
	
	public ControllerMusique cm;
	public VueMusique(String imagePath, String titre) throws IOException {
		super(new Pane());
		// Chargement du fichier FXML et création du root node
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Musique.fxml"));
        AnchorPane root = loader.load();
        
        // Affectation du noeud racine (root node) à la Scene
        this.setRoot(root);
     
        // Récupération du controleur
        this.cm = loader.getController();
        
        
        cm.afficherMusique( imagePath, titre);        	
        
        
	}
	
}
