package MyModel;
import java.util.ArrayList;
import java.util.Scanner;

public class CreatureModel {
    private int evoLvl;
    private int hp;
    private int id;
    private String name;
    private String type; 
    private String family;
    private boolean isActive;
    private boolean isCaptured;

    // The `public Creature` constructor is used to create a new `Creature` object with the specified
    // attributes. It takes in parameters such as `name`, `type`, `family`, `evoLvl`, `hp`, `isActive`,
    // `isCaptured`, and `id` and assigns them to the corresponding instance variables of the
    // `Creature` object. This allows for the initialization of a `Creature` object with specific
    // attributes when it is created.
    public CreatureModel(String name, String type, String family, int evoLvl, int hp, boolean isActive, boolean isCaptured, int id) {
        this.name = name;
        this.type = type;
        this.family = family;
        this.evoLvl = evoLvl;
        this.hp = hp;
        this.isActive = isActive;
        this.isCaptured = isCaptured;
        this.id = id;
    }

    // The `public Creature() { }` is a default constructor for the `Creature` class. It is an empty
    // constructor that does not take any parameters. This constructor is used when a new `Creature`
    // object is created without specifying any attributes.
    public CreatureModel() { }

    /**
     * The function initializes a list of creatures with their respective attributes.
     * 
     * @param creaturesList An ArrayList of Creature objects.
     */
    public void initializeCreatures(ArrayList<CreatureModel> creaturesList) {
        creaturesList.add(new CreatureModel("Strawander", "Fire",  "A", 1, 50, false, false, 1));
        creaturesList.add(new CreatureModel("Strawleon",  "Fire",  "A", 2, 100, false, false,2));
        creaturesList.add(new CreatureModel("Strawizard", "Fire",  "A", 3, 150, false, false,3));
        creaturesList.add(new CreatureModel("Chocowool",  "Fire",  "B", 1, 50, false, false ,4));
        creaturesList.add(new CreatureModel("Chocofluff", "Fire",  "B", 2, 100, false, false,5));
        creaturesList.add(new CreatureModel("Candaros",   "Fire",  "B", 3, 150, false, false,6));
        creaturesList.add(new CreatureModel("Parfwit",    "Fire",  "C", 1, 50, false, false ,7));
        creaturesList.add(new CreatureModel("Parfure",    "Fire",  "C", 2, 100, false, false,8));
        creaturesList.add(new CreatureModel("Parfelure",  "Fire",  "C", 3, 150, false, false,9));
        creaturesList.add(new CreatureModel("Brownisaur", "Grass", "D", 1, 50, false, false ,10));
        creaturesList.add(new CreatureModel("Chocosaur",  "Grass", "D", 2, 100, false, false,11));
        creaturesList.add(new CreatureModel("Fudgasaur",  "Grass", "D", 3, 150, false, false,12));
        creaturesList.add(new CreatureModel("Frubat",     "Grass", "E", 1, 50, false, false ,13));
        creaturesList.add(new CreatureModel("Golberry",   "Grass", "E", 2, 100, false, false,14));
        creaturesList.add(new CreatureModel("Croberry",   "Grass", "E", 3, 150, false, false,15));
        creaturesList.add(new CreatureModel("Malts",      "Grass", "F", 1, 50, false, false ,16));
        creaturesList.add(new CreatureModel("Kirlicake",  "Grass", "F", 2, 100, false, false,17));
        creaturesList.add(new CreatureModel("Velveoir",   "Grass", "F", 3, 150, false, false,18));
        creaturesList.add(new CreatureModel("Squirpie",   "Water", "G", 1, 50, false, false ,19));
        creaturesList.add(new CreatureModel("Tartotle",   "Water", "G", 2, 100, false, false,20));
        creaturesList.add(new CreatureModel("Piestoise",  "Water", "G", 3, 150, false, false,21));
        creaturesList.add(new CreatureModel("Chocolite",  "Water", "H", 1, 50, false, false ,22));
        creaturesList.add(new CreatureModel("Chocolish",  "Water", "H", 2, 100, false, false,23));
        creaturesList.add(new CreatureModel("Icesundae",  "Water", "H", 3, 150, false, false,24));
        creaturesList.add(new CreatureModel("Oshacone",   "Water", "I", 1, 50, false, false ,25));
        creaturesList.add(new CreatureModel("Dewice",     "Water", "I", 2, 100, false, false,26));
        creaturesList.add(new CreatureModel("Samurcone",  "Water", "I", 3, 150, false, false,27));
    }

