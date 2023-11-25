package MyViews;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
                    if(selectedCreature.getEvoLvl() == 3) {
                        JOptionPane.showMessageDialog(panel, "Cannot be evolved, maximum evolution level reached.", "Warning", JOptionPane.WARNING_MESSAGE);
                        break;
                    }

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

                                for (int i = 0; i < creaturesList.size(); i++) {
                                    CreatureModel nextLvlCreature = creaturesList.get(i);
                                    
                                    nextLvlCreature.setActive(false);
                                
                                    if (selectedCreature.getFamily() == nextLvlCreature.getFamily() &&
                                        selectedCreature.getEvoLvl() + 1 == nextLvlCreature.getEvoLvl()) {
                                            capturedList.add(nextLvlCreature);
                                            nextLvlCreature.setCaptured(false);
                                            nextLvlCreature.setActive(true);
                                            System.out.println(nextLvlCreature.getID() + " ");  // FOR TESTING
                                            i = creaturesList.size();
                                    }
                                }
                             
                                refreshInventoryTable();
                                return; 
                            }
                        }
            
                        JOptionPane.showMessageDialog(panel, "There is no similar creature that can be evolved!", "Warning", JOptionPane.WARNING_MESSAGE);
                        break; 
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

        panel.add(scrollPane, BorderLayout.CENTER); 
        panel.add(bottomPanel, BorderLayout.SOUTH); 
    }
    @Override
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

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        table.setModel(tableModel);
        tableModel.fireTableDataChanged(); 

        // Update the JComboBox
        creatureComboBox.setModel(new DefaultComboBoxModel<>(capturedList.stream().map(CreatureModel::getName).toArray(String[]::new)));
        creatureComboBox.setSelectedItem(null); 
    }
    

}