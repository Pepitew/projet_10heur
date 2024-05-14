package Modele;

import java.io.FileInputStream;

import Main.App;
import javafx.application.Platform;
import javazoom.jl.player.Player;

public class MP3Player {
    private static String filename;
    private static Player player;
    private static FileInputStream fis;
    private static double currentPosition;
    private static Thread lectureThread, positionThread ;

    public static void play(String filename) {
        lectureThread = new Thread(() -> {
            try {
                fis = new FileInputStream(filename);
                player = new Player(fis);
                player.play();
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
        });

        lectureThread.start();
       
        // Obtenir le temps de lecture en boucle tant que le lecteur est en train de jouer
        positionThread = new Thread(() -> {
        	try {
        		Thread.sleep(1000);
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	}
            while (player != null && !player.isComplete()) {
            	System.out.println(currentPosition);
                currentPosition = player.getPosition()/1000.0;
                // Mettez en pause pendant une courte période pour éviter une boucle infinie
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                	App.va.ca.timeCurrentLabel.setText(App.va.ca.formatTime(currentPosition));
                	App.va.ca.lecteur.setValue(currentPosition);
                });
            }
        });

        positionThread.start();
    }

    public static double getCurrentPosition() {
        return currentPosition;
    }
    
    public static void close() {
        if (player != null) {
            player.close();
            player = null;
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
        MP3Player.play("data/Musique/SINS.mp3");
    }
}
