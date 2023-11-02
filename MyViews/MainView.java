package MyViews;
import javax.swing.*;
import java.awt.*;

public class MainView {

    private JFrame mainFrame;
    private JPanel currentPanel;
    
    public MainView() {
        this.mainFrame = new JFrame("Pokemon Sweets");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout());
        this.mainFrame.setSize(500, 550);

        StartView startMenu = new StartView(this);
        currentPanel = startMenu.getPanel();

        this.mainFrame.add(startMenu.getPanel());
        this.mainFrame.setVisible(true);
    }

    public void switchtoEvolveMenu() {
        EvolveView evolve = new EvolveView(this);
        switchToNewPanel(evolve.getPanel());
    }

    public void switchtoExploreMenu() {
        ExploreView explore = new ExploreView(this);
        switchToNewPanel(explore.getPanel());
    }

    public void switchtoStartMenu() {
        StartView startMenu = new StartView(this);
        switchToNewPanel(startMenu.getPanel());
    }

    public void switchToPlayMenu() {
        // Create a new PlayMenu and switch the content of the main frame
        PlayView playMenu = new PlayView(this);
        switchToNewPanel(playMenu.getPanel());
    }
    public void switchToInventoryMenu() {
        InventoryView inventoryView = new InventoryView(this);
        switchToNewPanel(inventoryView.getPanel());
    }

    private void switchToNewPanel(JPanel newPanel) {
        if (currentPanel != null) {
            currentPanel.setVisible(false);
            mainFrame.remove(currentPanel);
        }
        currentPanel = newPanel;
        mainFrame.add(currentPanel, BorderLayout.CENTER);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
