package MyController;
import MyModel.InventoryModel;
import MyViews.InventoryView;



public class InventoryController {
    
    private InventoryModel model;
    private InventoryView view;

    public InventoryController(InventoryModel model, InventoryView view) {
        this.model = model;
        this.view = view;
    }

    public void addCreature() {
    }
}
