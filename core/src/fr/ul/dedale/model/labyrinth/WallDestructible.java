package fr.ul.dedale.model.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.model.LabyrinthLoader;
import fr.ul.dedale.model.World;

public class WallDestructible extends Cell{

    private boolean notDestroy;
    private int hp;

    public WallDestructible(int x,int y){
        super(x, y);
        hp = LabyrinthFactory.WALLDESTRUCTIBLEHP;
        notDestroy = true;
        type = "destructible";
    }

    @Override
    public boolean isSolid() {
        return notDestroy;
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
            notDestroy = false;
        }
    }

    public void draw (SpriteBatch sb) {
        Texture texture ;
        if(notDestroy){
            texture = TextureFactory.getInstance().getImage("wallDestructible");
        }else{
            texture = TextureFactory.getInstance().getImage("ground");
        }
        sb.draw(texture, x, y, 1, 1, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }


}
