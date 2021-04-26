package pl.orange.letsmeet.exception;

public class InvalidScheduleFormatException extends Exception {
    
    public static final String INVALID_SCHEDULE_FORMAT = "invalid_schedule_format";
    
    public InvalidScheduleFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
