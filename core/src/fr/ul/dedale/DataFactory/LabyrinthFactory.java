package fr.ul.dedale.DataFactory;

public class LabyrinthFactory {

    //The size of the world
    public static int HEIGHT = 22;
    public static int WIDTH = 20;

    //The characters which composed the labyrinth in the file
    public static final char WALL = 'w';
    public static final char GROUND = 'g';
    public static final char FIRE = 'f';
    public static final char DELTA = 'd';
    public static final char PASSAGE = 'p';
    public static final char MAGIC = 'm';
    public static final char DOOR = 'o';
    public static final char MYSTERY = '?';

    //The number of life which is given by the magic cell
    public static final int MAGICHEALTH = 1;
    // The number of damage wich do a trap
    public static final int TRAPDAMAGE = 1;

    //The number  corresponding to the characters
    public static final int PLAYER = 0;
    public static final int GHOST = 1;
    public static final int TROLL = 2;

    public static final int NB_LEVEL = 2;

    public static final int HP_PLAYER = 3 ; //life player

    public static final int SCREEN_WIDTH = 640;
    public static final int SCREEN_HEIGHT = 680;

    // Number of type the mystery cell can be
    public static final int NB_MYSTERY = 3;

    // The id corresponding of the different type the mystery cell can be
    public static final int MYSTERYTRAP = 0;
    public static final int MYSTERYCARE = 1;
    public static final int MYSTERYARROW = 2;

    // arrow number
    public static final int ARROWNUMBER = 3;

}
