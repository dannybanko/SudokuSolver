import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

public class Board {

    private char[][] board;

    public Board(String filePath) throws FileNotFoundException {
        board = BoardReaderFactory.getFormat(filePath).readFile(filePath);
    }

    public Board(char[][] board) {
        this.board = new char[9][9];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                this.board[row][col] = board[row][col];
            }
        }
    }

    char[][] getBoard() {
        return board;
    }

    public boolean isValid() {
        HashSet<Character> rowCheck = new HashSet<>();
        HashSet<Character> colCheck = new HashSet<>();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                char value = board[row][col];
                if (rowCheck.contains(value)) {
                    return false;
                }

                if (value == '1' || value == '2' || value == '3' || value == '4' || value == '5'
                        || value == '6' || value == '7' || value == '8' || value == '9') {
                    rowCheck.add(value);
                }

                value = board[col][row];
                if (colCheck.contains(value)) {
                    return false;
                }

                if (value == '1' || value == '2' || value == '3' || value == '4' || value == '5'
                        || value == '6' || value == '7' || value == '8' || value == '9') {
                    colCheck.add(value);
                }
            }

            rowCheck.clear();
            colCheck.clear();
        }

        HashSet<Character> gridConstraint = new HashSet<>();
        for (int row = 0; row < board.length; row = row + 3) {
            for (int col = 0; col < board[row].length; col = col + 3) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        char value = board[row + k][col + l];
                        if (gridConstraint.contains(value)) {
                            return false;
                        }

                        if (value == '1' || value == '2' || value == '3' || value == '4' || value == '5'
                                || value == '6' || value == '7' || value == '8' || value == '9') {
                            gridConstraint.add(value);
                        }
                    }
                }
                gridConstraint.clear();
            }
        }
        return true;
    }

    private int checkXs() {
        int xs = 0;
        for (char[] row: board) {
            for (char cell : row) {
                if (cell == 'x') {
                    xs++;
                }
            }
        }
        return xs;
    }

    public ArrayList<Board> getNeighbors() {
        ArrayList<Board> possibleSolutions = new ArrayList<>();

        for (int row = 0; row < this.getBoard().length; row++) {
            for (int col = 0; col < this.getBoard()[row].length; col++) {
                if (this.getBoard()[row][col] == 'x') {
                    for (int val = 1; val <= 9; val++) {
                        Board potentialSol = new Board(this.getBoard());
                        potentialSol.changeCellVal(row, col, val);

                        if (potentialSol.isValid()) {
                            possibleSolutions.add(potentialSol);
                        }
                    }
                }
            }
        }

        return possibleSolutions;
    }

    private void changeCellVal(int row, int col, int val) {
        this.board[row][col] = Character.forDigit(val, 10);
    }

    public Board solve(Board board) {
        if (!board.isSolved()) {
            for (Board potentialSol : board.getNeighbors()) {
                Board possibleAns = solve(potentialSol);
                if (possibleAns != null) {
                    return possibleAns;
                }
            }
        } else {
            return board;
        }
        return null;
    }

    public boolean isSolved() {
        return (isValid() && checkXs() == 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (char[] row : board) {
            for (char character : row) {
                sb.append(character);
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
