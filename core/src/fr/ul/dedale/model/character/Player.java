package fr.ul.dedale.model.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import fr.ul.dedale.DataFactory.TextureFactory;


public class Player extends Character {
    public static int HP = 1 ; //life player
    public static int CPTANIMATION = 4; // cpt animation
    public static int SPRITESIZEWIGHT = 100  ;    //size of sprite
    public static int SPRITESIZEHIGHT= 130  ;    //size of sprite

    /**
     * constructor player
     * @param x abscissa coordinate  player
     * @param y ordonate coordinate player
     */
    public Player(int x , int y ){
        hp = HP;
        throughWall = false;
        super.posX = x;
        super.posY = y;
        super.sprite = new Sprite(TextureFactory.getInstance().getImage("hero"),SPRITESIZEWIGHT,SPRITESIZEHIGHT);
        sprite.setSize(1,1);
    }


}