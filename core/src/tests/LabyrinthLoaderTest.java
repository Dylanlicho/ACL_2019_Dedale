package tests;

import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.model.LabyrinthLoader;
import fr.ul.dedale.model.labyrinth.Cell;
import fr.ul.dedale.model.labyrinth.Labyrinth;
import fr.ul.dedale.model.labyrinth.Passage;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class LabyrinthLoaderTest {

    private LabyrinthLoader loader = new LabyrinthLoader();

    @Test
    public void createLabyrinthPlusPetitQueTailleMonde() throws IOException {
        Labyrinth labyrinth = loader.createLabyrinth(0,1);
        Cell[][] cells = labyrinth.getCells();
        int size = 8;

        assertEquals("createLabyrinthPlusPetitQueTailleMonde | size height error", cells.length, size);
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | size width error", cells[0].length, size);

        String type;
        for (int i = 0; i < size; i++) {
            type = "wall";
            assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[0][i].getType());
        }

        for (int i = 0; i < size; i++) {
            type = "wall";
            assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[size-1][i].getType());
        }

        type = "wall";
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[1][0].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[1][5].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[1][7].getType());

        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[2][0].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[2][5].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[2][7].getType());

        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[3][0].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[3][5].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[3][7].getType());

        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[4][0].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[4][5].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[4][7].getType());

        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[5][0].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[5][7].getType());

        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[6][0].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[6][7].getType());

        type = "ground";
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[1][2].getType());

        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[2][1].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[2][2].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[2][3].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[2][6].getType());

        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[3][1].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[3][2].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[3][3].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[3][6].getType());

        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[4][1].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[4][2].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[4][3].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[4][6].getType());

        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[5][1].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[5][2].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[5][3].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[5][6].getType());

        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[6][1].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[6][2].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[6][3].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[6][4].getType());

        type = "mystery";
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[1][3].getType());

        type = "magic";
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, labyrinth.getCell(6,5).getType()); //npe

        type = "passage";
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[1][1].getType());
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | type error", type, cells[6][6].getType());

        Passage passage1 = (Passage) labyrinth.getCell(1,1);
        int aX = passage1.getArriveX();
        int aY = passage1.getArriveY();
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | destination passage x", 6, aX);
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | destination passage y", 6, aY);

        Passage passage2 = (Passage) labyrinth.getCell(6,6);
        int aX2 = passage2.getArriveX();
        int aY2 = passage2.getArriveY();
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | destination passage x", 1, aX2);
        assertEquals("createLabyrinthPlusPetitQueTailleMonde | destination passage y", 1, aY2);
    }

    @Test (expected = IOException.class)
    public void createLabyrinthFichierInexistant() throws IOException {
        Labyrinth labyrinth = loader.createLabyrinth(0,0);
        fail("createLabyrinthFichierInexistant | lecture of a file which not exist");
    }

    @Test
    public void createLabyrinth() throws IOException {
        Labyrinth labyrinth = loader.createLabyrinth(0,2);
        Cell[][] cells = labyrinth.getCells();
        assertEquals("createLabyrinth | size error", cells.length, LabyrinthFactory.WIDTH);
        assertEquals("createLabyrinth | size error", cells[0].length, LabyrinthFactory.WIDTH);
    }

    @Test
    public void createLabyrinthPlusGrandQueTailleMonde() throws IOException {
        Labyrinth labyrinth = loader.createLabyrinth(0,3);
        Cell[][] cells = labyrinth.getCells();
        System.out.println("cells.length = "+cells.length);
        assertEquals("createLabyrinth | size error", LabyrinthFactory.WIDTH, cells.length);
        assertEquals("createLabyrinth | size error", LabyrinthFactory.HEIGHT - 2, cells[0].length);
    }

}