package fr.ul.dedale.model.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import fr.ul.dedale.DataFactory.TextureFactory;

public class Troll extends Monster{
    public static int SPRITESIZE = 100  ;

    public Troll(int x , int y ){
        super.posX = x;
        super.posY = y;
        super.sprite = new Sprite(TextureFactory.getInstance().getImage("troll"),SPRITESIZE,SPRITESIZE);
        sprite.setSize(4,4);
    }

}
