package Modele;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class Playlist implements Collection<Musique> {
    private String name;
    private ArrayList<Musique> liste;

    public Playlist(String name) {
        this.name = name;
        this.liste = new ArrayList<Musique>();
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
}

