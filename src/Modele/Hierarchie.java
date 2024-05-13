package Modele;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import Modele.Musique.STYLE;

public class Hierarchie {
	
	public static TreeSet<Musique> hierarchie = new TreeSet<>();
	
	
	public static void ajouterMusique(Musique m) {
		Hierarchie.hierarchie.add(m);
	}
	
	public static void retirerMusique(Musique m) {
		Hierarchie.hierarchie.remove(m);
	}
	
	public static void encoder() {
	    //StringBuilder phrase = new StringBuilder();
	    String[] desString = new String[Hierarchie.hierarchie.size()];
	    int indice = 0;
	    for (Musique m : Hierarchie.hierarchie) {
	        //phrase.append(Musique.encoder(m));
	        desString[indice] = Musique.encoder(m);
	        indice++;
	    }
	    Record.write(desString, "database");
	    Hierarchie.hierarchie.clear();
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
			if (genre != null && m.getStyle() == genre) {
				recherche.add(m);
			}
			else if (artiste != null && m.getAuteur() == artiste) {
				recherche.add(m);
			}
			else if (album != null && m.getAlbum() == album) {
				recherche.add(m);
			}
		}
		return recherche;
	}
	
	public static TreeSet<Musique> recommandation() {
		TreeSet<Musique> reco = new TreeSet<Musique>();
		Map<String, Integer> auteurFav = new HashMap<String, Integer>(); //int : nbOccurence
		Map<STYLE, Integer> styleFav = new HashMap<Musique.STYLE, Integer>(); //int : nbOccurence
		
		/*for (Musique m : Hierarchie.hierarchie) {
			if (m.isLiked) {
				if (auteurFav.get(m.getAuteur()) != null) {
					int valeur = auteurFav.get(m.getAuteur());
					auteurFav.remove(m.getAuteur());
					auteurFav.put(m.getAuteur(), valeur++);
				}
				else {
					auteurFav.put(m.getAuteur(), 1);
				}
				
				if (styleFav.get(m.getStyle()) != null) {
					int valeur = styleFav.get(m.getStyle());
					styleFav.remove(m.getStyle());
					styleFav.put(m.getStyle(), valeur++);
				}
				else {
					styleFav.put(m.getStyle(), 1);
				}
			}
		}*/
		
		for (Musique m : Hierarchie.hierarchie) {
		    if (m.isLiked) {
		        auteurFav.merge(m.getAuteur(), 1, Integer::sum);
		        styleFav.merge(m.getStyle(), 1, Integer::sum);
		    }
		}
		
		int drapeau = 0;
		String auteurMax = "";
		
		for (String auteur : auteurFav.keySet()) {
			if (auteurFav.get(auteur) > drapeau) {
				auteurMax = auteur;
				drapeau = auteurFav.get(auteur);
			}
		}

		TreeSet<Musique> recherche = Hierarchie.rechercher(null, auteurMax, null);
		for (int i = 0; i < 5; i++) {

			if (recherche.size() != 0) {
				reco.add(recherche.first());
				reco.remove(reco.first());
			}
				
		}
		
		return reco;
	}
	
	public String toString() {
		return Hierarchie.hierarchie.toString();
		
	}
	
	
	


}
