package pl.orange.letsmeet.exception;

public class InvalidScheduleContentException extends Exception {
    
    public static final String INVALID_SCHEDULE_CONTENT = "invalid_schedule_content";

    public InvalidScheduleContentException(String message) {
        super(message);
    }
}
