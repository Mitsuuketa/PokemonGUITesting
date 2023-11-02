package MyViews;
import javax.swing.*;

import TextGame.Creature;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class ChooseStartersView {
    private JPanel panel;
    private MainView mainView;
    private JComboBox<String> starterComboBox;
    private ArrayList<Creature> creaturesList;
    
    public ChooseStartersView(MainView mainView, ArrayList<Creature> creaturesList) {

        this.creaturesList = creaturesList;
        this.mainView = mainView;
        this.panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Use BorderLayout for the main panel

        setStarterMenuGreetings();
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
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        greetingsPanel.add(greetingsPromptLbl, constraints);

        // Add the greetings panel to the main panel (panel)
        panel.add(greetingsPanel, BorderLayout.NORTH);
    }
 
    
}
