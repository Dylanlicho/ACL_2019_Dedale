package fr.ul.dedale.model.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.model.character.Character;

public class Player extends Character {

public Player(int x , int y ){
    super.posX = x;
    super.posY = y;
    super.sprite = new Sprite(TextureFactory.getInstance().getImage("hero"));
    sprite.setSize(1,1);
}
}
