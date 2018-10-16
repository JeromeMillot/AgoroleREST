package fr.agrorole.dnd.exceptions;

public class CharFieldsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4713661962340590650L;
	
	public CharFieldsException() {
		super();
	}
	
	public CharFieldsException(String msg) {
		super(msg);
	}
	
	public CharFieldsException(String msg, Throwable exception) {
		super(msg, exception);
	}
	
	public CharFieldsException(Throwable exception) {
		super(exception);
	}

}
