import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class StartView {
    private JPanel panel;
    private JLabel greetingsLbl;
    private MainView mainView;

    public StartView(MainView mainView) {
        this.mainView = mainView;
        this.panel = new JPanel();
        initializeStartMenuGreeting();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void initializeStartMenuGreeting() {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel greetingsPromptLbl = new JLabel();
        greetingsPromptLbl.setText("Welcome to Pokemon Sweets!~\n");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(greetingsPromptLbl, constraints);

        JButton playButton = new JButton("Play");
        playButton.setFocusable(false);
        playButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.switchToPlayMenu();
            }
            
        });
        constraints.gridy = 2;
        panel.add(playButton, constraints);

        this.greetingsLbl = new JLabel(" ");

        JButton exitButton = new JButton("Exit");
        exitButton.setFocusable(false);
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });
        constraints.gridy = 3;
        panel.add(exitButton, constraints);
    }
}
