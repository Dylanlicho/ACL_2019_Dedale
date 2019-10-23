package fr.ul.dedale.model.character;

public interface Movement {
    void moveRight();
    void moveLeft();
    void moveTop();
    void moveBottom();
    void turn(int direction);
}
