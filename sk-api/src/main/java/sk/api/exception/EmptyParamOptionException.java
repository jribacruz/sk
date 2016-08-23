package sk.api.exception;

public class EmptyParamOptionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyParamOptionException() {
		super();
	}

	public EmptyParamOptionException(String message) {
		super(message);
	}

}
