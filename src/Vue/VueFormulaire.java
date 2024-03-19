package Vue;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class VueFormulaire extends Scene { 


	public VueFormulaire() throws IOException {
		super(new Pane());
		 // Chargement du fichier FXML et création du root node
        GridPane root = FXMLLoader.load(getClass().getResource("Formulaire.fxml"));

        // Ajout de la feuille de style à la Scene
        this.getStylesheets().add(getClass().getResource("formulaire.css").toExternalForm());

        // Affectation du noeud racine (root node) à la Scene
        this.setRoot(root);
	}
	
}
