package fr.agrorole.dnd.exceptions;

public class RaceFieldsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3700993777008593993L;

	public RaceFieldsException() {
		super();		
	}

	public RaceFieldsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);		
	}

	public RaceFieldsException(String message, Throwable cause) {
		super(message, cause);		
	}

	public RaceFieldsException(String message) {
		super(message);		
	}

	public RaceFieldsException(Throwable cause) {
		super(cause);
	}
}
