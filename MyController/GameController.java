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

    /**
    * Switch to Choose Starter menu for creatures list. This is called when user clicks choose starter
    */
    public void switchtoChooseStarterMenu() {
        CreatureModel creature = new CreatureModel();
        creature.initializeCreatures(creaturesList);
        ChooseStartersView csv = new ChooseStartersView(this, creaturesList, capturedList);
        mainView.switchToNewPanel(csv.getPanel());
    }

    /**
    * Switch to evolve menu and show it's panel. This is called when user clicks the evolve
    */
    public void switchtoEvolveMenu() {
        EvolveView evolve = new EvolveView(this, capturedList, creaturesList);
        mainView.switchToNewPanel(evolve.getPanel());
    }

    /**
    * Switch to Explore menu and show the panel that was selected. This is a bit hacky but I don't know how to get it
    */
    public void switchtoExploreMenu() {
        ExploreView explore = new ExploreView(this);
        mainView.switchToNewPanel(explore.getPanel());
    }

    /**
    * Switch to the Start menu and start the game. This is called when the user clicks the Start menu
    */
    public void switchtoStartMenu() {
        StartView startMenu = new StartView(this);
        mainView.switchToNewPanel(startMenu.getPanel());
    }

    /**
    * Switch to the Play menu. This is called when the user clicks on the play menu button in the list
    */
    public void switchToPlayMenu() {
        PlayView playMenu = new PlayView(this, capturedList);
        mainView.switchToNewPanel(playMenu.getPanel());
    }
    
    /**
    * Switches to Inventory menu. This is called when the user clicks on the inventory menu button or when the list is
    */
    public void switchToInventoryMenu() {
        InventoryView inventoryView = new InventoryView(this, capturedList);
        mainView.switchToNewPanel(inventoryView.getPanel());
    }

    /**
    * Switch to Battle View and show it in main view. This is called when user presses Enter
    */
    public void switchtoBattleView() {
        BattleView battleView = new BattleView(this);
        mainView.switchToNewPanel(battleView.getPanel());
    }
    
}
