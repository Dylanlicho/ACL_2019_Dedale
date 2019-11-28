package fr.ul.dedale.model.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.model.World;

import java.io.Serializable;


public abstract class Cell implements Serializable {

    // true if the player can't go onto the cell, false otherwise
    private boolean solid;
    // Coordinate of the cell
    protected int x;
    protected int y;
    //The type of the cell
    protected String type;
    //True if the cell if activate(for the cells which we must know if those has been activated)
    protected boolean isActivate = false;

    /**
     * Constructor of Cell
     * @param x Abscissa
     * @param y Ordinate
     */
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Cell(){

    }

    /**
     * Return true if the player can't go onto the cell, false otherwise
     * @return
     */
    public abstract boolean isSolid();

    /**
     * Action do when the player is on this cell
     */
    public abstract void activate(World world);

    /**
     * Draw the cell
     * @param sb the SpriteBatch
     */
    public void draw (SpriteBatch sb) {
        Texture texture = TextureFactory.getInstance().getImage(getType());
        sb.draw(texture,x, y,1,1,0,0,texture.getWidth(),texture.getHeight(),false,false);
    }

    /**
     * getter type
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * getter  abscissa coordinate
     * @return the position in X
     */
    public int getX() {
        return x;
    }

    /**
     * getter ordinate coordinate
     * @return the position in Y
     */
    public int getY() {
        return y;
    }

    /**
     * change the value of the cell
     * true if the cell is activate
     * false if the cell isn't activate
     * @param activate the new value
     */
    public void setActivate(boolean activate) {
        isActivate = activate;
    }
}
