package tests;

import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.Game;
import fr.ul.dedale.model.World;
import fr.ul.dedale.model.character.Monster;
import fr.ul.dedale.model.labyrinth.Labyrinth;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class SaveTest {



    @Test
    public void TestSaveHero(){

        World world = new World(new Game());
        world.createLevel();
        world.save();
        world.moveHero(DirectionFactory.BOTTOM);
        world.load();

        assertEquals("Le hero doit etre en 1 1 ",1,world.getHero().getPosX());
        assertEquals("Le hero doit etre en 1 1 ",1,world.getHero().getPosY());

    }

    @Test
    public void TestSaveLevel(){

        World world = new World(new Game());
        world.createLevel();
        int level = world.getLevel();

        world.save();
        world.setLevel(2);
        world.createLevel();
        world.load();

        assertEquals("Le level doit etre egale",level,world.getLevel());

    }

    @Test
    public void TestSaveLabyrinth(){

        World world = new World(new Game());
        world.createLevel();
        Labyrinth level = world.getLabyrinth();

        world.save();
        world.setLevel(2);
        world.createLevel();
        world.load();

        assertEquals("Le level doit etre egale",true,level.equals(world.getLabyrinth()));

    }

    @Test
    public void TestSaveMonsters(){

        World world = new World(new Game());
        world.createLevel();
        ArrayList<Monster> monsters = world.getMonsters();

        world.save();


        world.load();

        ArrayList<Monster> monsters1 = world.getMonsters();
        for(int i=0;i<monsters1.size();i++){
            assertEquals("Les monstres devraient etre a la meme place",monsters.get(i).getPosX(),monsters1.get(i).getPosX());
            assertEquals("Les monstres devraient etre a la meme place",monsters.get(i).getPosY(),monsters1.get(i).getPosY());

        }



    }
}
