package fr.ul.dedale.model.labyrinth;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.DataFactory.LabyrinthFactory;


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
//        int passageX = -1, passageY = -1; //The first passage
        for (int i = 0; i < tabLevel.length; i++) {
            for (int j = 0; j < tabLevel[i].length; j++) {
                switch (tabLevel[i][j]) {
                    case LabyrinthFactory.WALL:
                        cellList[i][j] = new Wall(i, j);
                        break;
                    case LabyrinthFactory.GROUND:
                        cellList[i][j] = new Empty(i, j);
                        break;
                    case LabyrinthFactory.PASSAGE:
//                        if (passageX < 0) {
                            cellList[i][j] = new Passage(i, j, i, j);
//                            passageX = i;
//                            passageY = j;
//                        } else {
//                            cellList[i][j] = new Passage(i, j, passageX, passageY);
//                            ((Passage) cellList[passageX][passageY]).setDestination(i, j);
//                        }
                        break;
                    case LabyrinthFactory.FIRE:
                        cellList[i][j] = new Trap(i, j, 1);
                        break;
                    case LabyrinthFactory.DELTA:
                        cellList[i][j] = new Treasure(i, j);
                        break;
                    case LabyrinthFactory.MAGIC:
                        cellList[i][j] = new Magic(i,j);
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
