package fr.ul.dedale.model.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.model.Attacker;
import fr.ul.dedale.model.World;


public class Player extends Character implements Attacker {
    public static int HP = 3 ; //life player
    public static int CPTANIMATION = 4; // cpt animation
    public static int SPRITESIZEWIGHT = 100  ;    //size of sprite
    public static int SPRITESIZEHIGHT= 130  ;    //size of sprite
    protected static int SPRITEBTOP = 2;
    protected static int SPRITEBOTTOM = 0;
    protected static int SPRITELEFT = 1;
    protected static int SPRITERIGHT = 3;
    protected  int xAttack;
    protected  int yAttack;


    /**
     * constructor player
     * @param x abscissa coordinate  player
     * @param y ordonate coordinate player
     */
    public Player(int x , int y ){
        attack =false;
        hp = HP;
        throughWall = false;
        super.posX = x;
        super.posY = y;
        super.sprite = new Sprite(TextureFactory.getInstance().getImage("hero"),SPRITESIZEWIGHT,SPRITESIZEHIGHT);
        sprite.setSize(1,1);
    }
    public void setDirection(){
        if(DirectionFactory.TURNTOP.ordinal() == direction){
            super.sprite = new Sprite(TextureFactory.getInstance().getImage("hero"),0,SPRITEBTOP*SPRITESIZEHIGHT,SPRITESIZEWIGHT,SPRITESIZEHIGHT);
        }
        if(DirectionFactory.TURNBOTTOM.ordinal() == direction){
            super.sprite = new Sprite(TextureFactory.getInstance().getImage("hero"),0,SPRITEBOTTOM*SPRITESIZEHIGHT,SPRITESIZEWIGHT,SPRITESIZEHIGHT);
        }
        if(DirectionFactory.TURNLEFT.ordinal() == direction){
            super.sprite = new Sprite(TextureFactory.getInstance().getImage("hero"),0,SPRITELEFT*SPRITESIZEHIGHT,SPRITESIZEWIGHT,SPRITESIZEHIGHT);
        }
        if(DirectionFactory.TURNRIGHT.ordinal() == direction){
            super.sprite = new Sprite(TextureFactory.getInstance().getImage("hero"),0,SPRITERIGHT*SPRITESIZEHIGHT,SPRITESIZEWIGHT,SPRITESIZEHIGHT);
        }
        sprite.setSize(1,1);
    }

    @Override
    public void attackCollision(World world) {

    }

    @Override
    public void attackSword(World world) {
        int x = getPosX();
        int y = getPosY();
        if(direction==DirectionFactory.TURNLEFT.ordinal()){
            x--;
        }
        if(direction==DirectionFactory.TURNRIGHT.ordinal()){
            x++;
        }
        if(direction==DirectionFactory.TURNTOP.ordinal()){
            y++;
        }
        if(direction==DirectionFactory.TURNBOTTOM.ordinal()){
            y--;
        }
        for(Monster m :world.getMonsters()){
            if(m.getPosX()==x && m.getPosY()==y){
                m.decreaseHp(1);
            }
        }
        xAttack = x;
        yAttack = y;
       }
       public void hit(){
        attack =true;
       }
       public void nohit(){
        attack = false;
       }
    public void drawhit(SpriteBatch sb) {
        if(attack) {
            Texture texture = TextureFactory.getInstance().getImage("hit");
            sb.draw(texture, xAttack, yAttack, 1, 1, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
        }
    }


}