package fr.ul.dedale.model;


import java.io.*;

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
    public char[][] createLabyrinth(int numLevel) throws IOException {
        //initialization of the tab of the labyrinth
        char[][] labyrinth = new char[50][50];
        //name of the level file
        String namefile = "level/level" + numLevel + ".txt";
        //read the file
        InputStream ips=new FileInputStream(namefile);
        InputStreamReader ipsr=new InputStreamReader(ips);
        BufferedReader br=new BufferedReader(ipsr);
        //for all the line of the file
        String line = br.readLine();
        int l = 0;
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

        return labyrinth;
    }
}
