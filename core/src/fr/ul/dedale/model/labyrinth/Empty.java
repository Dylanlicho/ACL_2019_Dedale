package fr.ul.dedale.model.labyrinth;

import fr.ul.dedale.model.World;
import fr.ul.dedale.model.character.Player;

public class Empty extends Cell{

    /**
     * Constructor of Empty
     * @param x Abscissa
     * @param y Ordinate
     */
    public Empty(int x, int y) {
        super(x, y);
        type = "ground";
    }

    public Empty(){

    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void activate(World world) {
        // Nothing to do
    }
}
