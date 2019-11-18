package fr.ul.dedale.model.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.DirectionFactory;

public abstract class Character implements Movement {

    protected int hp ;
    protected  int attack ;
    protected  int posX;
    protected  int posY;
    protected int direction;
    protected boolean throughWall;
    protected Sprite sprite;

    /**
     * draw the labyrinth
     * @param sp the Sprite batch
     */
    public void draw(SpriteBatch sp){
        sprite.setPosition(posX,posY);
        sprite.setOriginCenter();
        sprite.draw(sp);
    }


    public void moveRight() {
        posX ++;
        turn(DirectionFactory.TURNRIGHT);
    }

    @Override
    public void moveLeft() {
        posX --;
        turn(DirectionFactory.TURNLEFT);
    }

    @Override
    public void moveTop() {
        posY ++;
        turn(DirectionFactory.TURNTOP);
    }

    @Override
    public void moveBottom() {
        posY -- ;
        turn(DirectionFactory.TURNBOTTOM);
    }

    @Override
    public void turn(DirectionFactory direction) {
        switch (direction){
            case TURNTOP:
                this.direction = DirectionFactory.TURNTOP.ordinal();
                setDirection();
                break;
            case TURNBOTTOM:
                this.direction = DirectionFactory.TURNBOTTOM.ordinal();
                setDirection();
                break;
            case TURNLEFT:
                this.direction = DirectionFactory.TURNLEFT.ordinal();
                setDirection();
                break;
            case TURNRIGHT:
                this.direction = DirectionFactory.TURNRIGHT.ordinal();
                setDirection();
                break;

        }
    }
    /**
     * Decrease the Health point of the player
     * @param damage Damage the player take
     */
    public void decreaseHp(int damage) {
        hp = hp - damage;
    }

    /**
     * Set the abscissa of the player
     * @param x the value of the set
     */
    public void setX(int x){
        posX = x;
    }

    /**
     * Set the ordinate of the player
     * @param y the value of the set
     */
    public void setY(int y) {
        posY = y;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean isThroughWall(){
        return throughWall;
    }

    public int getHp() {
        return hp;
    }
    public void setDirection(){

    }
}

