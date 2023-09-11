package ua.vart.portfolio.exception;

public class CodeAlreadyUsedException extends RuntimeException{
    public CodeAlreadyUsedException(String message) {
        super(message);
    }
}
