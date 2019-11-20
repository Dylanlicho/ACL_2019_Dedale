package fr.ul.dedale;

import fr.ul.dedale.model.View.ViewMenu;
import fr.ul.dedale.model.World;

public class Game extends com.badlogic.gdx.Game {

	ViewMenu es ;

	@Override
	public void create() {
	 	es =  new ViewMenu(this);
	 	setScreen(es);
	}
	@Override
	public void dispose () {
		es.dispose();
	}


}
