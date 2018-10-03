package fr.agrorole.dnd.exceptions;

public class UserFieldsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7830581325430448095L;

	public UserFieldsException() {
		super();
	}
	
	public UserFieldsException(String msg) {
		super(msg);
	}
	
	public UserFieldsException(String msg, Throwable exception) {
		super(msg, exception);
	}
	
	public UserFieldsException(Throwable exception) {
		super(exception);
	}

}
