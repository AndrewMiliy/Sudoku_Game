import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

    private JButton startButton;
    private JButton exitButton;
    private JFrame parentFrame;

    public MainMenu(JFrame frame) {
        this.parentFrame = frame;
        setLayout(new GridLayout(2, 1));
        setPreferredSize(new Dimension(300, 300));
        setLayout(new GridBagLayout());
        startButton = new JButton("Start Game");
        exitButton = new JButton("Exit");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new DifficultyMenu(parentFrame));
                parentFrame.pack();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(startButton);
        add(exitButton);
    }
}
