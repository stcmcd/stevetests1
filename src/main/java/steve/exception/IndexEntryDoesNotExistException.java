package steve.exception;

public class IndexEntryDoesNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1140616974881194612L;

    public IndexEntryDoesNotExistException(String message) {
        super(message);
    }

}
