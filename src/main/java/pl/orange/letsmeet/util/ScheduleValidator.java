package pl.orange.letsmeet.util;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import pl.orange.letsmeet.model.Schedule;
import pl.orange.letsmeet.model.TimePeriod;

public final class ScheduleValidator {

    private ScheduleValidator() {
    }

    public static boolean isScheduleValid(Schedule schedule) {
        return TimePeriodValidator
                .isValid(schedule.getWorkingHours())
                && areAllMeetingsValid(schedule.getPlannedMeetings())
                && areThereAnyCollisions(schedule);
    }

    private static boolean areAllMeetingsValid(List<TimePeriod> meetings) {
        for (TimePeriod meeting : meetings) {
            if (! TimePeriodValidator.isValid(meeting)) {
                return false;
            }
        }
        return true;
    }

    private static boolean areThereAnyCollisions(Schedule schedule) {
        final List<TimePeriod> meetings = schedule.getPlannedMeetings().stream().sorted().collect(Collectors.toList());

        if (conflictsWithWorkingHours(
                meetings.get(0).getStart(),
                meetings.get(meetings.size() - 1).getEnd(),
                schedule.getWorkingHours()
        )) {
            return true;
        }

        for (int i = 0; i < meetings.size() - 1; i++) {

            for (int j = i + 1; j < meetings.size(); j++) {

                if (meetings.get(i).getEnd().isAfter(meetings.get(j).getStart())) {
                    return true;
                }

            }
        }

        return false;
    }

    private static boolean conflictsWithWorkingHours(
            LocalTime firstMeetingStart, LocalTime lastMeetingEnd, TimePeriod workingHours
    ) {

        return firstMeetingStart.isBefore(workingHours.getStart())
                || lastMeetingEnd.isAfter(workingHours.getEnd());
    }
}
