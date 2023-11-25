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
        panel.setLayout(new BorderLayout()); // Use BorderLayout for the main panel

        setStarterMenuGreetings();
        selectStarterPokemon();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setStarterMenuGreetings() {
        JPanel greetingsPanel = new JPanel(); 
        greetingsPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel greetingsPromptLbl = new JLabel();
        greetingsPromptLbl.setText("Choose a starter pokemon!");
        // //                                                                               FOR TESTING
        // invmodel.initializeCreatures(creaturesList);
        // // 
        // for(CreatureModel creature : creaturesList) {
        //     if(creature.getEvoLvl() == 1)
        //         System.out.println(creature.getName());
        // }
        // 
        // //  
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        greetingsPanel.add(greetingsPromptLbl, constraints);

        panel.add(greetingsPanel, BorderLayout.NORTH);
    }

    public void selectStarterPokemon() {
        JPanel selectStarterPanel = new JPanel(new BorderLayout());
    
        JLabel starterLabel = new JLabel("Choose a starter Pokémon: ");
        starterComboBox = new JComboBox<>();
    
        for (CreatureModel creature : creaturesList) {
            if (creature.getEvoLvl() == 1) {
                starterComboBox.addItem(creature.getName());
            }
        }
    
        JButton chooseButton = new JButton("Choose");
    
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStarterName = (String) starterComboBox.getSelectedItem();
                if (selectedStarterName != null) {
                    CreatureModel selectedStarterCreature = findStarterCreatureByName(selectedStarterName, creaturesList);
    
                    if (selectedStarterCreature != null) {
                        selectedStarterCreature.setActive(true);
                        selectedStarterCreature.setCaptured(true);
                        capturedList.add(selectedStarterCreature);

                        //                                FOR TESTING
                        CreatureModel extraCreature1 = new CreatureModel("Strawander", "Fire",  "A", 1, 50, false, false, 127);
                        CreatureModel extraCreature2 = new CreatureModel("Pikachu", "Electricity",  "P", 1, 50, false, false, 130);
                        CreatureModel extraCreature3 = new CreatureModel("Strawleon", "Fire",  "A", 2, 150, false, false, 190);
                        extraCreature1.setCaptured(true);
                        extraCreature2.setCaptured(true);

                        //                                FOR TESTING
                        capturedList.add(extraCreature1);
                        capturedList.add(extraCreature2);
                        capturedList.add(extraCreature3);

                        gameController.switchToPlayMenu(); // Switch to the play menu or other relevant view
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
                return creature; 
            }
        }
        return null;
    }
 
    
}
