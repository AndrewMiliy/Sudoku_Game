import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyMenu extends JPanel {

    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;

    public DifficultyMenu(JFrame frame) {
        //setLayout(new GridLayout(3, 1));
        setSize(300, 300);
        setLayout(new GridBagLayout());
        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");

        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(Difficulty.EASY, frame);
            }
        });

        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(Difficulty.MEDIUM, frame);
            }
        });

        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(Difficulty.HARD, frame);
            }
        });

        add(easyButton);
        add(mediumButton);
        add(hardButton);
    }

    private void startGame(Difficulty difficulty, JFrame frame) {
        SudokuGenerator generator = new SudokuGenerator();
        int[][] board = generator.generate(difficulty);
        frame.setContentPane(new SudokuGameScreen(board, frame));
        frame.pack();
    }
}
