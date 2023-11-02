package TextGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Creature creature = new Creature();
    private Area area;
    private ArrayList<Creature> creaturesList;
    private ArrayList<Creature> capturedList;
    private Inventory inventory;
    private Evolution evo;
    private int choice;

    // The `public Game(int choice)` constructor is initializing the `Game` object with the provided
    // `choice` value. It also initializes the `creaturesList`, `capturedList`, `inventory`, `evo`, and
    // `battle` objects with empty lists and appropriate parameters. These objects are used throughout
    // the game to manage creatures, inventory, evolution, and battles.
    public Game(int choice) {
        this.choice = choice;
        this.creaturesList = new ArrayList<>();
        this.capturedList = new ArrayList<>();
        this.inventory = new Inventory(capturedList);
        this.evo = new Evolution(capturedList, creaturesList);
        this.area = new Area(capturedList, creaturesList, inventory);
    }

    // The `public Game(){ }` is a default constructor for the `Game` class. It is an empty constructor
    // that does not take any parameters. It is used to create an instance of the `Game` class without
    // initializing any values or objects.
    public Game(){ }

    /**
     * The startGame() function initializes creatures and prompts the user to play or quit the game,
     * then calls the MainMenu() function if the user chooses to play.
     */
    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        // initialize all the Creatures by the start of the Game 
        creature.initializeCreatures(creaturesList);

        while(choice == 1) {
            ClearScreen.cls();

            // Start Menu
            System.out.println("+-------------------------------------------------+");
            System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
            System.out.println("|           Welcome to Pokemon Sweets!            |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("|                                                 |");
            System.out.println("|           Would you like to play?               |");
            System.out.println("|                  [1] Play                       |");
            System.out.println("|                  [0] Quit                       |");
            System.out.println("|                                                 |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
            System.out.println("+-------------------------------------------------+");
            System.out.print(" INPUT: ");

            setChoice(scanner, 0, 1);

            System.out.println("CHOICE: " + getChoice());

            switch(getChoice()) {
                case 1:
                    ClearScreen.cls();
                    creature.setStarterCreature(scanner, creaturesList, capturedList);
                    ClearScreen.cls();
                    break;
                case 0:
                    ClearScreen.cls();
                    System.out.println("Thank you! Play again!");
                    choice = 0;
                    break;
            }

            if(choice != 0) {
                boolean play = MainMenu(scanner);
                if(!play)
                    choice = 1;
            }
        }
        
        scanner.close();
    }

    /**
     * The MainMenu function displays a menu to the user and allows them to choose different options
     * such as viewing inventory, exploring the area, evolving creatures, or exiting the game.
     * 
     * @param scanner The `scanner` parameter is an instance of the `Scanner` class, which is used to
     * read user input from the console.
     * @return The method is returning a boolean value of false.
     */
    public boolean MainMenu(Scanner scanner) { 
        boolean mainMenu = true;
        while(mainMenu) {
            ClearScreen.cls();
            System.out.println("+-------------------------------------------------+");
            System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("|              Welcome to Sweet Land!             |");
            System.out.println("|                                                 |");
            System.out.println("|            What would you like to do?           |");
            System.out.println("|                [1] View Inventory               |");
            System.out.println("|                [2] Explore Area                 |");
            System.out.println("|                [3] Evolve Creature              |");
            System.out.println("|                [4] Exit                         |");
            System.out.println("|                                                 |");
            System.out.println("+-------------------------------------------------+");
            System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
            System.out.println("+-------------------------------------------------+");
            System.out.print("INPUT: ");

            setChoice(scanner, 1, 4);

            switch(getChoice()) {
                case 1:
                    ClearScreen.cls();
                    if(inventory.showInventory(scanner))
                        mainMenu = true;
                    break;
                case 2:
                    area.exploreArea(scanner);
                    break;
                case 3:
                    ClearScreen.cls();
                    evo.evolutionScreen(scanner, capturedList);
                    break;
                case 4:
                    if(exitGame(scanner)) {
                        mainMenu = false;
                        resetGame();
                    }
                    break;
            }

        }
        return false;
    }

    /**
     * The function prompts the user to confirm if they want to exit the game and returns true if the
     * user chooses to exit, otherwise it returns false.
     * 
     * @param scanner The `Scanner` object is used to read user input from the console. It allows you
     * to prompt the user for input and retrieve the value entered by the user.
     * @return The method is returning a boolean value. If the user chooses to exit the game by
     * entering 1, the method will return true. Otherwise, it will return false.
     */
    public boolean exitGame(Scanner scanner) {
        System.out.println("Are you sure? All your progress will be lost.");
        System.out.println("[1] Yes");
        System.out.println("[0] No");
        System.out.print("INPUT: ");
        setChoice(scanner, 0, 1);

        if(getChoice() == 1) 
            return true;

        return false;
    }

    /**
     * The resetGame function sets all creatures in the creaturesList to be uncaptured and inactive.
     */
    public void resetGame() {
        List<Creature> copyList = new ArrayList<>(capturedList); // Create a copy of the list

        for (Creature creature : copyList) {
            creature.setActive(false);
            creature.setCaptured(false);
            capturedList.remove(creature); // Safely remove from the original list
        }
        
    }
 
    /**
     * The function sets the value of the "choice" variable based on user input, ensuring that the
     * input is within the specified range.
     * 
     * @param scanner The scanner object is used to read user input from the console. It is passed as a
     * parameter to the method so that the method can read the user's choice.
     * @param min The minimum value that the user can enter for their choice.
     * @param max The maximum value that the user can enter for their choice.
     */
    public void setChoice(Scanner scanner, int min, int max) {
        int isNum = 1;

        while (isNum == 1) {
            if (scanner.hasNextInt()) {
                this.choice = scanner.nextInt();
                if (this.choice >= min && this.choice <= max) {
                    isNum = 0; // Exit the loop when a valid choice is entered
                } else {
                    System.out.print("Invalid input. Enter "+ min + " to " + max + " : ");
                }
            } else {
                System.out.print("Invalid input. Enter "+ min +  " to " + max + " : ");
                scanner.next(); // Consume the invalid input
            }
        }
    }

    /**
     * The function returns the value of the variable "choice".
     * 
     * @return The method is returning an integer value.
     */
    public int getChoice() {
        return choice;
    }
}