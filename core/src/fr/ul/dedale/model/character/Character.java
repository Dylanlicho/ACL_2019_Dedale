package fr.ul.dedale.model.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import fr.ul.dedale.DataFactory.DirectionFactory;

public abstract class Character implements Movement, Json.Serializable {

    protected int hp ;
    protected  boolean attack ;
    protected  int posX;
    protected  int posY;
    protected int direction;
    protected boolean throughWall;
    protected Sprite sprite;
    protected  SpriteBatch spriteBatch;


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

    /**
     * Increase the HP of the character
     * @param health number of HP increased
     */
    public void increaseHP(int health){
        hp = hp + health;
    }




    @Override
    public void write(Json json) {
        json.writeValue("hp", hp);
        json.writeValue("attack", attack);
        json.writeValue("posX", posX);
        json.writeValue("posY", posY);
        json.writeValue("direction", direction);
        json.writeValue("throughWall", throughWall);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        hp=jsonData.getInt("hp");
        attack=jsonData.getBoolean("attack");
        posX=jsonData.getInt("posX");
        posY=jsonData.getInt("posY");
        direction=jsonData.getInt("direction");
        throughWall=jsonData.getBoolean("throughWall");

    }
}

