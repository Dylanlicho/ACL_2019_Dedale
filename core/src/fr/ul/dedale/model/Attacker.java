package fr.ul.dedale.model;

public interface Attacker {
    /**
     * collision between hero and monster
     * @param world world of this level
     */
    void attackCollision(World world);
}
