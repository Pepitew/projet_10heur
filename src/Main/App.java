package Main;

import Controleur.ControllerFormulaire;
import Vue.VueApplication;
import Vue.VueFormulaire;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

//Application est un classe javaFx qui permet d'afficher la fenêtre de base de l'app, 
//la méthode start est appelé au démarrage de l'app
public class App extends Application {
@Override
public void start(Stage primaryStage) {
	try {
		Image icon = new Image("logo.png");
		// Instanciation de la vue et du controleur
		VueApplication vueApplication = new VueApplication();
		VueFormulaire vueFormulaire = new VueFormulaire();
		ControllerFormulaire controllerFormulaire = vueFormulaire.cf;
		controllerFormulaire.stage = primaryStage; // je lui passe le stage en attribut mais c'est juste pour dépanner jsp sur que ce soit la chose à faire
		
		primaryStage.setScene(vueApplication);
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(icon);
		
		primaryStage.show();
		
	} catch(Exception e) {
		e.printStackTrace();
	}
}

public static void main(String[] args) {
	Application.launch(args);
}
}
