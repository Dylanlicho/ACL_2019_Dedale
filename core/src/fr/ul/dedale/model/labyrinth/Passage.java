package fr.ul.dedale.model.labyrinth;

import fr.ul.dedale.model.World;

public class Passage extends Cell {

    // The coordinates of where the player will be teleport
    private int arriveX;
    private int arriveY;

    /**
     * Constructor of Passage
     * @param posX Abscissa of the passage
     * @param posY Ordinate of the passage
     * @param arriveX Abscissa where the player will be teleport
     * @param arriveY Ordinate where the player will be teleport
     */
    public Passage(int posX, int posY, int arriveX, int arriveY){
        super(posX,posY);
        this.arriveX = arriveX;
        this.arriveY = arriveY;
        type = "passage";
    }

    public Passage() {
        type = "passage";
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    public void setDestination(int x, int y) {
        arriveX = x;
        arriveY = y;
    }

    public int getArriveX() {
        return arriveX;
    }

    public int getArriveY() {
        return arriveY;
    }

    @Override
    public void activate(World world) {
        world.teleportPlayer(arriveX,arriveY);
    }
}
