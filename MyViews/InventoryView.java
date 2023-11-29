package MyViews;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import MyModel.*;
import MyController.GameController;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InventoryView {
    protected JPanel panel;
    protected JTable table;
    protected JComboBox<String> creatureComboBox;
    protected GameController gameController;
    protected ArrayList<CreatureModel> capturedList;
    
    public InventoryView(GameController gameController, ArrayList<CreatureModel> capturedList) {
        this.capturedList = capturedList;
        this.gameController = gameController;
        this.panel = new JPanel();
        this.table = new JTable();
        panel.setLayout(new BorderLayout()); 
        initializeInvMenuGreetings();
        initializeInventory();
    }

/**
* Returns the panel that this panel is associated with or null if there is none for the panel type such as a menu.
* 
* 
* @return the panel that this panel is associated with or null if there is none for the panel type such as a
*/
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
    * Initializes invMenu greetings. Displays list of captured pokemon in list of creatures
    */
    /**
    * Initializes invMenu greetings. Displays list of captured pokemon in list of creatures
    */
    private void initializeInvMenuGreetings() {
        JPanel greetingsPanel = new JPanel(); 
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

        // for(CreatureModel creature : creaturesList) {                                            // TESTING PURPOSES
        //     if(creature.getisCaptured() == true)
        //         System.out.println(creature.getName());
        // }

        panel.add(greetingsPanel, BorderLayout.NORTH);
    }

    /**
    * Initializes creature inventory with data from captured list. Used to display inventory in GUI and for debugging purposes
    */
    /**
    * Initializes creature inventory. This method is used to create the data and set it to the database. If you don't want to change this method be sure to call super. initializeInventory
    */
    protected void initializeInventory() {
        String[] columnNames = {"Name", "Type", "Family", "Evolution Level"};
        String[][] data = new String[capturedList.size()][4];

        // This method is used to create the creature data.
        for (int i = 0; i < capturedList.size(); i++) {                       
            CreatureModel creature = capturedList.get(i);
            // getisActive returns the creature s name family and employee level
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
        activePkmnLbl.setText("Active Pokemon: ");
        bottomPanel.add(activePkmnLbl);
        bottomPanel.add(creatureComboBox);

        creatureComboBox.addActionListener(new ActionListener() {
            /**
            * Called when the user presses the button. This is the place where we set the creature's active or deactivates if they are in the list
            * 
            * @param e - The ActionEvent that caused this
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = creatureComboBox.getSelectedIndex();
        
                // This method is called when the selected creature is selected.
                if (selectedIndex >= 0 && selectedIndex < capturedList.size()) {
                    CreatureModel selectedCreature = capturedList.get(selectedIndex);
        
                    for(CreatureModel creature : capturedList) {
                        // This method is used to set the active state of the creature.
                        if(creature.getName().equalsIgnoreCase(selectedCreature.getName()) && creature.getID() == selectedCreature.getID()) {
                            for(CreatureModel creatures : capturedList)
                                System.out.println(creatures.getName() + " " + creatures.getID() + " " + selectedIndex);
                            System.out.println(creature.getID() + " C S " + selectedCreature.getID());
                            creature.setActive(true);
                        }
                        else   
                            creature.setActive(false);
                    }
                    refreshInventoryTable();
                }
            }
        });

        JButton exitBtn = new JButton("Exit");
        exitBtn.setFocusable(false);
        exitBtn.addActionListener(new ActionListener() {
            /**
            * Called when the user presses the button. Switches to the play menu. This is the method that should be called by the user.
            * 
            * @param e - The ActionEvent that caused this method to be called
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.switchToPlayMenu();
            }
        });

        bottomPanel.add(exitBtn);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
    * Refresh creature inventory table. This is called when the user clicks the " Refresh Inventory " button in the inventory
    */
    protected void refreshInventoryTable() {
        String[] columnNames = {"Name", "Type", "Family", "Evolution Level"};
        String[][] data = new String[capturedList.size()][4];
    
        // This method is used to create the data array for each creature in the captured list.
        for (int i = 0; i < capturedList.size(); i++) {
            CreatureModel creature = capturedList.get(i);
            String name = creature.getName() + (creature.getisActive() ? " *" : "");
            data[i][0] = name;
            data[i][1] = creature.getType();
            data[i][2] = creature.getFamily();
            data[i][3] = String.valueOf(creature.getEvoLvl());
        }
    
        DefaultTableModel newModel = new DefaultTableModel(data, columnNames);
        table.setModel(newModel);
        newModel.fireTableDataChanged(); 
    }
}
 