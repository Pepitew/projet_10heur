package Vue;

import java.util.Collection;

import Modele.Musique;
import Modele.Playlist;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class VueListeDeMusique extends ScrollPane {
	private HBox recommandationContainer;
	public String currentPlaylist;
	
    
    public VueListeDeMusique(Collection<Musique> ensembleMusique) {
    	this.recommandationContainer = new HBox();
    	loadComponents(ensembleMusique);
        setupLayout();
 	   //ajout d'un évènement sur la hbox recommandationContainer pour défiler au scroll de la souris
	   recommandationContainer.setOnScroll(event -> {
            this.setHvalue(this.getHvalue()+event.getDeltaY()/recommandationContainer.getChildren().size()/10);
	   });
}
    
    private void loadComponents(Collection<Musique> ensembleMusique) {
        recommandationContainer.getChildren().clear();
        for(Musique m : ensembleMusique) {
        	VueMusique v = new VueMusique(m);
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
    
    /** Mets à jour les musiques afficher **/
    public void miseAJourAffichage(Collection<Musique> listeMusique) {
    	this.loadComponents(listeMusique);
    }
    
}
