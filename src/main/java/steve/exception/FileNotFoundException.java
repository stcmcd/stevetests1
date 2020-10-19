package steve.exception;

public class FileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7504174143853614727L;

	public FileNotFoundException(String message) {
		super(message);
	}
}
