package Main;

import Modele.Hierarchie;
import Modele.MP3NewThread;
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
		
		for (Playlist p : Hierarchie.playlists) {
			Playlist.mesPlaylist.put(p.getName(), p);
		}
		
		//this.testPlaylist();
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
			primaryStage.setMinHeight(830);
			primaryStage.setMinWidth(1550);
			primaryStage.setHeight(830);
			primaryStage.setWidth(1550);
			primaryStage.setMaxHeight(830);
			primaryStage.setMaxWidth(1550);
			primaryStage.show();

			// évènement sur la fermeture de l'app
			primaryStage.setOnCloseRequest(event -> {
				Hierarchie.encoder();
				if(MP3NewThread.playerThread != null) {
					MP3NewThread.kill();
				}
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
	} 
	
	public static void lireMusique(Musique musique) {
		if (musique != Musique.musiqueJouée) {
    		if (MP3NewThread.playerThread != null) {
    			MP3NewThread.kill();        			
    		}
			new MP3NewThread(musique.getMusicPath(),0); 			
		}
		if(App.va.ca.play.isVisible()) {
			App.va.ca.play.setVisible(false);
			App.va.ca.pause.setVisible(true);
		}
		Musique.musiqueJouée = musique;
		App.vmec.afficherMusiqueEnCours();
	}
	
	/** TEST PLAYLIST **/
	public void testPlaylist() {
		
		Playlist playlistRecommandation = new Playlist("Recommandations"); 
		playlistRecommandation.addAll(Hierarchie.recommandation());
		
		Playlist.mesPlaylist.put("Recommandations", playlistRecommandation);
		Playlist jaime = new Playlist("Musiques likées");
		for(Musique m : Hierarchie.hierarchie) {
			if(m.isLiked) {
				jaime.add(m);
				App.save();
			}
		}
		Playlist.mesPlaylist.put("Musiques likées", jaime);
	}
	/** FIN TEST PLAYLIST **/
}
