package tests.Cell;

import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.Game;
import fr.ul.dedale.model.CharacterLoader;
import fr.ul.dedale.model.LabyrinthLoader;
import fr.ul.dedale.model.World;
import fr.ul.dedale.model.character.Player;
import fr.ul.dedale.model.labyrinth.Labyrinth;
import fr.ul.dedale.model.labyrinth.Magic;
import org.junit.Test;
import org.junit.runner.RunWith;
import tests.GdxTestRunner;

import java.io.IOException;

import static org.junit.Assert.*;
@RunWith(GdxTestRunner.class)
public class MagicTest {

    @Test
    public void testMaxHP() throws IOException {
        World world = new World(new Game());
        world.setLevel(0);
        world.setRoom(101);
        world.createRoom();
        world.moveHero(DirectionFactory.RIGHT);
        world.game();
        assertTrue(world.getHero().getHp() == LabyrinthFactory.HP_PLAYER);
    }



    @Test
    public void testHealMinus1() throws IOException {
        World world = new World(new Game());
        world.setLevel(0);
        world.setRoom(101);
        world.createRoom();
        world.damagePlayer(2);
        world.moveHero(DirectionFactory.RIGHT);
        world.game();
        assertTrue(world.getHero().getHp() == LabyrinthFactory.HP_PLAYER - 1);
    }

    @Test
    public void testHealToMax() throws IOException {
        World world = new World(new Game());
        world.setLevel(0);
        world.setRoom(101);
        world.createRoom();
        world.damagePlayer(1);
        world.moveHero(DirectionFactory.RIGHT);
        world.game();
        assertTrue(world.getHero().getHp() == LabyrinthFactory.HP_PLAYER);
    }

}