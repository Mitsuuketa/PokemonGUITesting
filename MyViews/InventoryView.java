package MyViews;
import javax.swing.*;

import MyModel.*;
import MyController.GameController;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InventoryView {
    private JPanel panel;
    private JComboBox<String> creatureComboBox;
    private GameController gameController;
    private ArrayList<CreatureModel> creaturesList;
    
    public InventoryView(GameController gameController) {
        this.creaturesList = new ArrayList<>();
        this.gameController = gameController;
        this.panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Use BorderLayout for the main panel
        initializeInvMenuGreetings();
        initializeInventory();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void initializeInvMenuGreetings() {
        JPanel greetingsPanel = new JPanel(); // Create a separate panel for greetings
        greetingsPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel greetingsPromptLbl = new JLabel();
        greetingsPromptLbl.setText("List of your Captured Pokemons!");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        greetingsPanel.add(greetingsPromptLbl, constraints);

        for(CreatureModel creature : creaturesList) {                                            // TESTING PURPOSES
            if(creature.getisCaptured())
                System.out.println(creature.getName());
        }

        // Add the greetings panel to the main panel (panel)
        panel.add(greetingsPanel, BorderLayout.NORTH);
    }

    private void initializeInventory() {
        // Initialize your captured creatures list from the InventoryModel
        ArrayList<CreatureModel> capturedList = new ArrayList<>();
        for(CreatureModel creature : capturedList) {
            if(creature.getisCaptured())
                capturedList.add(creature);
        }
        

        String[] columnNames = {"Name", "Type", "Family", "Evolution Level"};
        String[][] data = new String[capturedList.size()][4];

        for (int i = 0; i < capturedList.size(); i++) {                       
            CreatureModel creature = capturedList.get(i);
            // Check if the creature is active and add an asterisk if it is
            String name = creature.getName() + (creature.getisActive() ? "*" : "");
            data[i][0] = name;
            data[i][1] = creature.getType();
            data[i][2] = creature.getFamily();
            data[i][3] = String.valueOf(creature.getEvoLvl());
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        creatureComboBox = new JComboBox<>(capturedList.stream().map(CreatureModel::getName).toArray(String[]::new));

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        JLabel activePkmnLbl = new JLabel();
        activePkmnLbl.setText("Active Pokemon: ");
        bottomPanel.add(activePkmnLbl);
        bottomPanel.add(creatureComboBox);

        // Change the active creature when selecting a different one
        creatureComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPokemon = (String) creatureComboBox.getSelectedItem();
                // Update the active creature or perform other actions as needed
                for(CreatureModel creature : capturedList) {
                    if(creature.getName().equalsIgnoreCase(selectedPokemon))
                        creature.setActive(true);
                }
            }
        });

        JButton exitBtn = new JButton("Exit");
        exitBtn.setFocusable(false);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.switchToPlayMenu();
            }
        });

        bottomPanel.add(exitBtn);

        panel.add(scrollPane, BorderLayout.CENTER); // Add the JTable in the center
        panel.add(bottomPanel, BorderLayout.SOUTH); // Add the combo box and exit button at the bottom
    }   
}
