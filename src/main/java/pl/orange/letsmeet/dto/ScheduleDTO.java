package pl.orange.letsmeet.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScheduleDTO {

    @JsonProperty("working-hours")
    private TimePeriodDTO workingHours;

    @JsonProperty("planned-meeting")
    private final List<TimePeriodDTO> plannedMeetings;
}
