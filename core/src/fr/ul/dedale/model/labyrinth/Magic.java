package fr.ul.dedale.model.labyrinth;

import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.model.World;

public class Magic extends  Cell {

    /**
     * Constructor of Magic
     * @param x Abscissa
     * @param y Ordinate
     */
    public Magic(int x, int y) {
        super(x, y);
        type = "magic";
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void activate(World world) {
        world.healPlayer(LabyrinthFactory.MAGICHEALTH);
    }
}
