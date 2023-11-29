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
    private ArrayList<CreatureModel> capturedList;
    
    public ChooseStartersView(GameController gameController, ArrayList<CreatureModel> creaturesList, ArrayList<CreatureModel> capturedList) {
        this.creaturesList = creaturesList;
        this.capturedList = capturedList;
        this.gameController = gameController;
        this.panel = new JPanel();
        panel.setLayout(new BorderLayout()); 

        setStarterMenuGreetings();
        selectStarterPokemon();
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
    * Sets the greetings menu to be used for starter pokemon. This is a panel that allows the user to select a starter
    */
    public void setStarterMenuGreetings() {
        JPanel greetingsPanel = new JPanel(); 
        greetingsPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel greetingsPromptLbl = new JLabel();
        greetingsPromptLbl.setText("Choose a starter pokemon!");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        greetingsPanel.add(greetingsPromptLbl, constraints);

        panel.add(greetingsPanel, BorderLayout.NORTH);
    }

    /**
    * Select pokemon to play from the combobox and add it to capturedList when the user chooses a
    */
    public void selectStarterPokemon() {
        JPanel selectStarterPanel = new JPanel(new BorderLayout());
    
        JLabel starterLabel = new JLabel("Choose a starter Pokémon: ");
        starterComboBox = new JComboBox<>();
    
        for (CreatureModel creature : creaturesList) {
            // Add the creature to the starter combo box
            if (creature.getEvoLvl() == 1) {
                starterComboBox.addItem(creature.getName());
            }
        }
    
        JButton chooseButton = new JButton("Choose");
    
        chooseButton.addActionListener(new ActionListener() {
            /**
            * Called when an action occurs. This is where the user can select a creature to play. If the selected name is in the combo box it is added to the list of creatures that are captured and the game controller switches to play menu
            * 
            * @param e - The ActionEvent that caused this
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStarterName = (String) starterComboBox.getSelectedItem();
                // This method is called by the game controller to switch to play menu
                if (selectedStarterName != null) {
                    CreatureModel selectedStarterCreature = findStarterCreatureByName(selectedStarterName, creaturesList);
    
                    // This method is called when the player is playing the game.
                    if (selectedStarterCreature != null) {
                        selectedStarterCreature.setActive(true);
                        selectedStarterCreature.setCaptured(true);
                        capturedList.add(selectedStarterCreature);
                      
                        gameController.switchToPlayMenu();
                    }
                }
            }
        });
    
        JPanel comboBoxButtonPanel = new JPanel();
        comboBoxButtonPanel.add(starterComboBox);
        comboBoxButtonPanel.add(chooseButton);
    
        selectStarterPanel.add(starterLabel, BorderLayout.NORTH);
        selectStarterPanel.add(comboBoxButtonPanel, BorderLayout.CENTER);
    
        panel.add(selectStarterPanel, BorderLayout.CENTER);
    
        JButton btn4 = new JButton("Exit");
        btn4.setFocusable(false);
        btn4.addActionListener(new ActionListener() {
            /**
            * Called when the user presses the start button. This is the method that will be called when the user presses the switch to the start menu
            * 
            * @param e - The ActionEvent that caused this
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.switchtoStartMenu();
            }
        });
    
        panel.add(btn4, BorderLayout.SOUTH);
    }

    /**
    * Finds creature by name in list of creatures. It does not check if it is valid or not
    * 
    * @param name - Name of the creature to find
    * @param creaturesList - List of creatures to search in
    * 
    * @return CreatureModel or null if not found or not enough creatures to find the creature with the
    */
    public CreatureModel findStarterCreatureByName(String name, ArrayList<CreatureModel> creaturesList) {
        for (CreatureModel creature : creaturesList) {
            // The creature that is the creature of the creature.
            if (creature.getName().equals(name) && creature.getEvoLvl() == 1) {
                return creature; 
            }
        }
        return null;
    }
}
