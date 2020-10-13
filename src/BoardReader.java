import java.io.FileNotFoundException;

public interface BoardReader {
    char[][] readFile(String filePath) throws FileNotFoundException;
}
