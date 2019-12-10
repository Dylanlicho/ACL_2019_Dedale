package fr.ul.dedale.model.labyrinth;

import fr.ul.dedale.model.World;

public class Water extends Cell {

    public Water(int x, int y) {
        super(x,y);
        type = "water";
    }

    public Water() {

    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void activate(World world) {
        world.stopPlayer();
    }
}
