package MyViews;

import javax.swing.*;

import MyController.GameController;

import java.awt.*;
import java.awt.event.*;


public class StartView {
    private JPanel panel;
    private GameController gameController;

    public StartView(GameController gameController) {
        this.gameController = gameController;
        this.panel = new JPanel();
        initializeStartMenuGreeting();
    }

    /**
    * Returns the panel that this panel is associated with. This is used to provide access to the panel's properties when it is added to a JTabbedPane.
    * 
    * 
    * @return the panel that this panel is associated with or null if there is none for the panel type ( such as a menu
    */
    public JPanel getPanel() {
        return panel;
    }

    /**
    * Initializes the start menu greeting. This is called when the user clicks on the start menu to start
    */
    private void initializeStartMenuGreeting() {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel greetingsPromptLbl = new JLabel();
        greetingsPromptLbl.setText("Welcome to Pokemon Sweets!~\n");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(greetingsPromptLbl, constraints);

        JButton playButton = new JButton("Play");
        playButton.setFocusable(false);
        playButton.addActionListener(new ActionListener() {
            /**
            * Called when the user presses the button. Switches to the Choose Starter menu. Note that this is a blocking call
            * 
            * @param e - The ActionEvent that caused this
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.switchtoChooseStarterMenu();
            }
            
        });
        constraints.gridy = 2;
        panel.add(playButton, constraints);

        JButton exitButton = new JButton("Exit Game");
        exitButton.setFocusable(false);
        exitButton.addActionListener(new ActionListener() {
            /**
            * Invoked when the user presses the exit button. This is a no - op for the JPopupMenu
            * 
            * @param e - The ActionEvent that notified
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });
        constraints.gridy = 3;
        panel.add(exitButton, constraints);
    }
}
