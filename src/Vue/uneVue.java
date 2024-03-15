package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class uneVue extends JFrame {
    
    public uneVue() {
        super("Une Vue");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300)); // Définir une taille par défaut pour la fenêtre
        initComponents();
        pack(); // Redimensionne la fenêtre pour s'ajuster au contenu
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setVisible(true);
    }
    
    private void initComponents() {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf()); // Utilise FlatLaf avec un thème clair
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
        JButton jb = new JButton();
        jb.setBackground(Color.BLUE);
        jb.setPreferredSize(new Dimension(30, 30));
        
        JPanel jp = new JPanel();    
        jp.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        jp.add(jb);
        
        add(jp, BorderLayout.PAGE_END); // Ajoute le panneau contenant le bouton à la fenêtre
    }

    public static void main(String[] args) {
        new uneVue(); // Crée une instance de la classe UneVue
    }
}
