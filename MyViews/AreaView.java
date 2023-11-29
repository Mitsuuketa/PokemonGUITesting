package MyViews;

import javax.swing.*;

import MyController.GameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AreaView {

    private JPanel panel;
    private JButton[][] tiles;
    private int numRows;
    private int numCols;
    private int currentPlayerRow;
    private int currentPlayerCol;
    private GameController gameController;

    public AreaView(int numCols, int numRows, GameController gameController) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.currentPlayerRow = 0;
        this.currentPlayerCol = 0;
        this.gameController = gameController;

        initializeAreaView();
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
    * Initializes the area view. This is called from initialize () and should not be called directly by the user
    */
    private void initializeAreaView() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
    
        tiles = new JButton[numRows][numCols];
    
        // Add all tiles to the panel.
        for (int i = 0; i < numRows; i++) {
            // Add a tile to the panel.
            for (int j = 0; j < numCols; j++) {
                JButton tileButton = new JButton();
                tileButton.setFocusable(false);
                tiles[i][j] = tileButton;
                constraints.gridx = j;
                constraints.gridy = i;
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
                // Set the background color of the tile button.
                if (i == currentPlayerRow && j == currentPlayerCol) {
                    tileButton.setBackground(Color.GREEN);
                } else {
                    tileButton.setBackground(Color.RED);
                }
                panel.add(tileButton, constraints);
            }
        }
    
        addMoveButtons();
        addExploreButton();
    
        constraints.gridx = 0;
        constraints.gridy = numRows + 2; 
        constraints.gridwidth = numCols;
        constraints.weighty = 1.0;
        panel.add(new JPanel(), constraints);
    }

    /**
    * Adds buttons to the player's movement area. This is called from addPlayer () and adds them to the game
    */
    private void addMoveButtons() {
        JButton upButton = new JButton("UP");
        upButton.addActionListener(new ActionListener() {
            /**
            * Moves the player one step in the direction of the mouse. This is called when the user clicks on the button
            * 
            * @param e - The ActionEvent that caused this
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(-1, 0);
            }
        });

        JButton downButton = new JButton("DOWN");
        downButton.addActionListener(new ActionListener() {
            /**
            * Moves the player one step. This is called when the user clicks on the button. It does not take into account the action that was performed in the actionPerformed method.
            * 
            * @param e - The ActionEvent that caused this method to be called
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(1, 0);
            }
        });

        JButton leftButton = new JButton("LEFT");
        leftButton.addActionListener(new ActionListener() {
            /**
            * Moves the player one step. This is called when the user clicks on the button. It does not take into account the action that was performed in the actionPerformed method.
            * 
            * @param e - The ActionEvent that caused this method to be called
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(0, -1);
            }
        });

        JButton rightButton = new JButton("RIGHT");
        rightButton.addActionListener(new ActionListener() {
            /**
            * Moves the player one step. This is called when the user clicks on the button. It does not take into account the action that was performed in the actionPerformed method.
            * 
            * @param e - The ActionEvent that caused this method to be called
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(0, 1);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        buttonPanel.add(new JLabel());
        buttonPanel.add(upButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(leftButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(rightButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(downButton);
        buttonPanel.add(new JLabel());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = numRows;
        constraints.gridwidth = numCols;
        panel.add(buttonPanel, constraints);
    }

    /**
    * Adds a button to the panel that allows the user to explore the game. This is used when the game is over
    */
    private void addExploreButton() {
        JButton exploreButton = new JButton("Back");
        exploreButton.setFocusable(false);
        exploreButton.addActionListener(new ActionListener() {
            /**
            * Switches to the explore menu. This is called when the user clicks on the menu button.
            * 
            * @param e - The ActionEvent that caused this method to be called
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.switchtoExploreMenu();
            }
        });

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = numRows + 1;
        constraints.gridwidth = numCols;
        panel.add(exploreButton, constraints);
    }

    /**
    * Moves the player to a new position. If the move is valid the player is colored red and the creature is switched to battle
    * 
    * @param rowChange - the number of rows to move
    * @param colChange - the number of columns to
    */
    private void movePlayer(int rowChange, int colChange) {
        int newRow = currentPlayerRow + rowChange;
        int newCol = currentPlayerCol + colChange;

        // Move the player to the new row and column
        if (isValidMove(newRow, newCol)) {
            tiles[currentPlayerRow][currentPlayerCol].setBackground(Color.RED);
            currentPlayerRow = newRow;
            currentPlayerCol = newCol;
            tiles[currentPlayerRow][currentPlayerCol].setBackground(Color.GREEN);

            // Switch to battle view if creatureEncounter is true.
            if(creatureEncounter()) {
                gameController.switchtoBattleView();
            }
        }
    }

    /**
    * Checks if the move is valid. This is used to determine if we can move to a given row and column
    * 
    * @param row - The row to move to
    * @param col - The column to move to ( must be in range 0 < = col < numRows )
    * 
    * @return true if the move is valid false if it is not valid or if the coordinates are out of bounds
    */
    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    /**
    * Returns true if creature encounter. This is a method to be used in the game's methods.
    * 
    * 
    * @return true if creature encounter false if not or if random number between 1 and 100 is less than 40
    */
    private boolean creatureEncounter() {
        Random rand = new Random();
        int chance = rand.nextInt(1,100);

        // Check if the chance is 40.
        if(chance <= 40)
            return true;
        return false;
    }
}
