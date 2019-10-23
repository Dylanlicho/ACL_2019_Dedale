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
    public void moveHero(int direction){
        switch (direction){
            case DirectionFactory
                    .TOP :  hero.moveTop();
            break;

            case DirectionFactory
                    .BOTTOM :  hero.moveBottom();
                break;

            case DirectionFactory
                    .LEFT : { hero.moveLeft();
                break; }

            case DirectionFactory
                    .RIGHT : { hero.moveRight();
                break; }

        }
    }
}
