package fr.ul.dedale.model.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.model.World;

import java.util.Random;

public class Mystery extends Cell {

    private int contentActivate;
    private static int print;

    public Mystery(int posX, int posY){
        super(posX,posY);
        type = "mystery";
        contentActivate = 0;
        print = 0;
    }

    public Mystery() {
        type = "mystery";
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void activate(World world) {
        if(!isActivate) {
            contentActivate = getContent();
            world.mystery(contentActivate);
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
            if (print < 100) {
                switch (contentActivate){
                    case LabyrinthFactory.MYSTERYTRAP:
                        texture = TextureFactory.getInstance().getImage("fire");
                        break;
                    case LabyrinthFactory.MYSTERYCARE:
                        texture = TextureFactory.getInstance().getImage("magic");
                        break;
                    default:
                        texture = TextureFactory.getInstance().getImage("ground");
                        break;
                }
                print++;
            }
            else texture = TextureFactory.getInstance().getImage("ground");


        }
        sb.draw(texture, x, y, 1, 1, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

}
