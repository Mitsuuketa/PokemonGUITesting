package MyViews;

import javax.swing.*;

import MyController.GameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleView {

    private JPanel panel;
    private JPanel enemyTextPanel;
    private JPanel playerTextPanel;
    private JPanel enemyImagePanel;
    private JPanel playerImagePanel;
    private GameController gameController;

    public BattleView(GameController gameController) {
        initializeBattleView();
        this.gameController = gameController;
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
    * Initializes battle view. This is called from initialize () and should not be called directly by the user
    */
    private void initializeBattleView() {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        createEnemyTextPanel();
        createPlayerTextPanel();

        JPanel imagePanelsContainer = new JPanel(new GridBagLayout());

        createEnemyImagePanel(imagePanelsContainer);
        createPlayerImagePanel(imagePanelsContainer);

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
        panel.add(exploreButton, BorderLayout.SOUTH);

        panel.add(imagePanelsContainer, BorderLayout.CENTER);
    }

    /**
    * Creates and adds the player image panel to the given container. This is used to display the player's image when the player clicks on the button
    * 
    * @param container - Panel to add the
    */
    private void createPlayerImagePanel(JPanel container) {
        playerImagePanel = new JPanel();
        playerImagePanel.setBackground(Color.GREEN); // Set to actual image later
        playerImagePanel.setPreferredSize(new Dimension(200, 200)); // Set preferred size

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 0, 20); // Add spacing to the right
        container.add(playerImagePanel, constraints);
    }

    /**
    * Creates enemy image panel. This panel is used to display the image of the enemy
    * 
    * @param container - Panel to add panel
    */
    private void createEnemyImagePanel(JPanel container) {
        enemyImagePanel = new JPanel();
        enemyImagePanel.setBackground(Color.GREEN); // Set to actual image later
        enemyImagePanel.setPreferredSize(new Dimension(200, 200)); // Set preferred size

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        container.add(enemyImagePanel, constraints);
    }

    /**
    * Creates the panel that displays the player's creature. Used to add text to the panel when there is no creature
    */
    private void createPlayerTextPanel() {
        playerTextPanel = new JPanel();
        playerTextPanel.setBackground(Color.BLACK);
        JLabel playerLabel = new JLabel("Player's Creature");
        playerLabel.setForeground(Color.WHITE);
        playerTextPanel.add(playerLabel);

        panel.add(playerTextPanel, BorderLayout.SOUTH);
    }

    /**
    * Creates the enemy text panel and adds it to the panel. This is used to display the creature name
    */
    private void createEnemyTextPanel() {
        enemyTextPanel = new JPanel();
        enemyTextPanel.setBackground(Color.BLACK);
        JLabel enemyLabel = new JLabel("Enemy Creature");
        enemyLabel.setForeground(Color.WHITE);
        enemyTextPanel.add(enemyLabel);

        panel.add(enemyTextPanel, BorderLayout.NORTH);
    }
}
