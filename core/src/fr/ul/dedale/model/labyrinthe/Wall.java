package fr.ul.dedale.model.labyrinthe;

import fr.ul.dedale.model.character.Player;

public class Wall extends Cell{
    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public void activate(Player player) {
        // Nothing to do
    }

}
