package fr.ul.dedale.DataFactory;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TextureFactory {
    private static TextureFactory ourInstance = new TextureFactory();

    /**
     * getteur pour recuperer une Hashmap contenant toutes les textures
     * @return
     */
    public static TextureFactory getInstance() {
        return ourInstance;
    }
    private HashMap<String, Texture> images ;
    private TextureFactory() {
        images = new HashMap<String, Texture>();
        images.put("hero",new Texture("images/player.png"));
        images.put("troll",new Texture("images/troll.png"));
        images.put("grass",new Texture("images/grass.jpg"));
        images.put("ground",new Texture("images/ground.png"));
        images.put("groundTrap",new Texture("images/groundtrap.png"));
        images.put("wall",new Texture("images/wall.png"));
        images.put("fire",new Texture("images/fire.png"));
        images.put("delta",new Texture("images/delta.png"));
        images.put("passage",new Texture("images/hermes.png"));
        images.put("ghost",new Texture("images/ghost_planche.png"));
        images.put("magic",new Texture("images/magic.png"));
        images.put("life",new Texture("images/life.png"));
        images.put("hit",new Texture("images/hit.png"));
        images.put("tuto",new Texture("images/keyboard.png"));
    }
    public Texture getImage(String imageName){
        return images.get(imageName);
    }

}
