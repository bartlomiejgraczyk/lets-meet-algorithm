package pl.orange.letsmeet.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

public class StringToLocalTimeConverter implements Converter<String, LocalTime> {
    
    @Override
    public LocalTime convert(String value) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(value, formatter);
    }
}
