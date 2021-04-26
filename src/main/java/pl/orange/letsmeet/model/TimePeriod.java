package pl.orange.letsmeet.model;

import java.io.Serializable;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.orange.letsmeet.util.StringToLocalTimeConverter;

@Getter
@Setter
@ToString
public class TimePeriod implements Serializable {

    protected LocalTime start;
    protected LocalTime end;

    public TimePeriod(String start, String end) {
        final StringToLocalTimeConverter converter = new StringToLocalTimeConverter();
        this.start = converter.convert(start);
        this.end = converter.convert(end);
    }
}
