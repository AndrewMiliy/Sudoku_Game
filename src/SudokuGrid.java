import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SudokuGrid extends JPanel {

    private JTextField[][] fields;
    private int cellSize = 50;  // предположим, что размер клетки равен 50 пикселям

    public SudokuGrid(int[][] board) {
        fields = new JTextField[9][9];
        setLayout(new GridLayout(9, 9));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField field = new JTextField();

                if (board[row][col] != 0) {
                    field.setText(String.valueOf(board[row][col]));
                    field.setEditable(false);
                    field.setBackground(Color.LIGHT_GRAY);
                }

                field.setHorizontalAlignment(JTextField.CENTER);
                float fontSize = cellSize / 2.0f;
                field.setFont(field.getFont().deriveFont(fontSize));
                field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                // Добавление слушателя фокуса для сброса выделения текста при переходе на другое поле
                field.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        SwingUtilities.invokeLater(() -> {
                            field.selectAll();
                        });
                    }
                });

                fields[row][col] = field;
                add(field);
            }
        }
    }

    public int[][] getBoard() {
        int[][] board = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                try {
                    board[row][col] = Integer.parseInt(fields[row][col].getText());
                } catch (NumberFormatException e) {
                    board[row][col] = 0;
                }
            }
        }
        return board;
    }

    public void highlightErrors() {
        int[][] currentBoard = getBoard();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (currentBoard[row][col] != 0 && !SudokuChecker.isValidMove(currentBoard, row, col, currentBoard[row][col])) {
                    fields[row][col].setBackground(Color.RED);
                } else if (fields[row][col].isEditable()) {
                    fields[row][col].setBackground(Color.WHITE);
                }
            }
        }
    }
}
