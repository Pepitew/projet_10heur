package Modele;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import Modele.Musique.STYLE;

public class Hierarchie {
	
	public static TreeSet<Musique> hierarchie = new TreeSet<>();
	public static TreeSet<Playlist> playlists = new TreeSet<Playlist>();
	
	
	public static void ajouterMusique(Musique m) {
		Hierarchie.hierarchie.add(m);
	}
	
	public static void retirerMusique(Musique m) {
		Hierarchie.hierarchie.remove(m);
	}
	
	public static void ajouterPlaylist(Playlist p) {
		Hierarchie.playlists.add(p);
	}
	
	public static void retirerMusique(Playlist p) {
		Hierarchie.playlists.remove(p);
	}
	
	public static void encoder() {
	    if (!Hierarchie.hierarchie.isEmpty()) {
	    	String[] desString = new String[Hierarchie.hierarchie.size()];
	    	int indice = 0;
	    	for (Musique m : Hierarchie.hierarchie) {
	    		desString[indice] = Musique.encoder(m);
	    		indice++;
	    	}
	    	Record.write(desString, "database");
	    }
	    
	    if (!Hierarchie.playlists.isEmpty()) {	    	
	    	String[] desString2 = new String[Hierarchie.playlists.size()];
	    	int indice2 = 0;
	    	for (Playlist p : Hierarchie.playlists) {
	    		desString2[indice2] = Playlist.encoder(p);
	    		indice2++;
	    	}
	    	Record.write(desString2, "playlistBase");
	    	
	    }
		

	    Hierarchie.hierarchie.clear();
	    Hierarchie.playlists.clear();
	    Musique.ID = 0;
	    
  
	}
	
	public static TreeSet<Musique> rechercher(String chaine){
		TreeSet<Musique> recherche = new TreeSet<>();
		
		
		for (Musique m : Hierarchie.hierarchie) {
			if (m.getTitre().toLowerCase().contains(chaine.toLowerCase()) || m.getAuteur().toLowerCase().contains(chaine.toLowerCase())) {
				recherche.add(m);
			}
		}
		
		return recherche;
	}

	public static TreeSet<Musique> rechercher(STYLE genre, String artiste, String album) {
		TreeSet<Musique> recherche = new TreeSet<>();
		
		for (Musique m : Hierarchie.hierarchie) {
			if ((genre == null || m.getStyle() == genre)&& (artiste == null || m.getAuteur() == artiste) &&(album == null || m.getAlbum() == album)){
				recherche.add(m);
			}
		}
		return recherche;
	}
	
	public static TreeSet<Musique> recommandation() {
		TreeSet<Musique> reco = new TreeSet<Musique>();
		Map<String, Integer> auteurFav = new HashMap<String, Integer>(); //int : nbOccurence
		Map<STYLE, Integer> styleFav = new HashMap<Musique.STYLE, Integer>(); //int : nbOccurence
		
		for (Musique m : Hierarchie.hierarchie) {
			if (m.isLiked) {
				if (auteurFav.get(m.getAuteur()) != null) {
					int valeur = auteurFav.get(m.getAuteur());
					auteurFav.remove(m.getAuteur());
					auteurFav.put(m.getAuteur(), valeur+1);
				}
				else {
					auteurFav.put(m.getAuteur(), 1);
				}
				
				if (styleFav.get(m.getStyle()) != null) {
					int valeur = styleFav.get(m.getStyle());
					styleFav.remove(m.getStyle());
					styleFav.put(m.getStyle(), valeur+1);
				}
				else {
					styleFav.put(m.getStyle(), 1);
				}
			}
		}
		
		int drapeauAuteur = 0;
		String auteurMax = "";
		
		for (String auteur : auteurFav.keySet()) {
			if (auteurFav.get(auteur) > drapeauAuteur) {
				auteurMax = auteur;
				drapeauAuteur = auteurFav.get(auteur);
			}
		}
		int drapeauStyle = 0;
		STYLE styleMax = null;
		for (STYLE style : styleFav.keySet()) {
			if (styleFav.get(style) > drapeauStyle) {
				styleMax = style;
				drapeauStyle = styleFav.get(style);
			}
		}

		TreeSet<Musique> recherche = Hierarchie.rechercher(null, auteurMax, null);
		recherche.addAll(Hierarchie.rechercher(styleMax, null, null));
		
		int i = 0;
		while (recherche.size() != 0 && i < 15) {
				reco.add(recherche.first());
				recherche.remove(reco.last());
				i++;
		}
		
		
		if(drapeauAuteur == 0 && drapeauStyle == 0) {
			reco = new TreeSet<Musique>();
		}
		
		
		return reco;
	}
	
	public String toString() {
		return Hierarchie.hierarchie.toString() + "\n" + Hierarchie.playlists.toString();
		
	}
	
	
	


}
