package tests;

import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.model.CharacterLoader;
import fr.ul.dedale.model.character.Monster;
import fr.ul.dedale.model.character.Player;
import fr.ul.dedale.model.labyrinth.Labyrinth;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class CharacterLoaderTest {

    @Test
    public void createCharacterCheckHero() throws IOException {
        CharacterLoader loader = new CharacterLoader();
        loader.createCharacter(0,1);
        Player player = loader.getPlayer();
        int x = player.getPosX();
        int y = player.getPosY();
        assertTrue("createCharacterCheckHero | position of the hero wrong", (1 == x) && (y == 1));
    }

    @Test
    public void createCharacterCheckMonsters() throws IOException {
        CharacterLoader loader = new CharacterLoader();
        loader.createCharacter(0,1);
        HashMap<Integer, Integer> monsters = loader.getMonsters();
        int nbGhost = monsters.get(LabyrinthFactory.GHOST);
        int nbTroll = monsters.get(LabyrinthFactory.TROLL);
        assertTrue("createCharacterCheckHero | position of the hero wrong", (nbGhost == 0) && (nbTroll == 2));
    }

    @Test (expected = IOException.class)
    public void createCharacterFileNotExist() throws IOException {
        CharacterLoader loader = new CharacterLoader();
        loader.createCharacter(0,0);
        fail("createCharacterFileNotExist | lecture of a file which not exist");
    }
}