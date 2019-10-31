package fr.ul.dedale.model.labyrinth;

import fr.ul.dedale.model.World;

public class Trap extends Cell{

    // Damage of the trap
    private int damage;

    /**
     * Constructor of Trap
     * @param damage damage of the trap
     */
    public Trap(int x,int y,int damage){
        super(x,y);
        this.damage = damage;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void activate(World world) {
        world.damagePlayer(damage);
    }

}
