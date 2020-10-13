import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Board sdkSolver = new Board("src/board.sdk");
        System.out.println(sdkSolver);

        Board ssSolver = new Board("src/board.ss");
        System.out.println(ssSolver);

        System.out.println(sdkSolver.solve(sdkSolver));
    }


}
