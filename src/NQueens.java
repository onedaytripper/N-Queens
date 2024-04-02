import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

    int n;
    int queens;
    char[][] board;
    boolean placed = false;
    char queen = 'Q';

    public NQueens(int n) {
        this.n = n;
        this.queens = 0;
        this.board = new char[n][n];
        Arrays.stream(board).forEach(a -> Arrays.fill(a, '.'));
    }

    public List<List<String>> solve() {
        List<List<String>> solutions = new ArrayList<>();
            int row;

            for (int col = 0; col < n; col++) {
                placed = false;
                for (row = findQueen(col); row < n; row++) {
                    if (attempt(row, col)) {
                        break;
                    }
                }

                if (queens == n) {
                    solutions.add(copyBoard());
                    this.board[row][col] = '.';
                    placed = false;
                    queens--;
                }

                while (!placed) {
                    // No solutions possible
                    if (col == 0) {
                        break;
                    } else {
                        col--;
                        int previous = findQueen(col);
                        this.board[previous][col] = '.';
                        queens--;

                            for (row = previous + 1; row < n; row++) {
                            if (attempt(row, col)) {
                                break;
                            }
                        }
                    }
                }
            }
        System.out.println(solutions.size() + " solutions were found!");
        return solutions;
    }

    List<String> copyBoard() {
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            solution.add(new String(board[i]));
        }
        return solution;
    }

    boolean attempt(int row, int col) {
        if (isSafe(row, col)) {
            this.board[row][col] = queen;
            placed = true;
            queens++;
        }
        return placed;
    }

    /* Returns the row of the queen in a given column, if there is one. */
    int findQueen ( int col){
        for (int row = 0; row < n; row++) {
            if (board[row][col] == queen) {
                return row;
            }
        }
        return 0;
    }

    boolean isSafe (int row, int col){
        int i, j;

        // Check row
        for (i = 0; i < col; i++) {
            if (board[row][i] == queen) {
                return false;
            }
        }

        // Check ascending diagonal
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == queen) {
                return false;
            }
        }

        // Check descending diagonal
        for (i = row, j = col; j >= 0 && i < n; i++, j--) {
            if (board[i][j] == queen) {
                return false;
            }
        }
        return true;
    }

    void printBoard () {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
