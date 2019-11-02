package fr.ul.dedale.model.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import fr.ul.dedale.DataFactory.TextureFactory;

public abstract class Character implements Movement {
      protected int hp ;
      protected  int attack ;
      protected  int posX;
      protected  int posY;
      protected int direction;
      protected boolean throughWall;
      protected Sprite sprite;

    public void draw(SpriteBatch sp){
        sprite.setPosition(posX,posY);
        sprite.setOriginCenter();
        sprite.draw(sp);
    }


    public void moveRight() {
        posX ++;
    }

    @Override
    public void moveLeft() {
        posX --;
    }

    @Override
    public void moveTop() {
        posY ++;
    }

    @Override
    public void moveBottom() {
        posY -- ;
    }

    @Override
    public void turn(int direction) {
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

}

