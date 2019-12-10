package fr.ul.dedale.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.Game;
import fr.ul.dedale.model.View.ViewMenu;
import fr.ul.dedale.model.character.*;
import fr.ul.dedale.model.character.Character;
import fr.ul.dedale.model.character.Monster;
import fr.ul.dedale.model.character.Player;
import fr.ul.dedale.model.character.Troll;
import fr.ul.dedale.model.labyrinth.Cell;
import fr.ul.dedale.model.labyrinth.Labyrinth;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class World {

    // Thread useful for monsters' evolution
    private Thread one;

    // Hero class
    private Player hero ;

    // List of monsters
    private ArrayList<Monster> monsters;

    // Actual Maze
    private Labyrinth labyrinth;

    //The loader of the labyrinth
    private LabyrinthLoader labyrinthLoader;

    //The loader of the characters
    private CharacterLoader characterLoader;

    //The number of the last level play
    private int lastLevel;

    //The number of the current level
    private int level;

    //The number of the current room of the level
    private int room;

    //The game
    private Game game;

    //For know if the level is finished
    private boolean currentLevelFinish;

    // When we save the game
    private boolean isSaving;

    // Know if we have begin to play
    private boolean begin;

    // The player can move on the next cell
    private int canmove;

    // The monsters don't move
    private boolean pauseMonsters;


    public World(Game game) {
        this.game = game;
        this.lastLevel = 1;
        this.level = 8;
        room = 1;
        isSaving=false;
        labyrinthLoader = new LabyrinthLoader();
        characterLoader = new CharacterLoader();
        begin = false;
        canmove = 0;
        currentLevelFinish = false;
        pauseMonsters = true;
        monsters = new ArrayList<>();
        createThread();
    }

    public void begin() {
        begin = true;
//        launchThread();
//        pauseMon/sters = false;
        playThread();
    }

    private void createThread() {
        System.out.println("Creation of the thread");
        one = new Thread(new Runnable() {
            public void run() {

                while(true){
                    if (!currentLevelFinish && !pauseMonsters)
                        System.out.println("evolve monsters");
                        evolveMonsters();

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        one.start();
    }

    public void playThread() {
        System.out.println("Start the thread");
        pauseMonsters = false;
    }

    public void game(){

        if (!isCurrentLevelFinish()) {
            labyrinth.getCell(hero.getPosX(), hero.getPosY()).activate(this);
            checkLoosePLayer();
        }

    }

    public void draw(SpriteBatch sb){

        labyrinth.draw(sb);
        if(hero.getHp()>0) {
            hero.draw(sb);
        }
        for(int i = 0 ; i < monsters.size(); i++){
            if(monsters.get(i).getHp()>0) {
                monsters.get(i).draw(sb);
            }
        }
        hero.drawhit(sb);

    }

    public void stopPlayer() {
        if (canmove <= 0)
            canmove = LabyrinthFactory.WATERCOUNT;
        else
            canmove--;
    }

    public void moveHero(DirectionFactory direction) {
        if (canmove <= 0) {
            if (canMove(hero, direction)) {
                switch (direction) {
                    case TOP:
                        hero.moveTop();
                        break;

                    case BOTTOM:
                        hero.moveBottom();
                        break;

                    case LEFT: {
                        hero.moveLeft();
                        break;
                    }

                    case RIGHT: {
                        hero.moveRight();
                        break;
                    }

                }
            }
        }
    }

    public void moveMonster(int direction, int elem ){
        switch (direction){
            case 0 :  monsters.get(elem).moveTop();
                break;

            case 1 :  monsters.get(elem).moveBottom();
                break;

            case 2 : { monsters.get(elem).moveLeft();
                break; }

            case 3 : { monsters.get(elem).moveRight();
                break; }

        }


    }

    /**
     * Apply some damage to the player
     * @param damage Number of damage applied to the player
     */
    public void damagePlayer(int damage){
        hero.decreaseHp(damage);
    }

    /**
     * Teleport the player on an other cell
     * @param x Abscissa of the destination
     * @param y Ordinate of the destination
     */
    public void teleportPlayer(int x, int y){
        hero.setX(x);
        hero.setY(y);
    }

    /**
     * The hero win
     */
    public void winPlayer() {
            level++;
            if (level > lastLevel) lastLevel = level;
//            else {
                currentLevelFinish = true;
//            }
        pauseThread();
    }

    /**
     * check if the hero has lost
     */
    private void checkLoosePLayer(){
        if (hero.getHp()<=0){
            loose();
        }

    }

    /**
     * the player loose
     */
    public void loose(){
        pauseThread();
        hero = characterLoader.getPlayer();
        createMonsters();
        if (room == 1) labyrinth.init();
        else {
            room = 1;
            createRoom();
        }
        canmove = 0;
    }

    /**
     * return true if the character can move in futur case
     * @param c character
     * @param d direction
     * @return
     */
    public boolean canMove(Character c, DirectionFactory d){
        int x = c.getPosX() ;
        int y = c.getPosY();

        try {
            Cell nextCell = labyrinth.getNextCell(x, y, d);
            for(int i =0; i < monsters.size(); i++){
                if(nextCell.getX()==monsters.get(i).getPosX() && nextCell.getY()==monsters.get(i).getPosY()){
                    return false;
                }
            }
            if(!c.isThroughWall() && nextCell.isSolid()){
                return false;
            }

            return true;
        }catch (NullPointerException e){

        }
        return false;
    }
    public String nextCase(Character c, DirectionFactory d){
        int x = c.getPosX() ;
        int y = c.getPosY();
        return labyrinth.getNextCell(x, y, d).getType();

    }

    /**
     * getter hero
     * @return the hero
     */
    public Player getHero() {
        return hero;
    }

    public void setHero(Player player){
        this.hero = player;
    }

    /**
     * getter monsters
     * @return the monsters
     */
    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    /**
     * Increase the HP of the player
     * @param hp number of HP increased
     */
    public void healPlayer(int hp) {
        if (hero.getHp() < LabyrinthFactory.HP_PLAYER) {
            hero.increaseHP(hp);
        }
    }

    /**
     * According to the content do an effect
     * @param content Type of the effect
     */
    public void mystery(int content) {
        switch (content){
            case LabyrinthFactory.MYSTERYTRAP:
                damagePlayer(LabyrinthFactory.TRAPDAMAGE);
                break;
            case LabyrinthFactory.MYSTERYCARE:
                healPlayer(LabyrinthFactory.MAGICHEALTH);
                break;
            case LabyrinthFactory.MYSTERYARROW:
                if(hero.getNumberArrow() < LabyrinthFactory.ARROWNUMBER){
                    winArrow();
                }
                break;
            default:
                    break;
        }
    }

    /**
     * Increment the number of arrow the player have
     */
    public void winArrow(){
        hero.incrementArrow();
    }

    /**
     * create the current level
     */
    public void createLevel() {
        room = 1;
        currentLevelFinish = false;
        createRoom();
    }

    /**
     * Create the current room
     */
    public void createRoom() {
        int hp = LabyrinthFactory.HP_PLAYER;
        if (hero != null) hp = hero.getHp();
        try {
            labyrinth = labyrinthLoader.createLabyrinth(level, room);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            characterLoader.createCharacter(level, room);
            hero = characterLoader.getPlayer();
            if (room > 1) hero.setHp(hp);
            createMonsters();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the monsters with the list getting in characterLoader
     */
    private void createMonsters() {
        monsters = new ArrayList<Monster>();

        Integer value;
        Integer key;
        for(Map.Entry<Integer, Integer> entry : characterLoader.getMonsters().entrySet()){

            key = entry.getKey();
            value = entry.getValue();

            for(int i=0;i<value;i++) {

                Point newPoint = findEmptyCell();

                switch (key) {

                    // Case Ghost
                    case LabyrinthFactory.GHOST:
                        monsters.add(new Ghost(newPoint.x, newPoint.y));
                        break;

                    // Case Troll
                    case LabyrinthFactory.TROLL:
                        monsters.add(new Troll(newPoint.x, newPoint.y));
                        break;
                }
            }
        }

        playThread();
    }

    public Labyrinth getLabyrinth(){
        return labyrinth;
    }

    /**
     * Find an empty cell
     * @return a point corresponding to the position of an empty cell
     */
    public Point findEmptyCell(){
        int height = Math.min(labyrinth.getCells().length,LabyrinthFactory.HEIGHT-2);
        int widht = Math.min(labyrinth.getCells()[0].length, LabyrinthFactory.WIDTH);
        while(true) {
            int x = ThreadLocalRandom.current().nextInt(0, widht-2);
            int y = ThreadLocalRandom.current().nextInt(0, height-2);
            if (!labyrinth.getCell(x, y).isSolid()) {
                return new Point(x, y);
            }
        }
    }


    public void evolveMonsters(){
        for (int i = 0 ; i < monsters.size(); i++){

            // Player coordinates
            int posX_player = hero.getPosX();
            int posY_player = hero.getPosY();

            // Ghost coordinates
            int posX_monstre = monsters.get(i).getPosX();
            int posY_monstre = monsters.get(i).getPosY();

            // Distance between X and Y
            int deltaX = Math.abs(posX_monstre - posX_player);
            int deltaY = Math.abs(posY_monstre - posY_player);

            // If we move left or right
            int directionX = posX_monstre - posX_player;

            // If we move top or bottom
            int directionY = posY_monstre - posY_player;


            Point hero = new Point(posX_player,posY_player);
            Point monstre = new Point(posX_monstre,posY_monstre);

            double distance = hero.distance(monstre);

            Random r = new Random();

            // Random between 0 and 3
            int dir =  r.nextInt((3 - 0 ) + 1) + 0;

            // The monster is a troll
            if(!monsters.get(i).isThroughWall()  ) {

                if(distance < 5){
                    // The X has a bigger distance than the Y, So we move the ghost in absciss
                    if(deltaX > deltaY){
                        if(directionX >= 0){

                            // Left
                            if(canMove(monsters.get(i), DirectionFactory.LEFT))
                                moveMonster(2,i);
                        }
                        else{

                            // Right
                            if(canMove(monsters.get(i), DirectionFactory.RIGHT))
                                moveMonster(3,i);
                        }
                    }
                    // We move the ghost in ordinate
                    else {
                        if(directionY >= 0){
                            // Bottom
                            if(canMove(monsters.get(i), DirectionFactory.BOTTOM))
                                moveMonster(1,i);
                        }
                        else{
                            // Top
                            if(canMove(monsters.get(i), DirectionFactory.TOP))
                                moveMonster(0,i);
                        }
                    }
                }
                else{


                    while (!canMove(monsters.get(i), DirectionFactory.values()[dir]) || nextCase(monsters.get(i),DirectionFactory.values()[dir]).equals("fire")) {


                        dir = r.nextInt((3 - 0) + 1) + 0;
                    }

                    moveMonster(dir, i);
                }
            }

            // The monsters is a Ghost
            else{


                // The X has a bigger distance than the Y, So we move the ghost in absciss
                if(deltaX > deltaY){
                    if(directionX >= 0){

                        // Left
                        moveMonster(2,i);
                    }
                    else{

                        // Right
                        moveMonster(3,i);
                    }
                }
                // We move the ghost in ordinate
                else {
                    if(directionY >= 0){
                        // Bottom
                        moveMonster(1,i);
                    }
                    else{
                        // Top
                        moveMonster(0,i);
                    }
                }
            }


            //monster die
            if(monsters.get(i).getHp()<=0){
                monsters.remove(i);
            }else {
                monsters.get(i).attackCollision(this);

            }
        }

    }

    /**
     * Go to the next room
     */
    public void nextRoom() {
        room++;
        createRoom();
    }


    /**
     * Getter of the currentLevelFinish
     * @return true if the current level is finished, false otherwise
     */
    public boolean isCurrentLevelFinish() {
        return currentLevelFinish;
    }

    public int getLevel() {
        return level;
    }

    public void menuReturn() {
        pauseThread();
        if (level > LabyrinthFactory.NB_LEVEL) {
            FileHandle[] files = Gdx.files.local("save/").list();
            for (FileHandle file : files) {
                if (!file.name().equals("lastLevel.json"))
                    file.delete();
            }
        }
        begin = false;
        level = 1;
        room = 1;
        ViewMenu vm = new ViewMenu(game);
        game.setScreen(vm);
    }


    /*
    Méthode de sauvegarde du monde
     */
    public void save(){

        // Sauvegarde le héro
        FileHandle file = Gdx.files.local("save/hero.json");
        Json json = new Json();
        file.writeString(json.toJson(hero,Player.class),false);


        // Sauvegarde l'attribut lastLevel
        file = Gdx.files.local("save/lastLevel.json");
        json = new Json();
        file.writeString(json.toJson(lastLevel,Integer.class),false);

        // Sauvegarde l'attribut level
        file = Gdx.files.local("save/level.json");
        json = new Json();
        file.writeString(json.toJson(level,Integer.class),false);

        // Sauvegarde l'attribut room
        file = Gdx.files.local("save/room.json");
        json = new Json();
        file.writeString(json.toJson(room,Integer.class),false);

//        // Sauvegarde l'attribut pauseMonsters
//        file = Gdx.files.local("save/pauseMonsters.json");
//        json = new Json();
//        file.writeString(json.toJson(pauseMonsters,Boolean.class),false);

        // Sauvegarde l'attribut canmove
        file = Gdx.files.local("save/canmove.json");
        json = new Json();
        file.writeString(json.toJson(canmove,Integer.class),false);

        // Sauvegarde le labyrinth
        file = Gdx.files.local("save/labyrinth.json");
        json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        json.setElementType(Labyrinth.class, "cellList", Cell.class);
        file.writeString(json.toJson(labyrinth,Labyrinth.class),false);

        // Sauvegarde les monstres
        file = Gdx.files.local("save/monsters.json");
        json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        json.setElementType(Monsters.class, "monsters", Monster.class);

        // Classe temporaire avec la liste des monstres à sauvegarder
        Monsters monsters = new Monsters(this.monsters);
        file.writeString(json.toJson(monsters,Monsters.class),false);


        isSaving = true;

        // On fait un compteur afficher l'icone de sauvegarde
        Thread save = new Thread(new Runnable() {
            public void run() {

                try {
                    Thread.sleep(900);

                    isSaving=false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        save.start();
    }


    public void load(){
        boolean load = false;
        // Try si une sauvegarde existe
        try {
            pauseThread();

            // On récupert le hero
            FileHandle file = Gdx.files.local("save/hero.json");
            Json json = new Json();
            String heroJson = file.readString();
            hero = json.fromJson(Player.class, heroJson);

            // On récupert l'attribut room
            file = Gdx.files.local("save/room.json");
            json = new Json();
            heroJson = file.readString();
            room = json.fromJson(Integer.class, heroJson);

            // On récupert l'attribut last level
            file = Gdx.files.local("save/lastLevel.json");
            json = new Json();
            heroJson = file.readString();
            lastLevel = json.fromJson(Integer.class, heroJson);

            // On récupert l'attribut level
            file = Gdx.files.local("save/level.json");
            json = new Json();
            heroJson = file.readString();
            level = json.fromJson(Integer.class, heroJson);

            // On récupert l'attribut canmove
            file = Gdx.files.local("save/canmove.json");
            json = new Json();
            heroJson = file.readString();
            canmove = json.fromJson(Integer.class, heroJson);

//            // On récupert l'attribut pauseMonsters
//            file = Gdx.files.local("save/pauseMonsters.json");
//            json = new Json();
//            heroJson = file.readString();
//            pauseMonsters = json.fromJson(Boolean.class, heroJson);

            // On récupert l'attribut labyrinth
            file = Gdx.files.local("save/labyrinth.json");
            json = new Json();
            heroJson = file.readString();
            labyrinth = json.fromJson(Labyrinth.class, heroJson);

            // On récupert la classe temporaire Monsters
            file = Gdx.files.local("save/monsters.json");
            json = new Json();
            heroJson = file.readString();
            Monsters m = json.fromJson(Monsters.class, heroJson);

            // On instancie la nouvelle liste de monstre avec celle de la classe temporaire
            monsters=new ArrayList<Monster>();
            monsters=m.getMonsters();

            labyrinthLoader.createLabyrinth(level, room);
            characterLoader.createCharacter(level, room);

            load = true;
        }
        catch (GdxRuntimeException | IOException e){

        }

        if (!load) {
            createLevel();
        }
        else
            playThread();

    }

    public boolean isSaving() {

        return isSaving;
    }


    /**
     * Change the level
     * @param level the new number of the level
     */
    public void setLevel(int level) {
        this.level = level;
        room = 1;
    }

    /**
     * Change the room
     * @param room the number of the room
     */
    public void setRoom(int room){
        this.room = room;
    }

    /**
     * Getter of begin
     * @return true if we playing and false if we are to the menu
     */
    public boolean isBegin() {
        return begin;
    }

    /**
     * Setter of begin
     * @param begin the new value of begin
     */
    public void setBegin(boolean begin) {
        this.begin = begin;
    }

    /**
     * getter of the last level
     * @return the number of the last level
     */
    public int getLastLevel() {
        return lastLevel;
    }

    /**
     * Setter of the last level
     * @param lastLevel the new last level
     */
    public void setLastLevel(int lastLevel) {
        this.lastLevel = lastLevel;
    }

    public void pauseThread() {
        System.out.println("pause the thread");
//        if (one.isAlive())
//            this.one.interrupt();
        pauseMonsters = true;
    }

    public void stopThread() {
        this.one.stop();
    }


    public void setMonsters(ArrayList<Monster> m){
        this.monsters=m;
    }
}
