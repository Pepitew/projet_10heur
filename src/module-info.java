// Fichier module info nécessaire pour faire fonctionner javaFx
module projet_10heur {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires com.jfoenix;
	requires javafx.swing;
	requires jaudiotagger;
	
	
	opens Main to javafx.graphics;
	opens Vue to javafx.fxml;
	opens Controleur to javafx.fxml;
}
