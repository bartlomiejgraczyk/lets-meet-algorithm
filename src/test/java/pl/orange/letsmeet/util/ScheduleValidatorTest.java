package pl.orange.letsmeet.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.Test;
import pl.orange.letsmeet.model.Schedule;
import pl.orange.letsmeet.model.TimePeriod;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScheduleValidatorTest {

    @Test
    void should_beTrue_whenPassingCorrectFormat() {
        
        Schedule schedule = new Schedule(getParameterizedTimePeriod("09:00", "16:00"),
                List.of(
                        getParameterizedTimePeriod("09:00", "10:30"),
                        getParameterizedTimePeriod("11:00", "13:50"),
                        getParameterizedTimePeriod("15:00", "16:00")
                )
        );
        assertTrue(ScheduleValidator.isScheduleValid(schedule));
    }
    
    @Test
    void should_beFalse_when_passingMismatchedWorkingHours() {

        Schedule schedule = new Schedule(getParameterizedTimePeriod("17:00", "16:00"),
                List.of(
                        getParameterizedTimePeriod("09:00", "10:30"),
                        getParameterizedTimePeriod("11:00", "13:50"),
                        getParameterizedTimePeriod("15:00", "16:00")
                )
        );
        assertFalse(ScheduleValidator.isScheduleValid(schedule));
    }
    
    @Test
    void should_beFalse_when_passingMismatchedMeeting() {
        Schedule schedule = new Schedule(getParameterizedTimePeriod("09:00", "16:00"),
                List.of(
                        getParameterizedTimePeriod("09:00", "10:30"),
                        getParameterizedTimePeriod("14:00", "13:50"),
                        getParameterizedTimePeriod("15:00", "16:00")
                )
        );
        assertFalse(ScheduleValidator.isScheduleValid(schedule));
    }
    
    @Test
    void should_beFalse_when_passingCollisingMeetings() {
        Schedule schedule = new Schedule(getParameterizedTimePeriod("09:00", "16:00"),
                List.of(
                        getParameterizedTimePeriod("09:00", "10:30"),
                        getParameterizedTimePeriod("11:00", "15:50"),
                        getParameterizedTimePeriod("15:00", "16:00")
                )
        );
        assertFalse(ScheduleValidator.isScheduleValid(schedule));
    }
    
    private TimePeriod getParameterizedTimePeriod(String start, String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        
        return new TimePeriod(
                LocalTime.parse(start, formatter),
                LocalTime.parse(end, formatter)
        );
    }
}