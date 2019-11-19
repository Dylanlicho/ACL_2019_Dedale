package fr.ul.dedale.model.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.model.World;

public class Magic extends  Cell {


    /**
     * Constructor of Magic
     * @param x Abscissa
     * @param y Ordinate
     */
    public Magic(int x, int y) {
        super(x, y);
        type = "magic";
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void activate(World world) {
        isActivate = true;
        world.healPlayer(LabyrinthFactory.MAGICHEALTH);
    }

    public void draw (SpriteBatch sb) {
        Texture texture ;
        if(!isActivate){
            texture = TextureFactory.getInstance().getImage("magic");
        }else{
            texture = TextureFactory.getInstance().getImage("ground");
        }
        sb.draw(texture, x, y, 1, 1, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

}
