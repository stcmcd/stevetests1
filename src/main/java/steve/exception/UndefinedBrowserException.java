package steve.exception;

public class UndefinedBrowserException extends RuntimeException {

    private static final long serialVersionUID = -1278960931240859350L;

    public UndefinedBrowserException(String message) {
        super(message);
    }
}
