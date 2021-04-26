package pl.orange.letsmeet.service;

import java.time.Duration;
import java.util.List;
import pl.orange.letsmeet.exception.InvalidScheduleContentException;
import pl.orange.letsmeet.model.Schedule;
import pl.orange.letsmeet.model.TimePeriod;

public interface ScheduleService {
    
    List<TimePeriod> getPossibleMeetingHours(Schedule firstSchedule, Schedule secondSchedule, Duration meetingDuration) 
            throws InvalidScheduleContentException;
    
}
