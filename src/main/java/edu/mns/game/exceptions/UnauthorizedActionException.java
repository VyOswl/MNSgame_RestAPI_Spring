package edu.mns.game.exceptions;

public class UnauthorizedActionException extends MNSgamesException{

    private static final long serialVersionUID = 1L;

    public UnauthorizedActionException() {
        super();
    }

    public UnauthorizedActionException(String message) {
        super(message);
    }
}