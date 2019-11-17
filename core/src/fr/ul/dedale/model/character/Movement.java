package fr.ul.dedale.model.character;

import fr.ul.dedale.DataFactory.DirectionFactory;

public interface Movement {
    /**
     * move character to right
     */
    void moveRight();
    /**
     * move character to left
     */
    void moveLeft();
    /**
     * move character to top
     */
    void moveTop();
    /**
     * move character to bottom
     */
    void moveBottom();
    void turn(DirectionFactory direction);
}
