package Modele;

import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MP3NewThread {
    private String filename;
    private Player player;
    private FileInputStream fis;
    private boolean paused;

    public MP3NewThread(String filename) {
        this.filename = filename;
        this.paused = false;
    }

    
    //méthode play()
    public void play() {
        Thread playerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fis = new FileInputStream(filename);
                    player = new Player(fis);
                    while (!paused && player != null) {
                        player.play();
                    }
                } catch (Exception e) {
                    System.out.println("Erreur de lecture : " + e);
                } finally {
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
            }
        });
        playerThread.start();
    }
    // méthode : pause() et resume()
    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }
    //EXEMPLE
    public static void main(String[] args) {
        // Remplacer "chemin/vers/ton/fichier.mp3" par le chemin de ton fichier MP3
    	MP3NewThread mp3Player = new MP3NewThread("data/Musique/SINS.mp3");
    	mp3Player.play();
        /*
        // Mettre en pause la lecture pendant quelques secondes
    	mp3Player.pause();
        try {
            Thread.sleep(3000); // Pause pendant 3 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Reprendre la lecture
        mp3Player.resume();*/
    }
}
