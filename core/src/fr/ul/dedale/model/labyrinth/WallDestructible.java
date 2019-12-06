package fr.ul.dedale.model.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.model.World;

public class WallDestructible extends Cell{

    private int hp;

    public WallDestructible(int x,int y){
        super(x, y);
        hp = LabyrinthFactory.WALLDESTRUCTIBLEHP;
        type = "destructible";
    }

    public WallDestructible(){}

    @Override
    public boolean isSolid() {
        return !isActivate;
    }

    @Override
    public void activate(World world) {

    }

    /**
     * Decrease the number of hp when the player attack the wall
     */
    public void damage(){
        hp = hp - 1;
        if(hp <= 0){
            isActivate = true;
        }
    }

    public void draw (SpriteBatch sb) {
        Texture texture ;
        if(!isActivate){
            texture = TextureFactory.getInstance().getImage("wallDestructible");
        }else{
            texture = TextureFactory.getInstance().getImage("ground");
        }
        sb.draw(texture, x, y, 1, 1, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }


}
