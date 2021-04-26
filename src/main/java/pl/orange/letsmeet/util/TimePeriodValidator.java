package pl.orange.letsmeet.util;

import pl.orange.letsmeet.model.TimePeriod;

public class TimePeriodValidator {

    private TimePeriodValidator() {
    }
    
    public static boolean isValid(TimePeriod timePeriod) {
        return timePeriod.getStart().isBefore(timePeriod.getEnd());
    }
}
