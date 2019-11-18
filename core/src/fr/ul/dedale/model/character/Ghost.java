package fr.ul.dedale.model.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.model.Attacker;

public class Ghost extends Monster implements Attacker {

    static int SPRITESIZE = 35  ; // sprite size

    public Ghost(int x ,int y){
        super.hp = 1;
        super.posX = x;
        super.posY = y;
        super.sprite = new Sprite(TextureFactory.getInstance().getImage("ghost"),SPRITESIZE ,SPRITESIZE);
        super.sprite.setSize(1,1);
        super.throughWall = true;
    }
}
