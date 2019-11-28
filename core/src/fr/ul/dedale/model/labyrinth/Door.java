package fr.ul.dedale.model.labyrinth;


import fr.ul.dedale.model.World;

public class Door extends Cell {
    /**
     * Constructor of Cell
     *
     * @param x Abscissa
     * @param y Ordinate
     */
    public Door(int x, int y) {
        super(x, y);
        type = "door";
    }

    public Door(){

    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void activate(World world) {
        world.nextRoom();
    }


}
