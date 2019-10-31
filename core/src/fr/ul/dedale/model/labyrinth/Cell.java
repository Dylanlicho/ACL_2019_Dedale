package fr.ul.dedale.model.labyrinth;

import fr.ul.dedale.model.World;

public abstract class Cell {

    // true if the player can't go onto the cell, false otherwise
    private boolean solid;
    // Coordinate of the cell
    private int x;
    private int y;

    /**
     * Constructor of Cell
     * @param x Abscissa
     * @param y Ordinate
     */
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Return true if the player can't go onto the cell, false otherwise
     * @return
     */
    public abstract boolean isSolid();

    /**
     * Action do when the player is on this cell
     */
    public abstract void activate(World world);

}
