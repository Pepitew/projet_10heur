package Main;

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
		
		VueFormulaire vueFormulaire = new VueFormulaire();
		primaryStage.setScene(vueFormulaire);
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
