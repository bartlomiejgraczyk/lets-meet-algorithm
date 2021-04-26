package pl.orange.letsmeet.dto;

import java.util.List;
import lombok.Getter;

public class GetMeetingHoursResponse {
    
    @Getter
    private List<TimePeriodDTO> possibleMeetings;
    
}
