package Vue;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class VueApplication extends Scene{
	
	public VueApplication() throws IOException {
		super(new Pane());
		// Chargement du fichier FXML et création du root node
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Application.fxml"));
        GridPane root = loader.load();
      
        // Affectation du noeud racine (root node) à la Scene
        this.setRoot(root);
	}

}
