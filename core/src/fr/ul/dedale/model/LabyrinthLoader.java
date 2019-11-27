package fr.ul.dedale.model;


import fr.ul.dedale.model.character.Ghost;
import fr.ul.dedale.model.character.Monster;
import fr.ul.dedale.model.character.Player;
import fr.ul.dedale.model.character.Troll;
import fr.ul.dedale.model.labyrinth.Cell;
import fr.ul.dedale.model.labyrinth.Labyrinth;
import fr.ul.dedale.model.labyrinth.Passage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for load a labyrinth
 */
public class LabyrinthLoader {

    /**
     * Constructor of the class
     */
    public LabyrinthLoader() {}

    /**
     * create the labyrinth of the level
     * @param numLevel the number of the level
     * @return the tab of the labyrinth (-> after an instance of labyrinth)
     */
    public Labyrinth createLabyrinth(int numLevel, int room) throws IOException {
        //initialization of the tab of the labyrinth
//        char[][] labyrinth = new char[50][50];
        //name of the level file
        String directory = "levels/level" + numLevel;
        String namefile = directory + "/rooms/room" + room + ".txt";
        //read the file
        InputStream ips = new FileInputStream(namefile);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        //for all the line of the file
        String line = br.readLine();
        int nbLines = 0;
        int nbColumns = 0;

        //Get the number of lines and columns
        int l = 0;
        while(line != null) {
            nbLines ++;
            nbColumns = Math.max(nbColumns, line.length());
            line = br.readLine();
        }

        //Creation of the labyrinth
        char[][] labyrinth = new char[nbLines][nbColumns];
        ips = new FileInputStream(namefile);
        ipsr = new InputStreamReader(ips);
        br = new BufferedReader(ipsr);
        line = br.readLine();
        while(line != null) {
            char ch;
            //table filling
            for (int c = 0;  c < line.length(); c++) {
                ch = line.charAt(c);
                labyrinth[l][c] = ch;
            }
            l++;
            line = br.readLine();
        }

        //Instantiation of the labyrinth
        Labyrinth lab = new Labyrinth(labyrinth);

        //Open the passages
        namefile = directory + "/passages/passages" + room + ".txt";
        ips = new FileInputStream(namefile);
        ipsr = new InputStreamReader(ips);
        br = new BufferedReader(ipsr);

        line = br.readLine();
        while (line != null) {
            String[] passages = line.split("\\|");
            String[] passage1 = passages[0].split("~");
            String[] passage2 = passages[1].split("~");
            Passage p1 = (Passage)lab.getCell(Integer.parseInt(passage1[0]), Integer.parseInt(passage1[1]));
            Passage p2 = (Passage)lab.getCell(Integer.parseInt(passage2[0]), Integer.parseInt(passage2[1]));

            p1.setDestination(p2.getX(), p2.getY());
            p2.setDestination(p1.getX(), p1.getY());
            line = br.readLine();
        }

        return lab;
    }


}
