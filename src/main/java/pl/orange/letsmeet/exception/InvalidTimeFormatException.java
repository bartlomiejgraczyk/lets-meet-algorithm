package pl.orange.letsmeet.exception;

public class InvalidTimeFormatException extends Exception {
    
    public static final String INVALID_TIME_FORMAT = "invalid_time_format";
    
    public InvalidTimeFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
