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

    }
    public Texture getImage(String imageName){
        return images.get(imageName);
    }

}
