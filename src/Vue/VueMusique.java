package Vue;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class VueMusique extends Scene{

	public VueMusique() throws IOException {
		super(new Pane());
		// Chargement du fichier FXML et création du root node
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Musique.fxml"));
        GridPane root = loader.load();
        
        // Affectation du noeud racine (root node) à la Scene
        this.setRoot(root);
	}
	
}
