package pl.orange.letsmeet.model;

import java.time.LocalTime;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TimePeriod implements Comparable<TimePeriod> {

    private LocalTime start;
    private LocalTime end;

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
