package se.osoco.errors;

public class FileNotValidException extends RuntimeException {

    public FileNotValidException(String fileName) {
        super(fileName);
    }
}
