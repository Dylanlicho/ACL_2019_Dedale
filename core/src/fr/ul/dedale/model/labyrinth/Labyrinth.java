package fr.ul.dedale.model.labyrinth;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import java.io.Serializable;
import java.util.Arrays;


public class Labyrinth implements Serializable {

    // List of cells which compose the level
    private Cell[][] cellList;

    /**
     * Constructor of the Labyrinth
     * @param height Height of the level
     * @param width Width of the level
     */
    public void Labyrinth(int height, int width){
        cellList = new Cell[height][width];
    }

    public Labyrinth(){

    }

    /**
     * Constuctor of the Labyrinth
     * @param tabLevel the table of the level
     */
    public Labyrinth(char[][] tabLevel) {
        //initialisation of the level
        int X = Math.min(tabLevel.length, LabyrinthFactory.HEIGHT - 2);
        int Y = Math.min(tabLevel[0].length, LabyrinthFactory.WIDTH);
        cellList = new Cell[X][Y];

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                switch (tabLevel[i][j]) {
                    case LabyrinthFactory.WALL:
                        cellList[i][j] = new Wall(i, j);
                        break;
                    case LabyrinthFactory.GROUND:
                        cellList[i][j] = new Empty(i, j);
                        break;
                    case LabyrinthFactory.PASSAGE:
                            cellList[i][j] = new Passage(i, j, i, j);
                        break;
                    case LabyrinthFactory.FIRE:
                        cellList[i][j] = new Trap(i, j, LabyrinthFactory.TRAPDAMAGE);
                        break;
                    case LabyrinthFactory.DELTA:
                        cellList[i][j] = new Treasure(i, j);
                        break;
                    case LabyrinthFactory.MAGIC:
                        cellList[i][j] = new Magic(i,j);
                        break;
                    case LabyrinthFactory.DOOR:
                        cellList[i][j] = new Door(i,j);
                        break;
                    case LabyrinthFactory.MYSTERY:
                        cellList[i][j] = new Mystery(i,j);
                        break;
                    case LabyrinthFactory.WALLDESTRUCTIBLE:
                        cellList[i][j] = new WallDestructible(i,j);
                        break;
                    default:

                        break;
                }
            }
        }

    }

    /**getteur cell
     * @param x Abscissa of the cell where the character is
     * @param y Ordinate of the cell where the character is
     * @return
     */
    public Cell getCell(int x,int y){
        return cellList[x][y];
    }
    /**
     * @param x Abscissa of the cell where the character is
     * @param y Ordinate of the cell where the character is
     * @param direction Direction of the movement of the character( we need it to know the cell we want)
     * @return
     */
    public Cell getNextCell(int x, int y,DirectionFactory direction) throws NullPointerException{
        Cell res = null;
        switch(direction) {
            case TOP:
                if (y<cellList[x].length-1){
                    res = cellList[x][y + 1];
                }
                break;
            case BOTTOM:
                if(y > 0) {
                    res = cellList[x][y - 1];
                }
                break;
            case LEFT:
                if(x>0) {
                    res = cellList[x - 1][y];
                }
                break;
            case RIGHT:
                if(x<cellList.length-1) {
                    res = cellList[x + 1][y];
                }
                break;
        }
        return res;
    }

    /**
     * Reinitialize the cells, if the cells were activate, those may be not activate
     */
    public void init() {
        for (Cell[] cells : cellList) {
            for (Cell c : cells) {
                c.setActivate(false);
            }
        }
    }

    /**
     * draw the labyrinth
     * @param sb the Sprite batch
     */
    public void draw (SpriteBatch sb) {

        for (Cell[] cells : cellList) {
            for (Cell cell : cells) {
                if (cell != null) {
                    cell.draw(sb);
                }
            }
        }
    }

    public Cell[][] getCells() {
        return cellList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Labyrinth labyrinth = (Labyrinth) o;

        return Arrays.deepEquals(cellList, labyrinth.cellList);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(cellList);
    }
}
