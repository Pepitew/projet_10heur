package Vue;

import java.util.Collection;
import java.util.TreeSet;

import Modele.Musique;
import Modele.Playlist;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class VueListeDeMusique extends ScrollPane {
	private static VueListeDeMusique playlist;
	private static String nomPlaylist;
	private HBox recommandationContainer;
	
    
    public VueListeDeMusique(Collection<Musique> ensembleMusique, boolean isPlaylist) {
        loadComponents(ensembleMusique);
        setupLayout();
 	   //ajout d'un évènement sur la hbox recommandationContainer pour défiler au scroll de la souris
	   recommandationContainer.setOnScroll(event -> {
            this.setHvalue(this.getHvalue()+event.getDeltaY()/recommandationContainer.getChildren().size()/10);
	   });
	   if(isPlaylist) {
		   VueListeDeMusique.playlist = this;
	   }
}
    
    private void loadComponents(Collection<Musique> ensembleMusique) {
        recommandationContainer = new HBox();
        for(Musique m : ensembleMusique) {
        	VueMusique v = new VueMusique(m.getImage(), m.getTitre(), m.getAuteur(), m.ID_Musique);
    		recommandationContainer.getChildren().add(v);
        }

        setContent(recommandationContainer);
    }
    
    private void setupLayout() {
        setStyle("-fx-background: white; -fx-background-color: white; -fx-border-color: black; -fx-border-width: 0 0 1 0;");
        setVbarPolicy(ScrollBarPolicy.NEVER);
        
        GridPane.setColumnIndex(this, 2);
        GridPane.setColumnSpan(this, 7);
        GridPane.setRowIndex(this, 3);
        GridPane.setRowSpan(this, 4);
        
        setPadding(new Insets(0, 10, 0, 10));
        recommandationContainer.setSpacing(10);
    }
    /** Mets à jour les musiques afficher dans l'onglet d'affichage des playlists si la playlist mise à jour est celle affichée**/
    public static void miseAJourAffichagePlaylist() {
    	if(VueListeDeMusique.playlist != null) {
    		playlist.loadComponents(Playlist.mesPlaylist.get(VueListeDeMusique.nomPlaylist));
    	}
    }
}
