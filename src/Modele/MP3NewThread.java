package Modele;

import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MP3NewThread {
    static private Player player;
    static private FileInputStream fis;
    private static boolean paused;

    
    //méthode play()
    public static void play(String filename) {
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
    public static void pause() {
        paused = true;
    }

    public static void resume() {
        paused = false;
    }
    //EXEMPLE
    public static void main(String[] args) {
        // Remplacer "chemin/vers/ton/fichier.mp3" par le chemin de ton fichier MP3
    	MP3NewThread.play("data/Musique/SINS.mp3");
    
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
