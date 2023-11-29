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

    public JPanel getPanel() {
        return panel;
    }

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
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.switchtoExploreMenu();
            }
        });
        panel.add(exploreButton, BorderLayout.SOUTH);

        panel.add(imagePanelsContainer, BorderLayout.CENTER);
    }

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

    private void createEnemyImagePanel(JPanel container) {
        enemyImagePanel = new JPanel();
        enemyImagePanel.setBackground(Color.GREEN); // Set to actual image later
        enemyImagePanel.setPreferredSize(new Dimension(200, 200)); // Set preferred size

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        container.add(enemyImagePanel, constraints);
    }

    private void createPlayerTextPanel() {
        playerTextPanel = new JPanel();
        playerTextPanel.setBackground(Color.BLACK);
        JLabel playerLabel = new JLabel("Player's Creature");
        playerLabel.setForeground(Color.WHITE);
        playerTextPanel.add(playerLabel);

        panel.add(playerTextPanel, BorderLayout.SOUTH);
    }

    private void createEnemyTextPanel() {
        enemyTextPanel = new JPanel();
        enemyTextPanel.setBackground(Color.BLACK);
        JLabel enemyLabel = new JLabel("Enemy Creature");
        enemyLabel.setForeground(Color.WHITE);
        enemyTextPanel.add(enemyLabel);

        panel.add(enemyTextPanel, BorderLayout.NORTH);
    }
}
