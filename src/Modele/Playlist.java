package Modele;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import Main.App;
import Vue.VueListeDeMusique;
import javafx.application.Platform;


public class Playlist implements Collection<Musique>,Comparable<Playlist> {
    private String name;
    public ArrayList<Musique> liste;
    public int current; //indice de la musique courrante
    
    public static HashMap<String, Playlist> mesPlaylist = new HashMap<String, Playlist>();

    public Playlist(String name) {
    	this.current = 0;
        this.name = name;
        this.liste = new ArrayList<Musique>();
        Playlist.mesPlaylist.put(name, this);
        Hierarchie.ajouterPlaylist(this);
    }
    
    public Playlist(String name, ArrayList<Musique> liste) {
    	this.current = 0;
    	this.name = name;
    	this.liste = liste;
    	Hierarchie.ajouterPlaylist(this);
    }
    
    public void suivant() {
    	if (this.current < this.liste.size()-1) {
    		this.current = this.current + 1;
    	} else {
    		this.current = 0;
    	}
    	App.lireMusique(this.liste.get(current));
    }
    
    public void precedent() {
    	if (this.current > 0) {
    		this.current = this.current - 1;
    	} else {
    		this.current = this.liste.size()-1;
    	}
    	App.lireMusique(this.liste.get(current));
    }
    
  
    public String getName() {
    	return this.name;
    }
    @Override
    public int size() {
        return this.liste.size();
    }

    @Override
    public boolean isEmpty() {
        return this.liste.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.liste.contains(o);
    }

    @Override
    public Iterator<Musique> iterator() {
        return this.liste.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.liste.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.liste.toArray(a);
    }

    @Override
    public boolean add(Musique e) {   
        return this.liste.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return this.liste.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.liste.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Musique> c) {
        return this.liste.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.liste.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.liste.retainAll(c);
    }

    @Override
    public void clear() {
        this.liste.clear();
    }
    
    
    
    public static String encoder(Playlist p) {
		StringBuilder phrase = new StringBuilder();
		ArrayList<String> chaines = new ArrayList<String>();
		chaines.add(p.getName());
		for (Musique m : p) {
			chaines.add(m.getTitre());
		}
		
		
		for (String element : chaines) {
			for (int i = 0; i < element.length(); i++) {
	            char caractere = element.charAt(i);
	            int asciiValue = (int) caractere;
	            String hexValue = Integer.toHexString(asciiValue);
	            phrase.append(hexValue);
	        }
			phrase.append(Integer.toHexString((int) ';'));
		}
		phrase.append(Integer.toHexString((int) '|'));
		return phrase.toString();
    }

    //A APPELER APRES AVOIR ENREGISTRER LES MUSIQUES
    public static Playlist decoder(String playlistEncodee) {
		String mot = "";
		ArrayList<String> arguments = new ArrayList<String>();
	    for (int i = 0; i < playlistEncodee.length(); i = i + 2) {
	    	String hexChar = playlistEncodee.substring(i, i + 2);
	    	char caractere = (char) Integer.parseInt(hexChar, 16);
	    	if (caractere != '|' || caractere != ' ') {
	    		if (caractere == ';') {	    		
	    			arguments.add(mot);
	    			mot = "";
	    		} else {
	    			mot += caractere;
	    		}
	    	}
	    }
	    ArrayList<Musique> liste = new ArrayList<Musique>();
	    //Recherche le titre de la musique pour ajouter la musique dans la playlist via la methode de recherche
	    for (int i = 1; i < arguments.size(); i++) {
	    	liste.add(Hierarchie.rechercher(arguments.get(i)).first());
	    }
	    
	    Playlist p = new Playlist(arguments.get(0), liste);
	    Hierarchie.ajouterPlaylist(p);
		return p;
	}

	@Override
	public int compareTo(Playlist o) {
		return this.name.compareTo(o.name);
	}
	
	public String toString() {
		StringBuilder phrase = new StringBuilder();
		phrase.append("Playlist : " + this.name + "\n");
		if (!this.isEmpty()) {
			for (Musique m : this.liste) {
				phrase.append(m.getTitre() + "\n");
			}
		} else {
			phrase.append("la playlist est vide");
		}
		
		
		return phrase.toString();
	}
}

