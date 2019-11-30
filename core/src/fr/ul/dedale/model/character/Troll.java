package fr.ul.dedale.model.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.model.Attacker;

public class Troll extends Monster implements Attacker {

    public static int SPRITESIZE = 100  ; // sprite size

    /**
     * constructor troll
     * @param x abscissa coordinate  troll
     * @param y ordonate coordinate troll
     */
    public Troll(int x , int y ){
        super.hp = 1;
        super.posX = x;
        super.posY = y;
        super.sprite = new Sprite(TextureFactory.getInstance().getImage("troll"),SPRITESIZE,SPRITESIZE);
        super.sprite.setSize(1,1);
    }

    // Constructor use for the save
    public Troll(){
        super.hp = 1;
        super.sprite = new Sprite(TextureFactory.getInstance().getImage("troll"),SPRITESIZE,SPRITESIZE);
        super.sprite.setSize(1,1);
    }

}
