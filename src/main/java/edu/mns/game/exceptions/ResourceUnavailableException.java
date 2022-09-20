package edu.mns.game.exceptions;

public class ResourceUnavailableException extends MNSgamesException {
    private static final long serialVersionUID = 1L;

    public ResourceUnavailableException() {
        super();
    }

    public ResourceUnavailableException(String message) {
        super(message);
    }
}
