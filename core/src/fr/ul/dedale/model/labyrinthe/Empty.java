package fr.ul.dedale.model.labyrinthe;

import fr.ul.dedale.model.character.Player;

public class Empty extends Cell{
    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void activate(Player player) {
        // Nothing to do
    }
}
