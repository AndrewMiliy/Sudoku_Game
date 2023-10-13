import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class SudokuGameScreen extends JPanel {

    private SudokuGrid grid;
    private JButton checkSolutionButton;
    private JButton backButton;
    private JFrame parentFrame;
    private JLabel timerLabel;
    private Timer timer;
    private long startTime;
    private JButton resetButton;

    private Difficulty currentDifficulty;
    private JButton newGameButton;

    public SudokuGameScreen(int[][] board, JFrame frame) {
        this.parentFrame = frame;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 500));

        grid = new SudokuGrid(board);
        checkSolutionButton = new JButton("Check Solution");

        checkSolutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkSolution();
            }
        });

        add(grid, BorderLayout.CENTER);
        add(checkSolutionButton, BorderLayout.SOUTH);

        backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new MainMenu(frame));
                frame.pack();
            }
        });
        JPanel southPanel = new JPanel(new GridLayout(2, 1));
        southPanel.add(checkSolutionButton);
        southPanel.add(backButton);
        add(southPanel, BorderLayout.SOUTH);

        this.startTime = System.currentTimeMillis();
        this.timerLabel = new JLabel("Time: 00:00");
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime) - TimeUnit.MINUTES.toSeconds(minutes);
                timerLabel.setText(String.format("Time: %02d:%02d", minutes, seconds));
            }
        });
        timer.start();
        southPanel.add(timerLabel);

        this.resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new SudokuGameScreen(board, frame));
                frame.pack();
            }
        });
        southPanel.add(resetButton);
    }

    private void checkSolution() {
        grid.highlightErrors();
        int[][] userBoard = grid.getBoard();
        if (SudokuChecker.isValid(userBoard)) {
            JOptionPane.showMessageDialog(parentFrame, "Congratulations! You solved the Sudoku!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(parentFrame, "The solution is incorrect. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
