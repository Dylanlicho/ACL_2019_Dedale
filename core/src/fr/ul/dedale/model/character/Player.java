package fr.ul.dedale.model.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import fr.ul.dedale.DataFactory.TextureFactory;


public class Player extends Character {
    public static int CPTANIMATION = 4;
    public static int SPRITESIZE = 30  ;

    public Player(int x , int y ){
        super.posX = x;
        super.posY = y;
        super.sprite = new Sprite(TextureFactory.getInstance().getImage("hero"),SPRITESIZE,SPRITESIZE);
        sprite.setSize(4,4);
    }

    /**
     * Decrease the Health point of the player
     * @param damage Damage the player take
     */
    public void decreaseHp(int damage) {
        hp = hp - damage;
    }

    /**
     * Set the abscissa of the player
     * @param x the value of the set
     */
    public void setX(int x){
        posX = x;
    }

    /**
     * Set the ordinate of the player
     * @param y the value of the set
     */
    public void setY(int y) {
        posY = y;
    }
}
