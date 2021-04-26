package pl.orange.letsmeet.dto;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GetMeetingHoursRequest {

    @NotNull
    @JsonProperty("first-calendar")
    private ScheduleDTO firstCalendar;

    @NotNull
    @JsonProperty("second-calendar")
    private ScheduleDTO secondCalendar;

}
