package fr.ul.dedale.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.ul.dedale.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Le labyrinthe de Dedale";
		config.a = 8 ;
		config.width = 640;
//		config.width = 1980;
		config.height = 640;
//		config.height = 1080;
		new LwjglApplication(new Game(), config);
	}
}
