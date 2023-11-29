package MyViews;
import javax.swing.*;

import MyController.GameController;

import java.awt.*;

public class MainView {

    private JFrame mainFrame;
    private JPanel currentPanel;
    
    public MainView(GameController gameController) {
        this.mainFrame = new JFrame("Pokemon Sweets");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout());
        this.mainFrame.setSize(500, 550);

        StartView startMenu = new StartView(gameController);
        currentPanel = startMenu.getPanel();

        this.mainFrame.add(startMenu.getPanel());
        this.mainFrame.setVisible(true);
    }
    
    /**
    * Switches to the specified panel. This is useful when you want to change the layout of the panel in a way that preserves the user's screen and need to re - layout the panel after it has been moved to another panel.
    * 
    * @param newPanel - The panel to switch to. If null the current panel is
    */
    public void switchToNewPanel(JPanel newPanel) {
        // Removes the current panel from the main frame.
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
