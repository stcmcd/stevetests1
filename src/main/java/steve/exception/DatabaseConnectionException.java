package steve.exception;

public class DatabaseConnectionException extends RuntimeException {

	private static final long serialVersionUID = -4778248619056521516L;

	public DatabaseConnectionException(String message) {
		super(message);
	}

}
