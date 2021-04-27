package pl.orange.letsmeet.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import pl.orange.letsmeet.model.TimePeriod;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimePeriodValidatorTest {
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    @Test
    void should_beTrue_forValidTimePeriod() {
        TimePeriod timePeriod = new TimePeriod(LocalTime.parse("13:01", formatter), LocalTime.parse("13:02", formatter));
        assertTrue(TimePeriodValidator.isValid(timePeriod));
    }
    
    @Test
    void should_beFalse_forEqualStartAndEnd() {
        TimePeriod timePeriod = new TimePeriod(LocalTime.parse("13:01", formatter), LocalTime.parse("13:01", formatter));
        assertFalse(TimePeriodValidator.isValid(timePeriod));
    }
    
    @Test
    void should_beFalse_forStartGreater() {
        TimePeriod timePeriod = new TimePeriod(LocalTime.parse("13:02", formatter), LocalTime.parse("13:01", formatter));
        assertFalse(TimePeriodValidator.isValid(timePeriod));
    }
}