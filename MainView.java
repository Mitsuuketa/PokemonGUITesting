import javax.swing.*;
import java.awt.*;

public class MainView {

    private JFrame mainFrame;
    private JPanel currentPanel;
    
    public MainView() {
        this.mainFrame = new JFrame("Pokemon Sweets");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout());
        this.mainFrame.setSize(400, 400);

        StartView startMenu = new StartView(this);
        currentPanel = startMenu.getPanel();

        this.mainFrame.add(startMenu.getPanel());
        this.mainFrame.setVisible(true);
    }

    public void switchToPlayMenu() {
        // Create a new PlayMenu and switch the content of the main frame
        PlayView playMenu = new PlayView();
        currentPanel.setVisible(true);
        mainFrame.remove(currentPanel);
        currentPanel = playMenu.getPanel();
        mainFrame.add(currentPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
