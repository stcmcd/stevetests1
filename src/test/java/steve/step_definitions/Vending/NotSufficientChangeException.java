package steve.step_definitions.Vending;

public class NotSufficientChangeException extends RuntimeException {
    private String message;

    public NotSufficientChangeException(String string) { this.message = string; }

    @Override public String getMessage(){
        return message; } }


