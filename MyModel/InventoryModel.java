package MyModel;

import java.util.ArrayList;
import pokemon.Creature;

public class InventoryModel {
    private int evoLvl;
    private int hp;
    private int id;
    private String name;
    private String type; 
    private String family;
    private boolean isActive;
    private boolean isCaptured;

    public InventoryModel() {
    }

    public void addCreature() {

    }

    public void removeCreature() {

    }

    public ArrayList<Creature> getEvoLevel1() {
        ArrayList<Creature> level1Creatures = new ArrayList<>();
        ArrayList<Creature> creaturesList = new ArrayList<>();

        initializeCreatures(creaturesList);
    
        for (Creature creature : creaturesList) {
            if (creature.getEvoLvl() == 1) {
                level1Creatures.add(creature);
            }
        }
    
        return level1Creatures;
    }

    public ArrayList<Creature> getEvoLevel2() {
        ArrayList<Creature> level1Creatures = new ArrayList<>();
        ArrayList<Creature> creaturesList = new ArrayList<>();

        initializeCreatures(creaturesList);
    
        for (Creature creature : creaturesList) {
            if (creature.getEvoLvl() == 2) {
                level1Creatures.add(creature);
            }
        }
    
        return level1Creatures;
    }
    
    public ArrayList<Creature> getCaptured() {
        ArrayList<Creature> capturedCreatures = new ArrayList<>();
        ArrayList<Creature> creaturesList = new ArrayList<>();

        initializeCreatures(creaturesList);
    
        for (Creature creature : creaturesList) {
            if (creature.getisCaptured()) {
                capturedCreatures.add(creature);
            }
        }
    
        return capturedCreatures;
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean getisActive() {
        return isActive;
    }

    public void resetActive() {
        setActive(false);
    }

    public void setCaptured(boolean captured) {
        this.isCaptured = captured;
    }

    public boolean getisCaptured() {
        return isCaptured;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getFamily() {
        return family;
    }

    public int getEvoLvl() {
        return evoLvl;
    }

    public int getHP() {
        return hp;
    }

    public void initializeCreatures(ArrayList<Creature> creaturesList) {
        creaturesList.add(new Creature("Strawander", "Fire",  "A", 1, 50, false, false, 1));
        creaturesList.add(new Creature("Strawleon",  "Fire",  "A", 2, 100, false, false,2));
        creaturesList.add(new Creature("Strawizard", "Fire",  "A", 3, 150, false, false,3));
        creaturesList.add(new Creature("Chocowool",  "Fire",  "B", 1, 50, false, false ,4));
        creaturesList.add(new Creature("Chocofluff", "Fire",  "B", 2, 100, false, false,5));
        creaturesList.add(new Creature("Candaros",   "Fire",  "B", 3, 150, false, false,6));
        creaturesList.add(new Creature("Parfwit",    "Fire",  "C", 1, 50, false, false ,7));
        creaturesList.add(new Creature("Parfure",    "Fire",  "C", 2, 100, false, false,8));
        creaturesList.add(new Creature("Parfelure",  "Fire",  "C", 3, 150, false, false,9));
        creaturesList.add(new Creature("Brownisaur", "Grass", "D", 1, 50, false, false ,10));
        creaturesList.add(new Creature("Chocosaur",  "Grass", "D", 2, 100, false, false,11));
        creaturesList.add(new Creature("Fudgasaur",  "Grass", "D", 3, 150, false, false,12));
        creaturesList.add(new Creature("Frubat",     "Grass", "E", 1, 50, false, false ,13));
        creaturesList.add(new Creature("Golberry",   "Grass", "E", 2, 100, false, false,14));
        creaturesList.add(new Creature("Croberry",   "Grass", "E", 3, 150, false, false,15));
        creaturesList.add(new Creature("Malts",      "Grass", "F", 1, 50, false, false ,16));
        creaturesList.add(new Creature("Kirlicake",  "Grass", "F", 2, 100, false, false,17));
        creaturesList.add(new Creature("Velveoir",   "Grass", "F", 3, 150, false, false,18));
        creaturesList.add(new Creature("Squirpie",   "Water", "G", 1, 50, false, false ,19));
        creaturesList.add(new Creature("Tartotle",   "Water", "G", 2, 100, false, false,20));
        creaturesList.add(new Creature("Piestoise",  "Water", "G", 3, 150, false, false,21));
        creaturesList.add(new Creature("Chocolite",  "Water", "H", 1, 50, false, false ,22));
        creaturesList.add(new Creature("Chocolish",  "Water", "H", 2, 100, false, false,23));
        creaturesList.add(new Creature("Icesundae",  "Water", "H", 3, 150, false, false,24));
        creaturesList.add(new Creature("Oshacone",   "Water", "I", 1, 50, false, false ,25));
        creaturesList.add(new Creature("Dewice",     "Water", "I", 2, 100, false, false,26));
        creaturesList.add(new Creature("Samurcone",  "Water", "I", 3, 150, false, false,27));
    }
}
