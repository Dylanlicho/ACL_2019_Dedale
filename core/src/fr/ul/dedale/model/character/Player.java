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


}