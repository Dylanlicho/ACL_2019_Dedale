package fr.ul.dedale.model.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.model.Attacker;
import fr.ul.dedale.model.World;
import fr.ul.dedale.model.labyrinth.Cell;
import fr.ul.dedale.model.labyrinth.WallDestructible;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Player extends Character  {
    private static long startTime = 0 ;
    public static int CPTANIMATION = 4; // cpt animation
    public static int SPRITESIZEWIGHT = 100  ;    //size of sprite
    public static int SPRITESIZEHIGHT= 130  ;    //size of sprite
    protected static int SPRITEBTOP = 2;
    protected static int SPRITEBOTTOM = 0;
    protected static int SPRITELEFT = 1;
    protected static int SPRITERIGHT = 3;
    private ArrayList<Point> animAttack;
    private Date timeActual ;
    private Date timesave ;
    int cpt = 0 ;
    Texture textureHit ;
    protected  int xAttack;
    protected  int yAttack;
    protected  boolean attackSword;
    protected  boolean attackBow;
    protected  int numberArrow ;


    /**
     * constructor player
     * @param x abscissa coordinate  player
     * @param y ordonate coordinate player
     */
    public Player(int x , int y ){
        numberArrow = 3 ;
        attack =false;
        hp = LabyrinthFactory.HP_PLAYER;
        throughWall = false;
        super.posX = x;
        super.posY = y;
        super.sprite = new Sprite(TextureFactory.getInstance().getImage("hero"),SPRITESIZEWIGHT,SPRITESIZEHIGHT);
        sprite.setSize(1,1);
        direction = DirectionFactory.TURNBOTTOM.ordinal();
    }

    public Player(){
        super.sprite = new Sprite(TextureFactory.getInstance().getImage("hero"),SPRITESIZEWIGHT,SPRITESIZEHIGHT);
        sprite.setSize(1,1);
    }

    /**
     * set sprite of hero
     */
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

    /**
     * Increment the number of arrow of the player
     */
    public void incrementArrow(){
        numberArrow = numberArrow + 1;
    }

    /**
     * Decrement the number of arrow of the player
     */
    public void decrementArrow(){
        numberArrow = numberArrow - 1;
    }

    @Override
    public void attackSword(World world) {

        attackSword = true;
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
            if((m.getPosX()==x && m.getPosY()==y) ||(m.getPosX()==getPosX() && m.getPosY()==getPosY()) ){
                m.decreaseHp(1);

                if(m.getHp() <= 0){

                    if(super.getHp()<3) {
                        Random random = new Random();
                        int bonus = random.nextInt(1);
                        super.increaseHP(bonus);
                    }
                }
            }
        }
        Cell cell = world.getLabyrinth().getCell(x,y);
        if(cell.getType().equals("destructible")){
            WallDestructible wd = (WallDestructible)cell;
            wd.damage();
        }
        animAttack = new ArrayList<>();
        animAttack.add(new Point(x,y));
        xAttack = x;
        yAttack = y;
       }

    @Override
    public void attackArrow(World world) {
        numberArrow--;
        attackBow = true;
        animAttack = new ArrayList<>();
        int dirX = 0;
        int dirY = 0;
        int x = getPosX();
        int y = getPosY();
        if(direction == DirectionFactory.TURNLEFT.ordinal()){
            dirX = -1 ;
            dirY = 0 ;
        }
        if(direction == DirectionFactory.TURNRIGHT.ordinal()){
            dirX = 1 ;
            dirY = 0 ;
        }
        if(direction == DirectionFactory.TURNTOP.ordinal()){
            dirX = 0 ;
            dirY = 1 ;
        }
        if(direction == DirectionFactory.TURNBOTTOM.ordinal()){
            dirX = 0 ;
            dirY = -1 ;
        }
        boolean stop = false;
        while(!(x < 0 || y < 0 || x >=LabyrinthFactory.WIDTH || y >=  LabyrinthFactory.HEIGHT -2) && !stop ){
            if(!world.getLabyrinth().getCell(x,y).isSolid() ){

                for(Monster m : world.getMonsters()){
                    if(m.getPosX()==x+dirX && m.getPosY()==y+dirY){
                        m.decreaseHp(1);
                        return ;
                    }
                }

                x = x + dirX;
                y = y + dirY;
                animAttack.add(new Point(x,y));

            }else{
                stop = true;
            }
        }
    }

    /**
     * the player are attacking
     */
    public void hit(){
        attack =true;
        timesave = new Date();
       }

    /**
     * draw sprite attack
     * @param sb
     */


    /**
     * the play are't attacking
     */
    public void nohit(){
        attack = false;
        attackSword = false;
        attackBow = false;
       }
    public void drawhit(SpriteBatch sb) {
        if (attack) {
            timeActual = new Date();

            long time = timeActual.getTime() - timesave.getTime();

            if (time > 10 ) {
                    nohit();

            }
                timesave = timeActual;
                for (int x = 0; x < animAttack.size() ; x++) {
                    if(attackSword) {
                        textureHit = TextureFactory.getInstance().getImage("hit");
                        sb.draw(textureHit, (int) animAttack.get(x).getX(), (int) animAttack.get(x).getY(), 1, 1, 0, 0, textureHit.getWidth(), textureHit.getHeight(), false, false);
                    }
                     if(attackBow) {
                         textureHit = TextureFactory.getInstance().getImage("hitArrow");

                         if(direction == DirectionFactory.TURNLEFT.ordinal() || direction == DirectionFactory.TURNRIGHT.ordinal()){
                             sb.draw(textureHit, (int) animAttack.get(x).getX(), (int) animAttack.get(x).getY(), 1, 1, 0, textureHit.getHeight()/2, textureHit.getWidth(), textureHit.getHeight()/2, false, false);

                         }else{
                             sb.draw(textureHit, (int) animAttack.get(x).getX(), (int) animAttack.get(x).getY(), 1, 1, 0, 0, textureHit.getWidth(), textureHit.getHeight()/2, false, false);

                         }
                     }
                }
        }
    }

    public int getNumberArrow() {
        return numberArrow;
    }

    @Override
    public void write(Json json) {
        json.writeValue("hp", hp);
        json.writeValue("attack", attack);
        json.writeValue("posX", posX);
        json.writeValue("posY", posY);
        json.writeValue("direction", direction);
        json.writeValue("throughWall", throughWall);
        json.writeValue("arrow", numberArrow);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        hp=jsonData.getInt("hp");
        attack=jsonData.getBoolean("attack");
        posX=jsonData.getInt("posX");
        posY=jsonData.getInt("posY");
        direction=jsonData.getInt("direction");
        throughWall=jsonData.getBoolean("throughWall");
        numberArrow = jsonData.getInt("arrow");
    }
}