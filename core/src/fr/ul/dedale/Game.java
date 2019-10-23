package fr.ul.dedale;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.model.View.ViewWorld;
import fr.ul.dedale.model.World;

public class Game extends com.badlogic.gdx.Game {

	ViewWorld es ;
	World world;

	@Override
	public void create() {
	 	es =  new ViewWorld();
	 	setScreen(es);
	 	world = new World();
	}
	@Override
	public void dispose () {
		es.dispose();
	}
}
