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
    
    public void switchToNewPanel(JPanel newPanel) {
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
