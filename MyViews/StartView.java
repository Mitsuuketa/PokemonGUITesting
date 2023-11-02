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

    public JPanel getPanel() {
        return panel;
    }

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
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });
        constraints.gridy = 3;
        panel.add(exitButton, constraints);
    }
}
