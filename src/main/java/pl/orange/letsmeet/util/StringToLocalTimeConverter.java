package pl.orange.letsmeet.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import pl.orange.letsmeet.exception.InvalidTimeFormatException;

public class StringToLocalTimeConverter implements Converter<String, LocalTime> {
    
    @SneakyThrows
    @Override
    public LocalTime convert(String value) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            return LocalTime.parse(value, formatter);
        } catch (Exception e) {
            throw new InvalidTimeFormatException(InvalidTimeFormatException.INVALID_TIME_FORMAT, e);
        }
    }
}
