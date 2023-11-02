package MyController;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AreaController {
    private char[][] tiles;
    private int playerX;
    private int playerY;
    private int numRows;
    private int numCols;
    private Random random;
    private BattleController battle;
    private Game game;

   // The `public Area(ArrayList<Creature> capturedList, ArrayList<Creature> creaturesList, Inventory
   // inventory)` constructor is initializing a new instance of the `Area` class. It takes three
   // parameters: `capturedList`, `creaturesList`, and `inventory`.
    public AreaController(ArrayList<CreatureController> capturedList, ArrayList<CreatureController> creaturesList, InventoryController inventory) {
        this.playerX = 0;
        this.playerY = 0;
        this.battle = new BattleController(capturedList, creaturesList, inventory);
        this.game = new Game();
    }

    // The `public Area() {}` is a default constructor for the `Area` class. It creates an instance of
    // the `Area` class without any parameters.
    public AreaController() {}

    /**
     * The function "exploreArea" allows the user to continuously explore an area until they choose to
     * stop.
     * 
     * @param scanner The scanner parameter is an object of the Scanner class, which is used to read
     * user input from the console. It allows you to get input from the user during the exploration of
     * the area.
     */
    public void exploreArea(Scanner scanner) {
        boolean explore = true;
        while (explore) {
            ClearScreen.cls();
            explore = areaScreen(scanner, explore);
        }
    }

    /**
     * The function displays a menu of areas to explore and prompts the user to choose an area,
     * returning true if the user chooses to explore an area and false if they choose to quit.
     * 
     * @param scanner The `Scanner` object is used to read user input from the console. It allows you
     * to get input from the user by using methods like `nextInt()`, `nextLine()`, etc.
     * @param explore The `explore` parameter is a boolean variable that indicates whether the user
     * wants to explore an area or not.
     * @return The method is returning a boolean value.
     */
    public boolean areaScreen(Scanner scanner, boolean explore) {
        System.out.println("+-------------------------------------------------+");
        System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
        System.out.println("|           Choose an area to explore!            |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("|                                                 |");
        System.out.println("|                  [1] Area 1                     |");
        System.out.println("|                  [0] Quit                       |");
        System.out.println("|                                                 |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
        System.out.println("+-------------------------------------------------+");
        System.out.print(" INPUT: ");
        int areaChoice = areaChoice(scanner);

        if (areaChoice == 0)
            return false;

        return true;
    }

    /**
     * The function `areaChoice` takes user input and based on the choice, calls another function
     * `area1` and returns 1 if the choice is 1, otherwise returns 0.
     * 
     * @param scanner The "scanner" parameter is an instance of the Scanner class, which is used to
     * read user input from the console.
     * @return The method is returning an integer value.
     */
    public int areaChoice(Scanner scanner) {
        game.setChoice(scanner, 0, 1);

        switch (game.getChoice()) {
            case 1:
                area1(scanner);
                return 1;
        }
        return 0;
    }

    /**
     * The function prints the area with a player represented by "v" and prompts the user to choose a
     * direction to move.
     */
    private void printArea() {
        System.out.println("+-------------------------------------------------+");
        System.out.println("|~ ~ ~ ~ ~ ~ ~ ~ ~ ~ Area 1 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("|                                                 |");
        System.out.print("|                 ");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (i == playerX && j == playerY)
                    System.out.print("v  ");
                else
                    System.out.print(tiles[i][j] + "  ");
            }
            System.out.print("                 |");
            System.out.println();
        }
        System.out.println("|                                                 |");
        System.out.println("|   Player at: " + playerY + ", " + playerX  +"\t\t\t\t  |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("Where would you like to go?");
        System.out.println("[1] Left");
        System.out.println("[2] Right");
        System.out.println("[0] Exit");
    }

    /**
     * The function initializes a 2D array of characters with a specified number of rows and columns,
     * setting each element to the underscore character.
     * 
     * @param numRows The number of rows in the area grid.
     * @param numCols The `numCols` parameter represents the number of columns in the area.
     */
    private void initializeArea(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        tiles = new char[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                tiles[i][j] = '_';
            }
        }
    }

    /**
     * The function "area1" allows the player to navigate through a grid-based game area using the left
     * and right arrow keys, with the option to quit the area.
     * 
     * @param scanner The `scanner` parameter is an instance of the `Scanner` class, which is used to
     * read user input from the console. It allows the program to receive input from the user during
     * runtime.
     */
    private void area1(Scanner scanner) {
        boolean quitArea1 = true;
        initializeArea(1, 5);
        playerX = 0;
        playerY = 0;

        while (quitArea1) {
            ClearScreen.cls();
            printArea();

            game.setChoice(scanner, 0, 2);

            switch (game.getChoice()) {
                case 0:
                    quitArea1 = false;
                    break;
                case 1:
                    movePlayer(Direction.LEFT, scanner);
                    break;
                case 2:
                    movePlayer(Direction.RIGHT, scanner);
                    break;
            }
        }
    }

    /**
     * The function moves the player in the specified direction and checks for encounters with
     * creatures.
     * 
     * @param direction The direction parameter is an enum type called Direction. It represents the
     * direction in which the player wants to move. The possible values for direction are UP, DOWN,
     * LEFT, and RIGHT.
     * @param scanner The `scanner` parameter is an instance of the `Scanner` class, which is used to
     * read input from the user. It is passed to the `movePlayer` method so that it can read user input
     * for the battle phase or for displaying error messages.
     */
    public void movePlayer(Direction direction, Scanner scanner) {
        int prevX = playerX;
        int prevY = playerY;

        switch (direction) {
            case UP:
                if (isValidMove(playerX - 1, playerY)) {
                    playerX--;
                }
                break;
            case DOWN:
                if (isValidMove(playerX + 1, playerY)) {
                    playerX++;
                }
                break;
            case LEFT:
                if (isValidMove(playerX, playerY - 1)) {
                    playerY--;
                }
                break;
            case RIGHT:
                if (isValidMove(playerX, playerY + 1)) {
                    playerY++;
                }
                break;
        }

        if (prevX != playerX || prevY != playerY) {
            if (isCreatureEncounter()) {
                scanner.nextLine();
                battle.battlePhase(scanner);
            }
        } else {
            scanner.nextLine();
            System.out.println("Invalid move! You cannot go beyond the grid boundaries.");
            scanner.nextLine();
        }
    }

    /**
     * The function checks if a move is valid within a grid of specified dimensions.
     * 
     * @param x The x parameter represents the row number in a grid or matrix. It is used to check if
     * the given row number is within the valid range of rows in the grid.
     * @param y The y parameter represents the column index of a cell in a grid.
     * @return The method is returning a boolean value.
     */
    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < numRows && y >= 0 && y < numCols;
    }

    /**
     * The function returns true if a random number between 0 and 1 is less than 0.4, indicating a
     * creature encounter.
     * 
     * @return The method is returning a boolean value.
     */
    public boolean isCreatureEncounter() {
        random = new Random();
        return random.nextDouble(1) < 0.4;
    }

    /**
     * The function returns the number of rows in a data structure.
     * 
     * @return The method is returning the value of the variable "numRows".
     */
    public int getNumRows() {
        return this.numRows;
    }

    /**
     * The function returns the number of columns in a data structure.
     * 
     * @return The method is returning the value of the variable "numCols".
     */
    public int getNumCols() {
        return this.numCols;
    }
}