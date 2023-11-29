package MyModel;

import java.util.*;

public class GameModel {

    private ArrayList<CreatureModel> capturedList;

    public GameModel(ArrayList<CreatureModel> capturedList) {
        this.capturedList = capturedList;
    }

    /**
    * Generates a new ID that is not in use. This is used to ensure that an ID can be used in a transaction without violating security constraints.
    * 
    * 
    * @return a new non - unique ID for this transaction. Note that the ID is guaranteed to be unique within the time range of 0 to #getCacheSize ()
    */
    public int generateUniqueID() {
        Random random = new Random();
        int newID;

        do {
            newID = random.nextInt(1000);
        } while (!isIDInUse(newID));

        return newID;
    }
   
    /**
    * Checks if creature with given ID is in use. This is used to avoid duplicates in case of creatures that don't have the same ID
    * 
    * @param id - ID of creature to check
    * 
    * @return true if creature with given ID is in use false if not in use or creature has been
    */
    public boolean isIDInUse(int id) {
        for (CreatureModel creature : capturedList) {
            // Returns true if the creature is in the current creature.
            if (creature.getID() == id) {
                return false; 
            }
        }
        return true; 
    }
}
