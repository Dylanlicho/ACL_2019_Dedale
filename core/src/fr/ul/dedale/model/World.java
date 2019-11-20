package fr.ul.dedale.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.model.View.ViewMenu;
import fr.ul.dedale.model.character.*;
import fr.ul.dedale.model.character.Character;
import fr.ul.dedale.model.character.Monster;
import fr.ul.dedale.model.character.Player;
import fr.ul.dedale.model.character.Troll;
import fr.ul.dedale.model.labyrinth.Cell;
import fr.ul.dedale.model.labyrinth.Labyrinth;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class World {

    Player hero ;
    ArrayList<Monster> monsters;
    private Labyrinth labyrinth;
    //The loader of the labyrinth
    private LabyrinthLoader labyrinthLoader;
    //The loader of the characters
    private CharacterLoader characterLoader;
    private int activeFire ;
    //The number of the current level
    private int level;
    //The game
    private Game game;

    public World(Game game) {
        this.game = game;
        activeFire = 0;
        level = 1;
        createLevel();
    }
    public void game(){

        for (int i = 0 ; i < monsters.size(); i++){

            Random r = new Random();

            // Random between 0 and 3
            int dir =  r.nextInt((3 - 0 ) + 1) + 0;

            // The monster is a troll
            if(!monsters.get(i).isThroughWall()  ) {
                while (!canMove(monsters.get(i), DirectionFactory.values()[dir])) {
                    dir = r.nextInt((3 - 0) + 1) + 0;
                }
                moveMonster(dir,i);
            }

            // The monsters is a Ghost
            else{

                // Player coordinates
                int posX_player = hero.getPosX();
                int posY_player = hero.getPosY();

                // Ghost coordinates
                int posX_ghost = monsters.get(i).getPosX();
                int posY_ghost = monsters.get(i).getPosY();

                // Distance between X and Y
                int deltaX = Math.abs(posX_ghost - posX_player);
                int deltaY = Math.abs(posY_ghost - posY_player);

                // If we move left or right
                int directionX = posX_ghost - posX_player;

                // If we move top or bottom
                int directionY = posY_ghost - posY_player;

                // The X has a bigger distance than the Y, So we move the ghost in absciss
                if(deltaX > deltaY){
                    if(directionX >= 0){

                        // Left
                        moveMonster(2,i);
                    }
                    else{

                        // Right
                        moveMonster(3,i);
                    }
                }
                // We move the ghost in ordinate
                else {
                    if(directionY >= 0){
                        // Bottom
                        moveMonster(1,i);
                    }
                    else{
                        // Top
                        moveMonster(0,i);
                    }
                }
            }


            //monster die
            if(monsters.get(i).getHp()<=0){
                monsters.remove(i);
            }else {
                monsters.get(i).attackCollision(this);

            }
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
            if(monsters.get(i).getHp()>0) {
                monsters.get(i).draw(sb);
            }
        }
        hero.drawhit(sb);
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
            level++;
            if (level > LabyrinthFactory.NB_NIVEAUX) {
                ViewMenu vm = new ViewMenu(game);
                game.setScreen(vm);
            }
            else {

                createLevel();
            }
    }

    /**
     * check if the hero has lost
     */
    private void checkLoosePLayer(){
        if (hero.getHp()<=0){
            loose();
        }

    }

    /**
     * the player loose
     */
    private void loose(){
        hero = characterLoader.getPlayer();
        createMonsters();
        labyrinth.init();
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

        }
        return false;
    }

    /**
     * getter hero
     * @return the hero
     */
    public Player getHero() {
        return hero;
    }

    /**
     * getter monsters
     * @return the monsters
     */
    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    /**
     * Increase the HP of the player
     * @param hp number of HP increased
     */
    public void healPlayer(int hp) {
        hero.increaseHP(hp);
    }

    /**
     * create the current level
     */
    private void createLevel() {
        labyrinthLoader = new LabyrinthLoader();
        try {
            labyrinth = labyrinthLoader.createLabyrinth(level);
        } catch (IOException e) {
            e.printStackTrace();
        }
        characterLoader = new CharacterLoader();
        try {

            characterLoader.createCharacter(level);
            hero = characterLoader.getPlayer();
            createMonsters();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the monsters with the list getting in characterLoader
     */
    private void createMonsters() {
        monsters = new ArrayList<Monster>();

        Integer value;
        Integer key;
        for(Map.Entry<Integer, Integer> entry : characterLoader.getMonsters().entrySet()){

            key = entry.getKey();
            value = entry.getValue();

            for(int i=0;i<value;i++) {

                Point newPoint = findEmptyCell();

                switch (key) {

                    // Case Ghost
                    case LabyrinthFactory.GHOST:
                        monsters.add(new Ghost(newPoint.x, newPoint.y));
                        break;

                    // Case Troll
                    case LabyrinthFactory.TROLL:
                        monsters.add(new Troll(newPoint.x, newPoint.y));
                        break;
                }
            }
        }
    }


    /**
     * Find an empty cell
     * @return a point corresponding to the position of an empty cell
     */
    public Point findEmptyCell(){
        int height =LabyrinthFactory.HEIGHT;
        int widht = LabyrinthFactory.WIDTH;
        while(true) {
            int x = ThreadLocalRandom.current().nextInt(0, widht-2);
            int y = ThreadLocalRandom.current().nextInt(0, height-2);
            if (!labyrinth.getCell(x, y).isSolid()) {
                return new Point(x, y);
            }
        }
    }

}
