package MyController;

import java.util.ArrayList;

import MyModel.CreatureModel;
import MyViews.*;


public class GameController {
    private MainView mainView;
    private ArrayList<CreatureModel> creaturesList;
    
    public GameController() {
        mainView = new MainView(this);
        this.creaturesList = new ArrayList<>();
    }

    public void switchtoChooseStarterMenu() {
        CreatureModel creature = new CreatureModel();
        creature.initializeCreatures(creaturesList);
        ChooseStartersView csv = new ChooseStartersView(this, creaturesList);
        mainView.switchToNewPanel(csv.getPanel());
    }

    public void switchtoEvolveMenu() {
        EvolveView evolve = new EvolveView(this);
        mainView.switchToNewPanel(evolve.getPanel());
    }

    public void switchtoExploreMenu() {
        ExploreView explore = new ExploreView(this);
        mainView.switchToNewPanel(explore.getPanel());
    }

    public void switchtoStartMenu() {
        StartView startMenu = new StartView(this);
        mainView.switchToNewPanel(startMenu.getPanel());
    }

    public void switchToPlayMenu() {
        // Create a new PlayMenu and switch the content of the main frame
        PlayView playMenu = new PlayView(this);
        mainView.switchToNewPanel(playMenu.getPanel());
    }
    public void switchToInventoryMenu() {
        InventoryView inventoryView = new InventoryView(this);
        mainView.switchToNewPanel(inventoryView.getPanel());
    }

    
    
}
