package fr.ul.dedale.model.labyrinthe;

import fr.ul.dedale.model.World;

/**
 * The cell with the treasure
 */
public class Treasure extends Cell{

    /**
     * Constructor of Cell
     *
     * @param x Abscissa
     * @param y Ordinate
     */
    public Treasure(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void activate(World world) {
        world.winPlayer(); //The player win
    }
}
