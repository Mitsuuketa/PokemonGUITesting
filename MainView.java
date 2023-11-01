import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainView {

    private JFrame mainFrame;
    
    public MainView() {
        this.mainFrame = new JFrame("Pokemon Sweets");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout());
        this.mainFrame.setSize(500, 500);


        new StartMenu();
        
        this.mainFrame.setVisible(true);
    }


}
