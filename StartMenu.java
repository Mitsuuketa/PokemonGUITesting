import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class StartMenu {
    private JFrame mainFrame;

    public StartMenu() {
        this.mainFrame = new JFrame("My Simple GUI");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout());
        this.mainFrame.setSize(200,200);
        initializeStartMenuGreeting();
        this.mainFrame.setVisible(true);
    }


    private void initializeStartMenuGreeting() {
        JLabel greetingsPromptLbl = new JLabel();
        greetingsPromptLbl.setText("Welcome to Pokemon Sweets!~");

        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel playText = new JLabel();
                playText.setText("Play");
            }
            
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });
    }
}
