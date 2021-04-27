package pl.orange.letsmeet.service;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.Test;
import pl.orange.letsmeet.exception.InvalidScheduleContentException;
import pl.orange.letsmeet.model.Schedule;
import pl.orange.letsmeet.model.TimePeriod;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ScheduleServiceImplIT {

    private ScheduleService service;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public ScheduleServiceImplIT() {
        this.service = new ScheduleServiceImpl();
    }

    @Test
    void should_returnThreeElements_forSchedulesFromTask() {
        List<TimePeriod> possibleMeetingHours =
                assertDoesNotThrow(() -> service.getPossibleMeetingHours(
                        getFirstScheduleFromTask(),
                        getSecondScheduleFromTask(),
                        Duration.between(LocalTime.MIN, LocalTime.parse("00:30", formatter))));

        assertThat(possibleMeetingHours.size()).isEqualTo(3);
    }

    @Test
    void should_returnEmptyList_forTooLongMeetingDuration() {
        List<TimePeriod> possibleMeetingHours =
                assertDoesNotThrow(() -> service.getPossibleMeetingHours(
                        getFirstScheduleFromTask(),
                        getSecondScheduleFromTask(),
                        Duration.between(LocalTime.MIN, LocalTime.parse("03:01", formatter))));

        assertThat(possibleMeetingHours.size()).isZero();
    }

    @Test
    void should_throwException_forCollisingWorkingHours() {
        assertThatThrownBy(
                () -> service.getPossibleMeetingHours(
                        getFirstScheduleFromTask(),
                        getCollisingWorkingHoursSchedule(),
                        Duration.between(LocalTime.MIN, LocalTime.parse("03:00", formatter))))
                .isInstanceOf(InvalidScheduleContentException.class)
                .hasMessage(InvalidScheduleContentException.INVALID_SCHEDULE_CONTENT);
    }

    @Test
    void should_throwException_forCollisingMeetings() {
        assertThatThrownBy(
                () -> service.getPossibleMeetingHours(
                        getFirstScheduleFromTask(),
                        getCollisingMeetingsSchedule(),
                        Duration.between(LocalTime.MIN, LocalTime.parse("03:00", formatter))))
                .isInstanceOf(InvalidScheduleContentException.class)
                .hasMessage(InvalidScheduleContentException.INVALID_SCHEDULE_CONTENT);
    }

    @Test
    void should_throwException_forRevertedMeeting() {
        assertThatThrownBy(
                () -> service.getPossibleMeetingHours(
                        getFirstScheduleFromTask(),
                        getRevertedMeetingSchedule(),
                        Duration.between(LocalTime.MIN, LocalTime.parse("03:00", formatter))))
                .isInstanceOf(InvalidScheduleContentException.class)
                .hasMessage(InvalidScheduleContentException.INVALID_SCHEDULE_CONTENT);
    }

    private Schedule getFirstScheduleFromTask() {
        return new Schedule(
                getParameterizedTimePeriod("09:00", "19:55"),
                List.of(
                        getParameterizedTimePeriod("09:00", "10:30"),
                        getParameterizedTimePeriod("12:00", "13:00"),
                        getParameterizedTimePeriod("16:00", "18:00")
                )
        );
    }

    private Schedule getSecondScheduleFromTask() {
        return new Schedule(
                getParameterizedTimePeriod("10:00", "18:30"),
                List.of(
                        getParameterizedTimePeriod("10:00", "11:30"),
                        getParameterizedTimePeriod("12:30", "14:30"),
                        getParameterizedTimePeriod("14:30", "15:00"),
                        getParameterizedTimePeriod("16:00", "17:00")
                )
        );
    }

    private TimePeriod getParameterizedTimePeriod(String start, String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        return new TimePeriod(
                LocalTime.parse(start, formatter),
                LocalTime.parse(end, formatter)
        );
    }

    private Schedule getCollisingWorkingHoursSchedule() {
        return new Schedule(
                getParameterizedTimePeriod("10:00", "16:30"),
                List.of(
                        getParameterizedTimePeriod("10:00", "11:30"),
                        getParameterizedTimePeriod("12:30", "14:30"),
                        getParameterizedTimePeriod("14:30", "15:00"),
                        getParameterizedTimePeriod("16:00", "17:00")
                )
        );
    }

    private Schedule getCollisingMeetingsSchedule() {
        return new Schedule(
                getParameterizedTimePeriod("10:00", "18:30"),
                List.of(
                        getParameterizedTimePeriod("10:00", "12:31"),
                        getParameterizedTimePeriod("12:30", "14:30"),
                        getParameterizedTimePeriod("14:30", "15:00"),
                        getParameterizedTimePeriod("16:00", "17:00")
                )
        );
    }

    private Schedule getRevertedMeetingSchedule() {
        return new Schedule(
                getParameterizedTimePeriod("10:00", "18:30"),
                List.of(
                        getParameterizedTimePeriod("10:00", "11:30"),
                        getParameterizedTimePeriod("14:30", "12:30"),
                        getParameterizedTimePeriod("14:30", "15:00"),
                        getParameterizedTimePeriod("16:00", "17:00")
                )
        );
    }
}