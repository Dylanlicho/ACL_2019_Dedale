package fr.ul.dedale.model.labyrinth;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.javafx.css.Size;
import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.DataFactory.SizeFactory;

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
     * Constuctor of the Labyrinth
     * @param tabLevel the table of the level
     */
    public Labyrinth(char[][] tabLevel) {
        //initialisation of the level
        cellList = new Cell[tabLevel.length][tabLevel[0].length];


        for (int i = 0; i < tabLevel.length; i++) {
            for (int j = 0; j < tabLevel[i].length; j++) {
                switch (tabLevel[i][j]) {
                    case SizeFactory.WALL:
                        cellList[i][j] = new Wall(i, j);
                        break;
                    case SizeFactory.GROUND:
                        cellList[i][j] = new Empty(i, j);
                        break;
                    case SizeFactory.PASSAGE:
                        cellList[i][j] = new Passage(i, j, 0, 0);
                        break;
                    case SizeFactory.FIRE:
                        cellList[i][j] = new Trap(i, j, 1);
                        break;
                    case SizeFactory.DELTA:
                        cellList[i][j] = new Treasure(i, j);
                        break;

                }
            }
        }
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

}
