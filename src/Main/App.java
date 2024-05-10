package Main;

import Modele.Hierarchie;
import Modele.Record;
import Vue.VueApplication;
import Vue.VueFormulaire;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//Application est un classe javaFx qui permet d'afficher la fenêtre de base de l'app, 
public class App extends Application {
//la méthode start est appelé au démarrage de l'app
	
	public static enum nomScene {Application, Formulaire};	
	public static VueApplication va;
	public static VueFormulaire vf;
	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		App.primaryStage = primaryStage;
		// charger les données des musiques du dossier info_music
		Record.read("database");
		
		try {
			Image icon = new Image("file:../../Logo/logo.png");
			// Instanciation des vues  
			VueApplication vueApplication = new VueApplication();
			VueFormulaire vueFormulaire = new VueFormulaire();
			// Récupération de vueApplication et vueFormulaire
			App.va = vueApplication;
			App.vf = vueFormulaire;
			
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
	
	public static void changerDeScene(App.nomScene nomScene) {
		double currentWidthStage = App.primaryStage.getWidth();
		double currentHeightStage = App.primaryStage.getHeight();
		Scene scene = new Scene(new Pane());
		if (nomScene == App.nomScene.Application) {
			scene = App.va.getRoot().getScene();
			App.va.ca.afficherMusiqueRecommandee();
		}
		if(nomScene == App.nomScene.Formulaire){
			scene = App.vf.getRoot().getScene();
		}
		App.primaryStage.setScene(scene);
		App.primaryStage.setWidth(currentWidthStage);
		App.primaryStage.setHeight(currentHeightStage);
		App.primaryStage.show();
		App.primaryStage.getScene().getRoot().applyCss();
	}	
}
