package ru.nop.yerzhanbot.exceptions;

public class GameAlreadyExistsException extends RuntimeException {

    public GameAlreadyExistsException() {
    }

    public GameAlreadyExistsException(String message) {
        super(message);
    }

    public GameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
