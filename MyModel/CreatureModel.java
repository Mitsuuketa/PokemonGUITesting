package MyModel;

import java.util.ArrayList;

public class CreatureModel {
    private int evoLvl;
    private int hp;
    private int id;
    private String name;
    private String type; 
    private String family;
    private boolean isActive;
    private boolean isCaptured;

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

    public CreatureModel() { }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void resetActive() {
        setActive(false);
    }

    public void setCaptured(boolean captured) {
        this.isCaptured = captured;
    }

    public void setId(int id){
        this.id = id;
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

    public int getID() {
        return id;
    }

    public boolean getisActive() {
        return isActive;
    }

    public boolean getisCaptured() {
        return isCaptured;
    }

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
}


