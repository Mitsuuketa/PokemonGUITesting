package MyController;
import java.util.ArrayList;
import java.util.Scanner;

public class EvolutionController {
    private ArrayList<CreatureController> capturedList;
    private ArrayList<CreatureController> creaturesList;
    private Game game;

    // The `Evolution` constructor is initializing the `capturedList`, `creaturesList`, and `game`
    // variables of the `Evolution` class. It takes two `ArrayList` parameters, `capturedList` and
    // `creaturesList`, and assigns them to the corresponding instance variables. It also creates a new
    // `Game` object and assigns it to the `game` variable.
    public EvolutionController(ArrayList<CreatureController> capturedList, ArrayList<CreatureController> creaturesList) {
        this.capturedList = capturedList;
        this.creaturesList = creaturesList;
        this.game = new Game();
    }

    /**
     * The evolutionScreen function displays a list of captured creatures and prompts the user to
     * choose a creature to evolve.
     * 
     * @param scanner The `Scanner` object is used to read user input from the console.
     * @param capturedList An ArrayList of Creature objects that represents the list of captured
     * Pokemons.
     */
    public void evolutionScreen(Scanner scanner, ArrayList<CreatureController> capturedList) {
        int evolveMenu = 1;

        scanner.nextLine();

        while(evolveMenu == 1) {
            int creatureNumber = 1;
            System.out.println("+--------------------------------------------------------+");
            System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|");
            System.out.println("+--------------------------------------------------------+");
            System.out.println("|            Welcome to the Evolution Center!            |");
            System.out.println("|                                                        |");
            System.out.println("|                 Here is a List of                      |");
            System.out.println("|              Your Captured Pokemons!                   |");
            System.out.println("+-----------------+-----------+----- --+-----------------+");
            System.out.println("|       Name      |   Type    | Family | Evolution Level |");
            System.out.println("+-----------------+-----------+--------+-----------------+");
            for (CreatureController allCreature : capturedList) {
                if (allCreature.getisCaptured() == true || allCreature.getisActive() == true) {
                    System.out.print(allCreature.creatureDetails(creatureNumber));
                    creatureNumber++;
                }
            }
            System.out.println("+--------------------------------------------------------+");
            System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|");
            System.out.println("+--------------------------------------------------------+");
            System.out.println("Which pokemon would you like to evolve? (0 to exit)");
            System.out.print("INPUT NUMBER: ");

            boolean evolve = evolveCreature(scanner);

            if(!evolve) 
                evolveMenu = 0;
        }
        
    }

    /**
     * The function `evolveCreature` allows the user to evolve a captured creature by selecting it from
     * a list and finding a similar creature to evolve it with.
     * 
     * @param scanner The `Scanner` object is used to read user input from the console. It is passed as
     * a parameter to the `evolveCreature` method so that the user can make choices during the
     * evolution process.
     * @return The method `evolveCreature` returns a boolean value.
     */
    public boolean evolveCreature(Scanner scanner) {
        int capturedCount = 0;
        
        for(CreatureController creature : capturedList) {
            if(creature.getisCaptured()) {
                capturedCount++;
            }
        }

        // User chooses a pokemon based on index
        game.setChoice(scanner, 0, capturedCount);
        int selectedIndex = game.getChoice();

        // 0 to quit
        if(selectedIndex == 0) { return false; }

        // Find the selected pokemon
        int currentIndex = 1;
        CreatureController selectedCreature = null;
        for (CreatureController creature : capturedList) {
            if (creature.getisCaptured()) {
                if (currentIndex == selectedIndex) {
                    selectedCreature = creature;
                    break;
                } else {
                    creature.setActive(false);
                }
                currentIndex++;
            }
        }

        if(selectedCreature.getEvoLvl() == 3) {
            scanner.nextLine();
            System.out.print("Selected Creature already at max evolution level! Press Enter to continue...");
            scanner.nextLine();
            return true;
        }

        // Find a similar pokemon based on the selected pokemon
        CreatureController similarCreature = null;
        int nextSameCreatureIndex = 1;
        for(CreatureController creature : capturedList) {
            if(creature.getisCaptured()) {
                if(creature.getName().equals(selectedCreature.getName()) &&
                    nextSameCreatureIndex != currentIndex) {
                    similarCreature = creature;
                    break;
                }
                nextSameCreatureIndex++;
            }
        }

        if(similarCreature == null) { 
            scanner.nextLine();
            System.out.print("No similar creature found! Press Enter to continue...");
            scanner.nextLine();
            ClearScreen.cls();
            return true;
        }

        // FOR VERIFICATION
        System.out.println("Creature 1 to be evolved: " + selectedCreature.getName() + " Family: " + selectedCreature.getFamily() + " Type: " + selectedCreature.getType() +  " at : " + currentIndex);
        System.out.println("Creature 2 to be evolved: " + similarCreature.getName() + " Family: " + similarCreature.getFamily() + " Type: " + similarCreature.getType() +  " at : " + nextSameCreatureIndex + "\n\n");

        // finds the appropriate evolution based on the family and evolution level
        CreatureController evolvedCreature = null;
        for (CreatureController candidate : creaturesList) {
            if(candidate.getFamily().equalsIgnoreCase(selectedCreature.getFamily())
                && candidate.getEvoLvl() == selectedCreature.getEvoLvl() + 1) {
                evolvedCreature = candidate;
            }
        }

        if(evolvedCreature != null) {
            performEvolution(selectedCreature, similarCreature, evolvedCreature);
            scanner.nextLine();
        } 
       
        return true;
    }

    /**
     * The function performs the evolution of a selected creature into an evolved creature, updating
     * the captured list and setting the evolved creature as the active Pokemon.
     * 
     * @param selectedCreature The creature that was originally selected for evolution.
     * @param similarCreature The similarCreature parameter is a Creature object that is similar to the
     * selectedCreature.
     * @param evolvedCreature The evolvedCreature parameter is the creature that the selectedCreature
     * is evolving into.
     */
    private void performEvolution(CreatureController selectedCreature, CreatureController similarCreature, CreatureController evolvedCreature) {
        System.out.println("Evolution of " + selectedCreature.getName() + " into " + evolvedCreature.getName() + " succeeds!");
        System.out.println(evolvedCreature.getName() + " is now your active Pokemon!");

        capturedList.add(evolvedCreature);

        selectedCreature.setCaptured(false);
        similarCreature.setCaptured(false);
        
        evolvedCreature.setCaptured(true);

        for (CreatureController creature : capturedList) {
            creature.setActive(false);
        }

        evolvedCreature.setActive(true);

        capturedList.remove(selectedCreature);
        capturedList.remove(similarCreature);
    }
}
