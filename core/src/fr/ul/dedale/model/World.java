package fr.ul.dedale.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.model.character.Player;

public class World {
    Player hero ;

    public World(){
        hero = new Player(25,25);
    }
    public void draw(SpriteBatch sb){
        hero.draw(sb);
    }
    public void moveHero(DirectionFactory direction){
        switch (direction){
            case TOP :  hero.moveTop();
            break;

            case BOTTOM :  hero.moveBottom();
                break;

            case LEFT : { hero.moveLeft();
                break; }

            case RIGHT : { hero.moveRight();
                break; }

        }
    }
}
