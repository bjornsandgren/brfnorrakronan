package se.osoco.errors;

public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(String fileName) {
        super(fileName);
    }
}
