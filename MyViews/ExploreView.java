package MyViews;
import javax.swing.*;

import MyController.GameController;

import java.awt.*;
import java.awt.event.*;

public class ExploreView {

    private JPanel panel;
    private GameController gameController;
    
    public ExploreView(GameController gameController) {
        this.gameController = gameController;
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
                AreaView area1 = new AreaView(5, 1, gameController);
                panel.removeAll();
                panel.add(area1.getPanel());
                panel.revalidate();
                panel.repaint();
            }
        });
        constraints.gridy = 2;
        panel.add(btn1, constraints);

        JButton btn2 = new JButton("Area 2");
        btn2.setFocusable(false);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AreaView area2 = new AreaView(3, 3, gameController);
                panel.removeAll();
                panel.add(area2.getPanel());
                panel.revalidate();
                panel.repaint();
            }
        });
        constraints.gridy = 3;
        panel.add(btn2, constraints);

        JButton btn3 = new JButton("Area 3");
        btn3.setFocusable(false);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AreaView area3 = new AreaView(4, 4, gameController);
                panel.removeAll();
                panel.add(area3.getPanel());
                panel.revalidate();
                panel.repaint(); 
            }
        });
        constraints.gridy = 4;
        panel.add(btn3, constraints);

        JButton btn4 = new JButton("Exit");
        btn4.setFocusable(false);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.switchToPlayMenu();
            }
        });
        constraints.gridy = 5;
        panel.add(btn4, constraints);
    }
   
}
