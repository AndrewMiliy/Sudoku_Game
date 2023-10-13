import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SudokuGenerator {

    private int[][] board;
    private static final int SIZE = 9;
    private static final Random random = new Random();

    public SudokuGenerator() {
        board = new int[SIZE][SIZE];
    }

    public int[][] generate(Difficulty difficulty) {
        fillBoard();
        removeNumbers(difficulty);
        return board;
    }

    private boolean fillBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    ArrayList<Integer> numbers = new ArrayList<>();
                    for (int i = 1; i <= SIZE; i++) {
                        numbers.add(i);
                    }
                    Collections.shuffle(numbers);

                    for (int num : numbers) {
                        if (isValidMove(board, row, col, num)) {
                            board[row][col] = num;
                            if (fillBoard()) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidMove(int[][] board, int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num) return false;
            if (board[i][col] == num) return false;
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == num) return false;
        }
        return true;
    }

    private void removeNumbers(Difficulty difficulty) {
        int numbersToRemove;
        switch (difficulty) {
            case EASY:
                numbersToRemove = 20;
                break;
            case MEDIUM:
                numbersToRemove = 30;
                break;
            case HARD:
                numbersToRemove = 45;
                break;
            default:
                throw new IllegalArgumentException("Unknown difficulty: " + difficulty);
        }

        for (int i = 0; i < numbersToRemove; i++) {
            int x = random.nextInt(SIZE);
            int y = random.nextInt(SIZE);
            board[x][y] = 0;
        }
    }
}
