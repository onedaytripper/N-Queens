import java.util.List;

public class Main {

    public static void main(String[] args){
        int n = 8;
        NQueens nQueens = new NQueens(n);
        List<List<String>> solutions = nQueens.solve();
        System.out.println(solutions);
        // nQueens.printBoard();
    }
}