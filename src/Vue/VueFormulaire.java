package Vue;

import java.io.IOException;

import Controleur.ControllerFormulaire;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class VueFormulaire extends Scene { 

	public ControllerFormulaire cf;
	public VueFormulaire() throws IOException {
		super(new Pane());
		// Chargement du fichier FXML et création du root node
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Formulaire.fxml"));
        GridPane root = loader.load();
        // Récupération du controleur
        this.cf = loader.getController();
        
        // Affectation du noeud racine (root node) à la Scene
        this.setRoot(root);
	}
	
}
