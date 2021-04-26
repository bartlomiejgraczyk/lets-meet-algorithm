package pl.orange.letsmeet.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.orange.letsmeet.util.StringToLocalTimeConverter;

@Getter
@Setter
@ToString
public class TimePeriod implements Comparable<TimePeriod> {

    protected LocalTime start;
    protected LocalTime end;

    public TimePeriod(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TimePeriod that = (TimePeriod) o;
        return getStart().equals(that.getStart()) && getEnd().equals(that.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getEnd());
    }

    @Override
    public int compareTo(TimePeriod o) {
        return this.start.compareTo(o.getStart());
    }
}
