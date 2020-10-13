import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SSFormat implements BoardReader {

    private char[][] board = new char[9][9];

    public char[][] readFile(String filePath) throws FileNotFoundException {
        Scanner input = new Scanner(new File(filePath));
        int boardRow = 0;
        for (int row = 0; row < 11; row++) {
            String line = input.nextLine();
            int boardCol = 0;
            if (line.charAt(boardCol) != '-') {
                for (int col = 0; col < line.length(); col++) {
                    if (line.charAt(col) != '|' && line.charAt(col) != '-') {
                        board[boardRow][boardCol] = line.charAt(col);
                        if (line.charAt(col) == '.') {
                            board[boardRow][boardCol] = 'x';
                        }
                        boardCol++;
                    }
                }
                boardRow++;
            }
        }
        return board;
    }

}
