public class BoardReaderFactory {
    public static BoardReader getFormat(String filePath) {
        if (filePath.endsWith(".sdk")) {
            return new SDKFormat();
        } else if (filePath.endsWith(".ss")) {
            return new SSFormat();
        }
        throw new UnsupportedOperationException("File type " + filePath + " was not supported");
    }
}
