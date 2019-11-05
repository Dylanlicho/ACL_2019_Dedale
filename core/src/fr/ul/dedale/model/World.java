package fr.ul.dedale.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.model.character.Character;
import fr.ul.dedale.model.character.Monster;
import fr.ul.dedale.model.character.Player;
import fr.ul.dedale.model.character.Troll;
import fr.ul.dedale.model.labyrinth.Cell;
import fr.ul.dedale.model.labyrinth.Labyrinth;
import fr.ul.dedale.model.labyrinth.Treasure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class World {

    Player hero ;
    ArrayList<Monster> monsters;
    private Labyrinth labyrinth;
    private LabyrinthLoader loader;
    private int activeFire ;

    public World() {
        activeFire = 0;
        hero = new Player(1,1);
        monsters = new ArrayList<Monster>();
        monsters.add(new Troll(12,12));
        monsters.add(new Troll(5,5));
        loader = new LabyrinthLoader();
        try {
            labyrinth = loader.createLabyrinth(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void game(){
        for (int i = 0 ; i < monsters.size(); i++){
            Random r = new Random();
            int dir =  r.nextInt((3 - 0 ) + 1) + 0;
            if(!monsters.get(i).isThroughWall()  ) {
                while (!canMove(monsters.get(i), DirectionFactory.values()[dir])) {
                    dir = r.nextInt((3 - 0) + 1) + 0;
                }
                moveMonster(dir,i);

            }
            monsters.get(i).attackCollision(this);
        }
        labyrinth.getCell(hero.getPosX(),hero.getPosY()).activate(this);
        checkLoosePLayer();
    }
    public void draw(SpriteBatch sb){
        labyrinth.draw(sb);
        if(hero.getHp()>0) {
            hero.draw(sb);
        }
        for(int i = 0 ; i < monsters.size(); i++){
            monsters.get(i).draw(sb);
        }
    }

    public void moveHero(DirectionFactory direction){
        if(canMove(hero,direction)){
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
            System.out.println("you win");
            Gdx.app.exit();
    }

    /**
     * the hero loose
     */
    public void checkLoosePLayer(){
        if (hero.getHp()<0){
            System.out.println("you died");
//            Gdx.app.exit();
            hero = new Player(1,1);
        }

    }

    /**
     * return true if the character can move in futur case
     * @param c character
     * @param d direction
     * @return
     */
    public boolean canMove(Character c, DirectionFactory d){
        int x = c.getPosX() ;
        int y = c.getPosY();

        try {
            Cell nextCell = labyrinth.getNextCell(x, y, d);
            if(!c.isThroughWall() && nextCell.isSolid()){
                return false;
            }

            return true;
        }catch (NullPointerException e){
            System.out.println("Exception: la cell n'a pas pu être récupéré");
        }
        return false;
    }

    /**
     * getter hero
     * @return
     */
    public Player getHero() {
        return hero;
    }
}
