package MyViews;

import javax.swing.*;

import MyController.GameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public JPanel getPanel() {
        return panel;
    }

    private void initializeAreaView() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
    
        tiles = new JButton[numRows][numCols];
    
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                JButton tileButton = new JButton();
                tileButton.setFocusable(false);
                tiles[i][j] = tileButton;
                constraints.gridx = j;
                constraints.gridy = i;
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
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

    private void addMoveButtons() {
        JButton upButton = new JButton("UP");
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(-1, 0);
            }
        });

        JButton downButton = new JButton("DOWN");
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(1, 0);
            }
        });

        JButton leftButton = new JButton("LEFT");
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(0, -1);
            }
        });

        JButton rightButton = new JButton("RIGHT");
        rightButton.addActionListener(new ActionListener() {
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

    private void addExploreButton() {
        JButton exploreButton = new JButton("Back");
        exploreButton.setFocusable(false);
        exploreButton.addActionListener(new ActionListener() {
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

    private void movePlayer(int rowChange, int colChange) {
        int newRow = currentPlayerRow + rowChange;
        int newCol = currentPlayerCol + colChange;

        if (isValidMove(newRow, newCol)) {
            tiles[currentPlayerRow][currentPlayerCol].setBackground(Color.RED);
            currentPlayerRow = newRow;
            currentPlayerCol = newCol;
            tiles[currentPlayerRow][currentPlayerCol].setBackground(Color.GREEN);
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }
}
