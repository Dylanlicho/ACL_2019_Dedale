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
//        images.put("ground",new Texture("images/grass.jpg"));
        images.put("wall",new Texture("images/wall.png"));
        images.put("fire",new Texture("images/fire.png"));
        images.put("delta",new Texture("images/delta.png"));
        images.put("passage",new Texture("images/hermes.png"));

    }
    public Texture getImage(String imageName){
        return images.get(imageName);
    }

}
