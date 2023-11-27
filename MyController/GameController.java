package MyController;

import java.util.ArrayList;

import MyModel.CreatureModel;
import MyViews.*;


public class GameController {
    private MainView mainView;
    private ArrayList<CreatureModel> creaturesList;
    private ArrayList<CreatureModel> capturedList;
    
    public GameController() {
        mainView = new MainView(this);
        this.creaturesList = new ArrayList<>();
        this.capturedList = new ArrayList<>();
    }

    public void switchtoChooseStarterMenu() {
        CreatureModel creature = new CreatureModel();
        creature.initializeCreatures(creaturesList);
        ChooseStartersView csv = new ChooseStartersView(this, creaturesList, capturedList);
        mainView.switchToNewPanel(csv.getPanel());
    }

    public void switchtoEvolveMenu() {
        EvolveView evolve = new EvolveView(this, capturedList, creaturesList);
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
        PlayView playMenu = new PlayView(this, capturedList);
        mainView.switchToNewPanel(playMenu.getPanel());
    }
    
    public void switchToInventoryMenu() {
        InventoryView inventoryView = new InventoryView(this, capturedList);
        mainView.switchToNewPanel(inventoryView.getPanel());
    }

    
    
}
