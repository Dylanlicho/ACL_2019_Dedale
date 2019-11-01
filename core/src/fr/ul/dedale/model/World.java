package fr.ul.dedale.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.model.character.Monster;
import fr.ul.dedale.model.character.Player;
import fr.ul.dedale.model.character.Troll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class World {
    Player hero ;
    ArrayList<Monster> monsters;

    public World() {
        hero = new Player(25,25);
        monsters = new ArrayList<Monster>();
        monsters.add(new Troll(10,10));
    }

    public void draw(SpriteBatch sb){
        hero.draw(sb);
        for(int i = 0 ; i < monsters.size(); i++){
            monsters.get(i).draw(sb);
        }
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
        for (int i = 0 ; i < monsters.size(); i++){
            Random r = new Random();
            int dir =  r.nextInt((4 - 0 ) + 1) + 0;
            moveMonster(dir,i);


        }
    }
    public void moveMonster(int direction, int elem ){
        switch (direction){
            case 0 :  monsters.get(elem).moveTop();
                break;

            case 1 :  monsters.get(elem).moveBottom();
                break;

            case 2 : { monsters.get(elem).moveLeft();
                break; }

            case 3 : { monsters.get(elem).moveRight();
                break; }

        }


    }

    /**
     * Apply some damage to the player
     * @param damage Number of damage applied to the player
     */
    public void damagePlayer(int damage){
        hero.decreaseHp(damage);
    }

    /**
     * Teleport the player on an other cell
     * @param x Abscissa of the destination
     * @param y Ordinate of the destination
     */
    public void teleportPlayer(int x, int y){
        hero.setX(x);
        hero.setY(y);
    }

    /**
     * The hero win
     */
    public void winPlayer() {
        //The player win
    }

}
