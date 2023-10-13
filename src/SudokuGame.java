import javax.swing.*;

public class SudokuGame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sudoku Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 1000);
            frame.setContentPane(new MainMenu(frame));
            frame.setLocationRelativeTo(null);  // Расположение по центру экрана
            frame.setVisible(true);
        });
    }
}