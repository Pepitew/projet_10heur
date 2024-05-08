package Main;

import Modele.Hierarchie;
import Modele.Musique;
import Vue.VueApplication;
import Vue.VueFormulaire;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Modele.Record;

//Application est un classe javaFx qui permet d'afficher la fenêtre de base de l'app, 
public class App extends Application {
//la méthode start est appelé au démarrage de l'app
@Override
public void start(Stage primaryStage) {
	// charger les données des musiques du dossier info_music
	Record.read("database");
	for(Musique m : Hierarchie.hierarchie) {
	
		System.out.println(m);
		
	}
	
	try {
		Image icon = new Image("file:../../Logo/logo.png");
		// Instanciation des vues  
		VueApplication vueApplication = new VueApplication();
		VueFormulaire vueFormulaire = new VueFormulaire();
		// paramétrage du Stage
		primaryStage.setScene(vueApplication);
		primaryStage.setResizable(true);
		primaryStage.getIcons().add(icon);
		primaryStage.setMaximized(true); 
		primaryStage.show();
		
	} catch(Exception e) {
		e.printStackTrace();
	}
}

public static void main(String[] args) {
	Application.launch(args);
}
}
