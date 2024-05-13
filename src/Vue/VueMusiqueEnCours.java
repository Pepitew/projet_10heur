package Vue;

import Main.App;
import Modele.Hierarchie;
import Modele.Musique;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class VueMusiqueEnCours extends ScrollPane {
    private VBox content;
	private ImageView imageViewMusiqueEnCours;
    private Label labelNomMusiqueEnCours;
    private Label labelAuteurMusiqueEnCours;
    private Label labelGenreMusiqueEnCours;
      
    public VueMusiqueEnCours() {
        super();
        
        Insets padding = new Insets(10.0, 15.0, 10.0, 15.0);

        // Configuration de la ScrollPane
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.setStyle("-fx-border-color: black; -fx-border-width: 1 0 0 1;");

        // Création du contenu VBox
        content = new VBox();
        this.setContent(content);

        // Création et configuration de imageViewMusiqueEnCours
        imageViewMusiqueEnCours = new ImageView(); 
        imageViewMusiqueEnCours.setPickOnBounds(true);
        imageViewMusiqueEnCours.setPreserveRatio(true);
        VBox.setMargin(imageViewMusiqueEnCours, padding);
        content.getChildren().add(imageViewMusiqueEnCours);
        this.widthProperty().addListener(new ChangeListener<Number>() {
        	@Override
        	public void changed(ObservableValue<? extends Number> obs, Number oldValue, Number newValue) {
        		imageViewMusiqueEnCours.setFitWidth((double) newValue-30);				
        		imageViewMusiqueEnCours.setFitHeight((double) newValue-30);
        		gérerTailleTextLabel();
        	}
        });

        // Création et configuration de labelNomMusiqueEnCours 
        labelNomMusiqueEnCours = new Label("Nom Musique");
        labelNomMusiqueEnCours.setPadding(padding);
        content.getChildren().add(labelNomMusiqueEnCours);

        // Création et configuration de labelAuteurMusiqueEnCours
        labelAuteurMusiqueEnCours = new Label("Nom Auteur");
        labelAuteurMusiqueEnCours.setFont(new javafx.scene.text.Font(24.0));
        labelAuteurMusiqueEnCours.setPadding(padding);
        content.getChildren().add(labelAuteurMusiqueEnCours);

        // Création et configuration de labelGenreMusiqueEnCours
        labelGenreMusiqueEnCours = new Label("Genre Musique");
        labelGenreMusiqueEnCours.setFont(new javafx.scene.text.Font(24.0));
        labelGenreMusiqueEnCours.setPadding(padding);
        content.getChildren().add(labelGenreMusiqueEnCours);    
        
        // affichage dans la grille
        GridPane.setRowSpan(this, 9);
		GridPane.setColumnSpan(this, 3);
    }
    
    /** Méthode pour afficher la musique en cours 
     * Correspond à toutes les actions lors du changement du musique**/
    public void afficherMusiqueEnCours() {
    	// en attendant la méthode qui permet de récupérer la musique en cours...
    	if(! Hierarchie.hierarchie.isEmpty()) {
    		if(Musique.musiqueJouée == null) {
    			Musique.musiqueJouée = Hierarchie.hierarchie.first();
    		}
    		imageViewMusiqueEnCours.setImage(new Image("file:"+Musique.musiqueJouée.getImage()));
    		labelNomMusiqueEnCours.setText("Titre - "+Musique.musiqueJouée.getTitre());
    		labelAuteurMusiqueEnCours.setText("Artiste - "+Musique.musiqueJouée.getAuteur());
    		labelGenreMusiqueEnCours.setText("Genre - "+Musique.musiqueJouée.getStyle().toString());
    		// affiche le temps de la musique en cours au niveau du slider
    		App.va.ca.lecteur.setMax(Musique.musiqueJouée.getDuree());
    		App.va.ca.lecteur.setValue(0);
    		App.va.ca.lecteurChange();
    		App.va.ca.timeMaxLabel.setText(App.va.ca.formatTime(App.va.ca.lecteur.getMax()));
    		
    		// affiche le like de la musique
    		App.va.ca.afficherAttributLike();
    		
    		// gérer la taille du text
    		this.layout();
    		gérerTailleTextLabel();    			
    	}
    }
    
    /** Méthode pour redimensionner le text d'un label **/
    private void gérerTailleTextLabel() {
    	double tailleAenlever = 0;
    	double newSizeFont = 24;
    	boolean tailleRetrecit = false;
    	Label[] labels = {labelNomMusiqueEnCours, labelAuteurMusiqueEnCours, labelGenreMusiqueEnCours};

    	for( Label l : labels) {
    			tailleAenlever = Math.floor((imageViewMusiqueEnCours.getFitWidth() - l.getWidth())/16.8 );
    			if( l.getFont().getSize()+tailleAenlever < newSizeFont) {
    				newSizeFont = l.getFont().getSize()+tailleAenlever;
    			};
    			
    	}
    	if (newSizeFont > 10) {
    		labelNomMusiqueEnCours.setFont(new javafx.scene.text.Font(newSizeFont));
    		labelAuteurMusiqueEnCours.setFont(new javafx.scene.text.Font(newSizeFont));
    		labelGenreMusiqueEnCours.setFont(new javafx.scene.text.Font(newSizeFont));
    	}
	}
}
