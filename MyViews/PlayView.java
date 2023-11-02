package MyViews;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayView {

    private JPanel panel;
    private MainView mainView;

    public PlayView(MainView mainView) {
        this.mainView = mainView;
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
        btn1.setFocusable(false);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchToInventoryMenu();
            }
        });
        constraints.gridy = 2;
        panel.add(btn1, constraints);

        JButton btn2 = new JButton("Explore Area");
        btn2.setFocusable(false);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchtoExploreMenu();
            }
        });
        constraints.gridy = 3;
        panel.add(btn2, constraints);

        JButton btn3 = new JButton("Evolve Creature");
        btn3.setFocusable(false);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchtoEvolveMenu();
            }
        });
        constraints.gridy = 4;
        panel.add(btn3, constraints);

        JButton btn4 = new JButton("Exit");
        btn4.setFocusable(false);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchtoStartMenu();
            }
        });
        constraints.gridy = 5;
        panel.add(btn4, constraints);

    }

}
