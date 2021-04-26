package pl.orange.letsmeet.dto;

import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import pl.orange.letsmeet.util.StringToLocalTimeConverter;

@Getter
public class TimePeriodDTO {

    @JsonFormat(pattern = "HH:mm")
    protected LocalTime start;

    @JsonFormat(pattern = "HH:mm")
    protected LocalTime end;

    @JsonCreator
    public TimePeriodDTO(String start, String end) {
        final StringToLocalTimeConverter converter = new StringToLocalTimeConverter();
        this.start = converter.convert(start);
        this.end = converter.convert(end);
    }
    
}
