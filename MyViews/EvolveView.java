// package MyViews;
// import javax.swing.*;

// import MyController.GameController;
// import MyModel.CreatureModel;

// import java.awt.*;
// import java.awt.event.*;
// import java.util.ArrayList;

// public class EvolveView {

//     private JPanel panel;
//     private JComboBox<String> creatureComboBox;
//     private GameController gameController;
//     private ArrayList<CreatureModel> creatureList;
    
//     public EvolveView(GameController gameController, ArrayList<CreatureModel> creatureList) {
//         this.creatureList = creatureList;
//         this.gameController = gameController;
//         this.panel = new JPanel();
//         panel.setLayout(new BorderLayout()); // Use BorderLayout for the main panel
//         initializeEvolveMenuGreetings();
//         initializeInventory();
//     }

//     public JPanel getPanel() {
//         return panel;
//     }

//     private void initializeEvolveMenuGreetings() {
//         JPanel greetingsPanel = new JPanel(); // Create a separate panel for greetings
//         greetingsPanel.setLayout(new GridBagLayout());

//         GridBagConstraints constraints = new GridBagConstraints();
//         constraints.fill = GridBagConstraints.HORIZONTAL;
//         constraints.insets = new Insets(5, 5, 5, 5);

//         JLabel greetingsPromptLbl = new JLabel("Welcome to the Evolution Center!");
//         constraints.gridx = 0;
//         constraints.gridy = 0;
//         constraints.gridwidth = 2;
//         greetingsPanel.add(greetingsPromptLbl, constraints);

//         // Add the greetings panel to the main panel (panel)
//         panel.add(greetingsPanel, BorderLayout.NORTH);
//     }

//     private void initializeInventory() {
//         // Hard-coded Pokémon data
//         String[] pokemonNames = {"Pikachu", "Bulbasaur", "Charmander"};
//         String[] columnNames = {"Name", "Type", "Family", "Evolution Level"};
//         String[][] data = {
//             {"Pikachu", "Electric", "Mouse", "1"},
//             {"Bulbasaur", "Grass/Poison", "Seed", "1"},
//             {"Charmander", "Fire", "Lizard", "1"}
//         };

//         JTable table = new JTable(data, columnNames);
//         JScrollPane scrollPane = new JScrollPane(table);


//         creatureComboBox = new JComboBox<>(pokemonNames);

//         JPanel bottomPanel = new JPanel(new GridLayout(2,1));
//         JLabel activePkmnLbl = new JLabel("Select a Pokemon to Evolve: ");
//         bottomPanel.add(activePkmnLbl);
//         bottomPanel.add(creatureComboBox);

//         // Change the active creature when selecting a different one
//         creatureComboBox.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 /*String selectedPokemon = (String) */ creatureComboBox.getSelectedItem();
//                 // Update the active creature or perform other actions as needed
//             }
//         });

//         JButton exitBtn = new JButton("Exit");
//         exitBtn.setFocusable(false);
//         exitBtn.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 gameController.switchToPlayMenu();
//             }
//         });

//         bottomPanel.add(exitBtn);

//         panel.add(scrollPane, BorderLayout.CENTER); // Add the JTable in the center
//         panel.add(bottomPanel, BorderLayout.SOUTH); // Add the combo box and exit button at the bottom
        
//     }


// }

package MyViews;
import javax.swing.*;

import MyController.GameController;
import MyModel.CreatureModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EvolveView extends InventoryView{
    protected ArrayList<CreatureModel> creaturesList;

    public EvolveView(GameController gameController, ArrayList<CreatureModel> capturedList, ArrayList<CreatureModel> creaturesList) {
        super(gameController, capturedList);
        this.creaturesList = creaturesList;
    }

    
    
    @Override
    protected void initializeInventory() {
        String[] columnNames = {"Name", "Type", "Family", "Evolution Level"};
        String[][] data = new String[capturedList.size()][4];

        for (int i = 0; i < capturedList.size(); i++) {                       
            CreatureModel creature = capturedList.get(i);
            // Check if the creature is active and add an asterisk if it is
            if(creature.getisActive()) {
                String name = creature.getName() + (creature.getisActive() ? "*" : "");
                data[i][0] = name;
                data[i][1] = creature.getType();
                data[i][2] = creature.getFamily();
                data[i][3] = String.valueOf(creature.getEvoLvl());
            }
            else {
                String name = creature.getName();
                data[i][0] = name;
                data[i][1] = creature.getType();
                data[i][2] = creature.getFamily();
                data[i][3] = String.valueOf(creature.getEvoLvl());
            }
            
        }

        table = new JTable(data, columnNames);
        
        JScrollPane scrollPane = new JScrollPane(table);

        creatureComboBox = new JComboBox<>(capturedList.stream().map(CreatureModel::getName).toArray(String[]::new));

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        JLabel activePkmnLbl = new JLabel();
        activePkmnLbl.setText("Choose Pokemon to Evolve: ");
        bottomPanel.add(activePkmnLbl);
        bottomPanel.add(creatureComboBox);

        JButton evolveButton = new JButton("Evolve"); 
        evolveButton.setFocusable(false);
        evolveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCreatureName = (String) creatureComboBox.getSelectedItem();
            
                for (CreatureModel selectedCreature : capturedList) {
                    if (selectedCreature.getName().equalsIgnoreCase(selectedCreatureName)) {
                        for (CreatureModel creature : capturedList) {
                            if (creature.getName().equalsIgnoreCase(selectedCreatureName) && creature.getID() != selectedCreature.getID()) {
                                System.out.println("Selected: " + selectedCreature.getName() + " " + selectedCreature.getID() + "\n" +
                                        "Creature: " + creature.getName() + " " + creature.getID());

                                capturedList.remove(creature);
                                creature.setActive(false);
                                creature.setCaptured(false);

                                capturedList.remove(selectedCreature);
                                selectedCreature.setActive(false);
                                selectedCreature.setCaptured(false);

                                for(CreatureModel nextLvlCreature : creaturesList) {
                                    nextLvlCreature.setActive(false);

                                    if(selectedCreature.getFamily() == nextLvlCreature.getFamily() &&
                                        selectedCreature.getEvoLvl() + 1 == nextLvlCreature.getEvoLvl()) {
                                            capturedList.add(nextLvlCreature);
                                            nextLvlCreature.setCaptured(false);
                                            nextLvlCreature.setActive(true);
                                        }
                                }
                                refreshInventoryTable();
                                return; 
                            }
                        }
            
                        // If no similar creature is found
                        JOptionPane.showMessageDialog(panel, "There is no similar creature that can be evolved!", "Warning", JOptionPane.WARNING_MESSAGE);
                        break; // Break the loop after handling the selected creature
                    }
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
        bottomPanel.add(evolveButton);

        panel.add(scrollPane, BorderLayout.CENTER); // Add the JTable in the center
        panel.add(bottomPanel, BorderLayout.SOUTH); // Add the combo box and exit button at the bottom
    }
    

}