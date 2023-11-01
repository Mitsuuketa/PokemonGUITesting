import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayView {

    private JPanel panel;
    
    public PlayView() {
        this.panel = new JPanel();
        initializePlayMenuGreetings();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void initializePlayMenuGreetings() {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel greetingsPromptLbl = new JLabel();
        greetingsPromptLbl.setText("Welcome to Sweet Land ~");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(greetingsPromptLbl, constraints);

        JLabel subLabel = new JLabel("What would you like to do?");
        constraints.gridy = 1;
        panel.add(subLabel, constraints);

        JButton btn1 = new JButton("View Inventory");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel greetingsLbl = new JLabel();
                greetingsLbl.setText("View Inventory");
            }
        });
        constraints.gridy = 2;
        panel.add(btn1, constraints);


       JButton btn2 = new JButton("Explore Area");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel greetingsLbl = new JLabel();
                greetingsLbl.setText("Explore");
            }
        });
        constraints.gridy = 3;
        panel.add(btn2, constraints);

        JButton btn3 = new JButton("Evolve Creature");
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel greetingsLbl = new JLabel();
                greetingsLbl.setText("Evolve Creature");
            }
        });
        constraints.gridy = 4;
        panel.add(btn3, constraints);

        JButton btn4 = new JButton("Exit");
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel greetingsLbl = new JLabel();
                greetingsLbl.setText("Exit");
            }
        });
        constraints.gridy = 5;
        panel.add(btn4, constraints);

    }


 }
