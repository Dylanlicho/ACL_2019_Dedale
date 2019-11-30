package fr.ul.dedale.model.character;

import java.util.ArrayList;

/*
Tempory class use for the save of the monsters
 */
public class Monsters {

    private ArrayList<Monster> monsters;



    public Monsters(ArrayList<Monster> m){
        this.monsters=m;
    }

    public Monsters(){

    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
}
