package MyViews;
import javax.swing.*;

import MyController.GameController;
import MyModel.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChooseStartersView {
    private JPanel panel;
    private GameController gameController;
    private JComboBox<String> starterComboBox;
    private ArrayList<CreatureModel> creaturesList;
    
    public ChooseStartersView(GameController gameController, ArrayList<CreatureModel> creaturesList) {
        this.creaturesList = creaturesList;
        this.gameController = gameController;
        this.panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Use BorderLayout for the main panel

        setStarterMenuGreetings();
        selectStarterPokemon();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setStarterMenuGreetings() {
        JPanel greetingsPanel = new JPanel(); // Create a separate panel for greetings
        greetingsPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel greetingsPromptLbl = new JLabel();
        greetingsPromptLbl.setText("Choose a starter pokemon!");
        // // FOR TESTING
        // invmodel.initializeCreatures(creaturesList);
        // // 
        for(CreatureModel creature : creaturesList) {
            if(creature.getEvoLvl() == 1)
                System.out.println(creature.getName());
        }
        // 
        // //  
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        greetingsPanel.add(greetingsPromptLbl, constraints);

        // Add the greetings panel to the main panel (panel)
        panel.add(greetingsPanel, BorderLayout.NORTH);
    }

    public void selectStarterPokemon() {
        JPanel selectStarterPanel = new JPanel(new BorderLayout());
    
        // Create a label and a combo box to display the list of starter Pokémon
        JLabel starterLabel = new JLabel("Choose a starter Pokémon: ");
        starterComboBox = new JComboBox<>();
    
        // Populate the combo box with starter Pokémon names
        for (CreatureModel creature : creaturesList) {
            if (creature.getEvoLvl() == 1) {
                starterComboBox.addItem(creature.getName());
            }
        }
    
        // Create a button to confirm the selection
        JButton chooseButton = new JButton("Choose");
    
        // Add an action listener to the button
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStarterName = (String) starterComboBox.getSelectedItem();
                if (selectedStarterName != null) {
                    // Find the selected starter creature by name
                    CreatureModel selectedStarterCreature = findStarterCreatureByName(selectedStarterName, creaturesList);
    
                    if (selectedStarterCreature != null) {
                        // Initialize the selected starter creature
                        selectedStarterCreature.setActive(true);
                        selectedStarterCreature.setCaptured(true);
                        
                        // Perform any additional actions needed
                        gameController.switchToPlayMenu(); // Switch to the play menu or other relevant view
                    }
                }
            }
        });
    
        // Create a JPanel for the combo box and button
        JPanel comboBoxButtonPanel = new JPanel();
        comboBoxButtonPanel.add(starterComboBox);
        comboBoxButtonPanel.add(chooseButton);
    
        // Add components to the selectStarterPanel
        selectStarterPanel.add(starterLabel, BorderLayout.NORTH);
        selectStarterPanel.add(comboBoxButtonPanel, BorderLayout.CENTER);
    
        // Add the selectStarterPanel to the main panel
        panel.add(selectStarterPanel, BorderLayout.CENTER);
    
        JButton btn4 = new JButton("Exit");
        btn4.setFocusable(false);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.switchtoStartMenu();
            }
        });
    
        panel.add(btn4, BorderLayout.SOUTH);
    }

    public CreatureModel findStarterCreatureByName(String name, ArrayList<CreatureModel> creaturesList) {
        for (CreatureModel creature : creaturesList) {
            if (creature.getName().equals(name) && creature.getEvoLvl() == 1) {
                return creature; // Found a matching starter creature
            }
        }
        return null; // Starter creature with the specified name not found
    }
 
    
}
