package edu.mns.game.exceptions;

public class UserAlreadyExistedException extends MNSgamesException {

    private static final long serialVersionUID = 1L;

    public UserAlreadyExistedException() {
        super();
    }

    public UserAlreadyExistedException(String message) {
        super(message);
    }
}