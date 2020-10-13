import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SDKFormat implements BoardReader {

    private char[][] board = new char[9][9];

    public char[][] readFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        while(scanner.hasNextLine()) {
            for (int row = 0; row < board.length; row++) {
                String line = scanner.nextLine();
                for (int col = 0; col < board[row].length; col++) {
                    board[row][col] = line.charAt(col);
                }
            }
        }
        return board;
    }
}
