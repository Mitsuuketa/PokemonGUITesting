package MyModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class BattleModel {
    private InventoryModel inventory;
    private CreatureModel activeCreature = new CreatureModel();
    private CreatureModel enemyCreature = new CreatureModel();    
    private ArrayList<CreatureModel> level1Creatures = new ArrayList<>();
    private ArrayList<CreatureModel> capturedList;
    private ArrayList<CreatureModel> creaturesList;
    private int enemyCurrentHP;


    // The `public Battle` constructor is initializing the `Battle` object with the provided
    // parameters. It takes in three parameters: `capturedList`, `creaturesList`, and `inventory`.
    public BattleModel (ArrayList<CreatureModel> capturedList, ArrayList<CreatureModel> creaturesList, InventoryModel inventory) {
        this.creaturesList = creaturesList;
        this.capturedList = capturedList;
        this.inventory = inventory;
    }

    // The `public Battle() { }` is a default constructor for the `Battle` class. It is an empty
    // constructor that does not take any parameters. It is used to create an instance of the `Battle`
    // class without initializing any properties or performing any actions.
    public BattleModel() { }

    /**
     * The function represents the battle phase of a game where the player encounters an enemy and has
     * a limited number of actions to defeat it.
     * 
     * @param scanner The scanner parameter is an instance of the Scanner class, which is used to read
     * user input from the console. It is passed to the method so that the user can interact with the
     * battle phase by entering their choices.
     */
    public void battlePhase(Scanner scanner) {
        setActiveCreature(capturedList);
        setEnemyCreature();

        System.out.print("You have encountered an enemy!!! Press any key to continue . . .");
        scanner.nextLine();

        int nActions = 3;
        boolean play = true;

        enemyCurrentHP = enemyCreature.getHP();
        int activeCurrentHP = activeCreature.getHP();

        while(nActions != 0 && play) {
            battleScreen(enemyCurrentHP, activeCurrentHP, nActions);
            play = battleChoice(scanner, play, enemyCurrentHP, activeCurrentHP);
            nActions--;

            if(nActions == 0 && play) {
                System.out.print("You've run out of action! The enemy pokemon ran away!");
                scanner.nextLine();
            }
        }
    }

    /**
     * The function displays a battle screen with information about the enemy creature, the player's
     * active creature, and the available actions.
     * 
     * @param enemyCurrentHP The current HP (health points) of the enemy creature in the battle.
     * @param activeCurrentHP The parameter activeCurrentHP represents the current HP (Hit Points) of
     * the active creature (your Pokemon) in the battle.
     * @param nActions The parameter `nActions` represents the number of actions remaining for the
     * player in the battle.
     */
    public void battleScreen(int enemyCurrentHP, int activeCurrentHP, int nActions) {
        ClearScreen.cls();
        System.out.println("+------------------------------------------------------------------------------+");
        System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|");
        System.out.println("+------------------------------------------------------------------------------+");
        System.out.println("                                  Enemy: " + enemyCreature.getName() + "      Level " + enemyCreature.getEvoLvl() +  "   Type:" + enemyCreature.getType());
        System.out.println("                                     HP: " + enemyCurrentHP                         );
        System.out.println("                                                                                ");
        System.out.println("                                                                                ");
        System.out.println("                                                                                ");
        System.out.println("  Your Pokemon: " + activeCreature.getName() +"    Level: " + activeCreature.getEvoLvl() + "    Type: " + activeCreature.getType() );
        System.out.println("            HP: " + activeCurrentHP                                                  );
        System.out.println("                                                                                 ");
        System.out.println("+------------------------------------------------------------------------------+");
        System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|");
        System.out.println("+------------------------------------------------------------------------------+");
        System.out.println("What would you like to do? Actions remaining: " + nActions);
        System.out.println("[1] Attack");
        System.out.println("[2] Swap");
        System.out.println("[3] Catch");
        System.out.println("[4] Run Away");
        System.out.print("INPUT: ");
    }

    /**
     * The function allows the player to choose their action during a battle in a game, such as
     * attacking, swapping creatures, attempting to catch a creature, or running away.
     * 
     * @param scanner The scanner parameter is an instance of the Scanner class, which is used to read
     * user input from the console.
     * @param play A boolean variable indicating whether the battle should continue or not.
     * @param enemyCurrentHP The current HP (health points) of the enemy creature in the battle.
     * @param activeCurrentHP The current HP (hit points) of the active creature in the battle.
     * @return The method is returning the value of the boolean variable "play".
     */
    public boolean battleChoice(Scanner scanner, boolean play, int enemyCurrentHP, int activeCurrentHP) {
        Game game = new Game();

        game.setChoice(scanner, 1, 4);

        switch(game.getChoice()) {
            case 1:
                int damage = attack();
                this.enemyCurrentHP -= damage;
                scanner.nextLine();
                System.out.print(enemyCreature.getName() + " got damagaed for " + damage + " points!");
                scanner.nextLine();
                break;
            case 2:
                activeCreature = swap(scanner, activeCreature);
                break;
            case 3:
                if(catchCreature(scanner)){
                    System.out.print("\nYou have successfully catched the pokemon!");
                    play = false; 
                }
                scanner.nextLine();
                break;
            case 4:
                scanner.nextLine();
                System.out.print("Time to run!!!");
                scanner.nextLine();
                play = false;
                break;
        }
        return play;
    }

    /**
     * The function calculates the base attack damage of an active creature in a game, taking into
     * account the creature's evolution level and type advantage against the enemy creature.
     * 
     * @return The method is returning the calculated base attack value.
     */
    public int attack() {
        Random random = new Random();
        int baseAttack = random.nextInt(1,10) * activeCreature.getEvoLvl();

        if(activeCreature.getType().equalsIgnoreCase("Fire") && enemyCreature.getType().equalsIgnoreCase("Grass")){
            baseAttack *= 1.5;
        } else if(activeCreature.getType().equalsIgnoreCase("Grass") && enemyCreature.getType().equalsIgnoreCase("Water")) {
            baseAttack *= 1.5;
        } else if(activeCreature.getType().equalsIgnoreCase("Water") && enemyCreature.getType().equalsIgnoreCase("Fire")) {
            baseAttack *= 1.5;
        } else
            baseAttack *= 1;

        return baseAttack;
    }

    /**
     * The function swaps the active creature with a new one chosen from the inventory and returns the
     * new active creature.
     * 
     * @param scanner A Scanner object used for user input.
     * @param activeCreature The current active creature that the player has control over.
     * @return The method is returning the active creature from the inventory.
     */
    public CreatureModel swap(Scanner scanner, CreatureModel activeCreature) {
        if (inventory.showInventory(scanner)) {
            // After choosing a new active creature, return it
            return inventory.getActiveCreature();
        }

        return inventory.getActiveCreature();
    }

    /**
     * The function attempts to catch a creature and adds it to the captured list if successful.
     * 
     * @param scanner The `scanner` parameter is an instance of the `Scanner` class, which is used for
     * reading user input from the console.
     * @return The method is returning a boolean value. If the catch attempt is successful, it returns
     * true. If the catch attempt fails, it returns false.
     */
    public boolean catchCreature(Scanner scanner) {
        int baseCatchSuccess = (40 + 50 - enemyCreature.getHP());
        int uniqueID = inventory.generateUniqueID();
        Random random = new Random();
        List<CreatureModel> creaturesToAdd = new ArrayList<>();

        if (random.nextInt(1, 100) < baseCatchSuccess) {
            boolean creatureFound = false;
    
            for (CreatureModel creature : capturedList) {
                if (enemyCreature == creature) {
                    // Add the captured creature to the new list
                    creaturesToAdd.add(new CreatureModel(
                        enemyCreature.getName(),
                        enemyCreature.getType(),
                        enemyCreature.getFamily(),
                        enemyCreature.getEvoLvl(),
                        enemyCreature.getHP(),
                        false,
                        true,
                        uniqueID
                    ));
    
                    creatureFound = true;
                    break;
                }
            }
    
            if (!creatureFound) {
                // If the creature wasn't found, add the enemyCreature directly to the new list
                creaturesToAdd.add(enemyCreature);
                enemyCreature.setCaptured(true);
            }
    
            // Add the creatures from the new list to the capturedList
            capturedList.addAll(creaturesToAdd);
    
            scanner.nextLine();
            return true;
        }

        System.out.print("Catch Failed! The enemy is quite good at dodging...");
        scanner.nextLine();
        return false;
    }

    /**
     * The function sets the active creature by iterating through a list of captured creatures and
     * assigning the first one with the isActive property set to true.
     * 
     * @param capturedList An ArrayList of Creature objects.
     */
    public void setActiveCreature(ArrayList<CreatureModel> capturedList) {
        for(CreatureModel creature : capturedList) {
            if(creature.getisActive() == true) {
                this.activeCreature = creature;
            }
        }
    }

    /**
     * The function sets the enemy creature by randomly selecting a level 1 creature from a list of
     * creatures.
     */
    public void setEnemyCreature(){
        for(CreatureModel creature : creaturesList) {
            if(creature.getEvoLvl() == 1) {
                level1Creatures.add(creature);
            }
        }
        Random random = new Random();
        int randLevel1Enemy = random.nextInt(1,level1Creatures.size());
        enemyCreature = level1Creatures.get(randLevel1Enemy);
    }

    /**
     * The function isBattle() returns true if there is a 40% chance of encountering a battle, and
     * false otherwise.
     * 
     * @return The method is returning a boolean value. It returns true if the randomly generated
     * encounterChance is less than 40, and false otherwise.
     */
    public boolean isBattle() {
        Random random = new Random();
        int encounterChance = random.nextInt(100);

        if(encounterChance < 40){
            return true;
        } else {
            return false;
        }
    }
}
