package fr.ul.dedale.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Le labyrinthe de Dedale";
		config.a = 8 ;
		config.width = LabyrinthFactory.SCREEN_WIDTH;
//		config.width = 1980;
		config.height = LabyrinthFactory.SCREEN_HEIGHT;
//		config.height = 1080;
		new LwjglApplication(new Game(), config);
	}
}
