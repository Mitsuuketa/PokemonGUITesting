package MyViews;
import javax.swing.*;

import MyController.GameController;
import MyModel.CreatureModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayView {

    private JPanel panel;
    private GameController gameController;
    private ArrayList<CreatureModel> capturedList;

    public PlayView(GameController gameController, ArrayList<CreatureModel> capturedList) {
        this.capturedList = capturedList;
        this.gameController = gameController;
        this.panel = new JPanel();
        initializePlayMenuGreetings();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void initializePlayMenuGreetings() {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel greetingsPromptLbl = new JLabel();
        greetingsPromptLbl.setText("Welcome to Sweet Land ~");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(greetingsPromptLbl, constraints);

        JLabel subLabel = new JLabel("What would you like to do?");
        constraints.gridy = 1;
        panel.add(subLabel, constraints);

        JButton btn1 = new JButton("View Inventory");
        btn1.setFocusable(false);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.switchToInventoryMenu();
            }
        });
        constraints.gridy = 2;
        panel.add(btn1, constraints);

        JButton btn2 = new JButton("Explore Area");
        btn2.setFocusable(false);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.switchtoExploreMenu();
            }
        });
        constraints.gridy = 3;
        panel.add(btn2, constraints);

        JButton btn3 = new JButton("Evolve Creature");
        btn3.setFocusable(false);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.switchtoEvolveMenu();
            }
        });
        constraints.gridy = 4;
        panel.add(btn3, constraints);

        JButton btn4 = new JButton("Exit");
        btn4.setFocusable(false);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(panel, "Are you sure?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    ArrayList<CreatureModel> creaturesToRemove = new ArrayList<>();
                    for (CreatureModel creature : capturedList) {
                        creature.setActive(false);
                        creature.setCaptured(false);
                        creaturesToRemove.add(creature);
                    }
                    capturedList.removeAll(creaturesToRemove);
                    gameController.switchtoStartMenu();
                }
            }
        });
        constraints.gridy = 5;
        panel.add(btn4, constraints);

    }

}
