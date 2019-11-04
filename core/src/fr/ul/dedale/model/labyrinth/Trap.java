package fr.ul.dedale.model.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.model.Attacker;
import fr.ul.dedale.model.World;

public class Trap extends Cell {

    // Damage of the trap
    private int damage;
    private int x;
    private int y;
    private int nAnim = 1 ;

    /**
     * Constructor of Trap
     * @param damage damage of the trap
     */
    public Trap(int x,int y,int damage){
        super(x,y);
        this.damage = damage;
        type = "fire";
        this.x = x ;
        this.y = y ;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void activate(World world) {
        accrAnim();
        world.damagePlayer(damage);
    }

    public void draw (SpriteBatch sb) {
        Texture texture ;
        if(nAnim%2==0){
             texture = TextureFactory.getInstance().getImage("fire");
        }else{
            texture = TextureFactory.getInstance().getImage("ground");
        }
        sb.draw(texture, x, y, 1, 1, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }


    public void accrAnim() {
        nAnim ++ ;
    }
}
