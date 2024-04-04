package Modele;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import Modele.Musique.STYLE;

public class Ecriture {
    
    private static String path = "data/info_music/";

    public static void main (String[] args) {
        Musique m1 = new Musique("une iezjfhjhdsbg", "leTitre", 658621, false, STYLE.ELECTRO);
        Musique m2 = new Musique("une zpoiaueriury", "leTitre", 658621, false, STYLE.ROCK);
        Musique m3 = new Musique("une cvbxcvnxvcnbvxc", "leTitre", 658621, false, STYLE.ELECTRO);
        Musique m4 = new Musique("ouai c'est micheeeeeel tu donne pas de nouvelle", "leTitre", 658621, false, STYLE.ELECTRO);
        
        enregistrement(Musique.encoder(m1), "database");
        enregistrement(Musique.encoder(m2), "database");
        enregistrement(Musique.encoder(m3), "database");
        enregistrement(Musique.encoder(m4), "database");
    }

    public static void enregistrement(String unString, String fileName) {

        try {
            // Création fileWriter
            File file = new File(path+fileName);

            // Création buffered et file writer / reader
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true); // true pour append
            BufferedWriter output = new BufferedWriter(writer);
            
            // Ajout de la nouvelle musique
            output.write(unString);
            output.newLine();
            
            // Fermeture des flux
            output.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
