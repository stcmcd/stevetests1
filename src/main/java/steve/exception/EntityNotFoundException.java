package steve.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2657896341693151345L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
