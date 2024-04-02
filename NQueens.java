import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NQueens {

    int queens = 0;
    char[][] board;
    boolean placed = false;
    char queen = 'Q';

    public List<List<String>> solveNQueens(int n) {
        this.board = new char[n][n];
        Arrays.stream(board).forEach(a -> Arrays.fill(a, '.'));
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
        return solutions;
    }

    List<String> copyBoard() {
        List<String> solution = new ArrayList<>();
        for (char[] chars : board) {
            solution.add(new String(chars));
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

    int findQueen(int col){
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] == queen) {
                return row;
            }
        }
        return 0;
    }

    boolean isSafe(int row, int col){
        for (int i = 0; i < col; i++) {
            if (board[row][i] == queen) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == queen) {
                return false;
            }
        }

        for (int i = row, j = col; j >= 0 && i < board.length; i++, j--) {
            if (board[i][j] == queen) {
                return false;
            }
        }
        return true;
    }
}