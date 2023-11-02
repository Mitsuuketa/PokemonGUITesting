package MyModel;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class InventoryModel {
    private CreatureModel activeCreature= new CreatureModel();
    private ArrayList<CreatureModel> capturedList = new ArrayList<>();
    private Game game; 

    // The `public Inventory(ArrayList<Creature> capturedList)` is a constructor for the `Inventory`
    // class. It takes an `ArrayList` of `Creature` objects as a parameter and initializes the
    // `capturedList` variable with the provided list. It also creates a new `Game` object and assigns
    // it to the `game` variable.
    public InventoryModel(ArrayList<CreatureModel> capturedList) {
        this.capturedList = capturedList;
        this.game = new Game();
    }

    /**
     * The function "showInventory" displays a list of captured creatures and allows the user to change
     * the active creature or go back.
     * 
     * @param scanner The `Scanner` object is used to read user input from the console. It allows the
     * user to make choices and interact with the inventory system.
     * @return The method is returning a boolean value of false.
     */
    public boolean showInventory(Scanner scanner) {
        boolean displayInventory = true;
        while(displayInventory) {
            int creatureNumber = 1;
            ClearScreen.cls();
            System.out.println("+--------------------------------------------------------+");
            System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|");
            System.out.println("|            List of Your Captured Pokemons!             |");
            System.out.println("+-----------------+-----------+----- --+-----------------+");
            System.out.println("|       Name      |   Type    | Family | Evolution Level |");
            System.out.println("+-----------------+-----------+--------+-----------------+");
            for (CreatureModel allCreature : capturedList) {
                if(allCreature.getisActive() == true)
                    activeCreature = allCreature;

                System.out.print(allCreature.creatureDetails(creatureNumber));
                creatureNumber++;
            }
            System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|");
            System.out.println("+--------------------------------------------------------+");
            System.out.println("What would you like to do next? ");
            System.out.println("[1] Change active creature ");
            System.out.println("[2] Go back ");
            System.out.print("INPUT: ");

            game.setChoice(scanner, 1, 2);

            if(game.getChoice() == 1) {
                changeCreature(scanner);
            } else 
                displayInventory = false;
        }
        
        return false;
    }

    
    /**
     * The function allows the user to change the active creature from a list of captured creatures.
     * 
     * @param scanner The scanner parameter is an instance of the Scanner class, which is used to read
     * user input from the console.
     */
    public void changeCreature(Scanner scanner) {
        CreatureModel lastCaptured = null;
        boolean isValidIndex = true;
        int capturedCount = 0;
        
        for(CreatureModel creature : capturedList) {
            if(creature.getisCaptured()) {
                capturedCount++;
                lastCaptured = creature;
            }
        }

        if(capturedCount < 2) {
            System.out.println("You only have one captured Pokemon. Which is " + lastCaptured.getName());
            scanner.nextLine();
            System.out.print("Going back to Main Menu. Press ENTER to continue.");
            scanner.nextLine();
            isValidIndex = false;
        }

        lastCaptured.setActive(true);

        while (isValidIndex) {
            System.out.print("Enter the number of the creature you want to set as active: ");

            game.setChoice(scanner, 1 , capturedCount);
            int selectedIndex = game.getChoice();

            if (selectedIndex >= 1 && selectedIndex <= capturedCount) {
                int currentIndex = 1;
                for (CreatureModel creature : capturedList) {
                    if (creature.getisCaptured()) {
                        if (currentIndex == selectedIndex) {
                            creature.setActive(true);
                        } else {
                            creature.setActive(false);
                        } 
                        currentIndex++;
                    }
                }
                scanner.nextLine();
                isValidIndex = false;
            } else {
                System.out.print("Invalid number. Please enter a valid number: ");
            }
            
        }
    }

    /**
     * The function returns the active creature.
     * 
     * @return The method is returning the activeCreature object of type Creature.
     */
    public CreatureModel getActiveCreature() {
        return activeCreature;
    }

    /**
     * The function generates a random unique ID by repeatedly generating random numbers until a unique
     * one is found.
     * 
     * @return The method is returning an integer value, which is the generated unique ID.
     */
    public int generateUniqueID() {
        Random random = new Random();
        int newID;

        // Generate a random ID until it's unique
        do {
            newID = random.nextInt(1000); // Adjust the range as needed
        } while (!isIDInUse(newID));

        return newID;
    }

    /**
     * The function checks if a given ID is already in use by iterating through a list of captured
     * creatures and returning false if a match is found, otherwise it returns true.
     * 
     * @param id The parameter "id" is an integer representing the ID that we want to check if it is
     * already in use.
     * @return The method is returning a boolean value. If the ID is already in use, it will return
     * false. If the ID is unique and not in use, it will return true.
     */
    public boolean isIDInUse(int id) {
        for (CreatureModel creature : capturedList) {
            if (creature.getID() == id) {
                return false; // ID is already in use
            }
        }
        return true; // ID is unique
    }
}
