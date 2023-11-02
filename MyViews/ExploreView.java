package MyViews;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExploreView {

    private JPanel panel;
    private MainView mainView;
    
    public ExploreView(MainView mainView) {
        this.mainView = mainView;
        this.panel = new JPanel();
        initializeExploreMenuGreetings();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void initializeExploreMenuGreetings() {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel greetingsPromptLbl = new JLabel();
        greetingsPromptLbl.setText("Choose an area to explore ~");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(greetingsPromptLbl, constraints);

        JButton btn1 = new JButton("Area 1");
        btn1.setFocusable(false);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel greetingsLbl = new JLabel();
                greetingsLbl.setText("Area 1");
            }
        });
        constraints.gridy = 2;
        panel.add(btn1, constraints);

        JButton btn2 = new JButton("Area 2");
        btn2.setFocusable(false);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel greetingsLbl = new JLabel();
                greetingsLbl.setText("Area 2");
            }
        });
        constraints.gridy = 3;
        panel.add(btn2, constraints);

        JButton btn3 = new JButton("Area 3");
        btn3.setFocusable(false);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel greetingsLbl = new JLabel();
                greetingsLbl.setText("Area 3");
            }
        });
        constraints.gridy = 4;
        panel.add(btn3, constraints);

        JButton btn4 = new JButton("Exit");
        btn4.setFocusable(false);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchToPlayMenu();
            }
        });
        constraints.gridy = 5;
        panel.add(btn4, constraints);
    }
}
