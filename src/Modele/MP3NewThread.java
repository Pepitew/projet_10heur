package Modele;

import java.io.File;
import java.io.FileInputStream;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;

import Main.App;
import javafx.application.Platform;
import javazoom.jl.player.Player;

public class MP3NewThread {
	private Player player;
	private FileInputStream fis;
	private static double currentPosition;
	public static Thread playerThread, positionThread;
	public static String filename;
	public static boolean enPause;
	public MP3NewThread(String filename, final int startPositionInSeconds) {
		MP3NewThread.enPause = false;
		MP3NewThread.filename = filename;
		if (positionThread != null) { positionThread.stop();}
		playerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					fis = new FileInputStream(filename);
					player = new Player(fis);
					// récupérer le débit binaire de la musique pour pouvoir démarrer à un certain temps
					MP3File mp3File = (MP3File) AudioFileIO.read(new File(filename));
					long startPositionInBytes = (long) (startPositionInSeconds * mp3File.getAudioHeader().getBitRateAsNumber() * 1000/8  );
					fis.skip(startPositionInBytes); 
				    
					while (!player.isComplete()) {
						player.play();
					}
				}
				catch (Exception e) {
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
		
		// Obtenir le temps de lecture en boucle tant que le lecteur est en train de jouer
        positionThread = new Thread(() -> {
        	while(player == null) {
        		try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        	}
            while (player != null && !player.isComplete()) {
                currentPosition = player.getPosition()/1000.0;
                // Mettez en pause pendant une courte période pour éviter une boucle infinie
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                	App.va.ca.timeCurrentLabel.setText(App.va.ca.formatTime(startPositionInSeconds+currentPosition));
                	App.va.ca.lecteur.setValue(startPositionInSeconds+currentPosition);
                });
            }
        });

        positionThread.start();
	}

	//méthode resume()
	public static void resume(){
		MP3NewThread.enPause = false;
		playerThread.resume();
		positionThread.resume();
	}

	// méthode : pause()
	public static void pause() {
		MP3NewThread.enPause = true;
		playerThread.suspend();
		positionThread.suspend();
	}
	
	// m�thode stop()
	public static void kill() {
		playerThread.stop();
		positionThread.stop();
	}


	//EXEMPLE
	public static void main(String[] args) {
		// Remplacer "chemin/vers/ton/fichier.mp3" par le chemin de ton fichier MP3
		MP3NewThread mp3Player = new MP3NewThread("data/Musique/SINS.mp3", 0);


		// Mettre en pause la lecture pendant quelques secondes
		try {
			Thread.sleep(1000); // Pause pendant 3 secondes
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MP3NewThread.pause();

		try {
			Thread.sleep(1000); // Pause pendant 3 secondes
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MP3NewThread.resume();
		
		try {
			Thread.sleep(1000); // Pause pendant 3 secondes
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		MP3NewThread.kill();
	}
}
