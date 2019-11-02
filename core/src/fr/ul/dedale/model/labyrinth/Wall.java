package fr.ul.dedale.model.labyrinth;

import fr.ul.dedale.model.World;

public class Wall extends Cell{


    /**
     * Constructor of Wall
     * @param x Abscissa
     * @param y Ordinate
     */
    public Wall(int x, int y) {
        super(x, y);
        type = "wall";
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public void activate(World world) {
        // Nothing to do
    }

}
