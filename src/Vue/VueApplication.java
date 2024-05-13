package Vue;

import java.io.IOException;

import Controleur.ControllerApplication;
import Controleur.ControllerFormulaire;
import Main.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class VueApplication extends Scene{
	public ControllerApplication ca;
	
	public VueApplication() throws IOException {
		super(new Pane());
		// Chargement du fichier FXML et création du root node
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Application.fxml"));
        GridPane root = loader.load();
        // Récupération du controleur
        this.ca = loader.getController();
        // Affectation du noeud racine (root node) à la Scene
        this.setRoot(root);
        
        this.setOnMouseClicked(event -> {
        	this.viderRechercheTextField();
        });
	}
	
	public void viderRechercheTextField() {
		this.ca.textFieldRechercher.setText("");
		this.ca.afficherRechercheMusique();
		App.vl.requestFocus();
	}
}
