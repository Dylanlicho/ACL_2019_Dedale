package tests;

import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.Game;
import fr.ul.dedale.model.World;
import fr.ul.dedale.model.character.Player;
import org.junit.Test;
import org.junit.runner.RunWith;

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

    }

    @Test
    public void turn() {
        Player p = new Player();
        p.turn(DirectionFactory.TURNBOTTOM);
        assertEquals("error directtion", p.getDirection(),DirectionFactory.TURNBOTTOM.ordinal());
    }


    @Test
    public void getNumberArrow() {
        Player p = new Player(10,10);
        assertEquals("numberArrow error",  p.getNumberArrow(), LabyrinthFactory.ARROWNUMBER);
    }
}