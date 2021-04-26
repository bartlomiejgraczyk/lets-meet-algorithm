package pl.orange.letsmeet.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.orange.letsmeet.exception.InvalidScheduleFormatException;
import pl.orange.letsmeet.exception.InvalidTimeFormatException;

@ControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleConverterErrors(MethodArgumentTypeMismatchException ex) {
        Throwable cause = ex.getCause().getCause();

        if (cause instanceof InvalidTimeFormatException) {
            return ResponseEntity.badRequest().body(cause.getMessage());
        }
        return ResponseEntity.badRequest().body("custom bad request");
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        Exception exception = new InvalidScheduleFormatException(
                InvalidScheduleFormatException.INVALID_SCHEDULE_FORMAT, ex.getCause());

        return handleExceptionInternal(
                exception, 
                InvalidScheduleFormatException.INVALID_SCHEDULE_FORMAT, 
                headers, 
                HttpStatus.BAD_REQUEST, 
                request);
    }
}
