package fr.ul.dedale.model.labyrinth;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.model.World;


public abstract class Cell {

    // true if the player can't go onto the cell, false otherwise
    private boolean solid;
    // Coordinate of the cell
    private int x;
    private int y;
    protected String type;

    /**
     * Constructor of Cell
     * @param x Abscissa
     * @param y Ordinate
     */
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
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

    public void draw (SpriteBatch sb) {
        Texture texture = TextureFactory.getInstance().getImage(type);
        sb.draw(texture,x, y,1,1,0,0,texture.getWidth(),texture.getHeight(),false,false);
    }

}
