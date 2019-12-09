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
    private static final Texture tuto = new Texture("images/howmove.png");
    private static final Texture door = new Texture("images/door.png");
    private static final Texture win = new Texture("images/win_message.png");
    private static final Texture menu = new Texture("images/menu_return.png");
    private static final Texture save = new Texture("images/save.png");
    private static final Texture mystery = new Texture("images/mystery.png");
    private static final Texture arrowHit = new Texture("images/hitArrow.png");
    private static final Texture howtitle = new Texture("images/howtitle.png");
    private static final Texture howturn = new Texture("images/howturn.png");
    private static final Texture howhit = new Texture("images/howhit.png");
    private static final Texture howarrow = new Texture("images/howarrow.png");
    private static final Texture howsave = new Texture("images/howsave.png");
    private static final Texture howmenu = new Texture("images/howmenu.png");
    private static final Texture arrowNB = new Texture("images/FireWand.png");
    private static final Texture wallDestructible = new Texture("images/wallDestructible.png");
    private static final Texture water = new Texture("images/water.png");

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
        images.put("howtitle", howtitle);
        images.put("howturn", howturn);
        images.put("howhit", howhit);
        images.put("howarrow", howarrow);
        images.put("howsave", howsave);
        images.put("howmenu", howmenu);
        images.put("wallDestructible", wallDestructible);
        images.put("water", water);
    }

    public Texture getImage(String imageName) {
        return images.get(imageName);
    }

}