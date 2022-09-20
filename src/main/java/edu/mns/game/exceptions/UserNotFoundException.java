package edu.mns.game.exceptions;

public class UserNotFoundException extends MNSgamesException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}