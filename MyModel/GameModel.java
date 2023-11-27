package MyModel;

import java.util.*;

public class GameModel {

    private ArrayList<CreatureModel> capturedList;

    public GameModel(ArrayList<CreatureModel> capturedList) {
        this.capturedList = capturedList;
    }

    public int generateUniqueID() {
        Random random = new Random();
        int newID;

        do {
            newID = random.nextInt(1000);
        } while (!isIDInUse(newID));

        return newID;
    }
   
    public boolean isIDInUse(int id) {
        for (CreatureModel creature : capturedList) {
            if (creature.getID() == id) {
                return false; 
            }
        }
        return true; 
    }
}
