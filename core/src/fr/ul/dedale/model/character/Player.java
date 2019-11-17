package fr.ul.dedale.model.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.DataFactory.TextureFactory;


public class Player extends Character {
    public static int HP = 1 ; //life player
    public static int CPTANIMATION = 4; // cpt animation
    public static int SPRITESIZEWIGHT = 100  ;    //size of sprite
    public static int SPRITESIZEHIGHT= 130  ;    //size of sprite
    protected static int SPRITEBTOP = 2;
    protected static int SPRITEBOTTOM = 0;
    protected static int SPRITELEFT = 1;
    protected static int SPRITERIGHT = 3;

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
    public void setDirection(){
        if(DirectionFactory.TURNTOP.ordinal() == direction){
            super.sprite = new Sprite(TextureFactory.getInstance().getImage("hero"),0,SPRITEBTOP*SPRITESIZEHIGHT,SPRITESIZEWIGHT,SPRITESIZEHIGHT);
        }
        if(DirectionFactory.TURNBOTTOM.ordinal() == direction){
            super.sprite = new Sprite(TextureFactory.getInstance().getImage("hero"),0,SPRITEBOTTOM*SPRITESIZEHIGHT,SPRITESIZEWIGHT,SPRITESIZEHIGHT);
        }
        if(DirectionFactory.TURNLEFT.ordinal() == direction){
            super.sprite = new Sprite(TextureFactory.getInstance().getImage("hero"),0,SPRITELEFT*SPRITESIZEHIGHT,SPRITESIZEWIGHT,SPRITESIZEHIGHT);
        }
        if(DirectionFactory.TURNRIGHT.ordinal() == direction){
            super.sprite = new Sprite(TextureFactory.getInstance().getImage("hero"),0,SPRITERIGHT*SPRITESIZEHIGHT,SPRITESIZEWIGHT,SPRITESIZEHIGHT);
        }
        sprite.setSize(1,1);
    }

}