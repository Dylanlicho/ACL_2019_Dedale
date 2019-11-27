package fr.ul.dedale.model;


import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.model.character.Player;

import java.io.*;
import java.util.HashMap;

public class CharacterLoader {

    //The number of the monsters in the labyrinth
    private HashMap<Integer, Integer> monsters;
    //The player
    private Player player;


    /**
     * create the player and load the number of the different monsters
     * @param numLevel the number of the level
     * @throws IOException launch if the file is not charge correctly
     */
    public void createCharacter(int numLevel, int room) throws IOException {
        monsters = new HashMap<Integer, Integer>();
        String namefile = "levels/level" + numLevel + "/characters"
                + "/characters" + room + ".txt";
        //read the file
        InputStream ips = new FileInputStream(namefile);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        //for all the line of the file
        String line = br.readLine();
        while(line != null) {
            String[] character = line.split("\\|");
            switch(Integer.parseInt(character[0])) {
                case LabyrinthFactory.GHOST:
                    monsters.put(LabyrinthFactory.GHOST, Integer.parseInt(character[1]));
                    break;
                case LabyrinthFactory.TROLL:
                    monsters.put(LabyrinthFactory.TROLL, Integer.parseInt(character[1]));
                    break;
                case LabyrinthFactory.PLAYER:
                    String[] posPlayer = character[1].split("~");
                    player = new Player(Integer.parseInt(posPlayer[0]), Integer.parseInt(posPlayer[1]));
                    break;
                default:
                    break;
            }
            line = br.readLine();
        }


    }

    /**
     * @return the hash map of the monster, the number of all the monster
     */
    public HashMap<Integer, Integer> getMonsters() {
        return monsters;
    }

    /**
     * @return the player
     */
    public Player getPlayer () {
        return new Player(player.getPosX(), player.getPosY());
    }

}
