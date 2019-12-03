package tests;

import fr.ul.dedale.model.character.Player;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class LabyrinthLoaderTest {

    @Test
    public void createLabyrinth() {
        Player p = new Player(1,1);
        int x = p.getPosX();
        assertEquals("test bidon", 1, x);
    }
}