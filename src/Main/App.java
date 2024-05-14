package Main;

import Modele.Hierarchie;
import Modele.Musique;
import Modele.Playlist;
import Modele.Record;
import Vue.VueApplication;
import Vue.VueFormulaire;
import Vue.VueLogo;
import Vue.VueMusiqueEnCours;
import Vue.VueOptionsFiltrage;
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
	public static VueOptionsFiltrage vof;
	public static VueMusiqueEnCours vmec;
	public static VueLogo vl;
	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		App.primaryStage = primaryStage;
		// charger les données des musiques du dossier info_music
		Record.read("database");
		Record.read("playlistBase");
		
		this.testPlaylist();
		try {
			Image icon = new Image("file:../../Logo/logo.png");
			// Instanciation et Récupération des vues
			App.vf = new VueFormulaire();
			App.vof = new VueOptionsFiltrage();
			App.vmec = new VueMusiqueEnCours();
			App.vl = new VueLogo();
			App.va = new VueApplication();
			
			// paramétrage du Stage
			primaryStage.setScene(App.va);
			primaryStage.setResizable(true);
			primaryStage.getIcons().add(icon); 
			primaryStage.setMinHeight(580);
			primaryStage.setMinWidth(1000);
			primaryStage.setMaxHeight(830);
			primaryStage.setMaxWidth(1550);
			primaryStage.show();

			// évènement sur la fermeture de l'app
			primaryStage.setOnCloseRequest(event -> {
				Hierarchie.encoder();
	        });
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
	
	public static void save() {
		Hierarchie.encoder();
		Record.read("database");
		Record.read("playlistBase");
		App.vof.chargerOptions();
	} 
	
	/** TEST PLAYLIST **/
	public void testPlaylist() {
		Playlist playlistRecommandation = new Playlist("Recommandation"); 
		System.out.println("RECOMMANDATION " +Hierarchie.recommandation());
		for(Musique m : Hierarchie.recommandation()) {
			playlistRecommandation.add(m);
		}
		Playlist.mesPlaylist.put("Recommandation", playlistRecommandation);
		Playlist jaime = new Playlist("Musiques likées");
		for(Musique m : Hierarchie.hierarchie) {
			if(m.isLiked) {
				jaime.add(m);				
			}
		}
		Playlist.mesPlaylist.put("Musiques likées", jaime);
	}
	/** FIN TEST PLAYLIST**/
}
