package fr.ul.dedale.model.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.model.World;

import java.util.Random;

public class Mystery extends Cell {

    public Mystery(int posX, int posY){
        super(posX,posY);
        type = "mystery";
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void activate(World world) {
        if(!isActivate) {
            world.mystery(getContent());
            isActivate = true;
        }
    }

    /**
     * Return a number between the number of type the mystery cell can take and 0
     * @return a number between the number of type the mystery cell can take and 0
     */
    public int getContent(){
        Random random = new Random();
        return random.nextInt(LabyrinthFactory.NB_MYSTERY);
    }

    /**
     * Draw the sprite
     * @param sb the SpriteBatch
     */
    public void draw (SpriteBatch sb) {
        Texture texture ;
        if(!isActivate){
            texture = TextureFactory.getInstance().getImage("mystery");
        }else{
            texture = TextureFactory.getInstance().getImage("ground");
        }
        sb.draw(texture, x, y, 1, 1, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

}
