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

    /**
    * Sets the active state of the item. This is used to determine if the item is active or deactivated
    * 
    * @param active - true if the item is
    */
    public void setActive(boolean active) {
        this.isActive = active;
    }

    /**
    * Sets the active flag to false. This is useful when you want to change the state of a dialog
    */
    public void resetActive() {
        setActive(false);
    }

    /**
    * Sets whether or not this component is captured. This is used to prevent unintended behavior when an application is capturing a component in the event of a problem.
    * 
    * @param captured - true if this component should be captured
    */
    public void setCaptured(boolean captured) {
        this.isCaptured = captured;
    }

    /**
    * Sets the id of this message. This is used to distinguish messages from other messages that have the same id
    * 
    * @param id - the id of this
    */
    public void setId(int id){
        this.id = id;
    }

    /**
    * Returns the name of this entity. This is the entity's name in the form of a java. lang. String object.
    * 
    * 
    * @return the entity's name in the form of a java. lang. String object or null if there is no
    */
    public String getName() {
        return name;
    }

    /**
    * Returns the type of the message. This is the message type that will be used to create the JabRef message.
    * 
    * 
    * @return the type of the message or null if there is no type associated with the message ( for example if the message is a JSON message
    */
    public String getType() {
        return type;
    }

    /**
    * Returns the family of this IP address. This will be one of the values defined in RFC 4122 or " Ethereum " if none is defined.
    * 
    * 
    * @return the family of this IP address or null if none is defined in RFC 4122 or " Ethereum
    */
    public String getFamily() {
        return family;
    }

    /**
    * Returns evolution level of the object. Evo levels are used to determine how much time to wait between events.
    * 
    * 
    * @return the evolution level of the object in miliseconds ( 0 - 100 ) or - 1 if there is no
    */
    public int getEvoLvl() {
        return evoLvl;
    }

    /**
    * Returns the HP of the Pokemon. This is an integer between 0 and #HP_MAX ( inclusive ).
    * 
    * 
    * @return the HP of the Pokemon in the range [ 0.. #HP_MAX ] or - 1 if there is no
    */
    public int getHP() {
        return hp;
    }

    /**
    * Returns the ID of this message. This is used to distinguish messages that have been added to the message queue and not to the message queue itself.
    * 
    * 
    * @return the ID of this message or - 1 if there is no ID associated with this message ( for example if the message is an un - added message
    */
    public int getID() {
        return id;
    }

    /**
    * Returns whether or not this object is active. This is used to determine whether or not an action should be taken in the event of a state change that is due to the user interacting with the object such as being in a state where it has no action or a state change that would result in an action being taken.
    * 
    * 
    * @return true if this object is active false otherwise ( in which case the action is not taken ). Note that this will return false if the object is not
    */
    public boolean getisActive() {
        return isActive;
    }

    /**
    * Returns whether or not this event has been captured. This is useful for determining whether or not to stop the processing of the event or not.
    * 
    * 
    * @return whether or not this event has been captured or not ( false is returned if it is not captured
    */
    public boolean getisCaptured() {
        return isCaptured;
    }

    /**
    * Initializes the creatures in the database. This is called before the database is used to populate the list
    * 
    * @param creaturesList - The list to populate
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
}