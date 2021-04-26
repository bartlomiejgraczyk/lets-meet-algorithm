package pl.orange.letsmeet.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Schedule implements Serializable {
    
    private TimePeriod workingHours;
    private final List<TimePeriod> plannedMeetings;
}
