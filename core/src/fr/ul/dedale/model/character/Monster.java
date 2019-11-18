package fr.ul.dedale.model.character;

import fr.ul.dedale.model.Attacker;
import fr.ul.dedale.model.World;

public class Monster extends Character implements Attacker {

    @Override
    public void attackCollision(World world) {
        if(world.getHero().getPosX()==getPosX() && world.getHero().getPosY() == getPosY()){
            world.getHero().decreaseHp(1);
        }
    }

    @Override
    public void attackSword(World world) {

    }
}