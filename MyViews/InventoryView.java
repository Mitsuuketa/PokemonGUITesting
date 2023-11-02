package MyViews;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InventoryView {
    private JPanel panel;
    private JComboBox<String> creatureComboBox;
    private MainView mainView;
    
    public InventoryView(MainView mainView) {
        this.mainView = mainView;
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

        // Add the greetings panel to the main panel (panel)
        panel.add(greetingsPanel, BorderLayout.NORTH);
    }

    private void initializeInventory() {
        // Hard-coded Pokémon data
        String[] pokemonNames = {"Pikachu", "Bulbasaur", "Charmander"};

        String[] columnNames = {"Name", "Type", "Family", "Evolution Level"};
        String[][] data = {
            {"Pikachu", "Electric", "Mouse", "1"},
            {"Bulbasaur", "Grass/Poison", "Seed", "1"},
            {"Charmander", "Fire", "Lizard", "1"}
        };

        JTable table = new JTable(data, columnNames);
        // table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        // panel.setLayout(new BorderLayout());
        // panel.add(scrollPane, BorderLayout.CENTER);

        creatureComboBox = new JComboBox<>(pokemonNames);

        JPanel bottomPanel = new JPanel(new GridLayout(2,1));
        JLabel activePkmnLbl = new JLabel();
        activePkmnLbl.setText("Active Pokemon: ");
        bottomPanel.add(activePkmnLbl);
        bottomPanel.add(creatureComboBox);

        // Change the active creature when selecting a different one
        creatureComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*String selectedPokemon = (String) */ creatureComboBox.getSelectedItem();
                // Update the active creature or perform other actions as needed
            }
        });

        JButton exitBtn = new JButton("Exit");
        exitBtn.setFocusable(false);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchToPlayMenu();
            }
        });

        bottomPanel.add(exitBtn);

        panel.add(scrollPane, BorderLayout.CENTER); // Add the JTable in the center
        panel.add(bottomPanel, BorderLayout.SOUTH); // Add the combo box and exit button at the bottom
        
    }
}
