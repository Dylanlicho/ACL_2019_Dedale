package fr.ul.dedale.model.labyrinthe;

import com.sun.xml.internal.bind.v2.TODO;
import fr.ul.dedale.DataFactory.DirectionFactory;

public class Labyrinth {

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

    /**
     *
     * @param x
     * @param y
     * @param direction
     * @return
     */
    public Cell getCell(int x, int y,DirectionFactory direction){
        //TODO il faut faire un try catch dans la fonction appelante au cas où le "res" retourné est null
        Cell res = null;
        switch(direction){
            case TOP:
                res = cellList[x-1][y];
                break;
            case BOTTOM:
                res = cellList[x+1][y];
                break;
            case LEFT:
                res = cellList[x][y-1];
                break;
            case RIGHT:
                res = cellList[x][y+1];
                break;
        }
        return res;
    }

}
