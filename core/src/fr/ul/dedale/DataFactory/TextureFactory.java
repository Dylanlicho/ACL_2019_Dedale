package fr.ul.dedale.DataFactory;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TextureFactory {

    private static final Texture hero = new Texture("images/player.png");
    private static final Texture troll = new Texture("images/troll.png");
    private static final Texture grass = new Texture("images/grass.jpg");
    private static final Texture ground = new Texture("images/ground.png");
    private static final Texture groundTrap = new Texture("images/groundtrap.png");
    private static final Texture wall = new Texture("images/wall.png");
    private static final Texture fire = new Texture("images/fire.png");
    private static final Texture delta = new Texture("images/delta.png");
    private static final Texture passage = new Texture("images/hermes.png");
    private static final Texture ghost = new Texture("images/ghost_planche.png");
    private static final Texture magic = new Texture("images/magic.png");
    private static final Texture life = new Texture("images/life.png");
    private static final Texture hit = new Texture("images/hit.png");
    private static final Texture tuto = new Texture("images/keyboard.png");
    private static final Texture door = new Texture("images/door.png");
    private static final Texture win = new Texture("images/win_message.png");
    private static final Texture menu = new Texture("images/menu_return.png");
    private static final Texture save = new Texture("images/save.png");
    private static final Texture mystery = new Texture("images/mystery.png");
    private static final Texture arrowHit = new Texture("images/hitArrow.png");

    private static final Texture arrowNB = new Texture("images/FireWand.png");

    private static TextureFactory ourInstance = new TextureFactory();

    /**
     * getteur pour recuperer une Hashmap contenant toutes les textures
     *
     * @return
     */
    public static TextureFactory getInstance() {
        return ourInstance;
    }

    private static HashMap<String, Texture> images;

    private TextureFactory() {
        images = new HashMap<String, Texture>();
        images.put("hero", hero);
        images.put("troll", troll);
        images.put("grass", grass);
        images.put("ground", ground);
        images.put("groundTrap", groundTrap);
        images.put("wall", wall);
        images.put("fire", fire);
        images.put("delta", delta);
        images.put("passage", passage);
        images.put("ghost", ghost);
        images.put("magic", magic);
        images.put("life", life);
        images.put("hit", hit);
        images.put("tuto", tuto);
        images.put("door", door);
        images.put("win", win);
        images.put("menu", menu);
        images.put("save", save);
        images.put("mystery", mystery);
        images.put("hitArrow" ,arrowHit);
        images.put("nbRow", arrowNB);
    }

    public Texture getImage(String imageName) {
        return images.get(imageName);
    }

}