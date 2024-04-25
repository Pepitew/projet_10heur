package Main;

import Controleur.ControllerFormulaire;
import Modele.Hierarchie;
import Modele.Musique;
import Vue.VueApplication;
import Vue.VueFormulaire;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Modele.Record;

//Application est un classe javaFx qui permet d'afficher la fenêtre de base de l'app, 
//la méthode start est appelé au démarrage de l'app
public class App extends Application {
@Override
public void start(Stage primaryStage) {
	// charger les données des musiques du dossier info_music
	Record.read("database");
	
	try {
		Image icon = new Image("file:../../Logo/logo.png");
		// Instanciation de la vue et du controleur
		VueApplication vueApplication = new VueApplication();
		VueFormulaire vueFormulaire = new VueFormulaire();
		ControllerFormulaire controllerFormulaire = vueFormulaire.cf;
		
		primaryStage.setScene(vueApplication);
		primaryStage.setResizable(true);
		primaryStage.getIcons().add(icon);
		//primaryStage.setMaximized(true); pour lancer la fenêtre en grand
		primaryStage.show();
		
	} catch(Exception e) {
		e.printStackTrace();
	}
}

public static void main(String[] args) {
	Application.launch(args);
}
}
