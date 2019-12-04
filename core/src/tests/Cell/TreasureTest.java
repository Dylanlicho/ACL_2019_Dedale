package tests.Cell;

import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.Game;
import fr.ul.dedale.model.World;
import fr.ul.dedale.model.character.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import tests.GdxTestRunner;

import java.io.IOException;

import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class TreasureTest {

    @Test
    public void testTrue() throws IOException {
        World world = new World(new Game());
        world.setLevel(0);
        world.setRoom(104);
        world.createRoom();
        world.moveHero(DirectionFactory.RIGHT);
        world.game();
        assertTrue(world.getLevel() == 1);
    }

    @Test
    public void testFalse() throws IOException {
        World world = new World(new Game());
        world.setLevel(0);
        world.setRoom(104);
        world.createRoom();
        world.moveHero(DirectionFactory.RIGHT);
        world.game();
        assertFalse(world.getLevel() == 0);
    }

}