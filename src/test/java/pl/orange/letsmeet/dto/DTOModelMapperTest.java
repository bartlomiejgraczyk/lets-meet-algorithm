package pl.orange.letsmeet.dto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.Test;
import pl.orange.letsmeet.model.Schedule;
import pl.orange.letsmeet.model.TimePeriod;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DTOModelMapperTest {

    @Test
    void should_convertScheduleProperly() {
        ScheduleDTO scheduleDTO = new ScheduleDTO(new TimePeriodDTO("09:00", "16:00"),
                List.of(
                        new TimePeriodDTO("09:00", "10:30"),
                        new TimePeriodDTO("11:00", "13:50"),
                        new TimePeriodDTO("15:00", "16:00")
                )
        );

        DTOModelMapper mapper = new DTOModelMapper();

        Schedule modelSchedule = mapper.toSchedule(scheduleDTO);

        assertThat(modelSchedule).doesNotHaveSameClassAs(scheduleDTO);
        assertThat(modelSchedule).isExactlyInstanceOf(Schedule.class);
        assertThat(modelSchedule).hasFieldOrPropertyWithValue("plannedMeetings",
                List.of(
                        getParameterizedTimePeriod("09:00", "10:30"),
                        getParameterizedTimePeriod("11:00", "13:50"),
                        getParameterizedTimePeriod("15:00", "16:00")
                )
        );
        assertThat(modelSchedule)
                .hasFieldOrPropertyWithValue("workingHours", getParameterizedTimePeriod("09:00", "16:00"));
    }

    private TimePeriod getParameterizedTimePeriod(String start, String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        return new TimePeriod(
                LocalTime.parse(start, formatter),
                LocalTime.parse(end, formatter)
        );
    }
}