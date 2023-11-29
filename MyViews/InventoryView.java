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

    public JPanel getPanel() {
        return panel;
    }

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

    protected void initializeInventory() {
        String[] columnNames = {"Name", "Type", "Family", "Evolution Level"};
        String[][] data = new String[capturedList.size()][4];

        for (int i = 0; i < capturedList.size(); i++) {                       
            CreatureModel creature = capturedList.get(i);
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
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = creatureComboBox.getSelectedIndex();
        
                if (selectedIndex >= 0 && selectedIndex < capturedList.size()) {
                    CreatureModel selectedCreature = capturedList.get(selectedIndex);
        
                    for(CreatureModel creature : capturedList) {
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
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.switchToPlayMenu();
            }
        });

        bottomPanel.add(exitBtn);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
    }

    protected void refreshInventoryTable() {
        String[] columnNames = {"Name", "Type", "Family", "Evolution Level"};
        String[][] data = new String[capturedList.size()][4];
    
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
 