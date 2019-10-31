package fr.ul.dedale.model.labyrinth;

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
     * @param x Abscissa of the cell where the character is
     * @param y Ordinate of the cell where the character is
     * @param direction Direction of the movement of the character( we need it to know the cell we want)
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
