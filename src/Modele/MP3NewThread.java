package Modele;

import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MP3NewThread {
	private Player player;
	private FileInputStream fis;
	private static Thread playerThread;
	public MP3NewThread(String filename) {
		playerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					fis = new FileInputStream(filename);
					player = new Player(fis);
					while (true) {

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
	}

	//méthode resume()
	public static void resume(){
		playerThread.resume();
	}

	// méthode : pause()
	public static void pause() {
		playerThread.suspend();;
	}
	
	// m�thode stop()
	public static void kill() {
		playerThread.stop();
	}


	//EXEMPLE
	public static void main(String[] args) {
		// Remplacer "chemin/vers/ton/fichier.mp3" par le chemin de ton fichier MP3
		MP3NewThread mp3Player = new MP3NewThread("data/Musique/SINS.mp3");


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
