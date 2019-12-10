package tests;

import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.Game;
import fr.ul.dedale.model.World;
import fr.ul.dedale.model.character.Monster;
import fr.ul.dedale.model.character.Player;
import fr.ul.dedale.model.character.Troll;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class PlayerTest {


    @Test
    public void moveLeft() {
        Player p = new Player(10,10);
        p.moveLeft();
        assertEquals("moveLeft X error",  p.getPosX(), 9);
        assertEquals("moveLeft Y error",  p.getPosY(), 10);

    }

    @Test
    public void moveRight() {
        Player p = new Player(10,10);

        p.moveRight();
        assertEquals("moveRight Xerror",  p.getPosX(), 11);
        assertEquals("moveRight Y error",  p.getPosY(), 10);


    }

    @Test
    public void moveBottom() {
        Player p = new Player(10,10);

        p.moveBottom();
        assertEquals("moveBottom error",  p.getPosX(), 10);
        assertEquals("moveBottom error",  p.getPosY(), 9);


    }

    @Test
    public void moveTop() {
        Player p = new Player(10,10);
        p.moveTop();
        assertEquals("moveTop error",  p.getPosX(), 10);
        assertEquals("moveTop error",  p.getPosY(), 11);

    }

    @Test
    public void moveInAWall() {

        World world = new World(new Game());
        world.createLevel();

        int posX = world.getHero().getPosX();
        int posY = world.getHero().getPosY();

        world.moveHero(DirectionFactory.BOTTOM);

        assertEquals("Le hero ne devrait pas se deplacer",posX,world.getHero().getPosX());
        assertEquals("Le hero ne devrait pas se deplacer",posY,world.getHero().getPosY());

    }


    @Test
    public void attackSwordWrongDirection() {

        World world = new World(new Game());
        world.createLevel();

        int posX = world.getHero().getPosX();
        int posY = world.getHero().getPosY();


        Troll troll=new Troll(posX+1,posY);
        int vie = troll.getHp();

        ArrayList<Monster> tmp = new ArrayList<>();
        tmp.add(troll);
        world.setMonsters(tmp);

        world.getHero().attackSword(world);

        assertEquals("Le monstre ne devrait pas mourrir",vie,troll.getHp());

    }

    @Test
    public void attackSword() {

        World world = new World(new Game());
        world.createLevel();

        int posX = world.getHero().getPosX();
        int posY = world.getHero().getPosY();



        Troll troll=new Troll(posX+1,posY);
        int vie = troll.getHp();

        ArrayList<Monster> tmp = new ArrayList<>();
        tmp.add(troll);
        world.setMonsters(tmp);

        world.getHero().turn(DirectionFactory.TURNRIGHT);


        world.getHero().attackSword(world);

        assertEquals("Le monstre ne devrait pas mourrir",vie-1,troll.getHp());

    }


    @Test
    public void attackBow() {

        World world = new World(new Game());
        world.createLevel();

        int posX = world.getHero().getPosX();
        int posY = world.getHero().getPosY();



        Troll troll=new Troll(posX+1,posY);
        int vie = troll.getHp();

        ArrayList<Monster> tmp = new ArrayList<>();
        tmp.add(troll);
        world.setMonsters(tmp);

        world.getHero().turn(DirectionFactory.TURNRIGHT);



        world.getHero().attackArrow(world);

        assertEquals("Le monstre ne devrait pas mourrir",vie-1,troll.getHp());

    }

    @Test
    public void attackBowNoArrow() {

        World world = new World(new Game());
        world.createLevel();

        int posX = world.getHero().getPosX();
        int posY = world.getHero().getPosY();



        Troll troll=new Troll(posX+1,posY);
        int vie = troll.getHp();

        ArrayList<Monster> tmp = new ArrayList<>();
        tmp.add(troll);
        world.setMonsters(tmp);

        world.getHero().attackArrow(world);
        world.getHero().attackArrow(world);
        world.getHero().attackArrow(world);

        System.out.println(world.getHero().getNumberArrow());

        world.getHero().turn(DirectionFactory.TURNRIGHT);

        world.getHero().attackArrow(world);

        assertEquals("Le monstre ne devrait pas mourrir",vie,troll.getHp());

    }

    @Test
    public void turn() {
        Player p = new Player();
        p.turn(DirectionFactory.TURNBOTTOM);
        assertEquals("error directtion", p.getDirection(),DirectionFactory.TURNBOTTOM.ordinal());
    }

    @Test
    public void turnWorld(){
        World world = new World(new Game());
        world.setLevel(1);
        world.setRoom(1);
        world.createRoom();
        //world.setMonsters(new ArrayList<Monster>());
        //world.game();


        world.getHero().turn(DirectionFactory.TURNLEFT);
        assertEquals("Le hero devrait tourner a gauche",DirectionFactory.TURNLEFT.ordinal(),world.getHero().getDirection());

    }


    @Test
    public void getNumberArrow() {
        Player p = new Player(10,10);
        assertEquals("numberArrow error",  p.getNumberArrow(), LabyrinthFactory.ARROWNUMBER);
    }
}