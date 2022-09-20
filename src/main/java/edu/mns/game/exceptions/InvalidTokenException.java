package edu.mns.game.exceptions;

public class InvalidTokenException extends MNSgamesException {

	private static final long serialVersionUID = 1L;

	public InvalidTokenException() {
		super();
	}

	public InvalidTokenException(String message) {
		super(message);
	}
}