    /**
     * The function allows the user to choose a starter creature from a list and adds it to the
     * captured list.
     * 
     * @param scanner The `Scanner` object is used to read user input from the console. It is passed as
     * a parameter to the method so that the user can choose a starter creature by entering a number.
     * @param creaturesList An ArrayList of Creature objects that contains all the available creatures
     * in the game.
     * @param capturedList The `capturedList` parameter is an `ArrayList` that stores the creatures
     * that have been captured by the player.
     */
    public void setStarterCreature(Scanner scanner, ArrayList<CreatureModel> creaturesList, ArrayList<CreatureModel> capturedList) {
        ArrayList<CreatureModel> evoLvl1 = new ArrayList<CreatureModel>();
        Game game = new Game();
        int creatureNumber = 1;
        
        ClearScreen.cls();
        System.out.println("+--------------------------------------------------------+");
        System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|");
        System.out.println("|                  Choose a Starter Pokemon!             |");
        System.out.println("+-----------------+-----------+----- --+-----------------+");
        System.out.println("|       Name      |   Type    | Family | Evolution Level |");
        System.out.println("+-----------------+-----------+--------+-----------------+");
        for (CreatureModel creature : creaturesList) {
            if(creature.getEvoLvl() == 1) { 
                System.out.print(creature.creatureDetails(creatureNumber));
                evoLvl1.add(creature);
                creatureNumber++;
            }
        }
        System.out.println("| ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|");
        System.out.println("+--------------------------------------------------------+");
        System.out.print("Enter Number: ");

        game.setChoice(scanner, 1, creatureNumber - 1);
        scanner.nextLine();
        int selectedPokemon = game.getChoice();

        int currentIndex = 1;
        CreatureModel chosenStarter = null;

        while(chosenStarter == null) {
            for(CreatureModel creature : evoLvl1) {
                if(currentIndex == selectedPokemon) {
                    chosenStarter = creature;
                    break;
                } 
                currentIndex++;
            }

            if(chosenStarter != null){
                System.out.println("You chose: " + chosenStarter.getName());
                chosenStarter.setActive(true);
                chosenStarter.setCaptured(true);
                capturedList.add(chosenStarter);
            } else{
                System.out.print("Invalid choice. Enter again: ");
                System.out.println(selectedPokemon + " " + currentIndex + " ");
                selectedPokemon = scanner.nextInt();
            }
        }
    }

    // The `setActive(boolean active)` method is a setter method for the `isActive` instance variable
    // of the `Creature` class. It takes in a boolean parameter `active` and sets the value of
    // `isActive` to the value of the parameter.
    public void setActive(boolean active) {
        this.isActive = active;
    }

    /**
     * The function "resetActive" sets the active state to false.
     */
    public void resetActive() {
        setActive(false);
    }

    /**
     * The function sets the value of the "isCaptured" variable to the provided boolean value.
     * 
     * @param captured A boolean value indicating whether the object has been captured or not.
     */
    public void setCaptured(boolean captured) {
        this.isCaptured = captured;
    }

    /**
     * The function sets the value of the "id" variable.
     * 
     * @param id The parameter "id" is an integer value that represents the ID of an object.
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * The getName() function returns the name of an object.
     * 
     * @return The method is returning the value of the variable "name".
     */
    public String getName() {
        return name;
    }

    /**
     * The getType() function returns the type of an object as a string.
     * 
     * @return The method is returning a String value.
     */
    public String getType() {
        return type;
    }

    /**
     * The function returns the value of the "family" variable.
     * 
     * @return The method is returning a String value.
     */
    public String getFamily() {
        return family;
    }

    /**
     * The function returns the evolution level of an object.
     * 
     * @return The method is returning the value of the variable "evoLvl".
     */
    public int getEvoLvl() {
        return evoLvl;
    }

    /**
     * The function returns the value of the variable "hp".
     * 
     * @return The method is returning the value of the variable "hp".
     */
    public int getHP() {
        return hp;
    }

    /**
     * The function returns the value of the id variable.
     * 
     * @return The method is returning the value of the variable "id".
     */
    public int getID() {
        return id;
    }

    /**
     * The function returns the value of the isActive variable.
     * 
     * @return The method is returning the value of the variable "isActive", which is a boolean value.
     */
    public boolean getisActive() {
        return isActive;
    }

    /**
     * The function returns the value of the boolean variable isCaptured.
     * 
     * @return The method is returning the value of the variable "isCaptured".
     */
    public boolean getisCaptured() {
        return isCaptured;
    }

    /**
     * The function returns "Active" if the isActive variable is true, otherwise it returns an empty
     * string.
     * 
     * @return If the `isActive` variable is `true`, the method will return the string "Active".
     * Otherwise, it will return an empty string.
     */
    public String activeStatus() {
        if(getisActive() == true)
            return "Active";
        return "";
    }

    /**
     * The function "creatureDetails" returns a formatted string containing details about a creature,
     * including its number, name, type, family, evolution level, and active status.
     * 
     * @param number The number parameter represents the creature's identification number.
     * @return The method is returning a formatted string that includes the details of a creature.
     */
    public String creatureDetails(int number) {
        String format = "| [%d] %-12s| %-9s | %-6s | %-15s |%n";
        return String.format(format, number, name, type, family, + evoLvl + " " + activeStatus());
    }
}


