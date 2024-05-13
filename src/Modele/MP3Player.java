package Modele;

import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class MP3Player {
    private String filename;
    private Player player;
    private FileInputStream fis;

    public MP3Player(String filename) {
        this.filename = filename;
    }

    public void play() {
        try {
            fis = new FileInputStream(filename);
            player = new Player(fis);
            player.play();
        } catch (Exception e) {
            System.out.println("Erreur de lecture : " + e);
        }
    }

    public void close() {
        if (player != null) {
            player.close();
        }
        try {
            if (fis != null) {
                fis.close();
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la fermeture du flux : " + e);
        }
    }

    public static void main(String[] args) {
        MP3Player mp3Player = new MP3Player("data/Musique/SINS.mp3");
        mp3Player.play();
    }
}