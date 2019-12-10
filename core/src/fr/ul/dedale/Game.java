package fr.ul.dedale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import fr.ul.dedale.model.View.ViewMenu;
import fr.ul.dedale.model.World;

public class Game extends com.badlogic.gdx.Game {

	ViewMenu es ;
	private World world;
	private Music mp3Sound;

	@Override
	public void create() {
		world = new World(this);
		//Récupération du numéro du dernier niveau débloqué
		if ( Gdx.files.local("save/lastLevel.json").exists()) {
			// On récupert l'attribut last level
			FileHandle file = Gdx.files.local("save/lastLevel.json");
			Json json = new Json();
			String heroJson = file.readString();
			int lastLevel = json.fromJson(Integer.class, heroJson);
			world.setLastLevel(lastLevel);
		}
		mp3Sound =  Gdx.audio.newMusic(Gdx.files.internal("audio/Organ.mp3"));
		mp3Sound.setVolume(0.2f);
		mp3Sound.setLooping(true);
	 	es =  new ViewMenu(this);
	 	setScreen(es);


	}
	@Override
	public void dispose () {
		es.dispose();
	}

	public World getWorld() {
		return world;
	}

	public Music getSound() {
		return mp3Sound;
	}

}
